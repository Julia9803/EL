package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWinControllor {

	@FXML
	private void onExitBtnClick(){
		System.exit(0);
	}
	
	@FXML
	private void onStartBtnClick(){
		Platform.runLater(()->{
			LevelWin level1=new LevelWin();
		});
	}
	
	
}
