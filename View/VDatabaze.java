/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sqlite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;


/**
 *
 * @author pavel
 */
public class VDatabaze {
    
    Connection c = Sqlite.ConnectDb();
    PreparedStatement pst = null;
    ResultSet rs = null;
        //Přidání zákazníka do DB
    
        BorderPane databazePanel = new BorderPane();
        private BorderPane rightSide = new BorderPane();
        private BorderPane leftSide = new BorderPane();
    
        private Label zakaznikJmenoLabel = new Label("Jméno");
        private Label zakaznikPrijmeniLabel = new Label("Přijmení");
        private Label zakaznikEmailLabel = new Label("E-mail");
        
        private TextField zakaznikJmenoField = new TextField();
        private TextField zakaznikPrijmeniField = new TextField();
        private TextField zakaznikEmailField = new TextField();
        
        private HBox zakaznikJmeno = new HBox();
        private HBox zakaznikPrijmeni = new HBox();
        private HBox zakaznikEmail = new HBox();
        private VBox searchForm = new VBox();
        
        private Button insertButton = new Button("Vložit");
        
        
    
    public BorderPane getVDatabaze()
    {            
        setRightSide();
        setLeftSide();
        return databazePanel;
    }
    
    private void setRightSide()
    {
        ImageView img = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/search-icon.png"), 102, 102, false, false));
        rightSide.setCenter(img);      
        databazePanel.setRight(rightSide);
    }
    
    private void setLeftSide()
    {
        
        int widthLabel = 80;
        
        
        ImageView img = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/form-icon.png"), 90, 102, false, false));
        //leftSide.setTop(img);
        
        
        zakaznikJmeno.getChildren().clear();
        zakaznikJmenoLabel.setPrefWidth(widthLabel);
        zakaznikJmeno.getChildren().addAll(zakaznikJmenoLabel, zakaznikJmenoField);
        
        zakaznikPrijmeni.getChildren().clear();
        zakaznikPrijmeniLabel.setPrefWidth(widthLabel);
        zakaznikPrijmeni.getChildren().addAll(zakaznikPrijmeniLabel, zakaznikPrijmeniField);
        
        zakaznikEmail.getChildren().clear();
        zakaznikEmailLabel.setPrefWidth(widthLabel);
        zakaznikEmail.getChildren().addAll(zakaznikEmailLabel, zakaznikEmailField);
        
        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sql = "INSERT INTO Zakaznik(jmeno, prijmeni, email) VALUES(?,?,?)";
                try{
                    pst = c.prepareStatement(sql);
                    pst.setString(1, zakaznikJmenoField.getText());
                    pst.setString(2, zakaznikPrijmeniField.getText());
                    pst.setString(3, zakaznikEmailField.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Do databáze byly vloženy hodnoty: " + zakaznikJmenoField.getText() + ", " 
                            + zakaznikPrijmeniField.getText() + ", " + zakaznikEmailField.getText());
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
            
        }
            }
        });
        
        
        
        
        searchForm.setAlignment(Pos.CENTER);       
        searchForm.getChildren().clear();
        searchForm.getChildren().addAll(img, zakaznikJmeno, zakaznikPrijmeni, zakaznikEmail, insertButton);
        leftSide.setCenter(searchForm);
        
        databazePanel.setLeft(leftSide);
     
    }
    
}
