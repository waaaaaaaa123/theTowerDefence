package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public abstract class Tower {
    public static final int TOWER_CONE=1;
    public static final int TOWER_AXE=2;
    public static final int TOWER_WHIP=3;
    public static final int TOWER_SWORD=4;
    public static final int TOWER_SPLIT=5;
    public static final int TOWER_BOMB=6;
    public static final int TOWER_CHAIN=7;
    private int id=0;
    private int projectileId;

    public static final int TYPE_PHYSICAL=0;
    public static final int TYPE_MAGICAL=1;
    private int type;
    private int mushroom;
    protected float damage;
    protected float range=2;
    protected float speed=3;
    protected ArrayList<TowerAbility> abilities;
    public static final int LEVEL_MAX=4;
    private int level=1;
    private int kill=0;
    private long attackTime=0;



    private float degree;
    private float back;
    private float size;
    private float degreePoint=1;
    private float castPoint=0.1f;

    private RectF rect;
    private PointF point;
    protected Enemy target;


    public Tower(RectF rect){
        point=new PointF(rect.centerX(),rect.centerY());
        this.rect=new RectF(rect);
        abilities=new ArrayList<>(4);
        init();
    }
    public abstract void init();

    public void init(int id,int type,int projectileId,float damage,float speed,float range){
        this.id=id;
        this.type=type;
        this.damage=damage;
        this.speed=speed;
        this.range=range;
        this.projectileId=projectileId;
    }


    public int getId() {
        return id;
    }

    public float getDamage() {
        return damage;
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

    public PointF getPoint() {
        return point;
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



    public void onRedMushroom(){
        mushroom++;
    }
    public void onGreenMushroom(){
        mushroom++;
    }
    public void onYellowMushroom(){
        mushroom++;
    }
    public void onKill(Enemy enemy){
        kill++;
        for (TowerAbility ability : abilities) {
            switch (ability.getId()){
                case TowerAbility.ABILITY_TOWER_HEAL:
                    ability.cast();
                    break;
                case TowerAbility.ABILITY_TOWER_GREED:
                    Player.getShop().earnGold((int) (enemy.getDp()*0.2));
                    break;
            }
        }
    }

    public int getKill() {
        return kill;
    }

    public void onLevelUp(){
        level++;
    }

    public ArrayList<TowerAbility> getAbilities() {
        return abilities;
    }

    public int getLevel() {
        return level;
    }
    public boolean isInRange(Enemy enemy){
        float r=enemy.getRect().width()/2+range*Grid.getLength();
        float dx=point.x-enemy.getPoint().x;
        float dy=point.y-enemy.getPoint().y;
        if(r*r>=dx*dx+dy*dy)
            return true;
        else
            return false;
    }
    public void findTarget(){
        if(target==null||target.getState()==Enemy.STATE_DEAD||!isInRange(target)){
            target=null;
            for (int i = Player.getWave().getEnemies().size()-1; i >=0 ; i--) {
                Enemy enemy=Player.getWave().getEnemies().get(i);
                if(enemy.getState()==Enemy.STATE_ALIVE){
                    if(isInRange(enemy)){
                        target=enemy;
                        return;
                    }
                }
            }
        }
    }


    public Projectile getProjectile() {
        return Player.getProjectileManager().addProjectile(projectileId,this,getTarget());
    }


    private void setDegree() {
        if(target!=null){
            float x=target.getPoint().x-point.x;
            float y=target.getPoint().y-point.y;
            degree= (float) (Math.atan2(y,x)*180/Math.PI);
        }
    }
    private void beforeAttack(){}
    private void afterAttack(){}
    public void attack() {
        Projectile p=getProjectile();
        float damage=getDamage();
        for (TowerAbility towerAbility : abilities) {
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
                case TowerAbility.ABILITY_TOWER_POISON:p.addModifier(EnemyModifier.MODIFIER_POSION);break;
                case TowerAbility.ABILITY_TOWER_IMPETUS:p.addImpetus();break;

            }
        }
        damage+=(Player.getRandomSeed().nextFloat()-0.5f)*0.3f*damage;
        p.setDamage(damage);

    }

    public void timer(long dt){
        attackTime-=dt;
        if(target==null){
            if(attackTime<0)
                attackTime=0;
            return;
        }


        if(attackTime<=0){
            beforeAttack();
            attack();
            afterAttack();
            attackTime+=1000/speed;
            if(attackTime<=0)
                update(0);
        }
    }
    public void update(long dt){
        findTarget();
        setDegree();
        timer(dt);

    }


    public void onFocus(){

        Player.getTowerUI().setTower(this);
        Player.getTowerUI().setShow(true);
    }
}
