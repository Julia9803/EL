package gui;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.jws.Oneway;


/*
 * ������
 */
public class BlockManager {

	public static ArrayList<Block> twoBlocks=new ArrayList<>();
	//public static Block[] blocksList=new Block[100];

	public static Block[][] blocks=new Block[10][10];
	//static ArrayList<Block> blocks=new ArrayList<>();
	//static HashMap<String,Block> blockHashMap=new HashMap<>();



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
	
	//����Ѿ����ķ���
	public static void addBlocksToList(Block block){
		twoBlocks.add(block);
	}

	public static void exchange(){

		Block block0=twoBlocks.get(0);
		Block block1=twoBlocks.get(1);

		blockTransition(block0,block1);
		blockTransition(block1,block0);

		int x0=block0.getX();
		int y0=block0.getY();
		String key0=x0+","+y0;
		//System.out.println(key0);

		int x1=block1.getX();
		int y1=block1.getY();
		String key1=x1+","+y1;
		//System.out.println(key1);

		block1.setPosition(x0,y0);
		blocks[x0][y0]=block1;
		System.out.println(key0+":"+blocks[x0][y0].getColor());


		block0.setPosition(x1,y1);
		blocks[x1][y1]=block0;
		System.out.println(key1+":"+blocks[x1][y1].getColor());


		//System.out.println("block0"+blockHashMap.get(x1+","+y1).getColor());

//		for (int i=0;i<100;i++){
//			if(blocksList[i]==block0){
//				for(int j=0;j<100;j++){
//					if (blocksList[j]==block1){
//						blocksList[j]=block0;
//						blocksList[i]=block1;
//						break;
//					}
//				}
//				break;
//			}
//		}


		if(!twoBlocks.isEmpty()){
			twoBlocks.clear();
		}

	}

	//�Ƿ��������
	public static boolean erasable(Block block){
		return hSearch(block)||vSearch(block);
	}
	//��������
	public static boolean hSearch(Block block) {
		int count=0;
		int row=block.getX();
		int line=block.getY();
		Block anotherBlock;
		//String key;
		//����
		for(int i=row;(i<=row+2)&&(i<10)&&(i<(block.getX()+block.getY()*10)%10);i++){
			//key=i+","+line;
			anotherBlock=blocks[i][line];
			//System.out.println(anotherBlock.getColor());
			//�ж���ɫ�Ƿ����
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}
		//����
		for(int i=row;(i>=row-2)&&(i>=0);i--){

			anotherBlock=blocks[i][line];
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
		int row=block.getX();
		int line=block.getY();
		Block anotherBlock;

		//����
		for(int i=line;(i>=line-2)&&(i>=0);i--){
			anotherBlock=blocks[row][i];
			//�ж���ɫ�Ƿ����
			if(anotherBlock.getColor().equals(block.getColor())){
				count++;
			}else break;
		}	
		
		//����
		for(int i=line;(i<=line+2)&&(i<10)&&(i<(block.getX()+block.getY()*10)/10);i++){
			anotherBlock=blocks[row][i];
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


	//���齻������Ч��
	public static void blockTransition(Block block0,Block block1){


		int deltX=(block1.getX()-block0.getX())*60;
		int deltY=(block1.getY()-block0.getY())*60;

		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltX:"+deltX);
		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltY:"+deltY);

		TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5),block0);
		transition.setByX(deltX);
		transition.setByY(deltY);
		transition.play();

		//��ʱ�������ȴ��������
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				block0.setNotSelected();
				block0.setIsPressed(false);
				//block1.setNotSelected();
				//block1.setIsPressed(false);


			}
		},1500);

	}

}
