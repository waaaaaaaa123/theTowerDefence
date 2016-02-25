package com.example.waaaaaaaa123.thetowerdefence.button;

import android.app.Activity;
import android.graphics.RectF;
import android.view.SurfaceView;

/**
 * Created by aa081_000 on 2016/2/25.
 */
public class ExitButton extends Button {
    private static Activity activity;
    public ExitButton(RectF rect) {
        super(rect);
        string="EXIT";
    }

    public static void setActivity(Activity activity) {
        ExitButton.activity = activity;
    }

    @Override
    public void onClick() {
        activity.finish();
    }
}
