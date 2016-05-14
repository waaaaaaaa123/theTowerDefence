package com.example.waaaaaaaa123.thetowerdefence;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.ability.AbilityBook;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.button.Button;
import com.example.waaaaaaaa123.thetowerdefence.button.MenuButton;
import com.example.waaaaaaaa123.thetowerdefence.dialog.Dialog;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Bag;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.item.Shop;
import com.example.waaaaaaaa123.thetowerdefence.menu.Menu;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by aa081_000 on 2016/1/12.
 */
public class Player {
    public static final int STATE_PREPARE=0;
    public static final int STATE_DEFENCE=1;
    public static final int STATE_FAIL=2;

    //private int gold=625;
    private static int hp;
    private static int state;

    private static RectF topRect,mainRect,bottomRect;

    //private Button playButton;
    private static ArrayList<Button> buttons;
    private static Stack<Dialog> dialogs;
    private static Stack<Menu> menus;
    private static Grid grid;
    private static Wave wave;
    private static TowerManager towerManager;
    private static ProjectileManager projectileManager;
    private static Bag bag;
    private static TowerUI towerUI;
    private static AbilityBook abilityBook;
    private static Shop shop;
    private static Item focusItem;
    private static Random randomSeed;


    public Player(RectF rect){
        float l=Math.abs((rect.height()-rect.width())/2);
        topRect=new RectF(rect.left,rect.top,rect.right,rect.top+l/2);
        mainRect=new RectF(rect.left,topRect.bottom,rect.right,topRect.bottom+rect.width());
        bottomRect=new RectF(rect.left,mainRect.bottom,rect.right,rect.bottom);

        init();

    }
    public static void init(){



        randomSeed=new Random();

        grid=new Grid(mainRect,16,16);
        grid.setBlockIdByCount(0, 0, Block.START);
        grid.setBlockIdByCount(8, 5, Block.END);
        bag=new Bag();
        towerUI=new TowerUI();
        abilityBook=new AbilityBook();
        shop=new Shop();

        towerManager=new TowerManager();
        projectileManager=new ProjectileManager();

        wave=new Wave(0);




        for (int i = 0; i < 9; i++) {
            bag.addItem(1);
            bag.addItem(Item.ITEM_BOMBTOWER);
            bag.addItem(Item.ITEM_AXETOWER);
            bag.addItem(Item.ITEM_WHIPTOWER);
            bag.addItem(Item.ITEM_SWORDTOWER);
        }
        shop.earnGold(10000);

        wave.init();
        buttons=new ArrayList<>();
        menus=new Stack<>();
        buttons.add(new MenuButton(new RectF(Player.getTopRect().right-50,Player.getTopRect().top,Player.getTopRect().right,Player.getTopRect().top+50)));
        buttons.add(new Button(new RectF(Player.getTopRect().centerX()-50,Player.getTopRect().centerY()-30,Player.getTopRect().centerX()+50,Player.getTopRect().centerY()+30)));
        dialogs=new Stack<>();

        hp=100;
        state=STATE_PREPARE;
    }

    public static TowerUI getTowerUI() {
        return towerUI;
    }

    public static void setTowerUI(TowerUI towerUI) {
        Player.towerUI = towerUI;
    }

    public void update(long dt){
        switch (state){
            case STATE_PREPARE:
                towerManager.update(dt);
                //wave.update(dt);
                projectileManager.update(dt);
                break;
            case STATE_DEFENCE:
                towerManager.update(dt);
                wave.update(dt);
                projectileManager.update(dt);
                break;
            case STATE_FAIL:
                towerManager.update(dt);
                wave.update(dt);
                projectileManager.update(dt);
                break;
        }
    }

    public static void addHp(int add){
        hp+=add;
        if(hp<=0&&state!=STATE_FAIL){
            state=STATE_FAIL;
            menus.push(new Menu());
        }
    }

    public static void setState(int state) {
        Player.state = state;
    }

    public static int getState() {
        return state;
    }


    public int getHp() {
        return hp;
    }


    public static Grid getGrid() {
        return grid;
    }



    public static Bag getBag() {
        return bag;
    }

    public static Stack<Menu> getMenus() {
        return menus;
    }

    public static ArrayList<Button> getButtons() {
        return buttons;
    }

    public static ProjectileManager getProjectileManager() {
        return projectileManager;
    }
    public static RectF getMainRect() {
        return mainRect;
    }
    public static RectF getBottomRect() {
        return bottomRect;
    }

    public static RectF getTopRect() {
        return topRect;
    }

    public static TowerManager getTowerManager() {
        return towerManager;
    }

    public static Wave getWave() {
        return wave;
    }

    public static Shop getShop() {
        return shop;
    }

    public static Random getRandomSeed() {
        return randomSeed;
    }

    public static Stack<Dialog> getDialogs() {
        return dialogs;
    }

    public static Item getFocusItem() {
        return focusItem;
    }

    public static void setFocusItem(Item focusItem) {
        Player.focusItem = focusItem;
    }

    public static AbilityBook getAbilityBook() {
        return abilityBook;
    }
}
