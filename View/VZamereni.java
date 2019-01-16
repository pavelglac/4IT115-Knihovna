/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sqlite;
import Model.Kniha;
import Model.OblastZamereni;
import Model.Zakaznik;
import com.sun.prism.impl.Disposer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class VZamereni {
    
    Connection c = Sqlite.ConnectDb();
    PreparedStatement pst = null;
    ResultSet rs = null;
    private TableView<OblastZamereni> table;
    
    HBox zamereniPanel = new HBox();
    private BorderPane rightSide = new BorderPane();
    private VBox leftSide = new VBox();
    
    private Label zamereniNazevLabel = new Label("Název");
    private TextField zamereniNazevField = new TextField();
    
    private Label zamereniIdDeleteLabel = new Label("ID žánru");
    private TextField zamereniIdDeleteField = new TextField();
    private HBox zamereniIdDelete = new HBox();
    private Button deleteButton = new Button("Smazat");
    
    private HBox zamereniNazev = new HBox();
    
    private VBox searchForm = new VBox();

    private Button insertButton = new Button("Vložit");

    private ObservableList<OblastZamereni> data;
    private VBox searchFormRight = new VBox();
    private ImageView img2;
    private Button reloadButton = new Button("Aktualizovat");
    
    public HBox getVZamereni()
    {
        zamereniPanel.getChildren().clear();
        setLeftSide();
        setRightSide();
        return zamereniPanel;
    }
    
    
    private void setRightSide() {
        getTable();
        zamereniPanel.getChildren().add(rightSide);
        /*
        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getTable();
            }
        });
        */

    }
    
    private void getTable() {
        try {
            img2 = new ImageView(new Image(VVypujcka.class.
                    getResourceAsStream("/DATA/search-icon.png"), 102, 102, false, false));
            table = new TableView<>();
            table.setEditable(true);
            data = FXCollections.observableArrayList();

            ResultSet rs = c.createStatement().executeQuery("select * from OblastZamereni");
            while (rs.next()) {
                data.add(new OblastZamereni(rs.getInt("idZamereni"), rs.getString("zNazev")));
            }

            TableColumn idZamereniCol = new TableColumn("idZamereni");
            idZamereniCol.setMinWidth(100);
            idZamereniCol.setCellValueFactory(
                    new PropertyValueFactory<>("idZamereni"));
            
            TableColumn nazevCol = new TableColumn("nazev");
            nazevCol.setMinWidth(100);
            nazevCol.setCellValueFactory(
                    new PropertyValueFactory<>("nazev"));
            
            TableColumn col_action = new TableColumn<>("Akce");
            col_action.setMinWidth(70);
            col_action.setSortable(false);

            col_action.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                    ObservableValue<Boolean>>() {

                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });

            col_action.setCellFactory(
                    new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

                @Override
                public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                    return new ButtonCell(table);
                }

            });
            
            table.setItems(null);
            table.setItems(data);
            table.getColumns().addAll(idZamereniCol, nazevCol, col_action);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        searchFormRight.setAlignment(Pos.TOP_CENTER);
        searchFormRight.getChildren().clear();
        searchFormRight.setPadding(new Insets(20, 0, 0, 0));
        searchFormRight.setSpacing(20);
        searchFormRight.getChildren().addAll(img2, table);
        rightSide.setCenter(searchFormRight);

    }
    
    private void setLeftSide() {

        int widthLabel = 80;

        ImageView img = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/form-icon.png"), 90, 102, false, false));

        zamereniNazev.getChildren().clear();
        zamereniNazevLabel.setPrefWidth(widthLabel);
        zamereniNazev.getChildren().addAll(zamereniNazevLabel, zamereniNazevField);

        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                String sql = "INSERT INTO OblastZamereni(zNazev) VALUES(?)";
                try {
                    pst = c.prepareStatement(sql);
                    pst.setString(1, zamereniNazevField.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Do databáze byly vloženy hodnoty: " + zamereniNazevField.getText());
                    getTable();
                    zamereniNazevField.clear();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        });
        /*
        ImageView img3 = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/delete-icon.png"), 90, 102, false, false));

        zamereniIdDelete.getChildren().clear();
        zamereniIdDeleteLabel.setPrefWidth(widthLabel);
        zamereniIdDelete.getChildren().addAll(zamereniIdDeleteLabel, zamereniIdDeleteField);
        
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int Id = Integer.parseInt(zamereniIdDeleteField.getText());
                String sql = "DELETE FROM OblastZamereni WHERE idZamereni = " + Id;
                try {
                    pst = c.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Z databáze bylo smazáno: " + zamereniIdDeleteField.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        */
        searchForm.setAlignment(Pos.CENTER);
        searchForm.getChildren().clear();
        searchForm.getChildren().addAll(img, zamereniNazev, insertButton);
        searchForm.setMaxWidth(250);
        
        leftSide.getChildren().clear();
        leftSide.getChildren().addAll(img, searchForm, insertButton);
        leftSide.setAlignment(Pos.TOP_CENTER);
        leftSide.setPadding(new Insets(20, 0, 0, 0));
        leftSide.setSpacing(20);
        leftSide.setMinWidth(300);

        zamereniPanel.setAlignment(Pos.CENTER);
        zamereniPanel.getChildren().add(leftSide);
    }
        
 
    //Define the button cell
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Odstanit");
         
        ButtonCell(final TableView tblView){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>()
            {
 
                @Override
                public void handle(ActionEvent t)
                {
                    
                    OblastZamereni deleteItem = table.getItems().get(getIndex());
                    int deleteId = deleteItem.getIdZamereni();
                    String sql = "DELETE FROM OblastZamereni WHERE idZamereni = " + deleteId;
                    try
                    {
                       pst = c.prepareStatement(sql);
                       pst.execute();
                       getTable();
                       JOptionPane.showMessageDialog(null,"Bylo odstraněno zaměření s id: " + deleteId);

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
