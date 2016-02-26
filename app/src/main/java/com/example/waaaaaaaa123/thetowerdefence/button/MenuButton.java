package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.menu.Menu;

/**
 * Created by aa081_000 on 2016/2/25.
 */
public class MenuButton extends Button {
    public MenuButton(RectF rect) {
        super(rect);
        string="M";
    }

    @Override
    public void onClick() {
        if(Player.getMenus().empty())
            Player.getMenus().push(new Menu());
    }
}