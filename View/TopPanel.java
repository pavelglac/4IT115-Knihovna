/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Actions;
import Controller.Role;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author pavel
 */
public class TopPanel {
    
    private Role role = new Role();
    private Actions action = new Actions();
    BorderPane actionPanel = new BorderPane();
    
    VKniha vKniha = new VKniha();
    VVypujcka vVypujcka = new VVypujcka();
    VDatabaze vDatabaze = new VDatabaze();
    VZamereni vZamereni = new VZamereni();
    VZakaznik vZakaznik = new VZakaznik();
    
    private Button knihy = new Button("Knihy");
    private Button zakaznik = new Button("Zákazník");
    
        Button kniha = new Button("Kniha");                        
        Button zamereni = new Button("Zaměřeni");
        Button databaze = new Button("Databáze");
        Button vypujcka = new Button("Výpůjčka");
        
//        Button toDo = new Button("Dodělat");
//        Button toDo2 = new Button("Dodělat");
//        Button toDo3 = new Button("Dodělat");
        
    BorderPane topPanel = new BorderPane();
    
    public BorderPane getTopPanel()
    {
        topPanel.setTop(getRolePanel());
        topPanel.setBottom(getActionPanel());        
        return topPanel;
        
    }
    
    
    private HBox getRolePanel() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 12, 0, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setPrefHeight(20);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.setStyle("-fx-background-color: #858bac;");

        knihy.setPrefSize(100, 20);
        knihy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                role.setRole(1);
                action.setAction(1);
                updateActionPanel();
                setcss();
                update();
            }
        });
        
        zakaznik.setPrefSize(100, 20);
        zakaznik.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                role.setRole(2);
                action.setAction(5);
                updateActionPanel();
                setcss();
                update();
            }
        });
        
        hbox.getChildren().addAll(knihy, zakaznik);
        setcss();
        
        return hbox;
    }
        
    private HBox getActionPanel()
    {
        HBox actionPanel = new HBox();
        actionPanel.setPadding(new Insets(15, 12, 15, 12));
        actionPanel.setAlignment(Pos.TOP_RIGHT);
        actionPanel.setSpacing(10);   // Gap between nodes
        actionPanel.setMinHeight(55);
        actionPanel.setMaxHeight(55);
        actionPanel.setStyle("-fx-background-color: #bec2d7;");
        switch(role.getRole())
        {

            case 1 :
                    kniha.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            action.setAction(1);
                            updateActionPanel();
                            setcss();
                        }
                    });
                    
                    zamereni.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            action.setAction(2);
                            updateActionPanel();
                            setcss();
                        }
                    });
                
                    databaze.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            action.setAction(3);
                            updateActionPanel();
                            setcss();
                        }
                    });
                    
                    vypujcka.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            action.setAction(4);
                            updateActionPanel();
                            setcss();
                        }
                    });
                    actionPanel.getChildren().addAll(kniha, zamereni, vypujcka);
                    break;               

//            case 2 :
//
//                    actionPanel.getChildren().addAll(toDo, toDo2, toDo3);
//                    break; 

        
        }
        return actionPanel;
    }
    
    private void update()
    {
    
        topPanel.setTop(getRolePanel());
        topPanel.setBottom(getActionPanel());   
        
    }
    
    private void setcss()
    {

        switch(role.getRole())
        {

            case 1 :
                    knihy.setId("roleActive");
                    zakaznik.setId("roleNonActive");
                    break;               

            case 2 :

                    knihy.setId("roleNonActive");
                    zakaznik.setId("roleActive");
                    break; 

        
        }
        
        switch(action.getAction())
        {

            case 1 :
                    kniha.setId("actionActive");
                    zamereni.setId("actionNonActive");
                    databaze.setId("actionNonActive");
                    vypujcka.setId("actionNonActive");                   
                    break;               

            case 2 :
                    kniha.setId("actionNonActive");
                    zamereni.setId("actionActive");
                    databaze.setId("actionNonActive");
                    vypujcka.setId("actionNonActive");  
                    break; 

             case 3 :
                    kniha.setId("actionNonActive");
                    zamereni.setId("actionNonActive");
                    databaze.setId("actionActive");
                    vypujcka.setId("actionNonActive");  
                    break;  
                    
            case 4 :
                    kniha.setId("actionNonActive");
                    zamereni.setId("actionNonActive");
                    databaze.setId("actionNonActive");
                    vypujcka.setId("actionActive");  
                    break;                     
        }
    }
    
    public BorderPane getActions()
    {
    
        updateActionPanel();        
        return actionPanel;
        
    }
    
    public void updateActionPanel()
    {
    
        switch(action.getAction())
        {

            case 1 :
                    actionPanel.setTop(vKniha.getVKniha());
                    break;
                    
            case 2 :
                    actionPanel.setTop(vZamereni.getVZamereni());
                    break;
                    
            case 3 :
                    actionPanel.setTop(vDatabaze.getVDatabaze());
                    break;         

            case 4 :
                    actionPanel.setTop(vVypujcka.getVVypujcka());
                    break;
                    
             case 5 :
                    actionPanel.setTop(vZakaznik.getVZakaznik());
                    break; 
                    
            default : 
                    actionPanel.setTop(vVypujcka.getVVypujcka());
                    break; 

        
        }
               
    }
    
}
