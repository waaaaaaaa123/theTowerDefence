package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilitySlot;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.button.BookButton;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemSlot;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/31.
 */
public class TowerUI {
    private Tower tower;
    private boolean show=false;
    private RectF rect;
    //private BookButton bookButton;
    private ArrayList<AbilitySlot> abilitySlots;
    private RectF towerIcon;

    public TowerUI(){
        rect= Player.getBottomRect();

        float l=Math.min(rect.height(),rect.width()/3);
        towerIcon = new RectF(rect.left, rect.top, rect.left + l, rect.top + l);
        towerIcon.inset(towerIcon.width()*0.1f,towerIcon.height()*0.1f);
        //bookButton=new BookButton(towerIcon);

        abilitySlots =new ArrayList<>(4);
        float left,right,top,bottom;
        l=Math.min(rect.width()/6,rect.height()/2);
        left=rect.left+rect.width()/3;
        top=rect.top+rect.height()/2;
        right=left+l;
        bottom=top+l;
        for(int i=0;i<4;i++){
        abilitySlots.add(new AbilitySlot(new RectF(left + 5, top + 5, right - 5, bottom - 5)));
            left=right;
            right=left+l;
        }

    }

    public void draw(Canvas canvas){


    }
    public Tower getTower() {
        return tower;
    }

    public RectF getTowerIcon() {
        return towerIcon;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
        resetSlots();
    }

    public void resetSlots(){
        for (int i = 0; i < tower.getAbilities().size(); i++) {
            abilitySlots.get(i).setTowerAbility(tower.getAbilities().get(i));
        }
        for (int i = tower.getAbilities().size(); i <4 ; i++) {
            abilitySlots.get(i).setTowerAbility(null);
        }
    }
    public RectF getRect() {
        return rect;
    }

    /*public BookButton getBookButton() {
        return bookButton;
    }*/

    public ArrayList<AbilitySlot> getAbilitySlots() {
        return abilitySlots;
    }


    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
        if(!show){
            tower=null;
        }
    }
}
