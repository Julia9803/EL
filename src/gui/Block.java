package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

public class Block extends Button{

	private int[] position=new int[2];//存储行列信息
	
	private String color="red";
	public Block(int x,int y) {
		// TODO Auto-generated constructor stub
		super();
		this.setMinSize(50, 50);
		this.position[0]=x;
		this.position[1]=y;
	}
	
	public void setBackgroundColor(String color){
		this.color=color;
		//通过使用css设置背景颜色
		this.setStyle("-fx-background-color:"+this.color+";");
	}
	
	public void setSelected() {
		this.setStyle("-fx-background-color:"+this.color+";"
				+"-fx-effect: dropshadow(gaussian,rgba(255,255,255,1),8,0.8,0,0) ;");
		//"-fx-effect: dropshadow(gaussian,rgba(255,255,255,0.8),8,0.8,0,0) ;"
		/*"-fx-border-color: rgba(255,255,255,0.8);"
				+ "-fx-border-width: 5;"
				+ "-fx-border-style: dotted ;")
				*/
	}
	
	
	//color的get set
	public String getColor() {return this.color;}
	public void setColor(String color){this.color=color;}
	
	//position get set
	public int[] getPosition(){
		return position;
	}
	public void setPosition(int[] position) {
		this.position=position;
	}
}
