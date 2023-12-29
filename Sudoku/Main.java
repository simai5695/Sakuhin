// public class Main {

//     public static void main(String[] args) {
//         Controller.solve();
//     }
// }
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt. Robot;
import java.awt.event.InputEvent;
import java.awt.Dimension;
import java.awt.Toolkit;



public class Main extends Application{
    public void start(Stage stage) throws Exception {		
		stage.setResizable(false);

		Pane mainRoot = new FXMLLoader(getClass().getResource("normal.fxml")).load();
		Scene mainScene = new Scene(mainRoot, 710, 530);
		stage.setScene(mainScene);

		stage.setTitle("Number Place");
		Image icon = new Image(getClass().getResource("./icon.png").toString());
		stage.getIcons().add( icon );

		stage.show();
		
		Robot robot = new Robot();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // 画面の中央座標を計算
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;
    
		// マウスポインタを画面の中央に移動
        robot.mouseMove(centerX, centerY);
		
	}
    public static void main(String[] args) {
		launch(args);
		
	}
}
