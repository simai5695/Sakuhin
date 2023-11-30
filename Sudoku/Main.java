// public class Main {

//     public static void main(String[] args) {
//         Controller.solve();
//     }
// }
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	}
    public static void main(String[] args) {
		launch(args);
	}
}
