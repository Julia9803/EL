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
			x=i%10;
			y=i/10;
			Block btn=new Block(x,y);
			btn.getStyleClass().add("block");
			btn.setOnMouseClicked(e->{
				if(btn.getIsPressed()==false){
					btn.setIsPressed(true);
					btn.setSelected();
					BlockManager.addBlocksToList(btn);//加入到两个两块的list
					System.out.println(btn.getColor()+":"+btn.getX()+","+btn.getY());
				}
				if(BlockManager.twoBlocks.size()==2){
					BlockManager.exchange();
				}
			});
			String key=x+","+y;
			System.out.println(key);
			BlockManager.blocks[x][y]=btn;


			//BlockManager.blockHashMap.replace()

			//BlockManager.blocks.add(btn);
			BlockManager.setBlockBacdgroundColor(btn);
			blockGridPan.add(btn, x, y);
		}

		//BlockManager.blocks.toArray(BlockManager.blocksList);
	}
}
