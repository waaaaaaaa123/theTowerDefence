package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.ability.AbilitySlot;
import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/14.
 */
public class LearnButton extends Button {
    private AbilitySlot slot;
    public LearnButton(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(BUTTON_LEARN);
    }

    public boolean isLearnable(){
        Tower tower= Player.getTowerUI().getTower();
        if(tower!=null){
            if(slot!=null&&slot.getTowerAbility()!=null&&tower.getLevel()-tower.getAbilities().size()>0){
                return true;
            }
        }
        return false;
    }

    public AbilitySlot getSlot() {
        return slot;
    }

    public void setSlot(AbilitySlot slot) {
        this.slot = slot;
    }

    @Override
    public void onClick() {
        if(isLearnable()){
            TowerAbility towerAbility=AbilitySlot.CreateAbility(slot.getTowerAbility().getId());
            Tower tower=Player.getTowerUI().getTower();
            tower.getAbilities().add(towerAbility);
            towerAbility.setCaster(tower);
            Player.getTowerUI().resetSlots();
        }
    }
}
