package gui;

import java.util.ArrayList;

import javafx.scene.paint.Color;


/*
 * ������
 */
public class BlockManager {

	
	static ArrayList<Block> blocks=new ArrayList<>();
	
	/*
	 * ���������������ɫ
	 */
	public static void setBlockBacdgroundColor(Block block) {
		
		//��ɫ���飬�������ѡȡ
		String colors[]={"red","green","blue","yellow","cyan"};
		int ramdonNum=0;
		while(true){
			ramdonNum=(int)(Math.random()*5);
			block.setBackgroundColor(colors[ramdonNum]);
			if(!erasable(block)) break;
		}
	}
	
	
	//�Ƿ��������
	public static boolean erasable(Block block){
		return hSearch(block)||vSearch(block);
	}
	//��������
	public static boolean hSearch(Block block) {
		int count=0;
		int row=block.getPosition()[0];
		int line=block.getPosition()[1];
		Block anotherBlock;
		//����
		for(int i=row;(i<=row+2)&&(i<10)&&(i<blocks.size()%10);i++){
			anotherBlock=blocks.get(line*10+i);
			//�ж���ɫ�Ƿ����
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		//����
		for(int i=row;(i>=row-2)&&(i>=0);i--){
			anotherBlock=blocks.get(line*10+i);
			//�ж���ɫ�Ƿ����
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		
		//����
		if(count>=3){
			return true;
		}else{
			return false;
		}
		
	}
	//��������
	public static boolean vSearch(Block block) {
		int count=0;
		int row=block.getPosition()[0];
		int line=block.getPosition()[1];
		Block anotherBlock;
		
		//����
		for(int i=line;(i>=line-2)&&(i>=0);i--){
			anotherBlock=blocks.get(i*10+row);
			//�ж���ɫ�Ƿ����
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}	
		
		//����
		for(int i=line;(i<=line+2)&&(i<10)&&(i<blocks.size()/10);i++){
			anotherBlock=blocks.get(i*10+row);
			//�ж���ɫ�Ƿ����
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		
		//����
		if(count>=3){
			return true;
		}else{
			return false;
		}

	}
	
}
