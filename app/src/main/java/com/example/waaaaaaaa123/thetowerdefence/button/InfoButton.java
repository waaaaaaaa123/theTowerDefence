package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.dialog.DialogInfo;

/**
 * Created by aa081_000 on 2016/5/22.
 */
public class InfoButton extends Button {
    public InfoButton(RectF rect) {
        super(rect);
        string="info";
    }


    @Override
    public void onClick() {
        Player.info=true;
    }

}
