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

import org.w3c.dom.Attr;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class MainGameView extends SurfaceView implements SurfaceHolder.Callback  {

    private MainGameThread mThread;
    private GestureDetector gestureDetector;
    public MainGameView(Context context,AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder sfh=this.getHolder();
        sfh.addCallback(this);
        mThread=new MainGameThread(sfh,context,null);
        final MyGestureListener controller = new MyGestureListener();
        mThread.setMyGestureListener(controller);
        gestureDetector=new GestureDetector(context, controller);
        gestureDetector.setIsLongpressEnabled(false);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP)
                controller.onUp(event);
                return gestureDetector.onTouchEvent(event);

            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.init(getLeft(),getTop(),getRight(),getBottom());
        mThread.setRunning(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    mThread.setRunning(false);
    }


}
