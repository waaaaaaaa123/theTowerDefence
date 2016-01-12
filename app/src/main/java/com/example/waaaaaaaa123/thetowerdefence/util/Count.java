package com.example.waaaaaaaa123.thetowerdefence.util;

/**
 * Created by aa081_000 on 2016/1/11.
 */
public class Count{
    public int xCount;
    public int yCount;
    public Count(int xCount,int yCount){
        this.xCount=xCount;
        this.yCount=yCount;
    }


    public boolean equals(Count o) {
        if(o==null)
            return false;
        return xCount == o.xCount && yCount == o.yCount;
    }
}