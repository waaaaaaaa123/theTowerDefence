package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilityBook;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilitySlot;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.button.Button;
import com.example.waaaaaaaa123.thetowerdefence.button.BuyButton;
import com.example.waaaaaaaa123.thetowerdefence.button.LearnButton;
import com.example.waaaaaaaa123.thetowerdefence.button.ShopButton;
import com.example.waaaaaaaa123.thetowerdefence.dialog.Dialog;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Bag;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemSlot;
import com.example.waaaaaaaa123.thetowerdefence.menu.Menu;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerUI;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aa081_000 on 2016/1/9.
 */
public class DrawableManager {
    private Context context;
    private Player player;
    private HashMap<Integer,Drawable> blockPaints;
    private Grid grid;
    private HashMap<Integer,Drawable> enemyPaints;
    private TowerManager towerManager;
    private HashMap<Integer,Drawable> towerPaints;
    private HashMap<Integer,Drawable> abilityIconPaints;
    private ProjectileManager projectileManager;
    private HashMap<Integer,Drawable> projectilePaints;
    private Bag bag;
    private HashMap<Integer,Drawable> itemPaints;
    private Wave wave;
    public DrawableManager(Context context,Player player){
        this.context=context;
        this.player=player;
        this.grid=player.getGrid();
        this.towerManager=player.getTowerManager();
        this.projectileManager=player.getProjectileManager();
        this.bag=player.getBag();

        blockPaints=new HashMap<Integer,Drawable>();
        enemyPaints=new HashMap<Integer,Drawable>();
        towerPaints=new HashMap<Integer,Drawable>();
        abilityIconPaints=new HashMap<>();
        projectilePaints=new HashMap<Integer,Drawable>();
        itemPaints=new HashMap<>();
        init();
    }
    public void init(){
        blockPaints.put(Block.BASE, context.getResources().getDrawable(R.drawable.block_base));
        blockPaints.put(Block.BUILD, context.getResources().getDrawable(R.drawable.block_build));
        blockPaints.put(Block.TOWER, context.getResources().getDrawable(R.drawable.block_build));
        blockPaints.put(Block.START, context.getResources().getDrawable(R.drawable.block_start));
        blockPaints.put(Block.END, context.getResources().getDrawable(R.drawable.block_end));
        blockPaints.put(Block.CHECK,context.getResources().getDrawable(R.drawable.block_check));
        enemyPaints.put(0, context.getResources().getDrawable(R.drawable.dummy));


        towerPaints.put(Tower.TOWER_AXE,context.getResources().getDrawable(R.drawable.tower_axe));
        towerPaints.put(Tower.TOWER_WHIP,context.getResources().getDrawable(R.drawable.tower_whip));
        towerPaints.put(Tower.TOWER_SWORD,context.getResources().getDrawable(R.drawable.tower_sword));
        towerPaints.put(Tower.TOWER_BOMB,context.getResources().getDrawable(R.drawable.tower_bomb));
        towerPaints.put(Tower.TOWER_CHAIN,context.getResources().getDrawable(R.drawable.tower_chain));
        towerPaints.put(Tower.TOWER_CONE,context.getResources().getDrawable(R.drawable.tower_cone));
        towerPaints.put(Tower.TOWER_SPLIT,context.getResources().getDrawable(R.drawable.tower_split));


        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_ARMORREDUCE,context.getResources().getDrawable(R.drawable.ability_armor_reduce));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_CRITICALSTRIKE,context.getResources().getDrawable(R.drawable.ability_critical_strike));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_SLOWDOWN,context.getResources().getDrawable(R.drawable.ability_slowdown));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_STUN,context.getResources().getDrawable(R.drawable.ability_stun));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_GREED,context.getResources().getDrawable(R.drawable.ability_greed));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_IMPETUS,context.getResources().getDrawable(R.drawable.ability_impetus));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_HEAL,context.getResources().getDrawable(R.drawable.ability_heal));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_COMBO,context.getResources().getDrawable(R.drawable.ability_combo));
        abilityIconPaints.put(TowerAbility.ABILITY_TOWER_POISON,context.getResources().getDrawable(R.drawable.ability_poison));



        projectilePaints.put(Projectile.PROJECTILE_AXE,context.getResources().getDrawable(R.drawable.projectile_axe));
        projectilePaints.put(Projectile.PROJECTILE_WHIP,context.getResources().getDrawable(R.drawable.projectile_whip));
        projectilePaints.put(Projectile.PROJECTILE_SWORD,context.getResources().getDrawable(R.drawable.projectile_sword));
        projectilePaints.put(Projectile.PROJECTILE_BOMB,context.getResources().getDrawable(R.drawable.projectile_bomb));
        projectilePaints.put(Projectile.PROJECTILE_CHAIN,context.getResources().getDrawable(R.drawable.projectile_chain));
        projectilePaints.put(Projectile.PROJECTILE_CONE,context.getResources().getDrawable(R.drawable.projectile_cone));
        projectilePaints.put(Projectile.PROJECTILE_SPLIT,context.getResources().getDrawable(R.drawable.projectile_split));


        itemPaints.put(Item.ITEM_BUILDBLOCK, context.getResources().getDrawable(R.drawable.block_build));
        itemPaints.put(Item.ITEM_GREENMUSHROOM,context.getResources().getDrawable(R.drawable.green_mushroom));
        itemPaints.put(Item.ITEM_REDMUSHROOM,context.getResources().getDrawable(R.drawable.red_mushroom));
        itemPaints.put(Item.ITEM_YELLOWMUSHROOM,context.getResources().getDrawable(R.drawable.yellow_mushroom));
        itemPaints.put(Item.ITEM_AXETOWER,context.getResources().getDrawable(R.drawable.tower_axe));
        itemPaints.put(Item.ITEM_WHIPTOWER,context.getResources().getDrawable(R.drawable.tower_whip));
        itemPaints.put(Item.ITEM_SWORDTOWER,context.getResources().getDrawable(R.drawable.tower_sword));
        itemPaints.put(Item.ITEM_BOMBTOWER,context.getResources().getDrawable(R.drawable.tower_bomb));
        itemPaints.put(Item.ITEM_CHAINTOWER,context.getResources().getDrawable(R.drawable.tower_chain));
        itemPaints.put(Item.ITEM_CONETOWER,context.getResources().getDrawable(R.drawable.tower_cone));
        itemPaints.put(Item.ITEM_SPLITTOWER,context.getResources().getDrawable(R.drawable.tower_split));
        itemPaints.put(Item.ITEM_CHECKBLOCK,context.getResources().getDrawable(R.drawable.block_check));
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
            if(Player.getAbilityBook().isOpen())
                drawAbilityBook(canvas);
        }
        else
            drawBag(canvas);

        if(Player.getShop().isOpen())
            drawShop(canvas);

        drawPlayer(canvas);
        drawFocusItem(canvas);
        drawMenu(canvas);
    }
    public void drawMenu(Canvas canvas){
        if(!Player.getMenus().empty()){
            Menu menu=Player.getMenus().peek();
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            canvas.drawRect(menu.getRect(), paint);

            paint.setAlpha(255);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(40);
            RectF rect=new RectF();
            for (Button button : menu.getButtons()) {
                paint.setColor(Color.WHITE);
                rect.set(button.getRect());
                rect.inset(5, 5);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.BLACK);
                canvas.drawText(button.getString(),rect.centerX(),rect.bottom-5,paint);
            }
        }
    }
    public void drawBackground(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }
    public void drawPlayer(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(35);
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float f=fontMetrics.bottom-fontMetrics.top;
        //canvas.drawText("GOLD " + player.getGold(), player.getTopRect().left, player.getTopRect().top + f, paint);
        canvas.drawText("HP " + player.getHp(), player.getTopRect().left, player.getTopRect().top + f, paint);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(30);
        for (Button button : Player.getButtons()) {
            switch (button.getId()){
                case Button.BUTTON_BASE:
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(button.getRect(), paint);
                    paint.setColor(Color.WHITE);
                    canvas.drawText(button.getString(),button.getRect().centerX(),button.getRect().bottom-5,paint);
                    break;
            }
        }
        if(!Player.getDialogs().isEmpty()){
            for(Dialog dialog:Player.getDialogs()){
                paint.setColor(Color.BLACK);
                paint.setAlpha(128);
                canvas.drawRect(dialog.getRect(), paint);
                paint.setColor(Color.WHITE);
                paint.setAlpha(255);
                paint.setTextAlign(Paint.Align.CENTER);

                for (int i = 0; i <dialog.getText().size() ; i++) {
                    canvas.drawText(dialog.getText().get(i),dialog.getRect().centerX(), dialog.getRect().top+f*(i+1), paint);
                }



            }
        }

    }
    public void drawShop(Canvas canvas){
        if(Player.getShop().isOpen()){
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            Drawable drawable;
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
                    drawable=itemPaints.get(item.getId());
                    if(drawable!=null){
                        slot.getRect().round(rect);
                        drawable.setBounds(rect);
                        drawable.draw(canvas);

                        paint.setColor(Color.BLACK);
                        paint.setTextSize(30);
                        paint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(slot.getStack() + "", rect.right - 5, rect.bottom - 5, paint);
                    }
                }
            }
        }
    }

    public void drawAbilityBook(Canvas canvas){
        AbilityBook book=Player.getAbilityBook();
        if(book.isOpen()){
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            Drawable drawable;
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
                    drawable=abilityIconPaints.get(towerAbility.getId());
                    if(drawable!=null){
                        slot.getRect().round(rect);
                        drawable.setBounds(rect);
                        drawable.draw(canvas);
                    }
                }
            }
        }
    }
    public void drawBag(Canvas canvas){
        Paint paint=new Paint();
        Drawable drawable;
        Rect rect=new Rect();
        for(ItemSlot slot:bag.getSlots()){
            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            canvas.drawRect(slot.getRect(),paint);
            Item item=slot.getItem();
            if(item!=null){
                drawable=itemPaints.get(item.getId());
                if(drawable!=null){
                    slot.getRect().round(rect);
                    drawable.setBounds(rect);
                    drawable.draw(canvas);

                    paint.setColor(Color.BLACK);
                    paint.setTextSize(30);
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(slot.getStack() + "", rect.right - 5, rect.bottom - 5, paint);
                }
            }
        }

        ShopButton shopButton=bag.getShopButton();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(shopButton.getRect(),paint);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(Player.getShop().getGold()+"",shopButton.getRect().centerX(),shopButton.getRect().centerY(),paint);

    }

    public void drawTowerUI(Canvas canvas){
        TowerUI towerUI=Player.getTowerUI();
        Tower tower=towerUI.getTower();

        if(tower!=null){

            Rect rect=new Rect();
            Paint paint=new Paint();
            Drawable drawable=towerPaints.get(tower.getId());

            towerUI.getBookButton().getRect().round(rect);
            drawable.setBounds(rect);
            drawable.draw(canvas);

            int n=tower.getLevel()-tower.getAbilities().size();
            if(n>0){
                paint.setColor(Color.WHITE);
                paint.setAlpha(64);
                canvas.drawRect(rect, paint);
                paint.setColor(Color.YELLOW);
                paint.setAlpha(255);
                paint.setTextSize(55);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(n+"",rect.centerX(),rect.centerY(),paint);
            }

            paint.setColor(Color.BLACK);
            paint.setTextSize(25);
            paint.setTextAlign(Paint.Align.LEFT);
            Paint.FontMetrics fontMetrics=paint.getFontMetrics();
            float v=fontMetrics.bottom-fontMetrics.top;
            float left=towerUI.getRect().left+towerUI.getRect().width()/3,top=towerUI.getRect().top+10;
            canvas.drawText("DMG "+ tower.getDamage(), left,top+v, paint);
            canvas.drawText("SPD " + tower.getSpeed(), left,top+v*2,paint);
            canvas.drawText("RNG " + tower.getRange(), left, top + v * 3, paint);
            canvas.drawText("KILL "+tower.getKill(),left+towerUI.getRect().width()/3,top+v,paint);
            canvas.drawText("MASHROOM "+tower.getMushroom(),left+towerUI.getRect().width()/3,top+v*2,paint);

            paint.setColor(Color.BLACK);
            paint.setAlpha(128);
            for (AbilitySlot abilitySlot : towerUI.getAbilitySlots()) {
                canvas.drawRect(abilitySlot.getRect(),paint);
                if(abilitySlot.getTowerAbility()!=null){
                    drawable=abilityIconPaints.get(abilitySlot.getTowerAbility().getId());
                    abilitySlot.getRect().round(rect);
                    drawable.setBounds(rect);
                    drawable.draw(canvas);
                }
            }

        }
    }
    public void drawFocusItem(Canvas canvas){
        Paint paint=new Paint();
        Drawable drawable;
        Rect rect=new Rect();
        Item item=Player.getFocusItem();
        if(item!=null){
            drawable=itemPaints.get(item.getId());
            if(drawable!=null)
            {
                if(item.isShowRange()){
                    if(item.isUsable())
                        paint.setColor(Color.GREEN);
                    else
                        paint.setColor(Color.RED);
                    paint.setAlpha(64);
                    if(item.getRange()>0)
                        canvas.drawCircle(item.getRect().centerX(),item.getRect().centerY(),item.getRange(),paint);
                    else
                    {
                        canvas.drawRect(grid.getRect().left,item.getRect().top,grid.getRect().right,item.getRect().bottom,paint);
                        canvas.drawRect(item.getRect().left,grid.getRect().top,item.getRect().right,grid.getRect().bottom,paint);
                    }
                }

                item.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }
        }

    }
    public void drawBlock(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(35);
        for(Block block:grid){
            drawable=blockPaints.get(block.getId());

            if(drawable!=null){
                block.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }
        }
        ArrayList<Point> checks=Player.getGrid().getChecks();
        float x,y;
        for (int i = 0; i <checks.size(); i++) {
            x=(checks.get(i).x+0.5f)*Grid.getLength()+Player.getMainRect().left;
            y=(checks.get(i).y+0.85f)*Grid.getLength()+Player.getMainRect().top;
            canvas.drawText(i+1+"",x,y,paint);
        }
    }
    public void drawEnemy(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();

        Paint p1=new Paint();
        Paint p2=new Paint();
        Paint p3=new Paint();
        p1.setColor(Color.BLACK);
        p2.setColor(Color.RED);
        p3.setColor(Color.GREEN);
        RectF hpbar=new RectF();

        /*if(player.getState()==Player.STATE_DEFENCE)
            for(PointF pointF:wave.getPath()){
                canvas.drawCircle(pointF.x,pointF.y,10,p3);
            }*/

        for(Enemy enemy:wave){
            if(enemy.getState()==Enemy.STATE_ALIVE){
                drawable=enemyPaints.get(enemy.getId());
                if(drawable!=null){
                    enemy.getRect().round(rect);
                    drawable.setBounds(rect);
                    drawable.draw(canvas);

                    hpbar.set(rect.left, rect.top - 20, rect.right, rect.top - 10);
                    canvas.drawRect(hpbar, p1);
                    hpbar.set(hpbar.left + 3, hpbar.top +3, hpbar.right - 3, hpbar.bottom - 3);
                    canvas.drawRect(hpbar, p2);
                    hpbar.set(hpbar.left,hpbar.top,hpbar.left+enemy.getHpPercent()* hpbar.width(),hpbar.bottom);
                    canvas.drawRect(hpbar,p3);
                }
            }
        }
    }
    public void drawTower(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();
        for(Tower tower:towerManager){
            drawable=towerPaints.get(tower.getId());
            if(drawable!=null){
                tower.getRect().round(rect);
                drawable.setBounds(rect);

                canvas.save();
                canvas.rotate(tower.getDegree(), tower.getPoint().x, tower.getPoint().y);
                drawable.draw(canvas);
                canvas.restore();
            }
        }

        Tower focusTower=Player.getTowerUI().getTower();
        if(focusTower!=null){
            Paint p=new Paint();
            p.setColor(Color.GREEN);
            p.setAlpha(64);
            canvas.drawCircle(focusTower.getRect().centerX(),focusTower.getRect().centerY(),focusTower.getRange()*Grid.getLength(),p);

            drawable=towerPaints.get(focusTower.getId());
            focusTower.getRect().round(rect);
            drawable.setBounds(rect);

            canvas.save();
            canvas.rotate(focusTower.getDegree(), focusTower.getPoint().x, focusTower.getPoint().y);
            drawable.draw(canvas);
            canvas.restore();
        }
    }
    public void drawProjectile(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();
        for(Projectile projectile:projectileManager){
            if(projectile.getState()!=Projectile.STATE_ALIVE) continue;
            drawable=projectilePaints.get(projectile.getId());
            if(drawable!=null){
                projectile.getRect().round(rect);
                drawable.setBounds(rect);

                canvas.save();
                canvas.rotate(projectile.getDegree(),projectile.getPoint().x,projectile.getPoint().y);
                drawable.draw(canvas);
                canvas.restore();
            }
        }
    }
}
