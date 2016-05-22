package com.example.waaaaaaaa123.thetowerdefence;

import android.graphics.RectF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.waaaaaaaa123.thetowerdefence.ability.AbilityBook;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilitySlot;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.button.BookButton;
import com.example.waaaaaaaa123.thetowerdefence.button.Button;
import com.example.waaaaaaaa123.thetowerdefence.button.BuyButton;
import com.example.waaaaaaaa123.thetowerdefence.button.LearnButton;
import com.example.waaaaaaaa123.thetowerdefence.button.ShopButton;
import com.example.waaaaaaaa123.thetowerdefence.dialog.Dialog;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Bag;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemSlot;
import com.example.waaaaaaaa123.thetowerdefence.item.Shop;
import com.example.waaaaaaaa123.thetowerdefence.menu.Menu;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerUI;

/**
 * Created by aa081_000 on 2016/1/13.
 */
public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int STATE_NONE=0;
    private static final int STATE_BAG=1;
    private static final int STATE_SHOP=2;
    private static final int STATE_TOWER=3;
    private static final int STATE_SCROLLBAG =4 ;
    private static final int STATE_FOCUSITEM =5 ;

    public static MotionEvent event;
    public static ItemSlot focusSlot;
    //private int state;
    private Grid grid;
    private Player player;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private Wave wave;
    private RectF topRect,mainRect,bottomRect;
    //private Item focusItem;
    private Dialog dialog;
    private Bag bag;
    public void init(Player player){
        this.grid= Player.getGrid();
        this.player=player;
        this.towerManager= Player.getTowerManager();
        this.projectileManager= Player.getProjectileManager();
        this.bag= Player.getBag();
        topRect=player.getTopRect();
        bottomRect=player.getBottomRect();
        mainRect=player.getMainRect();
    }
    public void setWave(Wave wave){
        this.wave=wave;
    }

    public void onUp(MotionEvent event) {
        Log.i("onUp",event.getX(0)+" "+event.getY(0));
        float x=event.getX(0),y=event.getY(0);


        if(focusSlot!=null&&focusSlot.getItem()!=null){
            if(!Player.getTowerUI().getRect().contains(x,y))
                Player.getTowerUI().setShow(false);

            Item focusItem=focusSlot.getItem();
            focusItem.setUsable();
            if(focusItem.isUsable()){
                //focusItem.setState(Item.STATE_USE);
                focusItem.use();
            }
            else if(Player.getBag().getShopRect().contains(event.getX(0),event.getY(0))&&!focusItem.isShop())
                focusItem.sell();
            //focusItem.setState(Item.STATE_INSLOT);

            if(!focusItem.isShop()){

                ItemSlot slot=bag.getSlot(event.getX(0),event.getY(0));
                if(slot!=null&&slot.getType()==ItemSlot.TYPE_BAG)
                    focusSlot.swap(slot);
            }

            /*focusItem =null;
            Player.setFocusItem(focusItem);*/
            focusSlot=null;
        }

    }
    public boolean onEvent(MotionEvent e){
        event=e;
        float x=e.getX(0),y=e.getY(0);

        if(Player.mainRect.contains(x,y)){
            Player.getGrid().onFocus(x, y);
            if(!Player.getTowerUI().getRect().contains(x,y))
                Player.getTowerUI().setShow(false);
            for (Tower tower : towerManager) {
                if(tower.getRect().contains(x,y)){
                    tower.onFocus();
                    break;
                }
            }
        }
        if(e.getAction()==MotionEvent.ACTION_UP){
            onUp(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if(focusSlot!=null){
            focusSlot.offHold();
            Item focusItem=focusSlot.getItem();
            //Player.setFocusItem(focusItem);
            if(focusItem!=null){
                focusItem.onFocus(e2.getX(0),e2.getY(0));
            }
        }

        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i("onDoubleTap","3");
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i("onDoubleTapEvent","4");
        return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("onDown","5");

        /*if(Player.getAbilityBook().isOpen())
            return true;*/

        float x=e.getX(0),y=e.getY(0);
        if(!Player.getTowerUI().getRect().contains(x,y))
            Player.getTowerUI().setShow(false);
        for (Tower tower : towerManager) {
            if(tower.getRect().contains(x,y)){
                tower.onFocus();
                break;
            }
        }
        if(!Player.getTowerUI().isShow())
            focusSlot=bag.getSlot(e.getX(0),e.getY(0));

        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("onFling","6");
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i("onSingleTapConfirmed","7");

        return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i("onSingleTapUp","8");
        float x=e.getX(0),y=e.getY(0);
        if(!Player.getMenus().empty()){
            Menu menu=Player.getMenus().peek();
            menu.onClick(e);
            return true;
        }

        if(Player.getTowerUI().isShow()){
            TowerUI towerUI=Player.getTowerUI();
            /*
            BookButton bookButton=towerUI.getBookButton();
            if(bookButton.getRect().contains(x,y)){
                bookButton.onClick();
                return true;
            }
            AbilityBook book=Player.getAbilityBook();
            if(book.isOpen()){
                LearnButton learnButton=book.getLearnButton();
                AbilitySlot slot=book.getSlot(x, y);
                if(slot!=null)
                    learnButton.setSlot(slot);
                if(learnButton.getRect().contains(x,y))
                    learnButton.onClick();
                if(!book.getRect().contains(x,y))
                    bookButton.onClick();
            }*/
        }
        else{
            /*Shop shop=Player.getShop();
            ShopButton shopButton=bag.getShopButton();
            if(shopButton.getRect().contains(x,y)){
                shopButton.onClick();
                return true;
            }
            if(shop.isOpen()){
                BuyButton buyButton=shop.getBuyButton();
                ItemSlot slot=shop.getSlot(x,y);
                if(slot!=null)
                    buyButton.setSlot(slot);
                if(buyButton.getRect().contains(x,y))
                    buyButton.onClick();
                if(!shop.getRect().contains(x,y))
                    shopButton.onClick();
            }
*/
        }

        Player.info=false;
        for (Button button : Player.getButtons()) {
            if (button.getRect().contains(e.getX(0),e.getY(0)))
                button.onClick();
        }

        if(!Player.getDialogs().empty()) {
            Dialog dialog = Player.getDialogs().peek();
            if (dialog.getRect().contains(e.getX(0), e.getY(0))) {
                dialog.onClick(e);
            }
            else
                dialog.onNotClick(e);
        }

        if(focusSlot!=null)
            focusSlot.offHold();
        return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("onLongPress",e.getX(0)+" "+e.getY(0));
        super.onLongPress(e);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("onShowPress", e.getX(0) + " " + e.getY(0));
        /*if(focusSlot!=null)
            focusSlot.onHold();*/

        super.onShowPress(e);
    }
}
