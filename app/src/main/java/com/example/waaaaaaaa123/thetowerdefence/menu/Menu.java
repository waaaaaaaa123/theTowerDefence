package com.example.waaaaaaaa123.thetowerdefence.menu;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.button.Button;
import com.example.waaaaaaaa123.thetowerdefence.button.ExitButton;
import com.example.waaaaaaaa123.thetowerdefence.button.NewGameButton;
import com.example.waaaaaaaa123.thetowerdefence.dialog.Dialog;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/2/25.
 */
public class Menu {
    private ArrayList<Button> buttons;
    private RectF rect;
    private int state;
    public Menu(){
        float l=Player.mainRect.width();
        rect=new RectF(-l/2,-l/2,l/2,l/2);
        rect.inset(l*0.1f,-l*0.1f);
        rect.offset(Player.mainRect.centerX(),Player.mainRect.centerY());
        buttons=new ArrayList<>(2);
        float left=rect.left,top=rect.top,right=rect.right;
        l=rect.height()/6;
        top+=l;
        buttons.add(new NewGameButton(new RectF(left,top,right,top+l)));
        top+=l;
        buttons.add(new ExitButton(new RectF(left,top,right,top+l)));

        state=Player.getState();
        Player.setState(Player.STATE_PAUSE);
    }

    public RectF getRect() {
        return rect;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void onClick(MotionEvent e) {
        for (Button button : buttons) {
            if (button.getRect().contains(e.getX(0),e.getY(0))){
                button.onClick();
                Player.menu=false;
                return;
            }
        }
        if(!rect.contains(e.getX(0),e.getY(0)))
            onNotClick(e);
    }


    public void onNotClick(MotionEvent e) {
        Player.setState(state);
        if(state!=Player.STATE_FAIL)
            Player.menu=false;
            //Player.getMenus().pop();
    }
}
