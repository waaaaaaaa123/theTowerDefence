package com.example.waaaaaaaa123.thetowerdefence.dialog;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.waaaaaaaa123.thetowerdefence.Player;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/25.
 */
public class Dialog {
    public static final int DIALOG_BASE=0;
    public static final int DIALOG_ITEM=1;
    private int id;
    protected RectF rect;
    private ArrayList<String> text;
    public Dialog(){
        text=new ArrayList<>();
        rect=new RectF(-200,-300,200,300);
        rect.offset(Player.getMainRect().centerX(),Player.getMainRect().centerY());
        init();
    }
    public void init(){
        init(0);
    }
    public void init(int id){
        this.id=id;

    }
    public RectF getRect() {
        return rect;
    }

    public int getId() {
        return id;
    }
    public void initText(){

    }
    public ArrayList<String> getText() {
        return text;
    }

    public void onClick(MotionEvent e){

    }
    public void onNotClick(MotionEvent e){
        Player.getDialogs().pop();
    }
}
