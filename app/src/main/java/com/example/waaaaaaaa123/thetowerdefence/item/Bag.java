package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.Player;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/14.
 */
public class Bag{

    private ArrayList<ItemSlot> bag;
    private ArrayList<ItemSlot> shop;
    private int gold=10000;

    private RectF rect,shopRect;

    //private ShopButton shopButton;

    public Bag(){
        rect=Player.bottomRect;
        float l=Math.min(rect.height()/2,rect.width()/6);
        shopRect=new RectF(rect);
        shopRect.top=(rect.top+rect.bottom)/2;
        int n= (int) (rect.width()/l);
        Log.i("nnn",n+" "+l);
        bag =new ArrayList<>(n);
        shop=new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            RectF r=new RectF(rect.left,rect.top,rect.left+l,rect.top+l);
            r.offset(l * i, 0);
            r.inset(l * 0.1f, l * 0.1f);
            bag.add(new ItemSlot(r));

            RectF r2=new RectF(shopRect.left,shopRect.top,shopRect.left+l,shopRect.top+l);
            r2.offset(l * i, 0);
            r2.inset(l * 0.1f, l * 0.1f);
            ItemSlot slot=new ItemSlot(r2);
            slot.type=ItemSlot.TYPE_SHOP;
            shop.add(slot);
        }
        ItemSlot.setLength(l*0.8f);

        shop.get(0).addItem(Item.ITEM_YELLOWORB, 1);
        shop.get(1).addItem(Item.ITEM_GREENORB,1);
        shop.get(2).addItem(Item.ITEM_REDORB,1);
        shop.get(3).addItem(Item.ITEM_REMOVE,1);


    }
    /*
    public Bag(int a){
        rect=Player.getBottomRect();



        bag=new ArrayList<>(8);
        float left,top,right,bottom;
        left=rect.left+10;
        top=rect.top+10;
        right=rect.right-10;
        bottom=rect.bottom-10;
        float l=Math.min((right-left)/6,(bottom-top)/2);
        bottom=top+l;
        for(int i=0;i<2;i++)
        {
            left=(rect.left+rect.right)/2-2*l;
            right=left+l;
            for(int j=0;j<4;j++){
                bag.add(new ItemSlot(new RectF(left+5,top+5,right-5,bottom-5)));
                left=right;
                right+=l;
            }
            top=bottom;
            bottom+=l;
        }

        //shopButton=new ShopButton(new RectF(rect.right-l,rect.top,rect.right,rect.top+l));
    }*/
    public boolean addItem(int id,int n){
        for (ItemSlot slot : bag) {
            Item item=slot.getItem();
            if (item!=null&&id==item.getId())
            {
                item.add(n);
                return true;
            }
        }

        for (ItemSlot slot : bag) {
            if(slot.getItem()==null){
                slot.addItem(id,n);
                return true;
            }
        }
        return false;
    }


    public ItemSlot getSlot(float x,float y) {
        for (ItemSlot slot : bag) {
            if(slot.getRect().contains(x,y))
                return slot;
        }
        for (ItemSlot slot : shop) {//remove this part
            if(slot.getRect().contains(x,y))
                return slot;
        }
        return null;
    }

    public void remove(Item item){

        for (ItemSlot itemSlot : bag) {
            if(itemSlot.getItem()==item){
                itemSlot.removeItem();
                return;
            }
        }
    }

    /*public ShopButton getShopButton() {
        return shopButton;
    }*/

    public void buy(int n){
        gold-=n;
    }

    public int getGold() {
        return gold;
    }

    public RectF getShopRect() {
        return shopRect;
    }

    public ArrayList<ItemSlot> getBag() {
        return bag;
    }

    public ArrayList<ItemSlot> getShop() {
        return shop;
    }
}
