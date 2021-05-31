/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package NUM;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Stage3Controller  implements Initializable {

    public Button generate_Button,clear_Button;
    public TextField inner;
    public TextArea outer;
    public Label main , out;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generate_Button.setOnAction(event -> {
            if(inner.getText().equals("")){
                out.setText("Enter something");
            } else{
                try {
                    outer.setText(derive(inner.getText()));
                    out.setText("Done!");
                }catch (Exception e){
                    out.setText("I Can't do that");
                }
            }
        });
        clear_Button.setOnAction(event -> {
            outer.clear();
            inner.clear();
            out.setText("");
        });
    }
    private String derive(String expression){
        expression=expression.replaceAll(" ","");
        String []parts=expression.split("[+-]");
        String []signs=expression.replaceAll("[\\d\\w^*.]","").split("");
        expression="";
        for(int i=0;i<parts.length;i++) {
            if (parts[i].matches("\\d+|")){
                expression += "";
                continue;
            }
            else if(parts[i].matches("x")) {
                expression+="1";
            }
            else if(parts[i].matches("\\d+\\*x|^\\d*\\.\\d*\\*x")){
                expression+=parts[i].replaceAll("\\*x","");
            }
            else if(parts[i].matches("\\d+x")){
                expression+=parts[i].replaceAll("x","");
            }
            else if(parts[i].contains("^")){

                if(parts[i].matches("^x.*"))
                    parts[i]="1*"+parts[i];

                if(parts[i].matches("\\d+x.*"))
                    parts[i]=parts[i].replaceAll("(\\d+)(x.*)","$1*$2");

                String[]numbers=parts[i].replaceAll("x\\^","").split("\\*");
                expression+=Double.parseDouble(numbers[0])*Double.parseDouble(numbers[1]);
                expression+=parts[i].replaceAll("^\\d*\\.\\d*|^\\d+","").replaceAll("\\d+$",String.valueOf(Integer.parseInt(numbers[1])-1));
            }
            if(i!=parts.length-1)
                expression +=signs[i];
        }
        expression=expression.replaceAll("\\.0","");
        if(expression.matches(".*\\W$"))
            return expression.replaceAll("\\W$","");
        return expression;
    }
}
