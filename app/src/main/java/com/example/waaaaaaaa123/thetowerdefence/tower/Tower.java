package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityArmorReduce;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityCombo;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityCriticalStrike;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityGreed;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityHeal;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityImpetus;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityMoreAttack;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityMoreSpeed;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityNecromastery;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityNethertoxin;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityPoison;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilitySlowDown;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilitySplit;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityStatusUp;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbilityStun;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemSlot;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.modifier.TowerModifier;
import com.example.waaaaaaaa123.thetowerdefence.modifier.TowerModifierAttackup;
import com.example.waaaaaaaa123.thetowerdefence.modifier.TowerModifierRangeup;
import com.example.waaaaaaaa123.thetowerdefence.modifier.TowerModifierSpeedup;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileRifle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Tower {
    public static final int TOWER_ORB=0;
    public static final int TOWER_CONE=1;
    public static final int TOWER_AXE=2;
    public static final int TOWER_WHIP=3;
    public static final int TOWER_SWORD=4;
    public static final int TOWER_SPLIT=5;
    public static final int TOWER_BOMB=6;
    public static final int TOWER_CHAIN=7;
    public static final int TOWER_BURN=8;
    public static final int TOWER_RIFLE=9;
    public static final int TOWER_ROTATE=10;
    public static final int TOWER_COMBO=11;
    public static final int TOWER_RANDOM=12;

    private int id;
    private int projectileId;


    private boolean builded,finded,aimed,loaded,cooled;
    private float degree;
    private float offX=1f;
    private float offY=1f;
    private float buildingPoint=0.3f;
    private float degreePoint=0.5f;
    private float castPoint=0.2f;
    private float projectileSpeed=3f;

    private long buildTimer=0;
    private long aimedTimer=0;
    private long loadTimer=0;
    private long attackTimer =0;

    public static final int TYPE_PHYSICAL=0;
    public static final int TYPE_MAGICAL=1;
    protected float attackMin,attackMax;
    //protected float attackBonus=0;
    //protected float damage;
    protected float range=2;
    protected float speed=3;
    private HashSet<Enemy> enemies;
    protected ArrayList<TowerAbility> abilities;
    protected ArrayList<TowerModifier> modifiers;
    protected ArrayList<ItemSlot> slots;

    protected ArrayList<Integer> orbs;
    protected int mainOrb;
    public static final int LEVEL_MAX=4;
    private int level;

    private int enemyNum =1, projectileNum =1,comboNum=1;
    private int comboCnt;
    private int kill=0;




    private RectF rect;
    private Block block;
    //private PointF point;
    protected Enemy target;
    protected Enemy lastKilled;
    protected Projectile projectile;

    public static Tower create(int id,Block block){
        Tower tower=null;
        switch (id){
            case Tower.TOWER_ORB:tower=new Tower(block);break;//id==0 means it's an orb tower.
            case Tower.TOWER_CONE:tower=new ConeTower(block);break;
            case Tower.TOWER_AXE:tower=new AxeTower(block);break;
            case Tower.TOWER_WHIP:tower=new WhipTower(block);break;
            case Tower.TOWER_SWORD:tower=new SwordTower(block);break;
            case Tower.TOWER_BOMB:tower=new BombTower(block);break;
            case Tower.TOWER_CHAIN:tower=new ChainTower(block);break;
            case Tower.TOWER_SPLIT:tower=new SplitTower(block);break;
        }
        block.setId(Block.TOWER);
        return tower;
    }
    public Tower(Block block){
        this.block=block;
        rect=new RectF(block.getRect());
        rect.inset(rect.width()*0.3f,rect.height()*0.3f);
        //point=new PointF(rect.centerX(),rect.centerY());
        abilities=new ArrayList<>(4);
        modifiers=new ArrayList<>(3);
        slots=new ArrayList<>(4);
        orbs=new ArrayList<>(4);
        enemies=new HashSet<>(5);
        init();
    }
    public Tower(Tower tower){
        block=tower.block;
        rect=new RectF(block.getRect());
        abilities=new ArrayList<>(tower.abilities);
        modifiers=new ArrayList<>(tower.modifiers);
        slots=new ArrayList<>(tower.slots);
        orbs=new ArrayList<>(4);
        enemies=new HashSet<>(5);

    }
    public void init(){
        init(0,0,0,0,1f,1f,0.7f,2f,0.3f,0.5f,0.15f,3f);
    }
    public void init(int id,int type,int projectileId,float attackMin,float attackMax,float speed,float range){//remove this
        this.id=id;
        this.projectileId=projectileId;
        this.attackMin=attackMin;
        this.attackMax=attackMax;
        this.speed=speed;
        this.range=range;
    }

    private void init(int id,int projectileId,int mainOrb,int level,float attackMin,float attackMax,float speed,float range ,float buildingPoint,float degreePoint,float castPoint,float projectileSpeed){
        this.id=id;
        this.projectileId=projectileId;
        this.mainOrb=mainOrb;
        this.level=level;
        this.attackMin=attackMin;
        this.attackMax=attackMax;
        this.speed=speed;
        this.range=range;
        this.buildingPoint=buildingPoint;
        this.degreePoint=degreePoint;
        this.castPoint=castPoint;
        this.projectileSpeed=projectileSpeed;
    }
    public int getId() {
        return id;
    }

    public float getDamage() {
        return Player.getRandomSeed().nextFloat()*(attackMax-attackMin)+attackMin;
    }

    public float getAttackMin() {
        return attackMin;
    }

    public float getAttackMax() {
        return attackMax;
    }

    public float getSpeed() {
        return speed;
    }

    public float getRange() {
        return range;
    }

    public Enemy getTarget() {
        return target;
    }

    public PointF getPoint() {///////////////////////////////////////////////fix this
        return block.getCenter();
    }

    public RectF getRect() {
        return rect;
    }



    public float getDegree(){
        return degree;
    }


    public void attackUp(float bonus){
        attackMin+=bonus;
        attackMax+=bonus;
    }
    public void speedUp(float bonus){
        speed+=bonus;
    }
    public void rangeUp(float bonus){
        range+=bonus;
    }
    public void split(int n){
        enemyNum+=n;
    }
    public void combo(int n){
        comboNum+=n;
    }
    public void onKill(Enemy enemy){
        kill++;
        lastKilled=enemy;
        for (TowerAbility ability : abilities) {
            ability.cast(TowerAbility.STATE_KILLED);
        }
        lastKilled=null;
    }

    public int getKill() {
        return kill;
    }

    public ArrayList<Integer> getOrbs() {
        return orbs;
    }

    public int getMainOrb() {
        return mainOrb;
    }

    public Enemy getLastKilled() {
        return lastKilled;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public Block getBlock() {
        return block;
    }

    public ArrayList<TowerAbility> getAbilities() {
        return abilities;
    }

    public int getLevel() {
        return level;
    }
    public boolean isInRange(Enemy enemy){
        float r=enemy.getRect().width()/2+range*Grid.getLength();
        float dx=block.getCenter().x-enemy.getPoint().x;
        float dy=block.getCenter().y-enemy.getPoint().y;
        if(r*r>=dx*dx+dy*dy)
            return true;
        else
            return false;
    }
    public void findTarget(){
        if(comboCnt>0)
            return;
        if(target==null||target.getState()!=Enemy.STATE_ALIVE||!isInRange(target)){
            finded=false;
            target=null;
            for (int i = Player.getWave().getEnemies().size()-1; i >=0 ; i--) {
                Enemy enemy=Player.getWave().getEnemies().get(i);
                if(enemy.getState()==Enemy.STATE_ALIVE){
                    if(isInRange(enemy)){
                        target=enemy;
                        finded=true;
                        return;
                    }
                }
            }
        }
    }


    private Projectile getProjectile(Enemy target) {
        return Player.getProjectileManager().addProjectile(projectileId,this,target);
    }


    private float calDegree() {
        float d=degree;
        if(target!=null&&comboCnt==0){
            float x=target.getPoint().x-block.getRect().centerX();
            float y=target.getPoint().y-block.getRect().centerY();
            d= (float) (Math.toDegrees(Math.atan2(y,x)));
        }
        return d;
    }
    protected void afterAttack(long dt){
        if(!finded||!cooled||!aimed){
            loaded=false;
            loadTimer=0;
            float off=dt/(castPoint*1000);
            rect.offset(off*(block.getRect().centerX()-rect.centerX()),0 );
            rect.inset(-off*(block.getRect().width()*offX-rect.width()), -off*(block.getRect().height()*offY-rect.height()));

        }
    }
    public void attack(){

        enemies.clear();
        enemies.add(target);
        for (int i = 1; i < enemyNum; i++) {
            for (Enemy enemy : Player.getWave().getEnemies()) {
                if(enemy.getState()==Enemy.STATE_ALIVE&&!enemies.contains(enemy)&&isInRange(enemy)){
                    attack(enemy).setStateAlive();
                    enemies.add(enemy);
                }
            }
        }


        for (int i = 0; i < projectileNum; i++) {
            attack(i);
        }

    }

    public Projectile getProjectile() {
        return projectile;
    }

    private void attack(int i){
        Projectile p=attack(target);
        p.split(projectileNum,i);
        p.setStateAlive();
    }
    public Projectile attack(Enemy target) {
        Projectile p=getProjectile(target);
        float damage=getDamage();
        p.setDamage(damage);
        projectile=p;
        for (TowerAbility ability : abilities) {
            ability.cast(TowerAbility.STATE_ATTACK);
        }
        projectile=null;
        return p;
    }

    public void onFire(long dt){
        if(!cooled)
            attackTimer -=dt;
        if(attackTimer<=0){
            attackTimer=0;
            cooled=true;
        }

        if(aimed&&loaded&&cooled){
            attack();
            comboCnt++;
            if(comboCnt<comboNum){
                loaded=false;
                loadTimer=0;
            }
            else {
                for (TowerAbility ability : abilities) {
                    ability.cast(TowerAbility.STATE_FIRED);
                }
                comboCnt = 0;
                cooled = false;
                attackTimer += 1000 / speed;
                if (attackTimer <= 0)
                    update(0);
            }
        }
        /*if(!cooled)
            attackTimer+=dt;
        if(attackTimer>=1000/speed){
            cooled=true;
        }
        if(aimed&&loaded&&cooled){
            attack();

        }*/
    }
    public void update(long dt){//needs to reverse??
        if(!builded){
            onBuilding(dt);
            return;
        }
        findTarget();
        onAiming(dt);
        onLoading(dt);
        onFire(dt);
        afterAttack(dt);

        for (TowerAbility ability : abilities) {
            ability.update(dt);
        }

    }
    protected void onBuilding(long dt){
        buildTimer+=dt;
        float l=dt*Grid.getLength()*projectileSpeed/1000*0.8f;
        rect.inset(-l,-l);
        if(buildTimer>=buildingPoint*1000*0.6f)
            rect.inset(l*1.5f,l*1.5f);

        if(buildTimer>=buildingPoint*1000){
            buildTimer=0;
            builded=true;
        }
    }
    protected void onAiming(long dt){
        aimed=false;
        float d=calDegree();
        float od=degree;
        float dd=dt*360/(degreePoint*1000);
        if(d-od>180){
            degree-=dd;
        }
        else if(d-od>0){
            degree+=dd;
        }
        else if(d-od>-180){
            degree-=dd;
        }
        else{
            degree+=dd;
        }

        if((d-od)*(d-degree)<=0){
            degree=d;
            aimed=true;
        }
        else if(degree<-180)
            degree+=360;
        else if(degree>180)
            degree-=360;

    }
    protected void onLoading(long dt){
        if(!finded||!cooled||!aimed){
            loaded=false;
            loadTimer=0;
            return;
        }

        loadTimer+=dt;
        if(loadTimer>=castPoint*1000)
            loaded=true;
        else{
            float v = Grid.getLength()*projectileSpeed/1000;
            rect.set(block.getRect());
            rect.inset(block.getRect().width()*(1-offX)/2, block.getRect().height()*(1-offY)/2);
            if(loadTimer<castPoint*0.6f*1000){
                float a=v/(castPoint*0.6f*1000);
                float l=(v-a*loadTimer+v)/2*loadTimer;
                rect.inset(l*0.2f, -l*0.3f);
                rect.offset(-l*0.8f,0);
            }
            else{
                float l=v/2*castPoint*0.6f*1000;
                rect.inset(l*0.2f, -l * 0.3f);
                rect.offset(-l * 0.8f, 0);

                l=(loadTimer-0.6f*castPoint*1000)*v;
                rect.inset(-l * 0, l* 0);
                rect.offset(l*1.0f,0);
            }
        }

    }

    public void onLevelUp(){
        int[] c={0,0,0};
        if(level>0)
            orbs.add(mainOrb);
        Collections.sort(orbs);
        //int s=0;
        for (int i = 0; i < orbs.size(); i++) {
            Log.i("lvl "+level+" orbs",orbs.get(i)+" ");
            //s+=Math.pow(8,orbs.size()-i-1)*orbs.get(i);
            //s=16*s+orbs.get(i);
            c[orbs.get(i)]++;
            switch (orbs.get(i)){
                case Orb.ORB_RED:onRedOrb();break;
                case Orb.ORB_GREEN:onGreenOrb();break;
                case Orb.ORB_YELLOW:onYellowOrb();break;
            }
        }
        for (int i = 0; i < c.length; i++) {
            if(c[i]>c[mainOrb]){
                mainOrb=i;
                break;
            }
        }
        switch (level){
            case 0:onLevel0(mainOrb);break;
            case 1:break;
            case 2:onLevel2(Orb.levelUp(orbs));break;
            case 3:onLevel3(Orb.levelUp(orbs));break;
        }

        level++;
        orbs.clear();

        rect.set(block.getRect());
        rect.inset(rect.width()*0.3f,rect.height()*0.3f);
        builded=false;
        buildTimer=0;


        Log.i("levelup","id "+id+" mainOrb "+mainOrb+" level "+level);
    }
    private void onLevel0(int s){
        switch (s){
            case 0x0:id=TOWER_AXE;projectileId=Projectile.PROJECTILE_AXE;break;
            case 0x1:id=TOWER_WHIP;projectileId=Projectile.PROJECTILE_WHIP;break;
            case 0x2:id=TOWER_SWORD;projectileId=Projectile.PROJECTILE_SWORD;break;
        }
        id=TOWER_ORB;
    }
    private void onLevel2(int s){


        switch (s){//0-red,1-green,2-yellow
            case TOWER_AXE:id=TOWER_AXE;projectileId=Projectile.PROJECTILE_AXE;break;
            case TOWER_CHAIN:id=TOWER_CHAIN;projectileId=Projectile.PROJECTILE_CHAIN;break;
            case TOWER_BOMB:id=TOWER_BOMB;projectileId=Projectile.PROJECTILE_BOMB;break;

            case TOWER_WHIP:id=TOWER_WHIP;projectileId=Projectile.PROJECTILE_WHIP;break;
            case TOWER_RIFLE:id=TOWER_RIFLE;projectileId= Projectile.PROJECTILE_RIFLE;projectileNum+=2;break;
            case TOWER_CONE:id=TOWER_CONE;projectileId=Projectile.PROJECTILE_CONE;break;

            case TOWER_SWORD:id=TOWER_SWORD;projectileId=Projectile.PROJECTILE_SWORD;break;
            case TOWER_COMBO:id=TOWER_COMBO;projectileId=Projectile.PROJECTILE_COMBO;comboNum+=2;break;
            case TOWER_SPLIT:id=TOWER_SPLIT;projectileId=Projectile.PROJECTILE_SPLIT;enemyNum+=2;break;

            case TOWER_RANDOM:id=Player.getRandomSeed().nextInt(11)+1;projectileId=Player.getRandomSeed().nextInt(11)+1;break;//a random tower
        }

    }
    private void onLevel3(int s){
        addAbility(s);
        /*TowerAbility ability=TowerAbility.create(s,this);
        switch (s){
            case 0x0000:ability=new TowerAbilityArmorReduce(this);break;
            case 0x0001:ability=new TowerAbilityPoison(this);break;
            case 0x0002:ability=new TowerAbilityImpetus(this);break;
            case 0x0012:ability=new TowerAbilityHeal(this);break;

            case 0x0011:ability=new TowerAbilityCriticalStrike(this);break;
            case 0x0022:ability=new TowerAbilityCombo(this);break;

            case 0x0111:ability=new TowerAbilityGreed(this);break;
            case 0x0112:ability=new TowerAbilityMoreAttack(this);break;
            case 0x1111:ability=new TowerAbilityNethertoxin(this);break;
            case 0x1112:ability=new TowerAbilitySlowDown(this);break;

            case 0x1122:ability=new TowerAbilitySplit(this);break;

            case 0x0122:ability=new TowerAbilityMoreSpeed(this);break;
            case 0x0222:ability=new TowerAbilityNecromastery(this);break;
            case 0x1222:ability=new TowerAbilityStatusUp(this);break;
            case 0x2222:ability=new TowerAbilityStun(this);break;

        }
        if(ability!=null){
            abilities.add(ability);
            ability.cast(TowerAbility.STATE_LEARNED);
        }*/
    }
    public void addAbility(int aId){
        TowerAbility ability= TowerAbility.create(aId,this);
        if(ability!=null){
            abilities.add(ability);
            ability.cast(TowerAbility.STATE_LEARNED);
        }
    }
    private void onRedOrb(){
        attackMin+=10f;
        attackMax+=15f;
        speed+=0.3f;
        range+=0.2f;
    }
    private void onGreenOrb(){
        attackMin+=4f;
        attackMax+=5f;
        speed+=0.5f;
        range+=0.2f;
    }
    private void onYellowOrb(){
        attackMin+=6f;
        attackMax+=9f;
        speed+=0.2f;
        range+=0.5f;
    }
    public void addOrb(int oId){
        orbs.add(oId);
        if(orbs.size()>=level&&level<=LEVEL_MAX)
            onLevelUp();

    }
    public void addModifier(int mId,int stack){
        TowerModifier towerModifier=null;
        for (TowerModifier modifier : modifiers) {
            if(mId==modifier.getId()){
                towerModifier=modifier;
                break;
            }
        }
        if(towerModifier!=null)
            towerModifier.recycle(this,stack);
        else{
            switch (mId){
                case TowerModifier.MODIFIER_TOWER_ATTACKUP:towerModifier=new TowerModifierAttackup(this,stack);break;
                case TowerModifier.MODIFIER_TOWER_RANGEUP:towerModifier=new TowerModifierRangeup(this,stack);break;
                case TowerModifier.MODIFIER_TOWER_SPEEDUP:towerModifier=new TowerModifierSpeedup(this,stack);break;

            }
            if(towerModifier!=null)
                modifiers.add(towerModifier);
        }
    }
    public int hasModifier(int mId){
        for (TowerModifier modifier : modifiers) {
            if(modifier.getId()==mId){
                if (modifier.isAlive())
                    return modifier.getStack();
                else
                    return 0;
            }
        }
        return -1;
    }
    public void onFocus(){

        Player.getTowerUI().setTower(this);
        Player.getTowerUI().setShow(true);
    }
}
