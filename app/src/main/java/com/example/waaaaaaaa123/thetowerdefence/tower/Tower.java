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

    private int id;
    private int projectileId;


    private boolean builded,finded,aimed,loaded,cooled;
    private float degree;
    private float buildingPoint=2f;
    private float degreePoint=0.5f;
    private float castPoint=0.1f;
    private float projectileSpeed=3f;

    private long buildTimer=0;
    private long aimedTimer=0;
    private long loadTimer=0;
    private long attackTimer =0;

    public static final int TYPE_PHYSICAL=0;
    public static final int TYPE_MAGICAL=1;
    private int type;
    private int mushroom;
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


    public Tower(RectF rect){
        this.rect=new RectF(rect);
        block=Player.getGrid().getBlock(rect.centerX(),rect.centerY());
        //point=new PointF(rect.centerX(),rect.centerY());
        abilities=new ArrayList<>(4);
        modifiers=new ArrayList<>(3);
        slots=new ArrayList<>(4);
        orbs=new ArrayList<>(4);
        enemies=new HashSet<>(5);
        init();
    }
    public Tower(Block block){
        this.block=block;
        rect=new RectF(block.getRect());
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
     init(0,0,0,0,1f,1f,0.7f,2f,2f,0.5f,0.1f,3f);
    }
    public void init(int id,int type,int projectileId,float attackMin,float attackMax,float speed,float range){
        this.id=id;
        this.projectileId=projectileId;
        this.type=type;
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

    public int getType() {
        return type;
    }


    public float getDegree(){
        return degree;
    }
    public int getMushroom() {
        return mushroom;
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
    /*public void onRedMushroom(){
        mushroom++;
    }
    public void onGreenMushroom(){
        mushroom++;
    }
    public void onYellowMushroom(){
        mushroom++;
    }*/
    public void onKill(Enemy enemy){
        kill++;
        lastKilled=enemy;
        for (TowerAbility ability : abilities) {
            ability.cast(TowerAbility.STATE_KILLED);
        }
        lastKilled=null;
        /*for (TowerAbility ability : abilities) {
            switch (ability.getId()){
                case TowerAbility.ABILITY_TOWER_HEAL:
                    ability.cast();
                    break;
                case TowerAbility.ABILITY_TOWER_GREED:
                    Player.getShop().earnGold((int) (enemy.getDp()*0.2));
                    break;
            }
        }*/
    }

    public int getKill() {
        return kill;
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
    private void beforeAttack(){}
    protected void afterAttack(long dt){
        if(!finded||!cooled||!aimed){
            loaded=false;
            loadTimer=0;
            float off=dt/(castPoint*1000);
            rect.offset(off*(block.getRect().centerX()-rect.centerX()),0 );
            rect.inset(-off*(block.getRect().width()-rect.width()), -off*(block.getRect().height()-rect.height()));
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
        /*for (TowerAbility towerAbility : abilities) {
            switch (towerAbility.getId()){
                case TowerAbility.ABILITY_TOWER_CRITICALSTRIKE:
                    if(Player.getRandomSeed().nextFloat()<0.25f){
                        damage*=2;
                    }
                    break;
                case TowerAbility.ABILITY_TOWER_SLOWDOWN:p.addModifier(EnemyModifier.MODIFIER_SLOWDOWN);break;
                case TowerAbility.ABILITY_TOWER_ARMORREDUCE:p.addModifier(EnemyModifier.MODIFIER_ARMORREDUCE);break;
                case TowerAbility.ABILITY_TOWER_STUN:
                    if(Player.getRandomSeed().nextFloat()<0.25f){
                        p.addModifier(EnemyModifier.MODIFIER_STUN);
                    }
                    break;
                case TowerAbility.ABILITY_TOWER_POISON:p.addModifier(EnemyModifier.MODIFIER_POISON);break;
                case TowerAbility.ABILITY_TOWER_IMPETUS:p.addImpetus();break;

            }
        }

        p.setDamage(damage);*/
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
        if(buildTimer>=buildingPoint*1000)
            builded=true;
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
        int s=0;
        for (int i = 0; i < orbs.size(); i++) {
            Log.i("lvl "+level+" orbs",orbs.get(i)+" ");
            //s+=Math.pow(8,orbs.size()-i-1)*orbs.get(i);
            s=16*s+orbs.get(i);
            c[orbs.get(i)]++;
            switch (orbs.get(i)){
                case Orb.ORB_RED:onRedOrb();break;
                case Orb.ORB_GREEN:onGreenOrb();break;
                case Orb.ORB_YELLOW:onYellowOrb();break;
            }
        }
        Log.i("sss",s+"");
        for (int i : c) {
            Log.i("ccc",i+"");
        }
        for (int i = 0; i < c.length; i++) {
            if(c[i]>c[mainOrb]){
                mainOrb=i;
                break;
            }
        }
        switch (level){
            case 0:onLevel0(s);break;
            case 1:break;
            case 2:onLevel2(s);break;
            case 3:onLevel3(s);break;
        }

        level++;
        orbs.clear();

        Log.i("levelup","id "+id+" mainOrb "+mainOrb+" level "+level);
    }
    private void onLevel0(int s){
        switch (s){
            case 0x0:id=TOWER_AXE;projectileId=Projectile.PROJECTILE_AXE;break;
            case 0x1:id=TOWER_WHIP;projectileId=Projectile.PROJECTILE_WHIP;break;
            case 0x2:id=TOWER_SWORD;projectileId=Projectile.PROJECTILE_SWORD;break;
        }
    }
    private void onLevel2(int s){


        switch (s){//0-red,1-green,2-yellow
            case 0x000:id=TOWER_AXE;projectileId=Projectile.PROJECTILE_AXE;break;
            case 0x001:id=TOWER_CHAIN;projectileId=Projectile.PROJECTILE_CHAIN;break;
            case 0x002:id=TOWER_BOMB;projectileId=Projectile.PROJECTILE_BOMB;break;

            case 0x111:id=TOWER_WHIP;projectileId=Projectile.PROJECTILE_WHIP;break;
            case 0x011:id=TOWER_RIFLE;projectileId= Projectile.PROJECTILE_RIFLE;projectileNum+=2;break;
            case 0x112:id=TOWER_CONE;projectileId=Projectile.PROJECTILE_CONE;break;

            case 0x222:id=TOWER_SWORD;projectileId=Projectile.PROJECTILE_SWORD;break;
            case 0x022:id=TOWER_COMBO;projectileId=Projectile.PROJECTILE_COMBO;comboNum+=2;break;
            case 0x122:id=TOWER_SPLIT;projectileId=Projectile.PROJECTILE_SPLIT;enemyNum+=2;break;

            case 0x012:id=Player.getRandomSeed().nextInt(11)+1;projectileId=Player.getRandomSeed().nextInt(11)+1;break;//a random tower
        }
    }
    private void onLevel3(int s){
        TowerAbility ability=null;
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
        }
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
