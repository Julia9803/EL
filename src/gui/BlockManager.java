package gui;


import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.*;

public class BlockManager{


    final static int length=10;
    //public static ArrayList<Block> blocks = new ArrayList<>();


    public static Block[][] blocks=new Block[length ][length];
    public static ArrayList<Block> erasableHBlocks = new ArrayList<>();//可消除的方块
    public static ArrayList<Block> erasableVBlocks = new ArrayList<>();//可消除的方块
    public static ArrayList<Block> twoBlocks=new ArrayList<>();

    static GridPane gridPane;
    /*
	 * 给方块设置随机颜色，初始化时调用
	 */
	public static void setBlockBacdgroundColor(Block block) {

		//颜色数组，用于随机选取
		String colors[]={"red","green","blue","yellow","cyan"};
		int ramdonNum=0;
		while(true){
			ramdonNum=(int)(Math.random()*5);
			block.setBackgroundColor(colors[ramdonNum]);
			if(!erasable(block)) break;
		}
	}


	/*
    横向搜索
    block 为搜索起点
	 */
    public static boolean hSearch(Block block){

        int x=block.getX();
        int y=block.getY();//起点坐标

        int count=1;//相同颜色的方块数，包括起点方块

        //向右
        for (int i = x+1;i<length;i++){
            //超过边界，跳出
            if (i>=length) break;
            if(blocks[i][y]==null) break;
            if(blocks[i][y].getColor().equals(block.getColor())){
                erasableHBlocks.add(blocks[i][y]);//加入到可消除的横向方块list
                count++;
            }else break;
        }

        //向左
        for (int i = x-1;i>=x-2;i--){
            //超过边界，跳出
            if (i<0) break;
            if(blocks[i][y].getColor().equals(block.getColor())){
                erasableHBlocks.add(blocks[i][y]);
                count++;
            }else break;
        }

        //是否已经满足三消
        System.out.println("H:"+count);
        if (count>=3){
            erasableHBlocks.add(block);//将起点方块也加入
            return true;
        }else{
            erasableHBlocks.clear();
            return false;
        }
    }

    /*
    竖向搜索
     */
    public static boolean vSearch(Block block){
        int x=block.getX();
        int y=block.getY();//起点坐标

        int count=1;//相同颜色的方块数，包括起点方块

        //向上
        for (int i = y-1;i>=y-2;i--){
            //超过边界，跳出
            if (i<0) break;

            if(blocks[x][i].getColor().equals(block.getColor())){
                erasableVBlocks.add(blocks[x][i]);
                count++;
            }else break;
        }

        //向下
        for (int i = y+1;i<=y+2;i++){
            //超过边界，跳出
            if (i>=length) break;
            if(blocks[x][i]==null) break;
            if(blocks[x][i].getColor().equals(block.getColor())){
                erasableVBlocks.add(blocks[x][i]);
                count++;
            }else break;
        }

        System.out.println("V:"+count);
        //是否已经满足三消
        if (count>=3){
            erasableVBlocks.add(block);//将起点方块也加入
            return true;
        }else{
            erasableVBlocks.clear();
            return false;
        }

    }


    /*
    是否可以消除
     */
    public static boolean erasable(Block block){

        return hSearch(block)|vSearch(block);
    }


    /*
    消除方块
     */
    public static void erase(){

        ArrayList<Block> erasableBlock = new ArrayList<>();
        ArrayList<Block> fallDownBlocks=new ArrayList<>();

        //分两次加入fallDownBlocks
        if(!erasableVBlocks.isEmpty()){
            erasableBlock.addAll(erasableVBlocks);
            fallDownBlocks.addAll(getFallDownBlocks(erasableVBlocks,getFallDownGap(erasableVBlocks)));
            erasableVBlocks.clear();
        }
        if(!erasableHBlocks.isEmpty()){
            erasableBlock.addAll(erasableHBlocks);
            fallDownBlocks.addAll(getFallDownBlocks(erasableHBlocks,getFallDownGap(erasableHBlocks)));
            erasableHBlocks.clear();
        }

        //refreshPosition(erasableBlock);


        for (Iterator<Block> iterator=erasableBlock.iterator();iterator.hasNext();){
            Block block = iterator.next();
            //判断方块是否已被消除，主要防止十字形式出现异常
            if(block.getErased()==false){
                fadeOut(block);
                block.setErased(true);
            }
        }
        //待方块消除后再落下
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fallDown(fallDownBlocks);
            }
        },1500);


        erasableHBlocks.clear();
        erasableVBlocks.clear();
    }

    /*
    淡出动画
     */
    public static void fadeOut(Block block){
        FadeTransition transition = new FadeTransition(Duration.seconds(1),block);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
        transition.setOnFinished(e->{
            block.setErased(true);
           // gridPane.getChildren().remove(block);
        });
    }


    /*
    交换方块
     */
    public static void exchange(GridPane blockGridPan){

        gridPane=blockGridPan;

        Block block0=twoBlocks.get(0);
        Block block1=twoBlocks.get(1);

        exchangeTransition(block0,block1);
        exchangeTransition(block1,block0);

        //交换引用与坐标
        int x0=block0.getX();
        int y0=block0.getY();
        int x1=block1.getX();
        int y1=block1.getY();

        block0.setX(x1);
        block0.setY(y1);
        block1.setX(x0);
        block1.setY(y0);

        blocks[x0][y0]=block1;
        blocks[x1][y1]=block0;

        twoBlocks.clear();

    }

    /*
    交换动画
     */
    public static void exchangeTransition(Block block0,Block block1){
        int deltX=(block1.getX()-block0.getX())*60;
		int deltY=(block1.getY()-block0.getY())*60;

		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltX:"+deltX);
		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltY:"+deltY);

		TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5),block0);
		transition.setByX(deltX);
		transition.setByY(deltY);
		transition.play();
		//动画结束时
        transition.setOnFinished(e->{
            block0.setNotSelected();
            block0.setIsPressed(false);
            //是否可以消除
            if(erasable(block0)) {
                erase();
            }
        });

    }


    /*
    计算落下距离
     */
    public static int getFallDownGap(ArrayList<Block> blockArrayList){

        Collections.sort(blockArrayList,new yComparator());

        int minY=blockArrayList.get(0).getY();
        int maxY=blockArrayList.get(blockArrayList.size()-1).getY();

        //计算gap
        int gap=(maxY-minY+1)*60;
        System.out.println("gap:"+gap);
        return gap;
    }


    /*
    将应该下落的方块加到list中
    @param 待消除的方块，据此算出要下落的方块
    @return 一个要下落的方块的list
     */
    public static ArrayList<Block> getFallDownBlocks(ArrayList<Block> erasableBlocks,int gap){
       ArrayList<Block> fallDownBlocks=new ArrayList<>();
        int minY=length;//待消除方块最上方的方块的Y
        ArrayList<Integer> x=new ArrayList<>();//待消除方块的x

        for (Iterator<Block> iterator = erasableBlocks.iterator();iterator.hasNext();){
            Block block = iterator.next();
            //获得最高y
            if(block.getY()<minY) minY=block.getY();
            //获取所有x
            if(!x.contains(block.getX())) x.add(block.getX());
        }
        //待消除的方块是竖向的
        if (x.size()<3){
            int xTemp=x.get(0);
            for (int i = minY-1;i>=0;i--){
                fallDownBlocks.add(blocks[xTemp][i]);
                if(blocks[xTemp][i].fallDownGap>=gap) continue;
                blocks[xTemp][i].fallDownGap=gap;
            }
        }else {//是横向的
            for(int xTemp : x){//横行每一个上方的方块都要落下
                for(int i = minY-1;i>=0;i--){
                    fallDownBlocks.add(blocks[xTemp][i]);
                    if(blocks[xTemp][i].fallDownGap>=gap) continue;
                    blocks[xTemp][i].fallDownGap=gap;
                }

            }
        }

        return fallDownBlocks;
    }

    /*
    下落
    @param 要下落的方块list
     */
    public static void fallDown(ArrayList<Block> fallDownBlocks){
        for (Iterator<Block> iterator = fallDownBlocks.iterator();iterator.hasNext();){
            Block block = iterator.next();

            TranslateTransition transition = new TranslateTransition(Duration.seconds(1),block);
            transition.setByY(block.fallDownGap);
            transition.setByX(0);
            transition.play();

            transition.setOnFinished(e->{
                block.fallDownGap=0;//将下落的gap重设为0
                fallDownBlocks.remove(block);
                if(erasable(block)){
                    erase();
                }
            });

        }
    }




//    /*
//    更新坐标
//    @param 要更新坐标
//     */
//    public static void refreshPosition(ArrayList<Block> blockArrayList) {
//
////        Collections.sort(erasableBlocks, new yComparator());
////        Collections.sort(fallDownBlocks,new yComparator());
//
//        if (blockArrayList.get(0).getErased()) {
//            // 更新坐标的是被消除的方块
//
//            //最上方的方块，更新后y坐标应为0
//            int minY = blockArrayList.get(0).getY();
//
//            //更新坐标
//            for (Block block : blockArrayList) {
//                int newY = block.getY() - minY;
//                int x = block.getX();
//                block.setY(newY);
//                //block.setErased(false);//解除消除状态
//                blocks[x][newY] = block;
//
//            }
//        } else {
//
//            //要更新的是下落的方块
//
//            int maxY = blockArrayList.get(blockArrayList.size() - 1).getY();
//            //更新坐标
//            for (int i = blockArrayList.size() - 1; i >= 0; i--) {
//                Block block = blockArrayList.get(i);
//                int x = block.getX();
//                int newY = block.getY() + block.fallDownGap / 60;
//                block.setY(newY);
//                blocks[x][newY] = block;
//            }
//
//
//        }
//    }




    //无效的类
    static class yComparator implements Comparator<Block>{

        @Override
        public int compare(Block o1, Block o2) {
            return o1.getY()-o2.getY();
        }

    }

    /*
    无效
     */
//    public static void refresh(){
//       // gridPane.getChildren().removeAll();
//        for(Block block[] : BlockManager.blocks){
//            for(Block block1 : block){
//                //blockGridPan
//                if(block1.getErased()){
//                    //BlockManager.setBlockBacdgroundColor(block1);
//                    block1.setErased(false);
//                }
//                gridPane.getChildren().removeAll(Arrays.asList(blocks));
//                gridPane.add(block1, block1.getX(),block1.getY());
//                block1.setOpacity(0);
//            }
//        }
//    }

}