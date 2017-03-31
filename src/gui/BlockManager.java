package gui;

import java.util.ArrayList;

import javafx.scene.paint.Color;


/*
 * 管理方块
 */
public class BlockManager {

	
	static ArrayList<Block> blocks=new ArrayList<>();
	
	/*
	 * 给方块设置随机颜色
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
	
	
	//是否可以消除
	public static boolean erasable(Block block){
		return hSearch(block)||vSearch(block);
	}
	//横向搜索
	public static boolean hSearch(Block block) {
		int count=0;
		int row=block.getPosition()[0];
		int line=block.getPosition()[1];
		Block anotherBlock;
		//向右
		for(int i=row;(i<=row+2)&&(i<10)&&(i<blocks.size()%10);i++){
			anotherBlock=blocks.get(line*10+i);
			//判断颜色是否相等
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		//向左
		for(int i=row;(i>=row-2)&&(i>=0);i--){
			anotherBlock=blocks.get(line*10+i);
			//判断颜色是否相等
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		
		//三消
		if(count>=3){
			return true;
		}else{
			return false;
		}
		
	}
	//竖向搜索
	public static boolean vSearch(Block block) {
		int count=0;
		int row=block.getPosition()[0];
		int line=block.getPosition()[1];
		Block anotherBlock;
		
		//向上
		for(int i=line;(i>=line-2)&&(i>=0);i--){
			anotherBlock=blocks.get(i*10+row);
			//判断颜色是否相等
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}	
		
		//向下
		for(int i=line;(i<=line+2)&&(i<10)&&(i<blocks.size()/10);i++){
			anotherBlock=blocks.get(i*10+row);
			//判断颜色是否相等
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		
		//三消
		if(count>=3){
			return true;
		}else{
			return false;
		}

	}
	
}
