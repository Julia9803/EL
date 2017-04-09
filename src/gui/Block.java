package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

public class Block extends Button{

	//private int[] position=new int[2];
	private int x;
	private  int y;//存储行列信息
	private String color="red";
	private boolean isPressed=false;//记录方块是否被按下

	public Block(int x,int y) {
		// TODO Auto-generated constructor stub
		super();
		this.setMinSize(50, 50);
		this.x=x;
		this.y=y;
	}
	
	public void setBackgroundColor(String color){
		this.color=color;
		//通过使用css设置背景颜色
		this.setStyle("-fx-background-color:"+this.color+";");
	}
	//方块被选中后产生的视觉效果
	public void setSelected() {
		this.setStyle("-fx-background-color:"+this.color+";"
				+"-fx-effect: dropshadow(gaussian,rgba(255,255,255,1),8,0.8,0,0) ;");
	}
	public void setNotSelected(){
		this.setStyle("-fx-background-color:"+this.color+";");
	}
	
	
	//color的get set
	public String getColor() {return this.color;}
	public void setColor(String color){this.color=color;}
	
	//x get set
	public void setX(int x){
		this.x=x;
	}
	public int getX(){
		return  this.x;
	}

	//y get set
	public void setY(int y){
		this.y=y;
	}
	public int getY(){
		return  this.y;
	}

	public void setPosition(int x,int y){
		this.x=x;
		this.y=y;
	}

	public void setIsPressed(boolean value){
		this.isPressed=value;
	}
	public boolean getIsPressed(){
		return  this.isPressed;
	}

}
