package NUM;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import static NUM.out_Controller.valOfD;

public class Stage2Controller implements Initializable {
    @FXML public Label info,lab_S,lab_E,lab_Error,lab_out;
    @FXML public TextField S,E,Itr;
    @FXML public Button Run;
    public void initialize(URL location, ResourceBundle resources) {
        Run.setOnAction(event -> {
            try {
                lab_out.setText("Epsilon :"+num_of_iteration_method(valOfD(S.getText()),valOfD(E.getText()),valOfD(Itr.getText())));
            }catch (Exception e){
                lab_out.setText("Not allowed!!!,Error!");
            }
        });
    }
    public int num_of_iteration_method(double a , double b , double error){
        return (int) Math.ceil( (Math.log(b-a)-Math.log(error) )/ Math.log(2) );
    }
}
