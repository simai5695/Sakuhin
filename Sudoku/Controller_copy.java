import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;

public class Controller_copy{
    private static Solver solver;

    @FXML
    private Pane identity;
    @FXML
    private Label pane00;
    @FXML
    private Button delButton;
    @FXML
    private Button clearButton;
    @FXML
    private Pane numberBoard;
    @FXML
    private Label nowNum;
    @FXML
    private Label g00, g01, g02, g10, g11, g12, g20, g21, g22;

    private int[][] board = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private int[][] copy = board;
    private Label[][] numGrid;


    @FXML
    private void initialize(){
        this.numGrid = new Label[][]{{g00, g01, g02},
            {g10, g11, g12},
            {g20, g21, g22}};
    }

    public Pane getIdentity(){
        return this.identity;
    }
    @FXML
    private void solve(){
        solver = new Solver(this.board);
        solver.solve();
        this.boardView(this.board);
    }
    @FXML
    public void pressAction (MouseEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        x = (x-x%40)/40;
        y = (y-y%40)/40;
        if(x < 0) x=0;
        if(x > 8) x=8;
        if(y < 0) y=0;
        if(y > 8) y=8;
        //System.out.println(x + " : " + y);
        if(this.board[y][x] == this.copy[y][x]){
            this.board[y][x] = Integer.parseInt(this.nowNum.getText());
        }
        
        // if(x < 3 && y < 3){
        //     this.numGrid[y][x].setText(this.nowNum.getText());
        // }

        // s += board[0][1] + "\r\n";
        // s += board[1][0] + "   " + board[1][1] + "\r\n";
        // s += board[2][0] + "\r\n";
        // s += board[3][0] + "\r\n" + board[4][0] + "\r\n";
        // s += board[5][0];
        this.boardView(this.board);
    } 
    public void updateCopy(MouseEvent event) {
        // 数独ボードをコピーする
        this.copy = this.board;
    }
    @FXML
    public void clickAction (MouseEvent event){
        this.pressAction(event);
        this.updateCopy(event);
    }

    public void click1 (MouseEvent event){
        this.nowNum.setText("1");
    }
    public void click2 (MouseEvent event){
        this.nowNum.setText("2");
    }
    public void click3 (MouseEvent event){
        this.nowNum.setText("3");
    }
    public void click4 (MouseEvent event){
        this.nowNum.setText("4");
    }
    public void click5 (MouseEvent event){
        this.nowNum.setText("5");
    }
    public void click6 (MouseEvent event){
        this.nowNum.setText("6");
    }
    public void click7 (MouseEvent event){
        this.nowNum.setText("7");
    }
    public void click8 (MouseEvent event){
        this.nowNum.setText("8");
    }
    public void click9 (MouseEvent event){
        this.nowNum.setText("9");
    }
    public void clickDel (MouseEvent event){
        this.nowNum.setText("0");
    }
    @FXML
    public void clearBoard(MouseEvent event){
        for (int i = 0; i < this.board.length; i++){
            for(int j = 0;j < this.board[i].length; j++){
                this.board[i][j] = 0;
            }
        }
        this.boardView(this.board);
    }
    public void boardView(int[][] board){
        String s = "";
        for(int i = 0; i < 9; i++){
            s += String.format("  %d   %d   %d   %d    %d   %d   %d   %d   %d\r\n", board[i][0], board[i][1], board[i][2], board[i][3], board[i][4], board[i][5], board[i][6], board[i][7], board[i][8]);
        }
        s = s.replace("0", "  ");
        this.pane00.setText(s);
    }
}
