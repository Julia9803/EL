package gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.annotation.Resources;
import java.io.IOException;
import java.net.URL;

public class MainWinControllor {


	public static void changeScene(AnchorPane root, URL url){
		try {
			root= FXMLLoader.load(url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	private void onExitBtnClick(){
		System.exit(0);
	}
	
	@FXML
	private void onStartBtnClick(){

		Platform.runLater(()->{
			LevelWin levelWin=new LevelWin();
		});


	}


}
