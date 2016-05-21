package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;
import android.widget.*;

import com.example.waaaaaaaa123.thetowerdefence.Player;

/**
 * Created by aa081_000 on 2016/1/28.
 */
public class ShopButton extends Button {
    public ShopButton(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(BUTTON_SHOP);
    }

    @Override
    public void onClick() {
        //Player.getShop().turnOpen();
    }
}
