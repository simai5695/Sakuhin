import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Arrays;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
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
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;

public class Controller {
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
    private Label messageLabel;
    @FXML
    private Background back = null;
    @FXML
    private MenuButton menuBar;
    @FXML
    private Pane colorPane;
    @FXML
    private Label g00, g01, g02, g03, g04, g05, g06, g07, g08,
            g10, g11, g12, g13, g14, g15, g16, g17, g18,
            g20, g21, g22, g23, g24, g25, g26, g27, g28,
            g30, g31, g32, g33, g34, g35, g36, g37, g38,
            g40, g41, g42, g43, g44, g45, g46, g47, g48,
            g50, g51, g52, g53, g54, g55, g56, g57, g58,
            g60, g61, g62, g63, g64, g65, g66, g67, g68,
            g70, g71, g72, g73, g74, g75, g76, g77, g78,
            g80, g81, g82, g83, g84, g85, g86, g87, g88;
    // 変更点
    private Label[][] labels;
    @FXML
    private Button bg0, bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, white;

    private Button[] backs;

    private boolean selectBox = false;

    private boolean crossF = false;

    private int currentNum;

    private int[][] board = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    private int[][] boxes = { // 0～8でボックス
            { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
            { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
            { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
            { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
            { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
            { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
            { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
            { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
            { 6, 6, 6, 7, 7, 7, 8, 8, 8 }
    };

    @FXML
    public void initialize() {
        labels = new Label[][] {
                { g00, g01, g02, g03, g04, g05, g06, g07, g08 },
                { g10, g11, g12, g13, g14, g15, g16, g17, g18 },
                { g20, g21, g22, g23, g24, g25, g26, g27, g28 },
                { g30, g31, g32, g33, g34, g35, g36, g37, g38 },
                { g40, g41, g42, g43, g44, g45, g46, g47, g48 },
                { g50, g51, g52, g53, g54, g55, g56, g57, g58 },
                { g60, g61, g62, g63, g64, g65, g66, g67, g68 },
                { g70, g71, g72, g73, g74, g75, g76, g77, g78 },
                { g80, g81, g82, g83, g84, g85, g86, g87, g88 }
        };

        backs = new Button[] { bg0, bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, white };
        
        // for (int y = 0; y < 9; y++) {
        //    for (int x = 0; x < 9; x++) {
        //         labels[y][x].textProperty().bind(new SimpleStringProperty(String.valueOf(boxes[y][x])));
        //     }
        // }
        
        this.boardView();

    }


    public Pane getIdentity() {
        return this.identity;
    }

    public void update(){
        this.boardView();
    }

    @FXML
    private void solve() {
        // ナンプレを解く
        solver = new Solver(this.board, this.boxes, this.crossF);
        this.messageLabel.setText("解がありました!");
        if (solver.solve() == false) {
            this.messageLabel.setText("解がありません。入力を確認してください。");
        } else { // 変更点
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String number = String.valueOf(this.board[i][j]);
                    // if(number == "0")
                    // number = " ";
                    this.labels[i][j].setText(number);
                }
            }
        }
        this.boardView();
    }

    @FXML
    public void pressAction(MouseEvent event) {
        // マスを押された時に対応する位置に数字を入れる
        int x = (int) event.getX();
        int y = (int) event.getY();
        x = (x - x % 40) / 40;
        y = (y - y % 40) / 40;
        if (x < 0)
            x = 0;
        if (x > 8)
            x = 8;
        if (y < 0)
            y = 0;
        if (y > 8)
            y = 8;
        // System.out.println(x + " : " + y);
        if (!selectBox) {
            this.board[y][x] = currentNum;
            if (currentNum == 0) {
                this.labels[y][x].setText("");
                labels[y][x].setFont(new Font("System Regular", 28));
                labels[y][x].setBackground(backs[this.boxes[y][x]].getBackground());
            } else {
                this.labels[y][x].setText(String.valueOf(currentNum));
                labels[y][x].setBackground(backs[this.boxes[y][x]].getBackground());
                labels[y][x].setFont(new Font("System Bold", 28));
                
            }
        } else {
            this.boxes[y][x] = this.currentNum;
            labels[y][x].setBackground(backs[this.boxes[y][x]].getBackground());

        }
        labels[y][x].setBackground(backs[this.boxes[y][x]].getBackground());

        this.boardView();
        resetMessage();
    }

    public void clickn(MouseEvent event) {
        // 入れる数字の選択
        // this.selectBox = false;
        // this.currentNum = Integer.parseInt(((Button) event.getSource()).getText());
        // this.nowNum.setText(String.valueOf(this.currentNum));
        // this.nowNum.setBackground(back);
        changeNum(Integer.parseInt(((Button) event.getSource()).getText()));
    }

    public void clickDel(MouseEvent event) {
        // 数字の消去
        // this.selectBox = false;
        // this.currentNum = 0;
        // this.nowNum.setText("Del");
        // this.nowNum.setBackground(back);
        changeNum(0);
    }
    public void keyTyped(KeyEvent event){
        for(int i = 0; i < 10; i++){
            if(Integer.parseInt(event.getCharacter()) == i){
                // if(event.isShiftDown()){
                //     changeColor(backs[i], i);
                // }else{
                changeNum(i);
                // }
            }
        }
    }

    public void changeNum(int num){
        this.selectBox = false;
        this.currentNum = num;
        if(this.currentNum == 0){
            this.nowNum.setText("Del");
        } else {
            this.nowNum.setText(String.valueOf(this.currentNum));
        }
        this.nowNum.setBackground(this.back);
    }

    public void changeBox(MouseEvent event) {
        // this.selectBox = true;
        Button b = (Button) event.getSource();
        // this.currentNum = Integer.parseInt(((Button) event.getSource()).getText());
        // this.nowNum.setBackground(b.getBackground());
        // this.nowNum.setText("");
        changeColor(b, Integer.parseInt(b.getText()));

    }

    public void white(MouseEvent event) {
        // this.selectBox = true;
        Button b = (Button) event.getSource();
        // this.currentNum = 9;
        // this.nowNum.setBackground(b.getBackground());
        // this.nowNum.setText("");
        changeColor(b, 9);
    }
    public void changeColor(Button b,int num){
        this.selectBox = true;
        this.currentNum = num;
        this.nowNum.setBackground(b.getBackground());
        this.nowNum.setText("");
    }

    @FXML
    public void boxClear(MouseEvent event) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.boxes[i][j] = 9;
            }
        }
        boardView();
    }

    @FXML
    public void clearBoard(MouseEvent event) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = 0;
            }
        }
        // 変更点
        for (Label[] row : this.labels) {
            for (Label label : row) {
                label.setText(" ");
                label.setFont(new Font("System Regular", 28));
            }
        }

        //this.boardView();
        this.resetMessage();
    }

    @FXML
    public void boardView() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                labels[y][x].setBackground(backs[this.boxes[y][x]].getBackground());
            }
        }
    }

    @FXML
    public void resetMessage() {
        // ボード下のメッセージをリセットする
        this.messageLabel.setText("入力する数字を選択した後にマスをクリックしてください");
    }

    @FXML
    public void changeNormal() {
        this.colorPane.setVisible(false);
        this.setCross(false);
        this.crossF = false;
        this.menuBar.setText("Normal");
        this.selectBox = false;
        this.currentNum = 0;
        this.nowNum.setText("Del");
        this.nowNum.setBackground(back);
        this.boxes = new int[][] {
                { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
                { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
                { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
                { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
                { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
                { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
                { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
                { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
                { 6, 6, 6, 7, 7, 7, 8, 8, 8 }
        };
        this.boardView();
    }

    @FXML
    public void changeCross() {
        this.colorPane.setVisible(false);
        this.setCross(true);
        this.crossF = true;
        this.menuBar.setText("Cross");
        this.selectBox = false;
        this.currentNum = 0;
        this.nowNum.setText("Del");
        this.nowNum.setBackground(back);
        this.boxes = new int[][] {
                { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
                { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
                { 0, 0, 0, 1, 1, 1, 2, 2, 2 },
                { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
                { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
                { 3, 3, 3, 4, 4, 4, 5, 5, 5 },
                { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
                { 6, 6, 6, 7, 7, 7, 8, 8, 8 },
                { 6, 6, 6, 7, 7, 7, 8, 8, 8 }
        };
        this.boardView();
        
    }

    @FXML
    public void changeJigsaw() {
        this.colorPane.setVisible(true);
        this.setCross(false);
        this.crossF = false;
        this.menuBar.setText("Jigsaw");
        this.selectBox = false;
        this.currentNum = 0;
        this.nowNum.setText("Del");
        this.nowNum.setBackground(back);
    }

    @FXML
    public void changeCrossJigsaw() {
        this.colorPane.setVisible(true);
        this.setCross(true);
        this.crossF = true;
        this.menuBar.setText("Cross-Jigsaw");
        this.selectBox = false;
        this.currentNum = 0;
        this.nowNum.setText("Del");
        this.nowNum.setBackground(back);
    }

    @FXML
    public void setCross(Boolean b) {
        for (int i = 0; i < 9; i++) {
            if (b) {
                labels[i][i].setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY, new BorderWidths(3))));
                labels[i][8 - i].setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY, new BorderWidths(3))));
            } else {
                labels[i][i].setBorder(null);
                labels[i][8 - i].setBorder(null);
            }
        }
    }
}
