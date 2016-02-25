package com.example.waaaaaaaa123.thetowerdefence.dialog;

import com.example.waaaaaaaa123.thetowerdefence.item.Item;

import java.util.HashMap;

/**
 * Created by aa081_000 on 2016/1/26.
 */
public class DialogItem extends Dialog {
    Item item;
    public DialogItem(Item item){
        this.item=item;
        initText();
    }

    @Override
    public void init() {
        init(DIALOG_ITEM);
    }

    @Override
    public void initText() {
        getText().add("name:"+"balabala");
        getText().add("cost: "+item.getCost());
        getText().add("describe this item....");
    }
}
