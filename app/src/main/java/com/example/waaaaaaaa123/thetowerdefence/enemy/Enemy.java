package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbilityDodge;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbilityHeal;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierArmorReduce;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierPoison;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierSlowDown;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifierStun;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
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
    public static final int STATUS_DODGE=3;
    public static final int STATUS_CAST=4;
    public static final int STATUS_STUN=5;
    private int status=STATUS_MOVE;

    private boolean attacked,dodge,stun;


    private int id=0;

    private float speed=1;
    private float fullHp=100;
    private int bound=1;
    private float armor=0;
    private float castPoint=0.3f;
    private ArrayList<EnemyAbility> abilities;

    private long castTimer;

    private int dp;
    private int attack=1;

    private float hp=100;

    private int mainOrb;

    private ArrayList<EnemyModifier> enemyModifiers;

    private long lastAttackTime;

    private PointF forward;
    private RectF rect;
    private PointF point;
    private PointF nextPoint;
    private ArrayList<Block> path;
    private float lengthToNextPath;
    private int it;

    public Enemy(){
        float l=Grid.getLength()*0.75f;
        rect=new RectF(-l/2,-l/2,l/2,l/2);
        point=new PointF();
        nextPoint=new PointF();
        forward=new PointF();
        enemyModifiers =new ArrayList<>(6);
        abilities=new ArrayList<>(4);

    }
    public static final int hpTable[]={10,30,70,130,210,310,500,800,1200,1700,2500};
    public void init(int speedLvl,int hpLvl,int aId,int abilityLvl){
        bound=1;
        armor=0;
        speed=1+speedLvl*0.3f;
        fullHp=hpTable[hpLvl];
        hp=fullHp;
        for (int i = 0; i < abilityLvl&&aId!=-1; i++) {
            EnemyAbility ability=EnemyAbility.create(aId, this);
            if(ability!=null){
                ability.cast(EnemyAbility.STATE_SPAWN);
                abilities.add(ability);
            }
        }
        id=aId;
        switch (aId){
            case -1:mainOrb=-1;break;
            case EnemyAbility.ABILITY_ENEMY_DODGE:mainOrb=Orb.ORB_BLUE;break;
            case EnemyAbility.ABILITY_ENEMY_HEAL:mainOrb=Orb.ORB_YELLOW;break;
            case EnemyAbility.ABILITY_ENEMY_FLY:mainOrb=Orb.ORB_GREEN;break;
            case EnemyAbility.ABILITY_ENEMY_ARMOR:mainOrb=Orb.ORB_RED;break;
        }
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
            //orb

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

        for (EnemyAbility ability : abilities) {
            ability.cast(EnemyAbility.STATE_SPAWN);
        }
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

    public void setDodge() {
        dodge=true;
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
        rect.offset(point.x-rect.centerX(),point.y-rect.centerY());
        return rect;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setStun() {
        stun=true;
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

    public void setPath(ArrayList<Block> path) {
        this.path = path;

        point.set(path.get(0).getCenter());
        float dx=(Player.getRandomSeed().nextFloat()-0.5f)*Grid.getLength()*0.25f;
        float dy=(Player.getRandomSeed().nextFloat()-0.5f)*Grid.getLength()*0.25f;
        point.offset(dx, dy);
        setNextPoint(path.get(1).getCenter());
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
                setNextPoint(path.get(++it).getCenter());
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

        for (EnemyModifier enemyModifier : enemyModifiers) {
            if (enemyModifier.isAlive()){
                enemyModifier.update(dt);

            }
        }
        for (EnemyAbility ability : abilities) {
            ability.update(dt);
        }
        if(stun){
            status=STATUS_STUN;
            stun=false;
            return;
        }
        for (EnemyAbility ability : abilities) {
            ability.cast(EnemyAbility.STATE_READY);
        }

        status=STATUS_MOVE;
        move(dt);
        /*if(player.getGrid().getBlockByCount(point.x,point.y).getId()== Block.END)
            attack(dt);*/
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public float getHpPercent() {
        return hp/fullHp;
    }

    public int getDp() {
        return dp;
    }

    public int getStatus() {
        return status;
    }

    public float getHp() {
        return hp;
    }

    public int getMainOrb() {
        return mainOrb;
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
            enemyAbility.cast(EnemyAbility.STATE_ATTACKLANDED);
        }
        if(dodge){
            status=STATUS_DODGE;
            dodge=false;
            return;
        }

        status=STATUS_ATTACKLANDED;

        for (Integer id : projectile.getModifiers().keySet()) {
            addModifier(id,projectile.getModifiers().get(id),projectile.getCaster());
        }

        calDamage(projectile.getCaster().getMainOrb(),projectile.getDamage(),projectile.getCaster());

    }
    public void calDamage(int orb,float damage,Tower caster){
        damage*=Orb.damage(orb, mainOrb);
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
        dp= (int) (abilities.size()*speed*10+fullHp/20);
        Player.getBag().buy(-dp);
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
