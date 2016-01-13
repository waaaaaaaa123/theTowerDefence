package com.example.waaaaaaaa123.thetowerdefence.block;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Grid implements Iterable<Block>{


    private ArrayList<Block> blocks;
    private ArrayList<Point> starts;
    private Point end;
    private static float length;
    private RectF rect;
    private int xSize,ySize;
    public Grid(RectF rect,int xSize,int ySize){
        this.xSize=xSize;
        this.ySize=ySize;
        this.rect=rect;
        blocks=new ArrayList<Block>();
        starts=new ArrayList<Point>();

        length=Math.min(rect.width()/xSize,rect.height()/ySize);
        float left;
        float top=rect.top;
        for(int i=0;i<ySize;i++) {

            left=rect.left;
            for (int j = 0; j < xSize; j++) {
                blocks.add(new Block(new RectF(left,top,left+length,top+length)));
                left+=length;
            }
            top+=length;
        }
    }
    public Block getBlock(int xCount,int yCount){
        if(xCount<xSize&&xCount>=0&&yCount<ySize&&yCount>=0)
            return blocks.get(xSize*yCount+xCount);
        else
            return null;
    }
public Block getBlock(float x,float y){
    if(rect.contains(x,y)){
        for(Block block:blocks)
        {
            if(block.getRect().contains(x,y))
                return block;
        }
    }
    return null;
}
    public RectF getRect() {
        return rect;
    }

    public void setBlockId(int xCount,int yCount,int id){

        Block block=getBlock(xCount,yCount);
        if(block!=null){
            block.setId(id);
            Point count=new Point(xCount,yCount);
            switch (id){
                case 2:starts.add(count);break;
                case 3:end=count;break;
            }
        }

    }


    class PathBuilder{
        private int[][] m;
        private Point[][] from;
        private Point start,end;
        private int bound;
        private LinkedList<Point> list;
        PathBuilder(Point start,Point end,int bound){
            m=new int[ySize][xSize];
            from=new Point[ySize][xSize];
            list=new LinkedList<Point>();
            this.bound=bound;
            this.start=start;
            this.end=end;
            for(int i=0;i<ySize;i++){
                for(int j=0;j<xSize;j++){
                    Block b=getBlock(j,i);
                    if(b!=null)
                        switch (b.getId()){
                            case Block.BASE:
                            case Block.START:
                            case Block.END:m[i][j]=Integer.MAX_VALUE;break;
                            default:m[i][j]=-1;
                        }
                }
            }

            m[start.y][start.x]=0;
            list.add(start);
            relax();

    }
        public ArrayList<PointF> getPath(){
            if(from[end.y][end.x]==null)return null;
            ArrayList<PointF> path=new ArrayList<PointF>(m[end.y][end.x]+1);
            for(Point c=end;c!=null;c=from[c.y][c.x]){
                path.add(0,getBlock(c.x,c.y).getCenter());
            }
            return path;
        }
        private void relax(){
            while(!list.isEmpty())
            {
                    Point c=list.poll();
                    for(int x=c.x+bound;x>=c.x-bound;x--){
                        if(x<0||x>=xSize) continue;
                        int yBound=bound-(x<c.x?c.x-x:x-c.x);
                        for(int y=c.y+yBound;y>=c.y-yBound;y--){
                            if(y<0||y>=ySize) continue;;
                            if(x==c.x&&y==c.y)continue;
                            if(m[y][x]>m[c.y][c.x]+1){
                                m[y][x]=m[c.y][c.x]+1;
                                from[y][x]=c;
                                if(x==end.x&&y==end.y)
                                    return;
                                Point newCount=new Point(x,y);
                                int index=list.indexOf(newCount);
                                //if(index!=-1)
                                list.add(newCount);
                            }
                        }
                    }


            }
        }
    }

    public ArrayList<PointF>buildPath(int xStart,int yStart,int xEnd,int yEnd,int bound){
            PathBuilder pathBuilder = new PathBuilder(new Point(xStart,yStart), new Point(xEnd,yEnd), bound);
        return pathBuilder.getPath();
    }
    public static float getLength() {
        return length;
    }

    @Override
    public Iterator<Block> iterator() {
        return blocks.iterator();
    }


}
