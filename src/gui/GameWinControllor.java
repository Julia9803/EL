package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
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
					BlockManager.twoBlocks.add(btn);//加入到两个两块的list
					System.out.println(btn.getColor()+":"+btn.getX()+","+btn.getY());
				}else {
					btn.setIsPressed(false);
					btn.setNotSelected();
					BlockManager.twoBlocks.remove(btn);
				}
				if(BlockManager.twoBlocks.size()==2){
					BlockManager.exchange(blockGridPan);
					//refresh();
				}
			});
			String key=x+","+y;
			System.out.println(key);
			BlockManager.blocks[x][y]=btn;


			//BlockManagerOld.blockHashMap.replace()

			//BlockManagerOld.blocks.add(btn);
			BlockManager.setBlockBacdgroundColor(btn);
			blockGridPan.add(btn, x, y);
		}

		//BlockManagerOld.blocks.toArray(BlockManagerOld.blocksList);
	}



	public  void refresh(){
		blockGridPan.getChildren().removeAll();
		for(Block block[] : BlockManager.blocks){
			for(Block block1 : block){
				//blockGridPan
				if(block1.getErased()){
					BlockManager.setBlockBacdgroundColor(block1);
					block1.setErased(false);
				}
				blockGridPan.add(block1, block1.getX(),block1.getY());
			}
		}
	}

//	public  void addBlockToGridPane(Block block){
//
//	}
}
