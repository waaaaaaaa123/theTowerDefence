package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;
import android.view.SurfaceView;

import com.example.waaaaaaaa123.thetowerdefence.MainGameThread;
import com.example.waaaaaaaa123.thetowerdefence.Player;

/**
 * Created by aa081_000 on 2016/2/25.
 */
public class NewGameButton extends Button {
    private static MainGameThread mainGameThread;
    public NewGameButton(RectF rect) {
        super(rect);
        string="NEW GAME";
    }

    public static void setMainGameThread(MainGameThread mainGameThread) {
        NewGameButton.mainGameThread = mainGameThread;
    }

    @Override
    public void onClick() {
        mainGameThread.init();
    }
}
