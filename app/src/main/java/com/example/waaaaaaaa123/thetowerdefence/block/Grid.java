package com.example.waaaaaaaa123.thetowerdefence.block;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Grid implements Iterable<Block>{

    /**
     * Point is not for pixel coordinates,Point is Block Matrix's coordinates;
     * */
    private ArrayList<Block> blocks;
    private Point start;
    private ArrayList<Point> checks;
    private Point end;
    private static float length;
    private RectF rect;
    private int xSize,ySize;
    public Grid(RectF rect,int xSize,int ySize){
        this.xSize=xSize;
        this.ySize=ySize;
        this.rect=rect;
        blocks=new ArrayList<Block>();
        start=new Point();
        checks=new ArrayList<>();

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

    public void update(long dt){
        for (Block block : blocks) {
            block.update(dt);
        }
    }
    public Block getBlockByCount(int xCount, int yCount){
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

    public ArrayList<Point> getChecks() {
        return checks;
    }

    public void setBlockIdByCount(int xCount, int yCount, int id){

        Block block= getBlockByCount(xCount, yCount);
        if(block!=null){
            block.setId(id);
            Point count=new Point(xCount,yCount);
            switch (id){
                case Block.START:start=count;break;
                case Block.END:end=count;break;
                case Block.CHECK:checks.add(count);break;
            }
        }

    }
    public void setBlockId(float x,float y,int id){
        Block block=getBlock(x, y);
        if(block!=null){
            Point count=getBlockCount(block);
            setBlockIdByCount(count.x,count.y,id);
        }
    }
    public Point getBlockCount(Block block){
        for (int i = 0; i < blocks.size(); i++) {
            if(blocks.get(i)==block){
                Point p=new Point();
                p.y=i/xSize;
                p.x=i%xSize;
                return p;
            }
        }
        return null;
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
                    Block b= getBlockByCount(j, i);
                    if(b!=null)
                        switch (b.getId()){
                            case Block.BASE:
                            case Block.START:
                            case Block.CHECK:
                            case Block.END:m[i][j]=Integer.MAX_VALUE;break;
                            default:m[i][j]=-1;
                        }
                }
            }

            m[start.y][start.x]=0;
            list.add(start);
            relax();

        }

        public void recycle(Point start,Point end,int bound){

        }
        public ArrayList<PointF> getPath(){
            if(from[end.y][end.x]==null)return null;
            ArrayList<PointF> path= new ArrayList<>(m[end.y][end.x] + 1);
            for(Point c=end;c!=null;c=from[c.y][c.x]){
                path.add(0, getBlockByCount(c.x, c.y).getCenter());
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

    public ArrayList<PointF> buildPath(int bound){
        PathBuilder pathBuilder;
        ArrayList<PointF> path=new ArrayList<>();
        Point from=start,to=end;
        for (Point check : checks) {
            to=check;
            pathBuilder = new PathBuilder(from,to, bound);
            if(pathBuilder.getPath()==null)
                return null;
            else{
                path.addAll(pathBuilder.getPath());
                path.remove(path.size()-1);
            }
            from=to;
        }
        to=end;
        pathBuilder = new PathBuilder(from,to,bound);
        if(pathBuilder.getPath()==null)
            return null;
        else{
            path.addAll(pathBuilder.getPath());
            /*for (PointF pointF : path) {
            Log.i("path", pointF.x+" "+pointF.y);
            }*/
            return path;
        }
    }
    public static float getLength() {
        return length;
    }

    @Override
    public Iterator<Block> iterator() {
        return blocks.iterator();
    }


}
