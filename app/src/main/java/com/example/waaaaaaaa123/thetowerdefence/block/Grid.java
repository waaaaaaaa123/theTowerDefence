package com.example.waaaaaaaa123.thetowerdefence.block;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.util.Count;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Grid implements Iterable<Block>{


    private ArrayList<Block> blocks;
    private ArrayList<Count> starts;
    private Count end;
    private static int length;
    private int xSize,ySize;
    public Grid(Rect rect,int xSize,int ySize){
        this.xSize=xSize;
        this.ySize=ySize;
        blocks=new ArrayList<Block>();
        starts=new ArrayList<Count>();

        length=Math.min(rect.width()/xSize,rect.height()/ySize);
        int x;
        int y=rect.top+length/2;
        for(int i=0;i<ySize;i++) {

            x=rect.left+length/2;
            for (int j = 0; j < xSize; j++) {
                blocks.add(new Block(x,y));
                x+=length;
            }
            y+=length;
        }
    }
    public Block getBlock(int xCount,int yCount){
        if(xCount<xSize&&xCount>=0&&yCount<ySize&&yCount>=0)
        return blocks.get(xSize*yCount+xCount);
        else
        return null;
    }
    public void setBlockId(int xCount,int yCount,int id){

        Block block=getBlock(xCount,yCount);
        if(block!=null){
            block.setId(id);
            Count count=new Count(xCount,yCount);
            switch (id){
                case 2:starts.add(count);break;
                case 3:end=count;break;
            }
        }

    }

    /*class DijkstraSP{
        private int[] edgeTo;
        private int[] distTo;
        private int bound;
        class BlockDist implements Comparable<BlockDist>{

            public int blockIndex;
            public int dist;
            public BlockDist(int blockIndex,int dist){
                this.blockIndex=blockIndex;
                this.dist=dist;
            }
            @Override
            public int compareTo(BlockDist another) {
                if(dist<another.dist)return -1;
                else if(dist>another.dist) return 1;
                else return 0;
            }

        }
        private PriorityQueue<BlockDist> pq;
        public DijkstraSP(ArrayList<Block> G,int s){
            edgeTo=new int[G.size()];
            distTo=new int[G.size()];
            pq=new PriorityQueue<BlockDist>(G.size());
            for(int v=0;v<G.size();v++){
                distTo[v]=Integer.MAX_VALUE;
            }
            distTo[s]=0;
            pq.add(new BlockDist(s,0));
            while(!pq.isEmpty()){
                relax(G,pq.poll().dist);
            }
        }
        private void relax(ArrayList<Block> G,int v){
            if(G.get(v).getId()!=0)
                return;
            int yCount=v/xSize;
            int xCount=v-yCount*xSize;
            for(int i=-bound;i<=bound;i++)
                for(int j=bound-Math.abs(i);j>=0;j++)
                {
                    int x=xCount+i;
                    int y=yCount+j;
                    Block b=getBlock(x,y);
                    if(b!=null){
                        if(b.getId()==0){
                            int w=G.indexOf(b);
                            if(distTo[w]>distTo[v]+1){
                                distTo[w]=distTo[v]+1;
                                edgeTo[w]=v;
                                if(pq.contains(w)) ;
                            }
                        }
                    }
                }

        }
    }*/

    class PathBuilder{
        private int[][] m;
        private Count[][] from;
        private Count start,end;
        private int bound;
        private LinkedList<Count> list;
        PathBuilder(Count start,Count end,int bound){
            m=new int[ySize][xSize];
            from=new Count[ySize][xSize];
            list=new LinkedList<Count>();
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

            m[start.yCount][start.xCount]=0;
            list.add(start);
            relax();

    }
        public ArrayList<Point> getPath(){
            if(from[end.yCount][end.xCount]==null)return null;
            ArrayList<Point> path=new ArrayList<Point>(m[end.yCount][end.xCount]+1);
            for(Count c=end;c!=null;c=from[c.yCount][c.xCount]){
                path.add(0,getBlock(c.xCount,c.yCount).getPoint());
            }
            return path;
        }
        private void relax(){
            while(!list.isEmpty())
            {
                    Count c=list.poll();
                    for(int x=c.xCount+bound;x>=c.xCount-bound;x--){
                        if(x<0||x>=xSize) continue;
                        int yBound=bound-(x<c.xCount?c.xCount-x:x-c.xCount);
                        for(int y=c.yCount+yBound;y>=c.yCount-yBound;y--){
                            if(y<0||y>=ySize) continue;;
                            if(x==c.xCount&&y==c.yCount)continue;
                            if(m[y][x]>m[c.yCount][c.xCount]+1){
                                m[y][x]=m[c.yCount][c.xCount]+1;
                                from[y][x]=c;
                                if(x==end.xCount&&y==end.yCount)
                                    return;
                                Count newCount=new Count(x,y);
                                int index=list.indexOf(newCount);
                                //if(index!=-1)
                                list.add(newCount);
                            }
                        }
                    }


            }
        }
    }

    public ArrayList<Point>buildPath(int xStart,int yStart,int xEnd,int yEnd,int bound){
        ArrayList<Point> path=new ArrayList<Point>();

            PathBuilder pathBuilder = new PathBuilder(new Count(xStart,yStart), new Count(xEnd,yEnd), bound);

        return pathBuilder.getPath();
    }
    public static int getLength() {
        return length;
    }

    @Override
    public Iterator<Block> iterator() {
        return blocks.iterator();
    }


}
