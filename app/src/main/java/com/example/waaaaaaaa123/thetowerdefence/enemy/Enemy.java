package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbilityDodge;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbilityHeal;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierArmorReduce;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierPoison;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierSlowDown;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierStun;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Enemy implements Comparable<Enemy>{

    public static final int STATE_NOTSPAWN=0;
    public static final int STATE_ALIVE=1;
    public static final int STATE_DEAD=2;
    private int state=STATE_NOTSPAWN;

    public static final int STATUS_UPDATE=0;
    public static final int STATUS_MOVE=1;
    public static final int STATUS_ATTACKLANDED=2;


    private int id=0;

    private float speed=1;
    private float fullHp=100;
    private int bound=1;
    private float armor=0;
    private ArrayList<EnemyAbility> abilities;

    private int dp;
    private int attack=1;

    private float hp=100;
    public static final int ARMOR_PHYSICAL=0;
    public static final int ARMOR_MAGICAL=1;
    private int type;

    private ArrayList<EnemyModifier> enemyModifiers;

    private long lastAttackTime;

    private PointF forward;
    private RectF rect;
    private PointF point;
    private PointF nextPoint;
    private ArrayList<PointF> path;
    private float lengthToNextPath;
    private int it;

    public Enemy(){
        float l=Grid.getLength()*0.75f;
        rect=new RectF(-l/2,-l/2,l/2,l/2);
        point=new PointF();
        nextPoint=new PointF();
        forward=new PointF();
        enemyModifiers =new ArrayList<>();

    }

    public int init(int minhplvl,int mindp){
        if(mindp>=510)mindp=510;
        int[] seed=new int[5];
        seed[0]=1;seed[1]=1;seed[2]=0;seed[3]=minhplvl;seed[4]=1;
        //0:speed,1:armor,2:abilities,3:hp,4:bound
        while((seed[0]+seed[1]+seed[2]+seed[4])*seed[3]*seed[4]<mindp){
            int i=Player.getRandomSeed().nextInt(5);
            switch (i){
                case 0:if(seed[0]<5)seed[0]++;break;
                case 1:if(seed[1]<5)seed[1]++;break;
                case 2:if(seed[2]<4)seed[2]++;break;
                case 3:if(seed[3]<10)seed[3]++;break;
                case 4:if(seed[4]<3)seed[4]++;break;
            }
        }
        dp=(seed[0]+seed[1]+seed[2])*seed[3]*seed[4];
        speed=seed[0];
        switch (seed[1]){
            case 1:armor=Player.getRandomSeed().nextInt(5);break;
            case 2:armor=Player.getRandomSeed().nextInt(7)+5;break;
            case 3:armor=Player.getRandomSeed().nextInt(13)+12;break;
            case 4:armor=Player.getRandomSeed().nextInt(25)+25;break;
        }

        abilities=new ArrayList<>(4);
        for (int i = 0; i < seed[2]; i++) {
            int abilityId=Player.getRandomSeed().nextInt(2);
            switch (abilityId){
                case EnemyAbility.ABILITY_ENEMY_DODGE:abilities.add(new EnemyAbilityDodge(this));break;
                case EnemyAbility.ABILITY_ENEMY_HEAL:abilities.add(new EnemyAbilityHeal(this));break;
            }
        }
        if(abilities.size()>1)
            type=ARMOR_MAGICAL;

        fullHp= (float) (Math.pow(2,seed[3])*10);
        hp=fullHp;
        bound=seed[4];
        /*Log.i("speed",speed+"");
        Log.i("armor",armor+"");
        Log.i("armorType",type+"");
        Log.i("abilities",abilities.size()+"");
        Log.i("hp",fullHp+"");
        Log.i("bound",bound+"");
        Log.i("dp",dp+"");*/
        return dp;
    }

    public void init(float speed,float armor,int bound,float hp,ArrayList<EnemyAbility> abilities){
        this.speed=speed;
        this.armor=armor;
        this.bound=bound;
        this.fullHp=hp;
        this.hp=hp;
        this.abilities=abilities;

    }

    public void setNextPoint(PointF p) {
        nextPoint.set(p);
        float dx=(Player.getRandomSeed().nextFloat()-0.5f)*Grid.getLength()*0.25f;
        float dy=(Player.getRandomSeed().nextFloat()-0.5f)*Grid.getLength()*0.25f;
        nextPoint.offset(dx,dy);
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public int getBound() {
        return bound;
    }

    public PointF getPoint() {

        return point;
    }

    public RectF getRect() {
        rect.offsetTo(point.x-rect.width()/2,point.y-rect.height()/2);
        return rect;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public void setPath(ArrayList<PointF> path) {
        this.path = path;

        point.set(path.get(0));
        float dx=(Player.getRandomSeed().nextFloat()-0.5f)*Grid.getLength()*0.25f;
        float dy=(Player.getRandomSeed().nextFloat()-0.5f)*Grid.getLength()*0.25f;
        point.offset(dx, dy);
        setNextPoint(path.get(1));
        setForward();
        it=1;
    }

    public void setForward() {
        double dx=nextPoint.x-point.x;
        double dy=nextPoint.y-point.y;
        double dl=Math.sqrt(dx*dx+dy*dy);
        dx/=dl;
        dy/=dl;

        forward.x=(float)dx;
        forward.y=(float)dy;
        lengthToNextPath=(float)dl;
    }
    private void move(long dt){

        float l=  (speed* Grid.getLength()*dt/1000);

        if( l>=lengthToNextPath){
            point.set(nextPoint);
            if(it+1<path.size())
            {
                setNextPoint(path.get(++it));
                long dtt=(long) (dt - lengthToNextPath / (speed* Grid.getLength() )* 1000);
                setForward();
                move(dtt);
            }
            else
            {
                Player.addHp(-attack);
                setPath(path);

            }

        }
        else{
            point.x+=l*forward.x;
            point.y+=l*forward.y;
            lengthToNextPath-=l;
        }



    }
    /*public void attack(long dt){
        lastAttackTime-=dt;
        if(lastAttackTime<=0){
            player.setHp(player.getHp()-attack);
            lastAttackTime+=1000;
            if(lastAttackTime<=0)
                attack(0);
        }
    }*/
    public void update(long dt){
        boolean isStun=false;
        for (EnemyModifier enemyModifier : enemyModifiers) {
            if (enemyModifier.isAlive()){
                enemyModifier.update(dt);
                if(enemyModifier.getId()== EnemyModifier.MODIFIER_STUN)
                    isStun=true;
            }
        }
        if(isStun)
            return;

        for (EnemyAbility ability : abilities) {
            ability.update(dt);
            ability.cast(EnemyAbility.STATE_READY);
        }

        move(dt);
        /*if(player.getGrid().getBlockByCount(point.x,point.y).getId()== Block.END)
            attack(dt);*/
    }

    public float getHpPercent() {
        return hp/fullHp;
    }

    public int getDp() {
        return dp;
    }

    public float getHp() {
        return hp;
    }

    public int getType() {
        return type;
    }

    public ArrayList<EnemyModifier> getEnemyModifiers() {
        return enemyModifiers;
    }

    public void addModifier(int mId,int stack,Tower caster){
        EnemyModifier enemyModifier =null;
        for(EnemyModifier modifier : enemyModifiers){
            if(mId== modifier.getId()){
                enemyModifier=modifier;
                break;
            }
        }
        if(enemyModifier!=null){
            if(enemyModifier.isAlive()){
                enemyModifier.stackUp(stack);
            }
            else{
                enemyModifier.recycle(this,stack);
            }
        }
        else{

            switch (mId){
                case EnemyModifier.MODIFIER_SLOWDOWN:
                    enemyModifier =new EnemyModifierSlowDown(this,stack);break;
                case EnemyModifier.MODIFIER_STUN:
                    enemyModifier =new EnemyModifierStun(this,stack);break;
                case EnemyModifier.MODIFIER_ARMORREDUCE:
                    enemyModifier =new EnemyModifierArmorReduce(this,stack);break;
                case EnemyModifier.MODIFIER_POISON:
                    enemyModifier=new EnemyModifierPoison(this,stack); break;
            }

            if(enemyModifier !=null){
                enemyModifiers.add(enemyModifier);
            }
        }
            if(mId==EnemyModifier.MODIFIER_POISON &&enemyModifier!=null&&enemyModifier.getStack()<=stack){
                ((EnemyModifierPoison) enemyModifier).setCaster(caster);
            }

    }
    public void attackLanded(Projectile projectile){
        for (EnemyAbility enemyAbility:abilities){
            switch (enemyAbility.getId()){
                case EnemyAbility.ABILITY_ENEMY_DODGE:
                    if(Player.getRandomSeed().nextFloat()<0.2f)
                        return;
            }
        }

        for (Integer id : projectile.getModifiers().keySet()) {
            addModifier(id,projectile.getModifiers().get(id),projectile.getCaster());
        }

        calDamage(projectile.getCaster().getType(),projectile.getDamage(),projectile.getCaster());

    }
    public void calDamage(int damageType,float damage,Tower caster){
        if(damageType==type)
            damage*=0.75;
        if(armor>=0){
            damage/=1+0.06*armor;
        }
        else{
            damage*=(1-0.12*armor)/(1-0.06*armor);
        }
        hp-=damage;
        if(hp<=0)
        {
            caster.onKill(this);
            onKilled();
        }
    }

    public void onKilled(){
        hp=0;
        Player.getShop().earnGold(dp);
        state=STATE_DEAD;
    }
    public void heal(float healpercent){
        hp*=1+healpercent;
        if(hp>fullHp)
            hp=fullHp;
    }
    @Override
    public int compareTo(Enemy another) {
        return Float.compare(this.getRect().centerY(),another.getRect().centerY());
    }
}
