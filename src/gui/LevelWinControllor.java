package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class LevelWinControllor {

	@FXML
	private void onLevelOneBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin();
		});
	}
}
