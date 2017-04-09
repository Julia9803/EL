package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
	



		//´ò¿ª»¶Ó­´°¿Ú
		Platform.runLater(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					new WelcomeWin().start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		//MessageWin msg=new MessageWin();
		
		//LevelWin level=new LevelWin();
		
		GameWin game=new GameWin();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}



