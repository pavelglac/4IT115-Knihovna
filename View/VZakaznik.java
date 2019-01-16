/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sqlite;
import Model.Zakaznik;
import com.sun.prism.impl.Disposer.Record;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author pavel
 */
public class VZakaznik {

    Connection c = Sqlite.ConnectDb();
    PreparedStatement pst = null;
    ResultSet rs = null;
    private TableView<Zakaznik> table;
    //Přidání zákazníka do DB

    HBox zakaznikPanel = new HBox();
    private BorderPane rightSide = new BorderPane();
    private VBox leftSide = new VBox();

    private Label zakaznikJmenoLabel = new Label("Jméno");
    private Label zakaznikPrijmeniLabel = new Label("Přijmení");
    private Label zakaznikEmailLabel = new Label("E-mail");

    private TextField zakaznikJmenoField = new TextField();
    private TextField zakaznikPrijmeniField = new TextField();
    private TextField zakaznikEmailField = new TextField();

    private Label zakaznikIdDeleteLabel = new Label("ID zákazníka");
    private TextField zakaznikIdDeleteField = new TextField();
    private HBox zakaznikIdDelete = new HBox();
    private Button deleteButton = new Button("Smazat");

    private HBox zakaznikJmeno = new HBox();
    private HBox zakaznikPrijmeni = new HBox();
    private HBox zakaznikEmail = new HBox();
    private VBox searchForm = new VBox();
//    private VBox deleteForm = new VBox();

    private Button insertButton = new Button("Vložit");

    private ObservableList<Zakaznik> data;
    private VBox searchFormRight = new VBox();
    ImageView img2;
//    private Button reloadButton = new Button("Aktualizovat");

    public HBox getVZakaznik() {
        zakaznikPanel.getChildren().clear();
        setLeftSide();
        setRightSide();
        return zakaznikPanel;
    }

    private void setRightSide() {
        getTable();
        zakaznikPanel.getChildren().add(rightSide);
//        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                getTable();
//            }
//        });

    }

    private void getTable() {
        try {
            img2 = new ImageView(new Image(VVypujcka.class.
                    getResourceAsStream("/DATA/search-icon.png"), 102, 102, false, false));
            table = new TableView<>();
            table.setEditable(true);
            data = FXCollections.observableArrayList();

            ResultSet rs = c.createStatement().executeQuery("select * from Zakaznik");
            while (rs.next()) {
                data.add(new Zakaznik(rs.getInt("idZakaznik"), rs.getString("jmeno"), rs.getString("prijmeni"), rs.getString("email")));
            }
            TableColumn idZakaznikCol = new TableColumn("idZakaznik");
            idZakaznikCol.setMinWidth(100);
            idZakaznikCol.setCellValueFactory(
                    new PropertyValueFactory<>("idZakaznik"));

            TableColumn firstNameCol = new TableColumn("Jméno");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("jmeno"));

            TableColumn lastNameCol = new TableColumn("Přijmení");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("prijmeni"));

            TableColumn emailCol = new TableColumn("Email");
            emailCol.setMinWidth(200);
            emailCol.setCellValueFactory(
                    new PropertyValueFactory<>("email"));
            
            TableColumn col_action = new TableColumn<>("Akce");
            col_action.setMinWidth(70);
            col_action.setSortable(false);

            col_action.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                    ObservableValue<Boolean>>() {

                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });

            col_action.setCellFactory(
                    new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

                @Override
                public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                    return new ButtonCell(table);
                }

            });
            
            table.setItems(null);
            table.setItems(data);
            table.getColumns().addAll(idZakaznikCol, firstNameCol, lastNameCol, emailCol);
            table.getColumns().add(col_action);
            
            

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        searchFormRight.setAlignment(Pos.TOP_CENTER);
        searchFormRight.getChildren().clear();
        searchFormRight.setPadding(new Insets(20, 0, 0, 0));
        searchFormRight.setSpacing(20);
        searchFormRight.getChildren().addAll(img2, table);
        //searchFormRight.setMaxWidth(500);
        //rightSide.setMinWidth(500);
        rightSide.setCenter(searchFormRight);

    }

    private void setLeftSide() {

        int widthLabel = 80;

        ImageView img = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/form-icon.png"), 90, 102, false, false));

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
                if(!isValidEmailAddress(zakaznikEmailField.getText()))
                {
                     JOptionPane.showMessageDialog(null, "Neplatný formát e-mailu");
                    return;
                }
                try {
                    pst = c.prepareStatement(sql);
                    pst.setString(1, zakaznikJmenoField.getText());
                    pst.setString(2, zakaznikPrijmeniField.getText());
                    pst.setString(3, zakaznikEmailField.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Do databáze byly vloženy hodnoty: " + zakaznikJmenoField.getText() + ", "
                            + zakaznikPrijmeniField.getText() + ", " + zakaznikEmailField.getText());
                    getTable();
                    zakaznikJmenoField.clear();
                    zakaznikPrijmeniField.clear();
                    zakaznikEmailField.clear();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
            }
        });

//        ImageView img3 = new ImageView(new Image(VVypujcka.class.
//                getResourceAsStream("/DATA/delete-icon.png"), 90, 102, false, false));
//
//        zakaznikIdDelete.getChildren().clear();
//        zakaznikIdDeleteLabel.setPrefWidth(widthLabel);
//        zakaznikIdDelete.getChildren().addAll(zakaznikIdDeleteLabel, zakaznikIdDeleteField);
//
//        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                int Id = Integer.parseInt(zakaznikIdDeleteField.getText());
//                String sql = "DELETE FROM Zakaznik WHERE IdZakaznik = " + Id;
//                try {
//                    pst = c.prepareStatement(sql);
//                    pst.execute();
//                    JOptionPane.showMessageDialog(null, "Z databáze bylo smazáno: " + zakaznikIdDeleteField.getText());
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e);
//                }
//            }
//        });

        searchForm.setAlignment(Pos.TOP_CENTER);
        searchForm.getChildren().clear();
//        deleteForm.getChildren().clear();
        leftSide.getChildren().clear();
        searchForm.getChildren().addAll(zakaznikJmeno, zakaznikPrijmeni, zakaznikEmail);
//        deleteForm.setAlignment(Pos.TOP_CENTER);
//        deleteForm.getChildren().addAll(img3, zakaznikIdDelete, deleteButton);
               
        leftSide.setMinWidth(400);
        leftSide.getChildren().addAll(img, searchForm, insertButton);
        leftSide.setAlignment(Pos.TOP_CENTER);
        leftSide.setPadding(new Insets(20, 0, 0, 0));
        leftSide.setSpacing(20);
        leftSide.setMinWidth(270);

        zakaznikPanel.setAlignment(Pos.CENTER);
        zakaznikPanel.getChildren().add(leftSide);
    }
    
    public static boolean isValidEmailAddress(String email)
    {
       boolean result = false;
       String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(email);
       if (matcher.matches())
       {
           
         result = true;   
         
       }
       return result;
       
    }
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Odstanit");
         
        ButtonCell(final TableView tblView){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>()
            {
 
                @Override
                public void handle(ActionEvent t)
                {
                    
                    Zakaznik deleteItem = table.getItems().get(getIndex());
                    int deleteId = deleteItem.getIdZakaznik();
                    String sql = "DELETE FROM Zakaznik WHERE IdZakaznik = " + deleteId;
                    
                    try
                    {

                       pst = c.prepareStatement(sql);
                       pst.execute();
                       getTable();
                       JOptionPane.showMessageDialog(null,"Byl odstaněn zákazník s id: " + deleteId);

                    } catch (Exception e)
                    {

                        JOptionPane.showMessageDialog(null, e);

                    }

                }
            });
        }
 
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
     
}
