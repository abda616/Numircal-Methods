/*
 * Copyright (c) 2021.
 * ABDEL RAHMAN ALHERBAWi.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * All Rights reserved.
 */
package NUM;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static NUM.methods.*;
public class out_Controller implements Initializable{
    @FXML private Pane pane;
    @FXML private SplitPane splitPane;
    @FXML private AnchorPane out1;
    @FXML private ToggleGroup tG;
    @FXML public Label lab_F_x, lab_numOfIt, lab_G_x, lab_Period, lab_digitsAfter,lab_outPut;
    @FXML public Button run_button, clear , find_numIteration, find_error ,generate_fx,generate_gra;
    @FXML public TextField F_x,A,B,Eps,G_x,num_of_itr;
    @FXML public TextField d_after ;
    @FXML public RadioButton RB1, RB2, RB3 ,RB4 ,RB5 ;
    @FXML public TableView<Calculated> table;
    @FXML public TableColumn<Calculated, SimpleDoubleProperty> iteration, x_lower, x_upper,root, f_lower, f_upper, f_root, fLower_fUpper, error;

    public ObservableList<Calculated> Data = FXCollections.observableArrayList();
    public Stage stage = new Stage();
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        tG = new ToggleGroup();
        this.RB1.setToggleGroup(tG);
        this.RB2.setToggleGroup(tG);
        this.RB3.setToggleGroup(tG);
        this.RB4.setToggleGroup(tG);
        this.RB5.setToggleGroup(tG);

        iteration.setCellValueFactory(new PropertyValueFactory<>("iteration"));
        x_lower.setCellValueFactory(new PropertyValueFactory<>("x_lower"));
        x_upper.setCellValueFactory(new PropertyValueFactory<>("x_upper"));
        root.setCellValueFactory(new PropertyValueFactory<>("root"));
        f_lower.setCellValueFactory(new PropertyValueFactory<>("f_lower"));
        f_upper.setCellValueFactory(new PropertyValueFactory<>("f_upper"));
        f_root.setCellValueFactory(new PropertyValueFactory<>("f_root"));
        fLower_fUpper.setCellValueFactory(new PropertyValueFactory<>("fLower_fUpper"));
        error.setCellValueFactory(new PropertyValueFactory<>("error"));

        clear.setOnAction(event -> {
            F_x.clear();
            G_x.clear();
            num_of_itr.clear();
            Eps.clear();
            A.clear();
            B.clear();
            Data.clear();
            d_after.setText("5");
        });
        find_error.setOnAction(event -> {
            d();
            try {
                AnchorPane anchorPane  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stage1.fxml")));
                stage.setTitle("FIND ERROR");
                stage.setScene(new Scene(anchorPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        find_numIteration.setOnAction(event -> {

            try {
                AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stage2.fxml")));
                stage.setTitle("FIND NUMBER OF ITERATIONS");
                stage.setScene(new Scene(anchorPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        generate_fx.setOnAction(event -> {
            try {
                AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stage3.fxml")));
                stage.setTitle("Generate Fx");
                stage.setScene(new Scene(anchorPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        generate_gra.setOnAction(event -> {
            try {
                AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chart.fxml")));
                stage.setTitle("Generate Graph");
                stage.setScene(new Scene(anchorPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        run_button.setOnAction(event -> {
            d();
            if (number_of_clicked > 0) Data.clear();
            //////
            boolean flag;
            flag = check_field();
            if (flag) {
                if (RB1.isSelected()||RB2.isSelected()) {
                    int max_length = valOfI(num_of_itr.getText());
                    ArrayList<Integer> num = new ArrayList<>();
                    ArrayList<Double> arr_root = new ArrayList<>();
                    ArrayList<Double> upper = new ArrayList<>();
                    ArrayList<Double> lower = new ArrayList<>();
                    ArrayList<Double> ero_arr = new ArrayList<>();
                    ero_arr.add(100.0);
                    if (RB1.isSelected()) {
                        lab_outPut.setText(methods.Bic_met(F_x.getText(), valOfD(A.getText()), valOfD(B.getText()),
                                max_length, valOfD(Eps.getText()), arr_root, ero_arr, num, upper, lower));
                    }
                    if (RB2.isSelected()) {
                        lab_outPut.setText(methods.false_met(F_x.getText(), valOfD(A.getText()), valOfD(B.getText()),
                                max_length, valOfD(Eps.getText()), arr_root, ero_arr, num, upper, lower));
                    }
                    Show_data(num, arr_root, lower, upper, ero_arr);
                    table.setItems(Data);
                }
            } else {
                lab_outPut.setText("Error  incorrect Entering");
                lab_outPut.setVisible(true);
            }

            flag = check_field(G_x.getText());
            if (flag) {
                if (RB3.isSelected()||RB4.isSelected()) {
                    int max_length = valOfI(num_of_itr.getText());
                    ArrayList<Integer> num = new ArrayList<>();
                    ArrayList<Double> arr_root = new ArrayList<>();
                    ArrayList<Double> ero_arr = new ArrayList<>();
                    ArrayList<Double> cur_root = new ArrayList<>();
                    ero_arr.add(100.0);
                    if (RB3.isSelected()){
                        methods.Fixed_point_met(F_x.getText(), G_x.getText(), valOfD(A.getText()), valOfD(Eps.getText()), max_length, num, arr_root, ero_arr);
                        Show_data(num, arr_root, ero_arr);
                    }
                    if (RB4.isSelected()) {
                        methods.Newton_met(F_x.getText(), G_x.getText(), valOfD(A.getText()), valOfD(Eps.getText()), max_length, num, cur_root, arr_root, ero_arr);
                        Show_data(num,cur_root,arr_root, ero_arr);
                    }
                    lab_outPut.setText("Done!!! ...");
                    table.setItems(Data);

                } else {
                    lab_outPut.setText("Error incorrect Entering or G_x not Entered");
                    lab_outPut.setVisible(true);
                }
            }

            flag = check_field();
            if (flag) {
                if (RB5.isSelected()) {
                    int max_length = valOfI(num_of_itr.getText());
                    ArrayList<Integer> num = new ArrayList<>();
                    ArrayList<Double> iCurr = new ArrayList<>();
                    ArrayList<Double> iLast = new ArrayList<>();
                    ArrayList<Double> iNew = new ArrayList<>();
                    ArrayList<Double> ero_arr = new ArrayList<>();
                    ero_arr.add(100.0);
                    try {
                        lab_outPut.setText(methods.Secant_met(F_x.getText(),valOfD(A.getText()),valOfD(B.getText()),valOfD(Eps.getText()),max_length,num,iCurr,iLast,iNew,ero_arr));
                        Show_dataS(num, iLast, iCurr, iNew, ero_arr);
                        table.setItems(Data);
                    }catch (Exception e){
                        lab_outPut.setText("Divergence");
                    }
                }
            } else {
                lab_outPut.setText("Error  incorrect Entering");
                lab_outPut.setVisible(true);
            }

            num_of_it = 0;
            number_of_clicked++;
        });
    }
    int number_of_clicked = 0;
    protected void d(){ dic = valOfI( d_after.getText() ); }
    /***/
    private boolean check_field() {
        return !F_x.getText().equals("") && !F_x.getText().equals(" ")
                && !A.getText().equals("") && !A.getText().equals("start")
                && !B.getText().equals("") && !B.getText().equals("end")
                && !Eps.getText().equals("") && !Eps.getText().equals("EPSILON")
                && !num_of_itr.getText().equals("") && !num_of_itr.getText().equals("num");
    }
    private boolean check_field(String h) {
        return !F_x.getText().equals("") && !F_x.getText().equals(" ")
                && !A.getText().equals("") && !A.getText().equals("start")
                && !Eps.getText().equals("") && !Eps.getText().equals("EPSILON")
                && !num_of_itr.getText().equals("") && !num_of_itr.getText().equals("num")
                && !h.equals("") && !h.equals("G_x");
    }

    private void Show_data( ArrayList<Integer> num, ArrayList<Double> arr_root, ArrayList<Double> lower, ArrayList<Double> upper, ArrayList<Double> ero_arr) {
        for (int i = 0; i < num.size(); i++) {
                Data.add(new Calculated( (num.get(i)), (lower.get(i)), (upper.get(i)), (arr_root.get(i)),
                        (fx(F_x.getText(), lower.get(i))), (fx(F_x.getText(), upper.get(i))), (fx(F_x.getText(), arr_root.get(i))),
                        (fx(F_x.getText(), lower.get(i)) * fx(F_x.getText(), upper.get(i))), (ero_arr.get(i) ) ) );
        }
    }
    private void Show_dataS( ArrayList<Integer> num, ArrayList<Double> Last, ArrayList<Double> Cur , ArrayList<Double> New, ArrayList<Double> ero_arr) {
        for (int i = 0; i < num.size(); i++) {
            Data.add(new Calculated( num.get(i),Last.get(i),Cur.get(i),New.get(i),
                    fx(F_x.getText(),Last.get(i)),fx(F_x.getText(),Cur.get(i)),fx(F_x.getText(),New.get(i)),
                    ero_arr.get(i)  )  );
        }
    }
    private void Show_data( ArrayList<Integer> num, ArrayList<Double> arr_root, ArrayList<Double> ero_arr) {
        for (int i = 0; i < num.size(); i++)
            Data.add(new Calculated((num.get(i)), (arr_root.get(i)), (fx(F_x.getText(), arr_root.get(i))), (ero_arr.get(i))));

    }
    private void Show_data( ArrayList<Integer> num, ArrayList<Double> cur_root,ArrayList<Double> arr_root,ArrayList<Double> ero_arr ) {
        for (int i = 0; i < num.size(); i++)
            Data.add(new Calculated( num.get(i), cur_root.get(i) ,fx(F_x.getText(),cur_root.get(i)), fx(G_x.getText(),cur_root.get(i)) , arr_root.get(i),ero_arr.get(i) ) );
    }
    /**
     * This method will update the radioButtonLabel when ever a different
     * radio button is clicked */
    @FXML
    private void radioButtonChanged() {
        if (this.tG.getSelectedToggle().equals(this.RB1)) {
            setVisible(true, false, false, true, true, true, true, true);
            setRadio("Period", 55, 82,"F(X)-LOWER","F(X)-UPPER","F(X)-ROOT", "X-Lower", "X-Upper", "X-ROOT");
        }
        else if (this.tG.getSelectedToggle().equals(this.RB2)) {
            setVisible(true, false, false, true, true, true, true, true);
            setRadio("Period", 55, 82,"F(X)-LOWER","F(X)-UPPER","F(X)-ROOT", "X-Lower", "X-Upper", "X-ROOT");
        }
        else if (this.tG.getSelectedToggle().equals(this.RB3)) {
            setVisible(false, true, true, false, false, false, false, false);
            setRadio("Initial value", 80, 150,"F(X)-LOWER","F(X)-UPPER","F(X)-ROOT", "X-Lower", "X-Upper", "X-ROOT");
        }
        else if (this.tG.getSelectedToggle().equals(this.RB4)) {
            setVisible(false, true, true, false, false, true, true, false);
            setRadio("Initial value", 80, 150,"F(x)","F'(x)","New", "X-Lower", "X-Upper", "X-ROOT");
        }
        else if (this.tG.getSelectedToggle().equals(this.RB5)) {
            setVisible(true, false, false, true, true, true, true, false);
            setRadio("InitialPoint", 55, 82,"F(X)-Last","F(X)-Current","F(X)-New I", "Last-I", "Current-I", "New-I");
        }

    }
    private void setVisible(boolean B, boolean lab_G_x, boolean G_x, boolean x_lower,
                            boolean x_upper, boolean f_lower, boolean f_upper, boolean fLower_fUpper) {
        this.B.setVisible(B);
        this.lab_G_x.setVisible(lab_G_x);
        this.G_x.setVisible(G_x);
        this.lab_outPut.setVisible(true);
        this.x_lower.setVisible(x_lower);
        this.x_upper.setVisible(x_upper);
        this.f_lower.setVisible(f_lower);
        this.f_upper.setVisible(f_upper);
        this.f_root.setVisible(true);
        this.fLower_fUpper.setVisible(fLower_fUpper);
        this.run_button.setVisible(true);
    }
    private void setRadio(String lab_PStr, int i, int i2,
                          String TC_lStr , String TCU_Str , String TCR_Str,
                          String lower , String upper , String root){
        lab_Period.setText(lab_PStr);
        lab_Period.setPrefWidth(i);
        A.setLayoutX(i2);
        f_lower.setText(TC_lStr);
        f_upper.setText(TCU_Str);
        f_root.setText(TCR_Str);
        x_lower.setText(lower);
        x_upper.setText(upper);
        this.root.setText(root);
    }
    public static int valOfI(String str) { return Integer.parseInt(str); }
    public static double valOfD(String str) { return Double.parseDouble(str); }
}