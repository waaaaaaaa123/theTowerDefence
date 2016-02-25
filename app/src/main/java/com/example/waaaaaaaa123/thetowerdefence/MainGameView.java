package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.waaaaaaaa123.thetowerdefence.button.ExitButton;
import com.example.waaaaaaaa123.thetowerdefence.button.NewGameButton;

import org.w3c.dom.Attr;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class MainGameView extends SurfaceView implements SurfaceHolder.Callback  {

    private MainGameThread mThread;
    private SurfaceHolder sfh;
    public MainGameView(Context context,AttributeSet attrs) {
        super(context, attrs);
        sfh=this.getHolder();
        sfh.addCallback(this);
        mThread=new MainGameThread(sfh,context,null);
        this.setOnTouchListener(mThread);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("surface","created");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("surface", "changed");
        mThread.init(getLeft(), getTop(), getRight(), getBottom());
        mThread.setRunning(true);
        if(!mThread.isAlive())
            mThread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("surface", "destroyed");
    mThread.setRunning(false);
        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
