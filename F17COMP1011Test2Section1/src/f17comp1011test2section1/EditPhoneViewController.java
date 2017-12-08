/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f17comp1011test2section1;

import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class EditPhoneViewController implements Initializable {

    @FXML    private TextField manufacutureTextField;
    @FXML    private TextField modelTextField;
    @FXML    private TextField memoryTextField;
    @FXML    private TextField colourTextField;
    @FXML    private TextField screenSizeTextField;
    @FXML    private Spinner<Integer> phoneIDSpinner;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
    int startvaluespinner,endvaluespinner;
    public void loadPhoneInfo() throws SQLException
    {
                 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comp1011Test2", "student", "student");
                 statement = conn.createStatement();
                 resultSet = statement.executeQuery("SELECT * FROM phones Where phoneId = " + phoneIDSpinner.getValue().toString());
                 resultSet.next();
                 manufacutureTextField.setText(resultSet.getString("model"));
                 modelTextField.setText(String.valueOf(resultSet.getInt("memory")));
                 memoryTextField.setText(resultSet.getString("colour"));
                 colourTextField.setText(String.valueOf(resultSet.getDouble("screenSize")));
                 screenSizeTextField.setText(String.valueOf(resultSet.getDouble("cameraRes")));
             manufacutureTextField.setDisable(false);
        modelTextField.setDisable(false);
        memoryTextField.setDisable(false);
        colourTextField.setDisable(false);
        screenSizeTextField.setDisable(false);
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manufacutureTextField.setDisable(true);
        modelTextField.setDisable(true);
        memoryTextField.setDisable(true);
        colourTextField.setDisable(true);
        screenSizeTextField.setDisable(true);
         try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comp1011Test2", "root", "");
            //2.  create a statement object
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM phones");
                 resultSet.next();
                       startvaluespinner=Integer.parseInt(resultSet.getString("phoneID"));
                 
                 resultSet.last();
                 endvaluespinner=Integer.parseInt(resultSet.getString("phoneID"));
                 resultSet=null;
         }catch(Exception ex)
         {
             System.err.println(ex.toString());
         }finally
        {
            try {
                if (conn != null)
                    conn.close();
                if(statement != null)
                    statement.close();
                if(resultSet != null)
                    resultSet.close();
            } catch (SQLException ex) {
          System.err.println(ex.toString());    
            }
        }
        SpinnerValueFactory<Integer> phoneIDValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(startvaluespinner,endvaluespinner);
        phoneIDSpinner.setValueFactory(phoneIDValueFactory);
        phoneIDSpinner.setEditable(false);
    }    
    
}
