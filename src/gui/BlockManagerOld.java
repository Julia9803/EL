//package gui;
//
//import java.sql.Time;
//import java.util.*;
//import java.util.Timer;
//
//import javafx.animation.Transition;
//import javafx.animation.TranslateTransition;
//import javafx.application.Platform;
//import javafx.scene.paint.Color;
//import javafx.util.Duration;
//
//import javax.jws.Oneway;
//import javax.swing.*;
//
//
///*
// * ������
// */
//public class BlockManagerOld {
//
//	public static ArrayList<Block> twoBlocks=new ArrayList<>();
//	//public static Block[] blocksList=new Block[100];
//
//	//public static Block[][] blocks=new Block[10][10];
//
//	public static ArrayList<Block> erasableHBlocks=new ArrayList<>();
//	public static ArrayList<Block> erasableVBlocks=new ArrayList<>();//�洢���������ķ���
//
//	public static  ArrayList<Block> fallDownBlocks=new ArrayList<>();//�洢׼�����µķ���
//	static ArrayList<Block> blocks=new ArrayList<>();
//	//static HashMap<String,Block> blockHashMap=new HashMap<>();
//
//
//
//	/*
//	 * ���������������ɫ
//	 */
//	public static void setBlockBacdgroundColor(Block block) {
//
//		//��ɫ���飬�������ѡȡ
//		String colors[]={"red","green","blue","yellow","cyan"};
//		int ramdonNum=0;
//		while(true){
//			ramdonNum=(int)(Math.random()*5);
//			block.setBackgroundColor(colors[ramdonNum]);
//			if(!erasable(block,true)) break;
//		}
//	}
//
//	//����Ѿ����ķ���
//	public static void addBlocksToList(Block block){
//
//		twoBlocks.add(block);
//	}
//
//	public static void exchange(){
//
//		Block block0=twoBlocks.get(0);
//		Block block1=twoBlocks.get(1);
//
//		blockTransition(block0,block1);
//		blockTransition(block1,block0);
//
//		int x0=block0.getX();
//		int y0=block0.getY();
//		String key0=x0+","+y0;
//		//System.out.println(key0);
//
//		int x1=block1.getX();
//		int y1=block1.getY();
//		String key1=x1+","+y1;
//		//System.out.println(key1);
//
//
//		//�����������������
//		block1.setPosition(x0,y0);
//		blocks[x0][y0]=block1;
//		System.out.println(key0+":"+blocks[x0][y0].getColor());
//
//
//		block0.setPosition(x1,y1);
//		blocks[x1][y1]=block0;
//		System.out.println(key1+":"+blocks[x1][y1].getColor());
//
//		//����ݴ��������������list
//		if(!twoBlocks.isEmpty()){
//			twoBlocks.clear();
//		}
//
//
//	}
//	//���erasableBlocks�еķ���
//	public static void erase(){
//
//		Block block;
//		for(Iterator<Block> iterator = erasableHBlocks.iterator();iterator.hasNext();){
//			//System.out.println(iterator.next().getColor()+":"+iterator.next().getX()+","+iterator.next().getY());
//			block=iterator.next();
//			addBlocksToFall(block.getX(),block.getY(),60);
//			block.setVisible(false);
//			//iterator.remove();
//		}
//		erasableHBlocks.clear();
//		//fallDown(fallDownBlocks,60);
//
//		int maxY=0;
//		int minY=10;
//		int x=0;
//
//		//����������������
//		for(Iterator<Block> iterator = erasableVBlocks.iterator();iterator.hasNext();){
//			//System.out.println(iterator.next().getColor()+":"+iterator.next().getX()+","+iterator.next().getY());
//			block=iterator.next();
//			if(maxY<block.getY()){
//				maxY=block.getY();
//			}
//			if(minY>block.getY()){
//				minY=block.getY();
//			}
//			x=block.getX();
//			block.setVisible(false);
//			//iterator.remove();
//		}
//		addBlocksToFall(x,maxY,(maxY-minY+1)*60);
//		erasableVBlocks.clear();
//		//fallDown(fallDownBlocks,180);
//
//
//	}
//
//	//�Ƿ��������
//	public static boolean erasable(Block block,boolean single){
//		//�����erasableBlocks
//		erasableHBlocks.clear();
//		return hSearch(block,single)|vSearch(block,single);
//	}
//
//	//��������
//	/*
//
//	 */
//	public static boolean hSearch(Block block,boolean leftOnly) {
//		int count=1;
//		int row=block.getX();
//		int line=block.getY();
//		Block anotherBlock;
//
//
//		if(!leftOnly){
//			//����
//			for(int i=row+1;(i<=row+2)&&(i<10);i++){
//				anotherBlock=blocks[i][line];
//				//�ж���ɫ�Ƿ����
//				if(anotherBlock.getColor().equals(block.getColor())){
//					System.out.println(anotherBlock.getColor()+":"+anotherBlock.getX()+","+anotherBlock.getY());
//					erasableHBlocks.add(anotherBlock);
//					count++;
//				}else break;
//			}
//		}
//		//����
//		for(int i=row-1;(i>=row-2)&&(i>=0);i--){
//
//			anotherBlock=blocks[i][line];
//			//�ж���ɫ�Ƿ����
//			if(anotherBlock.getColor().equals(block.getColor())){
//				System.out.println(anotherBlock.getColor()+":"+anotherBlock.getX()+","+anotherBlock.getY());
//				erasableHBlocks.add(anotherBlock);
//				count++;
//			}else break;
//		}
//
//		System.out.println("����"+count);
//		//����
//		if(count>=3){
//			erasableHBlocks.add(block);
//			return true;
//		}else{
//			erasableHBlocks.clear();
//			return false;
//		}
//
//	}
//	//��������
//	public static boolean vSearch(Block block,boolean upOnly) {
//		int count=1;
//		int row=block.getX();
//		int line=block.getY();
//		Block anotherBlock;
//
//		if(!upOnly){
//			//����
//			for(int i=line+1;(i<=line+2)&&(i<10);i++){
//				anotherBlock=blocks[row][i];
//				//�ж���ɫ�Ƿ����
//				if(anotherBlock.getColor().equals(block.getColor())){
//					System.out.println(anotherBlock.getColor()+":"+anotherBlock.getX()+","+anotherBlock.getY());
//					erasableVBlocks.add(anotherBlock);
//					count++;
//				}else break;
//			}
//
//		}
//
//		//����
//		for(int i=line-1;(i>=line-2)&&(i>=0);i--){
//			anotherBlock=blocks[row][i];
//			//�ж���ɫ�Ƿ����
//			if(anotherBlock.getColor().equals(block.getColor())){
//				System.out.println(anotherBlock.getColor()+":"+anotherBlock.getX()+","+anotherBlock.getY());
//				erasableVBlocks.add(anotherBlock);
//				count++;
//			}else break;
//		}
//
//		System.out.println("����:"+count);
//		//����
//		if(count>=3){
//			erasableVBlocks.add(block);
//			return true;
//		}else{
//			erasableVBlocks.clear();
//			return false;
//		}
//
//	}
//
//
//	//���齻������Ч��
//	public static void blockTransition(Block block0,Block block1){
//
//
//		int deltX=(block1.getX()-block0.getX())*60;
//		int deltY=(block1.getY()-block0.getY())*60;
//
//		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltX:"+deltX);
//		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltY:"+deltY);
//
//		TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5),block0);
//		transition.setByX(deltX);
//		transition.setByY(deltY);
//		transition.play();
//
//		//��ʱ�������ȴ��������
//		Timer timer=new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				block0.setNotSelected();
//				block0.setIsPressed(false);
//				//block1.setNotSelected();
//				//block1.setIsPressed(false);
//
//				if(erasable(block0,false)){
//					erase();
//					fallDown(fallDownBlocks);
//				}
//
//
//			}
//		},1500);
//
//	}
//
//	//��Ҫ����ķ������list
//	public static void addBlocksToFall(int x,int y,int gap){
//		for(int i=y-1;i>=0;i--){
//			blocks[x][i].fallDownGap=gap;
//			fallDownBlocks.add(blocks[x][i]);
//
//		}
//	}
//
//	//��������Ч��
//	public static void fallDown(ArrayList<Block> fallDownBlocks){
//		for(Iterator<Block> iterator=fallDownBlocks.iterator();iterator.hasNext();){
//			Block block=iterator.next();
//			TranslateTransition transition = new TranslateTransition(Duration.seconds(1),block);
//			transition.setByX(0);
//			transition.setByY(block.fallDownGap);
//			transition.play();
//
//			//����Y���꣬����������
//			int newY=block.getY()-block.fallDownGap/60-1;
//			block.setY(newY);
//			//blocks[block.getX()][newY]=block;
//
//		}
//
//		Timer timer=new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				fallDownBlocks.clear();
//			}
//		},1000);
//	}
//}
