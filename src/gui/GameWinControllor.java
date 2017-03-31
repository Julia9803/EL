package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class GameWinControllor {

	@FXML private GridPane blockGridPan;
	@FXML private AnchorPane root;
	
	@FXML void initialize(){
		createBlocks();
		BlockManager.blocks.clear();
	}
	
//	@FXML void onExitBtnClick(){
//		//root.setVisible(false);
//		
//	}
	
	public  void createBlocks(){
		blockGridPan.setPrefWidth(600);
		blockGridPan.setPrefHeight(600);
		blockGridPan.setVgap(10);
		blockGridPan.setHgap(10);
		
		int x,y;
		
		for(int i=0;i<100;i++){
//			Button btn=new Button();
//			btn.setMinSize(60, 60);
//			btn.setStyle(
//					"-fx-background-image:url(img/exit_hover.png) ;-fx-background-color: transparent ;"
//					);
			//btn.setBackground(new Background(new BackgroundImage(new Image("img\\star\\1.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			
			x=i%10;
			y=i/10;
			
			Block btn=new Block(x,y);
			BlockManager.blocks.add(btn);
			BlockManager.setBlockBacdgroundColor(btn);
			
			
			blockGridPan.add(btn, x, y);			
		}
	}
}
