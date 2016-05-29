package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;

/**
 * Created by aa081_000 on 2016/1/12.
 */
public abstract class Item {
    public static final int ITEM_BUILDBLOCK=1;
    public static final int ITEM_GREENMUSHROOM=2;
    public static final int ITEM_REDMUSHROOM=3;
    public static final int ITEM_YELLOWMUSHROOM=4;
    public static final int ITEM_AXETOWER=5;
    public static final int ITEM_WHIPTOWER=6;
    public static final int ITEM_SWORDTOWER=7;
    public static final int ITEM_BOMB=8;
    public static final int ITEM_NET=9;
    public static final int ITEM_BOMBTOWER=10;
    public static final int ITEM_CHAINTOWER=11;
    public static final int ITEM_CONETOWER=12;
    public static final int ITEM_SPLITTOWER=13;
    public static final int ITEM_CHECKBLOCK=14;
    public static final int ITEM_REDORB=15;
    public static final int ITEM_GREENORB=16;
    public static final int ITEM_YELLOWORB=17;
    public static final int ITEM_ABILITY_CRITICALSTRIKE =18;
    public static final int ITEM_ABILITY_SLOWDOWN =19;
    public static final int ITEM_ABILITY_ARMORREDUCE =20;
    public static final int ITEM_ABILITY_STUN =21;
    public static final int ITEM_ABILITY_GREED=22;
    public static final int ITEM_ABILITY_COMBO =23;//////////////
    public static final int ITEM_ABILITY_HEAL =24;
    public static final int ITEM_ABILITY_IMPETUS=25;
    public static final int ITEM_ABILITY_POISON=26;
    public static final int ITEM_ABILITY_MOREATTACK=27;
    public static final int ITEM_ABILITY_MORESPEED=28;
    public static final int ITEM_ABILITY_SPLIT=29;
    public static final int ITEM_ABILITY_NECROMASTERY =30;
    public static final int ITEM_ABILITY_STATUSUP=31;
    public static final int ITEM_ABILITY_NETHERTOXIN =32;
    public static final int ITEM_REMOVE=33;

    public static final int STATE_INSLOT =0;
    public static final int STATE_ONFOCUS =1;
    public static final int STATE_USE=2;

    private int id=0;
    private int state;

    public static final int TYPE_BAG=0;
    public static final int TYPE_SHOP=1;
    //private int type=TYPE_BAG;
    protected boolean bag=false;
    protected boolean shop=false;

    protected float range;
    protected int cost;
    private int num;
    //protected boolean stackable;

    //private RectF rect;
    //private ItemSlot slot;

    protected Block block;
    private boolean showRange=false;
    protected boolean usable =false;



    /* public Item(ItemSlot slot){
         this.slot=slot;
         rect =new RectF(slot.getRect());
         init();
     }*/
    public Item(){
        init();
    }
    public static Item create(int id,int num){
        Item item=null;
        switch(id){
            case ITEM_BUILDBLOCK:item=new ItemBuildBlock();break;
            case ITEM_REMOVE:item =new ItemRemove();break;

            case ITEM_GREENMUSHROOM:item=new ItemGreenMushroom();break;
            case ITEM_REDMUSHROOM:item=new ItemRedMushroom();break;
            case ITEM_YELLOWMUSHROOM:item=new ItemYellowMushroom();break;

            case ITEM_AXETOWER:item=new ItemAxeTower();break;
            case ITEM_WHIPTOWER:item=new ItemWhipTower();break;
            case ITEM_SWORDTOWER:item=new ItemSwordTower();break;
            case ITEM_BOMBTOWER:item=new ItemBombTower();break;
            case ITEM_CONETOWER:item=new ItemConeTower();break;
            case ITEM_CHAINTOWER:item=new ItemChainTower();break;
            case ITEM_SPLITTOWER:item=new ItemSplitTower();break;

            case ITEM_CHECKBLOCK:item=new ItemCheckBlock();break;

            case ITEM_GREENORB:item=new ItemGreenOrb();break;
            case ITEM_YELLOWORB:item=new ItemYellowOrb();break;
            case ITEM_REDORB:item=new ItemRedOrb();break;

            case ITEM_ABILITY_ARMORREDUCE:item=new ItemAbilityArmorReduce();break;
            case ITEM_ABILITY_COMBO:item=new ItemAbilityCombo();break;
            case ITEM_ABILITY_CRITICALSTRIKE:item=new ItemAbilityCriticalStrike();break;
            case ITEM_ABILITY_GREED:item=new ItemAbilityGreed();break;
            case ITEM_ABILITY_IMPETUS:item=new ItemAbilityImpetus();break;
            case ITEM_ABILITY_MOREATTACK:item=new ItemAbilityMoreAttack();break;
            case ITEM_ABILITY_MORESPEED:item=new ItemAbilityMoreSpeed();break;
            case ITEM_ABILITY_NECROMASTERY:item=new ItemAbilityNecromastery();break;
            case ITEM_ABILITY_NETHERTOXIN:item=new ItemAbilityNethertoxin();break;
            case ITEM_ABILITY_STATUSUP:item=new ItemAbilityStatusUp();break;
            case ITEM_ABILITY_STUN:item=new ItemAbilityStun();break;
            case ITEM_ABILITY_SLOWDOWN:item=new ItemAbilitySlowDown();break;
            case ITEM_ABILITY_POISON:item=new ItemAbilityPoison();break;
            case ITEM_ABILITY_HEAL:item=new ItemAbilityHeal();break;
            case ITEM_ABILITY_SPLIT:item=new ItemAbilitySplit();break;
            //case ITEM_ABILITY:item=new ItemAbility();break;
        }
        if(item!=null)
            item.num=num;
        return item;
    }
    public abstract void init();

    public void init(int id,float range,int cost){
        this.id=id;
        this.range=range;
        this.cost=cost;
    }
/*

    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect){
        this.rect.set(rect);
    }
*/



    public void add(int n){
        num+=n;
    }

    public int getNum() {
        return num;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }

    public void setBag(boolean bag) {
        this.bag = bag;
    }

    public final float getRange() {
        return range*Grid.getLength();
    }

    public int getCost() {
        return cost;
    }

    public final boolean isShowRange() {
        return showRange;
    }

    public boolean isShop() {
        return shop;
    }

    public boolean isBag() {
        return bag;
    }

    public void sell(){
        if(!shop){
            Player.getBag().buy(-cost/2);
            num--;
            if(num<1)
                Player.getBag().remove(this);
        }
    }
    public void onFocus(float x,float y){
        if(Player.mainRect.contains(x,y)){
            block=Player.getGrid().getBlock(x,y);
            setUsable();
            showRange=true;
        }
        else{
            block=null;
            usable=false;
            showRange=false;
        }
    }

    public void use(){
        //slot.stackDown();
        //setState(STATE_INSLOT);
        if(bag)
            num--;
        if(num<1){
            Player.getBag().remove(this);
        }
        if (shop)
            Player.getBag().buy(cost);

        block=null;

    }
    public void setUsable() {

        usable=block!=null;
        if (bag)usable&=num>0;
        if (shop)usable&=Player.getBag().getGold()>=cost;



        //usable = block.getId() == Block.BUILD;
    }

    public int getState() {
        return state;
    }


    public Block getBlock() {
        return block;
    }
    /*
    public void setState(int state) {//remove this
        this.state = state;
        switch (state){
            case STATE_INSLOT:
               // rect.set(slot.getRect());
                showRange=false;
                break;
            case STATE_USE:
                use();
                break;
        }
    }*/
/*
    public void rectOffset(float x,float y) {
        rect.offset(x,y);
    }*/


    public int getId() {
        return id;
    }

    public boolean isUsable() {
        return usable;
    }

}
