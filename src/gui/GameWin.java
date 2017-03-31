package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameWin extends Stage{

	Parent root;
	
	
	public GameWin() {
		// TODO Auto-generated constructor stub
		try {
			this.initStyle(StageStyle.TRANSPARENT);
			
			root=FXMLLoader.load(getClass().getResource("GameWin.fxml"));
			
			Scene scene=new Scene(root, 1200, 800);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
			
			
			//不能使用这种语法，因为会new 一个新的controllor,而controllor不是这样用的
			//new GameWinControllor().createBlocks();
			
			//用lookup来定位控件
			Button exitBtn=(Button) root.lookup("#exitBtn");
			exitBtn.setOnAction(e->{
				this.close();
			});
			
			this.setScene(scene);
			this.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
