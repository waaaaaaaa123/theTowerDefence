package com.example.waaaaaaaa123.thetowerdefence.ability;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.button.LearnButton;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/2/14.
 */
public class AbilityBook {
    private ArrayList<AbilitySlot> slots;
    private RectF rect;
    private LearnButton learnButton;
    private boolean open;

    public AbilityBook(){
        slots=new ArrayList<>(16);

        rect=new RectF(Player.getMainRect());
        float left,top,right,bottom;
        left=rect.left+10;
        top=rect.top+10;
        right=rect.right-10;
        bottom=rect.bottom-10;
        float l=(bottom-top)/6;
        top=rect.centerY()-2*l;
        bottom=top+l;
        for(int i=0;i<4;i++)
        {
            left=rect.centerX()-2*l;
            right=left+l;
            for(int j=0;j<4;j++){
                AbilitySlot slot=new AbilitySlot(new RectF(left+5,top+5,right-5,bottom-5));
                slots.add(slot);
                left=right;
                right+=l;
            }
            top=bottom;
            bottom+=l;
        }
        learnButton=new LearnButton((new RectF(rect.centerX()-l,rect.bottom-l-10,rect.centerX()+l,rect.bottom-10)));

        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).setTowerAbility(AbilitySlot.CreateAbility(i));
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void turnOpen(){
        open=!open;
    }
    public RectF getRect() {
        return rect;
    }

    public LearnButton getLearnButton() {
        return learnButton;
    }
    public AbilitySlot getSlot(float x,float y){
        for (AbilitySlot slot : slots) {
            if(slot.getRect().contains(x,y))
                return slot;
        }
        return null;
    }
    public ArrayList<AbilitySlot> getSlots() {
        return slots;
    }
}
