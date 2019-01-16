



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.Actions;
import Controller.Sqlite;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import View.TopPanel;

/**
 *
 * @author brcm00
 */
public class JavaKnihovna
        extends Application
{
    
    TopPanel topPanel = new TopPanel();
    Actions actionPanel = new Actions();
    VBox border = new VBox();
    
    @Override
    public void start(Stage primaryStage) {
        
        Sqlite db = new Sqlite();
        
        border.getChildren().addAll(topPanel.getTopPanel(), topPanel.getActions()); 
        
        Scene scene = new Scene(border);
        scene.getStylesheets().add
            (JavaKnihovna.class.getResource("style.css").toExternalForm());
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Knihovna");
        primaryStage.show();
        
    }
               
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
