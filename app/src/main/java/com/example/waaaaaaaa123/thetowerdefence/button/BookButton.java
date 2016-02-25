package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;

/**
 * Created by aa081_000 on 2016/2/14.
 */
public class BookButton extends Button {
    public BookButton(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(BUTTON_BOOK);
    }

    @Override
    public void onClick() {
        Player.getAbilityBook().turnOpen();
    }
}
