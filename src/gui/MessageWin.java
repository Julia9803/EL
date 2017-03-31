package gui;



import org.omg.CORBA.TIMEOUT;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MessageWin extends Stage{

	AnchorPane root;
	
	
	public MessageWin() {
		// TODO Auto-generated constructor stub
		root=new AnchorPane();
		Scene scene=new Scene(root, 1200, 800);
		scene.setFill(Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("messagewin.css").toExternalForm());
		//scene.setFill(Color.GREEN);
		
		
		circleAnimation();
		
		
		
		this.initStyle(StageStyle.TRANSPARENT);
		this.setScene(scene);
		this.show();
		
		
	}
	
	public void circleAnimation(){
		
		int x=600;
		int y=530;
		
		Circle circle=new Circle(x, y, 20);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
		circle.setStrokeWidth(5);
		circle.setCursor(Cursor.HAND);
		
		//点击圆形关闭消息窗口
		circle.setOnMouseClicked(e->{
			this.close();
		});
				
//		ImageView circle2=new ImageView(new Image("img\\circle2.png"));
//		circle2.setLayoutX(x);
//		circle2.setLayoutY(y);
		
		root.getChildren().add(circle);
		
		//缩放动画
		ScaleTransition st=new  ScaleTransition(Duration.millis(2000),circle);		
		st.setByX(0.4);
		st.setByY(0.4);	
		st.setCycleCount(Timeline.INDEFINITE);
		st.play();
		
		FadeTransition ft=new FadeTransition(Duration.millis(2000), circle);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setFromValue(1);
		ft.setToValue(0.1);
		ft.play();
		
	}
}
