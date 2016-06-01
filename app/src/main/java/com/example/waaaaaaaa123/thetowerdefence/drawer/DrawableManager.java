package com.example.waaaaaaaa123.thetowerdefence.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.MyGestureListener;
import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.R;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilityBook;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilitySlot;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.button.Button;
import com.example.waaaaaaaa123.thetowerdefence.button.LearnButton;
import com.example.waaaaaaaa123.thetowerdefence.dialog.Dialog;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Bag;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemSlot;
import com.example.waaaaaaaa123.thetowerdefence.menu.Menu;
import com.example.waaaaaaaa123.thetowerdefence.modifier.TowerModifier;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerUI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aa081_000 on 2016/1/9.
 */
public class DrawableManager {
    private Context context;
    private Player player;
    private HashMap<Integer,Bitmap> blockPaints;
    private Grid grid;
    private HashMap<Integer,Bitmap> enemyPaints;
    private TowerManager towerManager;
    private HashMap<Integer,Bitmap> towerPaints;
    private HashMap<Integer,Bitmap> orbPaints;
    private HashMap<Integer,Bitmap> abilityIconPaints;
    private HashMap<Integer,Bitmap> towerModifierPaints;
    private ProjectileManager projectileManager;
    private HashMap<Integer,Bitmap> projectilePaints;
    private Bag bag;
    private HashMap<Integer,Bitmap> itemPaints;
    private Wave wave;
    public DrawableManager(Context context,Player player){
        this.context=context;
        this.player=player;
        this.grid=player.getGrid();
        this.towerManager=player.getTowerManager();
        this.projectileManager=player.getProjectileManager();
        this.bag=player.getBag();


        blockPaints= new HashMap<>();
        enemyPaints= new HashMap<>();
        towerPaints= new HashMap<>();
        towerModifierPaints=new HashMap<>();
        orbPaints=new HashMap<>();
        abilityIconPaints=new HashMap<>();
        projectilePaints= new HashMap<>();
        itemPaints=new HashMap<>();
        init();
    }
    public void init(){

        blockPaints.put(Block.BASE, BitmapFactory.decodeResource(context.getResources(), R.drawable.block_base));
        blockPaints.put(Block.BUILD,BitmapFactory.decodeResource(context.getResources(), R.drawable.block_build));
        blockPaints.put(Block.TOWER, BitmapFactory.decodeResource(context.getResources(), R.drawable.block_build));
        blockPaints.put(Block.START, BitmapFactory.decodeResource(context.getResources(), R.drawable.block_start));
        blockPaints.put(Block.END, BitmapFactory.decodeResource(context.getResources(), R.drawable.block_end));
        blockPaints.put(Block.CHECK,BitmapFactory.decodeResource(context.getResources(), R.drawable.block_check));

        enemyPaints.put(-1, BitmapFactory.decodeResource(context.getResources(), R.drawable.dummy));
        enemyPaints.put(EnemyAbility.ABILITY_ENEMY_ARMOR,BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_armor));
        enemyPaints.put(EnemyAbility.ABILITY_ENEMY_HEAL,BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy_heal));
        enemyPaints.put(EnemyAbility.ABILITY_ENEMY_DODGE,BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy_dodge));
        enemyPaints.put(EnemyAbility.ABILITY_ENEMY_FLY,BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy_fly));


        orbPaints.put(Orb.ORB_GREEN,BitmapFactory.decodeResource(context.getResources(), R.drawable.orb_green));
        orbPaints.put(Orb.ORB_RED,BitmapFactory.decodeResource(context.getResources(),R.drawable.orb_red));
        orbPaints.put(Orb.ORB_YELLOW,BitmapFactory.decodeResource(context.getResources(), R.drawable.orb_yellow));

        towerPaints.put(Tower.TOWER_AXE,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_axe));
        towerPaints.put(Tower.TOWER_WHIP,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_whip));
        towerPaints.put(Tower.TOWER_SWORD,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_sword));
        towerPaints.put(Tower.TOWER_BOMB,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_bomb));
        towerPaints.put(Tower.TOWER_CHAIN,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_chain));
        towerPaints.put(Tower.TOWER_CONE,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_cone));
        towerPaints.put(Tower.TOWER_SPLIT,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_split));
        towerPaints.put(Tower.TOWER_RIFLE,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_rifle));
        towerPaints.put(Tower.TOWER_ROTATE,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_rotate));
        towerPaints.put(Tower.TOWER_BURN,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_burn));
        towerPaints.put(Tower.TOWER_COMBO,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_combo));
        towerPaints.put(Tower.TOWER_ORB,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_orb));
        towerPaints.put(Tower.TOWER_RANDOM,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_orb));

        towerModifierPaints.put(TowerModifier.MODIFIER_TOWER_ATTACKUP,BitmapFactory.decodeResource(context.getResources(),R.drawable.red_mushroom));
        towerModifierPaints.put(TowerModifier.MODIFIER_TOWER_SPEEDUP,BitmapFactory.decodeResource(context.getResources(),R.drawable.green_mushroom));
        towerModifierPaints.put(TowerModifier.MODIFIER_TOWER_RANGEUP,BitmapFactory.decodeResource(context.getResources(),R.drawable.yellow_mushroom));

        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_ARMORREDUCE,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_armor_reduce));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_CRITICALSTRIKE,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_critical_strike));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_SLOWDOWN,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_slowdown));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_STUN,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_stun));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_GREED,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_greed));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_IMPETUS,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_impetus));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_HEAL,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_heal));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_COMBO,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_combo));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_POISON,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_poison));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_MOREATTACK,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_moreattack));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_MORESPEED,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_morespeed));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_NECROMASTERY,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_necromastery));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_NETHERTOXIN,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_nethertoxin));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_STATUSUP,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_statusup));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_SPLIT,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_split));



        projectilePaints.put(Projectile.PROJECTILE_AXE,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_axe));
        projectilePaints.put(Projectile.PROJECTILE_WHIP,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_whip));
        projectilePaints.put(Projectile.PROJECTILE_SWORD,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_sword));
        projectilePaints.put(Projectile.PROJECTILE_BOMB,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_bomb));
        projectilePaints.put(Projectile.PROJECTILE_CHAIN,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_chain));
        projectilePaints.put(Projectile.PROJECTILE_CONE,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_cone));
        projectilePaints.put(Projectile.PROJECTILE_SPLIT,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_split));
        projectilePaints.put(Projectile.PROJECTILE_RIFLE,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_rifle));
        projectilePaints.put(Projectile.PROJECTILE_ROTATE,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_rotate));
        projectilePaints.put(Projectile.PROJECTILE_BURN,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_burn));
        projectilePaints.put(Projectile.PROJECTILE_COMBO,BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_combo));


        itemPaints.put(Item.ITEM_BUILDBLOCK, BitmapFactory.decodeResource(context.getResources(), R.drawable.block_build));
        itemPaints.put(Item.ITEM_GREENMUSHROOM,BitmapFactory.decodeResource(context.getResources(), R.drawable.green_mushroom));
        itemPaints.put(Item.ITEM_REDMUSHROOM,BitmapFactory.decodeResource(context.getResources(), R.drawable.red_mushroom));
        itemPaints.put(Item.ITEM_YELLOWMUSHROOM,BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow_mushroom));
        itemPaints.put(Item.ITEM_AXETOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_axe));
        itemPaints.put(Item.ITEM_WHIPTOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_whip));
        itemPaints.put(Item.ITEM_SWORDTOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_sword));
        itemPaints.put(Item.ITEM_BOMBTOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_bomb));
        itemPaints.put(Item.ITEM_CHAINTOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_chain));
        itemPaints.put(Item.ITEM_CONETOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_cone));
        itemPaints.put(Item.ITEM_SPLITTOWER,BitmapFactory.decodeResource(context.getResources(), R.drawable.tower_split));
        itemPaints.put(Item.ITEM_CHECKBLOCK,BitmapFactory.decodeResource(context.getResources(), R.drawable.block_check));
        itemPaints.put(Item.ITEM_GREENORB,BitmapFactory.decodeResource(context.getResources(),R.drawable.orb_green));
        itemPaints.put(Item.ITEM_REDORB, BitmapFactory.decodeResource(context.getResources(), R.drawable.orb_red));
        itemPaints.put(Item.ITEM_YELLOWORB, BitmapFactory.decodeResource(context.getResources(), R.drawable.orb_yellow));
        itemPaints.put(Item.ITEM_ABILITY_ARMORREDUCE,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_armor_reduce));
        itemPaints.put(Item.ITEM_ABILITY_CRITICALSTRIKE,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_critical_strike));
        itemPaints.put(Item.ITEM_ABILITY_SLOWDOWN,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_slowdown));
        itemPaints.put(Item.ITEM_ABILITY_STUN, BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_stun));
        itemPaints.put(Item.ITEM_ABILITY_GREED,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_greed));
        itemPaints.put(Item.ITEM_ABILITY_IMPETUS,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_impetus));
        itemPaints.put(Item.ITEM_ABILITY_HEAL,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_heal));
        itemPaints.put(Item.ITEM_ABILITY_COMBO,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_combo));
        itemPaints.put(Item.ITEM_ABILITY_POISON,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_poison));
        itemPaints.put(Item.ITEM_ABILITY_MOREATTACK,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_moreattack));
        itemPaints.put(Item.ITEM_ABILITY_MORESPEED,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_morespeed));
        itemPaints.put(Item.ITEM_ABILITY_NECROMASTERY,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_necromastery));
        itemPaints.put(Item.ITEM_ABILITY_NETHERTOXIN,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_nethertoxin));
        itemPaints.put(Item.ITEM_ABILITY_STATUSUP,BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_statusup));
        itemPaints.put(Item.ITEM_ABILITY_SPLIT, BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_split));
        itemPaints.put(Item.ITEM_REMOVE,BitmapFactory.decodeResource(context.getResources(),R.drawable.item_remove));

    }



    public void setWave(Wave wave) {
        this.wave = wave;

    }

    public void draw(Canvas canvas){
        drawBackground(canvas);
        drawBlock(canvas);
        drawTower(canvas);
        drawEnemy(canvas);
        drawProjectile(canvas);

        if(Player.getTowerUI().isShow()){
            drawTowerUI(canvas);
            /*if(Player.getAbilityBook().isOpen())
                drawAbilityBook(canvas);*/
        }
        else{
            drawBag(canvas);
            drawShop(canvas);
        }

        drawPlayer(canvas);
        drawFocusItem(canvas);
        if(Player.info)drawInfo(canvas);
        if(Player.menu)drawMenu(canvas);
    }
    public void drawMenu(Canvas canvas){
            //Menu menu=Player.getMenus().peek();
            Menu menu=Player.theMenu;
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            canvas.drawRect(menu.getRect(), paint);

            paint.setAlpha(255);
            paint.setTextAlign(Paint.Align.CENTER);
            RectF rect=new RectF();
            float l=ItemSlot.getLength();
            for (Button button : menu.getButtons()) {
                paint.setColor(Color.WHITE);
                rect.set(button.getRect());
                rect.inset(l*0.05f,l*0.05f);
                paint.setTextSize(rect.height()*0.75f);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.BLACK);
                canvas.drawText(button.getString(),rect.centerX(),rect.centerY()+paint.getTextSize()*0.5f,paint);
            }

    }
    public void drawBackground(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        float l=ItemSlot.getLength()*0.05f;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(l);
        RectF rect=new RectF(Player.mainRect);
        rect.inset(-l, -l);
        canvas.drawRect(rect, paint);

        rect.set(Player.bottomRect);
        rect.inset(-l, -l);
        canvas.drawRect(rect, paint);
        /*player.animator.draw(canvas);
        canvas.drawColor(Color.WHITE & 0xC0FFFFFF);*/

    }
    public void drawHpBar(Canvas canvas){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ItemSlot.getLength() * 0.05f);
        paint.setColor(Color.BLACK);
        //RectF rect=new RectF(Player.rect.left, Player.topRect.bottom, Player.mainRect.left, Player.bottomRect.top);
        RectF rect=new RectF(Player.rect.left,Player.topRect.bottom,Player.rect.right,Player.mainRect.top);
        rect.inset(rect.width() * 0.05f, rect.height() * 0.2f);
        rect.offsetTo(rect.left, Player.topRect.bottom);
        canvas.drawRect(rect, paint);
        rect.inset(ItemSlot.getLength() * 0.05f, ItemSlot.getLength() * 0.05f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);
        paint.setColor(Color.GREEN);
        //rect.top=rect.bottom-(rect.bottom-rect.top)*(player.getHp()/100f);
        rect.right=rect.left+(rect.right-rect.left)*(player.getHp()/100f);
        canvas.drawRect(rect,paint);

        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(rect.height());
        canvas.drawText(player.getHp() + "", Player.getMainRect().centerX(),rect.bottom, paint);
    }
    public void drawGold(Canvas canvas){
        Paint paint=new Paint();
        String s=Player.getBag().getGold()+"";
        RectF rect=new RectF();
        rect.offset(Player.mainRect.centerX(), (Player.bottomRect.top + Player.mainRect.bottom) / 2);
        float l=(Player.bottomRect.top-Player.mainRect.bottom)*0.8f;
        rect.inset(-l*0.5f*s.length()*0.6f,-l*0.5f);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rect, paint);
        paint.setTextSize(l);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(s,rect.centerX(),rect.bottom - paint.getTextSize() * 0.2f,paint);
    }
    public void drawPlayer(Canvas canvas){
        drawHpBar(canvas);
        drawGold(canvas);

        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(35);
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float f=fontMetrics.bottom-fontMetrics.top;
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(ItemSlot.getLength()*0.3f);
        for (Button button : Player.getButtons()) {
            switch (button.getId()){
                case Button.BUTTON_BASE:
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(ItemSlot.getLength() * 0.05f);
                    canvas.drawRect(button.getRect(), paint);
                    //paint.setColor(Color.WHITE);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawText(button.getString(),button.getRect().centerX(),button.getRect().centerY()+paint.getTextSize()*0.5f,paint);
                    break;
            }
        }
        if(!Player.getDialogs().isEmpty()){
            for(Dialog dialog:Player.getDialogs()){
                if(dialog.getId()==Dialog.DIALOG_INFO)
                    drawInfo(canvas);
                /*paint.setColor(Color.BLACK);
                paint.setAlpha(128);
                canvas.drawRect(dialog.getRect(), paint);
                paint.setColor(Color.WHITE);
                paint.setAlpha(255);
                paint.setTextAlign(Paint.Align.CENTER);

                for (int i = 0; i <dialog.getText().size() ; i++) {
                    canvas.drawText(dialog.getText().get(i),dialog.getRect().centerX(), dialog.getRect().top+f*(i+1), paint);
                }*/



            }
        }

    }

    public void drawShop(Canvas canvas){

        Paint paint=new Paint();
        Bitmap bitmap;
        RectF rect=new RectF();
        for(ItemSlot slot:bag.getShop()){
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            canvas.drawRect(slot.getRect(),paint);
            Item item=slot.getItem();
            if(item!=null){
                bitmap=itemPaints.get(item.getId());
                if(bitmap!=null){
                    rect.set(slot.getRect());
                    drawItem(canvas,item,rect);
                }
            }
        }
    }
    /*
    public void drawShop(Canvas canvas){
        if(Player.getShop().isOpen()){
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            Bitmap bitmap;
            Rect rect=new Rect();
            canvas.drawRect(Player.getMainRect(),paint);
            BuyButton button=Player.getShop().getBuyButton();

            if(button.isBuyable()){
                paint.setColor(Color.GREEN);
            }
            else {
                paint.setColor(Color.RED);
            }
            canvas.drawRect(button.getRect(), paint);
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(30);
            canvas.drawText(button.getCost() + "", button.getRect().centerX(), button.getRect().bottom -button.getRect().height()/3, paint);

            if(button.getSlot()!=null){
                button.getSlot().getRect().round(rect);
                rect.inset(-5, -5);
                paint.setColor(Color.WHITE);
                canvas.drawRect(rect,paint);

            }
            for(ItemSlot slot:Player.getShop().getSlots()){
                paint.setColor(Color.BLACK);
                paint.setAlpha(128);
                canvas.drawRect(slot.getRect(),paint);
                Item item=slot.getItem();
                if(item!=null){

                    bitmap=itemPaints.get(item.getId());
                    if(bitmap!=null){
                        slot.getRect().round(rect);
                        canvas.drawBitmap(bitmap,null,slot.getRect(),null);


                        paint.setColor(Color.BLACK);
                        paint.setTextSize(30);
                        paint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(slot.getStack() + "", rect.right - 5, rect.bottom - 5, paint);
                    }
                }
            }
        }
    }
*/
    public void drawAbilityBook(Canvas canvas){
        AbilityBook book=Player.getAbilityBook();
        if(book.isOpen()){
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            Bitmap bitmap;
            Rect rect=new Rect();
            canvas.drawRect(book.getRect(),paint);
            LearnButton button=book.getLearnButton();

            if(button.isLearnable()){
                paint.setColor(Color.GREEN);
            }
            else {
                paint.setColor(Color.RED);
            }
            canvas.drawRect(button.getRect(), paint);
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(30);
            canvas.drawText("LEARN", button.getRect().centerX(), button.getRect().bottom -button.getRect().height()/3, paint);

            if(button.getSlot()!=null){
                button.getSlot().getRect().round(rect);
                rect.inset(-5, -5);
                paint.setColor(Color.WHITE);
                canvas.drawRect(rect,paint);
            }
            for(AbilitySlot slot:book.getSlots()){
                paint.setColor(Color.BLACK);
                paint.setAlpha(128);
                canvas.drawRect(slot.getRect(), paint);
                TowerAbility towerAbility =slot.getTowerAbility();
                if(towerAbility !=null){
                    bitmap=abilityIconPaints.get(towerAbility.getId());
                    if(bitmap!=null){
                        slot.getRect().round(rect);
                        canvas.drawBitmap(bitmap, null, slot.getRect(), null);
                    }
                }
            }
        }
    }
    public void drawBag(Canvas canvas){
        Paint paint=new Paint();
        Bitmap bitmap;
        RectF rect=new RectF();
        for(ItemSlot slot:bag.getBag()){
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            canvas.drawRect(slot.getRect(),paint);
            Item item=slot.getItem();
            if(item!=null&&slot!= MyGestureListener.focusSlot){
                bitmap=itemPaints.get(item.getId());
                if(bitmap!=null){
                    rect.set(slot.getRect());
                    drawItem(canvas,item,rect);
                    /*canvas.drawBitmap(bitmap,null,slot.getRect(),null);

                    paint.setColor(Color.BLACK);
                    paint.setTextSize(ItemSlot.getLength()*0.2f);
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(item.getNum() + "", rect.right - paint.getTextSize()*0.2f, rect.bottom - paint.getTextSize()*0.2f, paint);*/
                }
            }
        }

        /*ShopButton shopButton=bag.getShopButton();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(shopButton.getRect(),paint);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(Player.getShop().getGold()+"",shopButton.getRect().centerX(),shopButton.getRect().centerY(),paint);*/

    }

    public void drawTowerUI(Canvas canvas){
        TowerUI towerUI=Player.getTowerUI();
        Tower tower=towerUI.getTower();
        RectF towerIcon=towerUI.getTowerIcon();

        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(ItemSlot.getLength() * 0.05f);
        canvas.drawRect(towerIcon, paint);

        Bitmap bitmap=towerPaints.get(tower.getId());
        RectF rect=new RectF(tower.getRect());
        rect.offset(towerIcon.centerX() - tower.getBlock().getRect().centerX(), towerIcon.centerY() - tower.getBlock().getRect().centerY());
        float l=(towerIcon.width()- Grid.getLength())/2*0.6f;
        rect.inset(-l, -l);

        canvas.save();
        canvas.rotate(-90, towerIcon.centerX(), towerIcon.centerY());
        canvas.drawBitmap(bitmap, null, rect, null);
        if(tower.getId()==Tower.TOWER_ORB){
            bitmap=orbPaints.get(tower.getMainOrb());
            rect.inset(rect.width() * 0.2f, rect.height() * 0.2f);
            canvas.drawBitmap(bitmap,null,rect,null);
        }
        canvas.restore();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        l=ItemSlot.getLength()*0.3f;
        paint.setTextSize(l);
        paint.setTextAlign(Paint.Align.LEFT);
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float v=fontMetrics.bottom-fontMetrics.top;
        float left=towerUI.getRect().left+towerUI.getRect().width()/3,top=towerUI.getRect().top+10;
        DecimalFormat df=new DecimalFormat("0.0");
        canvas.drawText("ACT "+ df.format(tower.getAttackMin())+"-"+df.format(tower.getAttackMax()), left,top+v, paint);
        canvas.drawText("SPD " + df.format(tower.getSpeed()), left,top+v*2,paint);
        canvas.drawText("RNG " + df.format(tower.getRange()), left, top + v * 3, paint);
        canvas.drawText("KILL " + tower.getKill(), left + towerUI.getRect().width() / 3, top + v, paint);

        rect.set(0, 0, l, l);
        rect.offsetTo(left + towerUI.getRect().width() / 3, top +v+ fontMetrics.descent);
        bitmap=orbPaints.get(tower.getMainOrb());
        canvas.drawBitmap(bitmap, null, rect, null);

        paint.setColor(Color.BLACK);
        paint.setAlpha(128);
        for (int i = 0; i < tower.getLevel()&&tower.getLevel()<Tower.LEVEL_MAX; i++) {
            rect.offset(l * 1.2f, 0);
            canvas.drawRect(rect, paint);
            if(i<tower.getOrbs().size()){
                bitmap=orbPaints.get(tower.getOrbs().get(i));
                canvas.drawBitmap(bitmap,null,rect,null);

            }
        }

        paint.setAlpha(128);
        rect.offsetTo(left + towerUI.getRect().width() / 3, top +v+ fontMetrics.descent+l*1.2f);
        for (TowerModifier towerModifier : tower.getModifiers()) {
            if(towerModifier.isAlive()){
                bitmap=towerModifierPaints.get(towerModifier.getId());
                canvas.drawBitmap(bitmap,null,rect,null);
                canvas.save();
                canvas.clipRect(rect);
                rect.inset(-l,-l);
                canvas.drawArc(rect,-90,towerModifier.getPercent()*360,true,paint);
                rect.inset(l,l);
                canvas.restore();
                rect.offset(l*1.2f,0);
            }
        }

        paint.setAlpha(128);
        l=ItemSlot.getLength();
        rect.set(0,0,l,l);
        rect.offsetTo(towerUI.getRect().left + towerUI.getRect().width() / 3, towerUI.getRect().top+towerUI.getRect().height()/2);

        for (int i=0;i<tower.getLevel();i++) {
            canvas.drawRect(rect,paint);
            if(i<tower.getAbilities().size()){
                TowerAbility towerAbility=tower.getAbilities().get(i);
                bitmap=abilityIconPaints.get(towerAbility.getId());
                canvas.drawBitmap(bitmap,null,rect,null);
            }
            rect.offset(l*1.2f,0);

        }
    }

    public void drawFocusItem(Canvas canvas){
        ItemSlot slot=MyGestureListener.focusSlot;
        if (slot==null)
            return;
        Paint paint=new Paint();
        Bitmap bitmap;
        RectF rect=new RectF();
        Item item=slot.getItem();
        float x=MyGestureListener.event.getX(0);
        float y=MyGestureListener.event.getY(0);

        if(item!=null){
            bitmap=itemPaints.get(item.getId());
            if(bitmap!=null)
            {
                if(Player.mainRect.contains(x,y)){
                    if(item.getBlock()==null){
                        float l=Grid.getLength();
                        rect.set(x-l/2,y-l/2,x+l/2,y+l/2);
                    }
                    else{

                        rect.set(item.getBlock().getRect());
                        if(item.isUsable())
                            paint.setColor(Color.GREEN);
                        else
                            paint.setColor(Color.RED);
                        paint.setAlpha(64);
                        if(item.getRange()>0){
                            canvas.save();
                            canvas.clipRect(Player.mainRect);
                            canvas.drawCircle(rect.centerX(), rect.centerY(), item.getRange(), paint);
                            canvas.restore();
                        }
                        else
                        {
                            canvas.drawRect(grid.getRect().left,rect.top,grid.getRect().right,rect.bottom,paint);
                            canvas.drawRect(rect.left,grid.getRect().top,rect.right,grid.getRect().bottom,paint);
                        }

                    }
                    canvas.drawBitmap(bitmap, null, rect, null);
                    rect.set(slot.getRect());

                }
                else /*if(Player.bottomRect.contains(x,y))*/{
                    float l=ItemSlot.getLength();
                    if (item.isShop()) {
                        if (slot.getRect().contains(x, y)) {
                            float dx = x - slot.getRect().centerX();
                            float dy = y - slot.getRect().centerY();
                            l *= (1.25f - (dx * dx + dy * dy) / (l * l) * 0.5f);
                            x = slot.getRect().centerX();
                            y = slot.getRect().centerY();
                            rect.set(slot.getRect());
                            rect.inset((rect.width() - l) / 2, ((rect.width() - l) / 2));
                            drawItem(canvas, slot.getItem(), rect);

                        }
                    }
                    else if(Player.getBag().getShopRect().contains(x,y)) {
                        paint.setColor(Color.YELLOW);
                        paint.setAlpha(64);
                        canvas.drawRect(Player.getBag().getShopRect(), paint);
                    }
                    else
                        for (ItemSlot itemSlot : bag.getBag()) {
                            if (itemSlot.getRect().contains(x, y)){
                                float dx=x-itemSlot.getRect().centerX();
                                float dy=y-itemSlot.getRect().centerY();
                                l*=(1.25f-(dx*dx+dy*dy)/(l*l)*0.5f);
                                x=itemSlot.getRect().centerX();
                                y=itemSlot.getRect().centerY();
                                if(itemSlot.getItem()!=null){
                                    rect.set(slot.getRect());
                                    rect.inset((rect.width()-l)/2,((rect.width()-l)/2));
                                    drawItem(canvas,itemSlot.getItem(),rect);
                                }
                                break;
                            }
                        }
                    rect.set(x - l / 2, y - l / 2, x + l / 2, y + l / 2);
                    drawItem(canvas,item,rect);
                }
            }

        }


    }
    public void drawItem(Canvas canvas,Item item,RectF rect){
        ////////////finish this to draw a single item image
        if(item!=null){
            Bitmap bitmap=itemPaints.get(item.getId());
            Paint paint=new Paint();
            canvas.drawBitmap(bitmap,null,rect,null);
            if (item.isShop())
            {
                float l=ItemSlot.getLength()*0.25f;
                paint.setColor(Color.BLACK);
                String s=item.getCost()+"";
                canvas.drawRect(rect.right - l*0.6f*s.length(), rect.bottom - l, rect.right, rect.bottom, paint);
                paint.setColor(Color.YELLOW);
                paint.setTextSize(l);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(s, rect.right, rect.bottom - paint.getTextSize() * 0.2f, paint);

            }
            if(item.isBag()){
                float l=ItemSlot.getLength()*0.3f;
                String s=item.getNum()+"";
                paint.setColor(Color.WHITE);
                canvas.drawRect(rect.left , rect.bottom - l, rect.left+l*0.6f*s.length(), rect.bottom, paint);
                paint.setColor(Color.BLACK);
                paint.setTextSize(l);
                paint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(s, rect.left, rect.bottom - paint.getTextSize() * 0.2f, paint);
            }
        }



    }
    public void drawBlock(Canvas canvas){
        Bitmap bitmap;
        RectF rect=new RectF();
        Paint paint=new Paint();
        for(Block block:grid){
            bitmap=blockPaints.get(block.getId());

            if(bitmap!=null){
                paint.setColor(block.getColor());

                canvas.drawRect(block.getRect(),paint);
                canvas.drawBitmap(bitmap, null, block.getRect(), null);
            }
        }
        ArrayList<Point> checks=Player.getGrid().getChecks();
        float x,y;
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Grid.getLength()*0.8f);
        for (int i = 0; i <checks.size(); i++) {
            x=(checks.get(i).x+0.5f)*Grid.getLength()+Player.getMainRect().left;
            y=(checks.get(i).y+0.8f)*Grid.getLength()+Player.getMainRect().top;
            canvas.drawText(i+1+"",x,y,paint);
        }

        float l;
        paint.setColor(Color.MAGENTA);
        paint.setAlpha(64);
        for (Block block : grid.getPath()) {
            if(block.getId()==Block.BASE){
                rect.set(block.getRect());
                l=Grid.getLength()*0.2f;
                //l*=Player.getRandomSeed().nextFloat()+0.5f;
                rect.inset(l, l);
                canvas.drawCircle(block.getRect().centerX(),block.getRect().centerY(),l,paint);
                //canvas.drawRect(rect, paint);
            }

        }
    }
    public void drawEnemy(Canvas canvas){
        Bitmap bitmap;
        RectF rect=new RectF();
        float l=Grid.getLength()*0.15f;

        Paint paint=new Paint();

        /*if(player.getState()==Player.STATE_DEFENCE)
            for(PointF pointF:wave.getPath()){
                canvas.drawCircle(pointF.x,pointF.y,10,p3);
            }*/

        for(Enemy enemy:wave){
            if(enemy.getState()==Enemy.STATE_ALIVE){
                bitmap=enemyPaints.get(enemy.getId());
                if(bitmap!=null){
                    rect.set(enemy.getRect());
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(l * 0.5f);
                    canvas.drawRect(rect, paint);
                    rect.inset(l * 0.1f, l * 0.1f);
                    paint.setStyle(Paint.Style.FILL);

                    switch (enemy.getStatus()){
                        case Enemy.STATUS_ATTACKLANDED:
                            paint.setColor(Color.RED);
                            canvas.drawRect(rect,paint);
                            break;
                        case Enemy.STATUS_DODGE:
                            paint.setColor(Color.WHITE);
                            canvas.drawRect(rect,paint);
                            break;
                        default:canvas.drawBitmap(bitmap, null, enemy.getRect(), null);
                    }



                    /*rect.inset((rect.width() - l) / 2, (rect.width() - l) / 2);
                    rect.offsetTo(enemy.getRect().left, enemy.getRect().top - l * 2.75f);
                    canvas.drawRect(rect, paint);
                    bitmap=orbPaints.get(enemy.getMainOrb());
                    canvas.drawBitmap(bitmap, null, rect, null);*/
                    rect.set(enemy.getRect());
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);

                    rect.set(enemy.getRect().left, enemy.getRect().top - l * 1.75f, enemy.getRect().right, enemy.getRect().top-l*0.75f);
                    canvas.drawRect(rect, paint);
                    rect.inset(l * 0.1f, l * 0.1f);
                    paint.setColor(Color.RED);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(rect, paint);
                    rect.right=rect.left+enemy.getHpPercent()*rect.width();
                    paint.setColor(Color.GREEN);
                    canvas.drawRect(rect,paint);
                }
            }
        }
    }
    public void drawTower(Canvas canvas){
        Bitmap bitmap;
        RectF rect=new RectF();
        for(Tower tower:towerManager){

            bitmap=towerPaints.get(tower.getId());
            if(bitmap!=null){

                canvas.save();
                canvas.rotate(tower.getDegree(), tower.getPoint().x, tower.getPoint().y);
                canvas.drawBitmap(bitmap,null,tower.getRect(),null);
                if(tower.getId()==Tower.TOWER_ORB){
                    bitmap=orbPaints.get(tower.getMainOrb());
                    rect.set(tower.getRect());
                    rect.inset(tower.getRect().width() * 0.2f, tower.getRect().height() * 0.2f);
                    canvas.drawBitmap(bitmap,null,rect,null);
                }

                canvas.restore();
            }
        }

        Tower focusTower=Player.getTowerUI().getTower();
        if(focusTower!=null){
            Paint p=new Paint();
            p.setColor(Color.GREEN);
            p.setAlpha(64);
            canvas.save();
            canvas.clipRect(Player.mainRect);
            canvas.drawCircle(focusTower.getBlock().getRect().centerX(), focusTower.getBlock().getRect().centerY(), focusTower.getRange() * Grid.getLength(), p);
            canvas.restore();
            bitmap=towerPaints.get(focusTower.getId());


            canvas.save();
            canvas.rotate(focusTower.getDegree(), focusTower.getPoint().x, focusTower.getPoint().y);
            canvas.drawBitmap(bitmap,null,focusTower.getRect(),null);
            if(focusTower.getId()==Tower.TOWER_ORB){
                bitmap=orbPaints.get(focusTower.getMainOrb());
                rect.set(focusTower.getRect());
                rect.inset(focusTower.getRect().width()*0.2f,focusTower.getRect().height()*0.2f);
                canvas.drawBitmap(bitmap,null,rect,null);
            }
            canvas.restore();
        }
    }
    public void drawProjectile(Canvas canvas){
        Bitmap bitmap;
        Paint paint=new Paint();
        Rect rect=new Rect();
        for(Projectile projectile:projectileManager){
            if(projectile.getState()==Projectile.STATE_DEAD) continue;
            bitmap=projectilePaints.get(projectile.getId());
            if(bitmap!=null){
                projectile.getRect().round(rect);


                canvas.save();
                canvas.rotate(projectile.getDegree(), projectile.getRect().centerX(), projectile.getRect().centerY());
                paint.setAlpha(projectile.getAlpha());
                canvas.drawBitmap(bitmap, null, projectile.getRect(), paint);
                canvas.restore();

                projectile.getAnimator().draw(canvas);
            }
        }
    }

    public void drawInfo(Canvas canvas){

        float l=Player.mainRect.width();
        Bitmap bitmap;
        Paint paint=new Paint();
        RectF rect=new RectF(-l/2,-l/2,l/2,l/2);
        rect.inset(l * 0.1f, -l * 0.1f);
        rect.offset(Player.mainRect.centerX(), Player.mainRect.centerY());

        paint.setColor(Color.BLACK);
        paint.setAlpha(128);
        canvas.drawRect(rect, paint);

        l=ItemSlot.getLength()*0.3f;
        float left=rect.left+l,top=rect.top+l,mid=rect.centerX();
        rect.set(left,top,left+l,top+l);

        for (int[] t : Orb.tower) {
            for (int i = 0; i < 3; i++) {
                bitmap=orbPaints.get(t[i]);
                canvas.drawBitmap(bitmap,null,rect,null);
                rect.offset(l*1.2f,0);
            }
            rect.offset(l*0.2f,0);
            bitmap=towerPaints.get(t[3]);
            canvas.drawBitmap(bitmap,null,rect,null);
            rect.offsetTo(left,rect.bottom+l*0.4f);
        }

        left=mid;
        rect.offsetTo(left,top);

        for (int[] a : Orb.ability) {
            for (int i = 0; i < 4; i++) {
                bitmap=orbPaints.get(a[i]);
                canvas.drawBitmap(bitmap,null,rect, null);
                rect.offset(l*1.2f,0);
            }
            rect.offset(l*0.2f,0);
            bitmap=abilityIconPaints.get(a[4]);
            canvas.drawBitmap(bitmap,null,rect,null);
            rect.offsetTo(left,rect.bottom+l*0.4f);
        }
    }
}
