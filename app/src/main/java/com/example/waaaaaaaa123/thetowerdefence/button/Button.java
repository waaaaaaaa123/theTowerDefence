package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbility;
import com.example.waaaaaaaa123.thetowerdefence.ability.EnemyAbilityHeal;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/15.
 */
public class Button {
    public static final int BUTTON_BASE=0;
    public static final int BUTTON_SHOP=1;
    public static final int BUTTON_BUY=2;
    public static final int BUTTON_LEARN=3;
    public static final int BUTTON_BOOK=4;
    public static final int BUTTON_INFO=5;
    private int id;
    private RectF rect;
    protected String string;
    public Button(RectF rect){
        this.rect=new RectF(rect);
        this.rect.inset(rect.width()*0.1f,rect.height()*0.1f);
        string="GO!";
        init();
    }
    public void init(){
        init(BUTTON_BASE);
    }
    public void init(int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public RectF getRect() {
        return rect;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void onClick(){
        switch (Player.getState()) {
            case Player.STATE_PREPARE:
                Player.getWave().nextWave();
                Player.setState(Player.STATE_DEFENCE);
                break;
        }
    }
}
