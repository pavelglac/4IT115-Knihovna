/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sqlite;
import Model.Kniha;
import Model.OblastZamereni;
import Model.Vypujcka;
import Model.Zakaznik;
import com.sun.prism.impl.Disposer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pavel
 */
public class VVypujcka {

    Connection c = Sqlite.ConnectDb();
    PreparedStatement pst = null;
    ResultSet rs = null;
    private TableView<Vypujcka> table;

    HBox vypujckaPanel = new HBox();
    private BorderPane rightSide = new BorderPane();
    private VBox leftSide = new VBox();


    private Label vypujckaIdZakaznikLabel = new Label("Id Zákazníka");
    private Label vypujckaIdKnihaLabel = new Label("Id Knihy");
    private Label vypujckaDatumOdLabel = new Label("Datum od");
    private Label vypujckaDatumDoLabel = new Label("Datum do");
    private Label vypujckaDatumVraceniLabel  = new Label("Datum vrácení");

    private TextField vypujckaIdZakaznikField = new TextField();
    private TextField vypujckaIdKnihaField = new TextField();
    private DatePicker vypujckaDatumOdField = new DatePicker();

    private DatePicker vypujckaDatumDoField = new DatePicker();
    private DatePicker vypujckaDatumVraceniField = new DatePicker();
    

    private Label vypujckaIdDeleteLabel = new Label("ID knihy");
    private TextField vypujckaIdDeleteField = new TextField();
    private HBox vypujckaIdDelete = new HBox();
    private Button deleteButton = new Button("Smazat");

    private HBox vypujckaIdZakaznik = new HBox();
    private HBox vypujckaIdKniha = new HBox();
    private HBox vypujckaDatumOd = new HBox();
    private HBox vypujckaDatumDo = new HBox();
    private HBox vypujckaDatumVraceni = new HBox();

    private VBox searchForm = new VBox();

    private Button insertButton = new Button("Vložit");

    private ObservableList<Vypujcka> data;
    private VBox searchFormRight = new VBox();
    private ImageView img2;
    private Button loadButton = new Button("Načíst");

    public HBox getVVypujcka() {
        vypujckaPanel.getChildren().clear();
        setLeftSide();
        setRightSide();
        return vypujckaPanel;
    }

    private void setRightSide() {
        getTable();
        vypujckaPanel.getChildren().add(rightSide);

    }

    private void getTable() {
        try {

            img2 = new ImageView(new Image(VVypujcka.class.
                    getResourceAsStream("/DATA/search-icon.png"), 102, 102, false, false));
            table = new TableView<>();
            table.setEditable(true);
            data = FXCollections.observableArrayList();
           ResultSet rs = c.createStatement().executeQuery("Select * from Vypujcka;");
           
           /*
           ResultSet rs = c.createStatement().executeQuery("SELECT Vypujcka.idVypujcka, Zakaznik.prijmeni, Kniha.nazev, Vypujcka.datumOd, Vypujcka.datumDo, Vypujcka.skutecneVraceni\n" +
"FROM Vypujcka\n" +
"Inner JOIN Zakaznik using(idZakaznik)\n" +
"Inner JOIN Kniha using (idKniha);");
           */

            while (rs.next()) {
                data.add(new Vypujcka(rs.getInt("idVypujcka"), rs.getString("idZakaznik"),rs.getString("idKniha"), rs.getString("datumOd"), rs.getString("datumDo"), rs.getString("skutecneVraceni")));
            }
            TableColumn idVypujckaCol = new TableColumn("idVypujcka");
            idVypujckaCol.setMinWidth(80);
            idVypujckaCol.setCellValueFactory(
                    new PropertyValueFactory<>("idVypujcka"));

            TableColumn idZakaznikCol = new TableColumn("idZakaznik");
            idZakaznikCol.setMinWidth(100);
            idZakaznikCol.setCellValueFactory(
                    new PropertyValueFactory<>("idZakaznik"));

            TableColumn idKnihaCol = new TableColumn("idKniha");
            idKnihaCol.setMinWidth(100);
            idKnihaCol.setCellValueFactory(
                    new PropertyValueFactory<>("idKniha"));

            TableColumn datumOdCol = new TableColumn("datumOd");
            datumOdCol.setMinWidth(100);
            datumOdCol.setCellValueFactory(
                    new PropertyValueFactory<>("datumOd"));

            TableColumn datumDoCol = new TableColumn("datumDo");
            datumDoCol.setMinWidth(100);
            datumDoCol.setCellValueFactory(
                    new PropertyValueFactory<>("datumDo"));

            TableColumn SkutecneVraceniCol = new TableColumn("skutecneVraceni");
            SkutecneVraceniCol.setMinWidth(100);
            SkutecneVraceniCol.setCellValueFactory(
                    new PropertyValueFactory<>("skutecneVraceni"));
            
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
            table.getColumns().addAll(idVypujckaCol, idZakaznikCol, idKnihaCol, datumOdCol, datumDoCol, SkutecneVraceniCol, col_action);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        vypujckaDatumVraceni.getChildren().clear();
        vypujckaDatumVraceniLabel.setPrefWidth(100);
        vypujckaDatumVraceniField.setValue(LocalDate.now());
        vypujckaDatumVraceni.getChildren().addAll(vypujckaDatumVraceniLabel, vypujckaDatumVraceniField, loadButton);
        vypujckaDatumVraceni.setAlignment(Pos.CENTER);
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String vypujcka = vypujckaDatumVraceniField.getValue().format(DateTimeFormatter.ISO_DATE);
                try {

            img2 = new ImageView(new Image(VVypujcka.class.
                    getResourceAsStream("/DATA/search-icon.png"), 102, 102, false, false));
            table = new TableView<>();
            table.setEditable(true);
            data = FXCollections.observableArrayList();
           ResultSet rs = c.createStatement().executeQuery("Select * from Vypujcka WHERE datumDo = \"" +vypujcka + "\"");

           /*
           ResultSet rs = c.createStatement().executeQuery("SELECT Vypujcka.idVypujcka, Zakaznik.prijmeni, Kniha.nazev, Vypujcka.datumOd, Vypujcka.datumDo, Vypujcka.skutecneVraceni\n" +
"FROM Vypujcka\n" +
"Inner JOIN Zakaznik using(idZakaznik)\n" +
"Inner JOIN Kniha using (idKniha);");
           */

            while (rs.next()) {
                data.add(new Vypujcka(rs.getInt("idVypujcka"), rs.getString("idZakaznik"),rs.getString("idKniha"), rs.getString("datumOd"), rs.getString("datumDo"), rs.getString("skutecneVraceni")));
            }
            TableColumn idVypujckaCol = new TableColumn("idVypujcka");
            idVypujckaCol.setMinWidth(80);
            idVypujckaCol.setCellValueFactory(
                    new PropertyValueFactory<>("idVypujcka"));

            TableColumn idZakaznikCol = new TableColumn("idZakaznik");
            idZakaznikCol.setMinWidth(100);
            idZakaznikCol.setCellValueFactory(
                    new PropertyValueFactory<>("idZakaznik"));

            TableColumn idKnihaCol = new TableColumn("idKniha");
            idKnihaCol.setMinWidth(100);
            idKnihaCol.setCellValueFactory(
                    new PropertyValueFactory<>("idKniha"));

            TableColumn datumOdCol = new TableColumn("datumOd");
            datumOdCol.setMinWidth(100);
            datumOdCol.setCellValueFactory(
                    new PropertyValueFactory<>("datumOd"));

            TableColumn datumDoCol = new TableColumn("datumDo");
            datumDoCol.setMinWidth(100);
            datumDoCol.setCellValueFactory(
                    new PropertyValueFactory<>("datumDo"));

            TableColumn SkutecneVraceniCol = new TableColumn("skutecneVraceni");
            SkutecneVraceniCol.setMinWidth(100);
            SkutecneVraceniCol.setCellValueFactory(
                    new PropertyValueFactory<>("skutecneVraceni"));
            
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
            table.getColumns().addAll(idVypujckaCol, idZakaznikCol, idKnihaCol, datumOdCol, datumDoCol, SkutecneVraceniCol, col_action);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        searchFormRight.setAlignment(Pos.TOP_CENTER);
        searchFormRight.getChildren().clear();
        searchFormRight.setPadding(new Insets(20, 0, 0, 0));
        searchFormRight.setSpacing(20);        
        searchFormRight.getChildren().addAll(img2, vypujckaDatumVraceni, table);
        rightSide.setCenter(searchFormRight);

                
                
            }
        });
        
        searchFormRight.setAlignment(Pos.TOP_CENTER);
        searchFormRight.getChildren().clear();
        searchFormRight.setPadding(new Insets(20, 0, 0, 0));
        searchFormRight.setSpacing(20);        
        searchFormRight.getChildren().addAll(img2, vypujckaDatumVraceni, table);
        rightSide.setCenter(searchFormRight);

    }

    private void setLeftSide() {

        int widthLabel = 80;

        ImageView img = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/form-icon.png"), 90, 102, false, false));

        vypujckaIdZakaznik.getChildren().clear();
        vypujckaIdZakaznikLabel.setPrefWidth(widthLabel);
        vypujckaIdZakaznik.getChildren().addAll(vypujckaIdZakaznikLabel, vypujckaIdZakaznikField);
        
        vypujckaIdKniha.getChildren().clear();
        vypujckaIdKnihaLabel.setPrefWidth(widthLabel);
        vypujckaIdKniha.getChildren().addAll(vypujckaIdKnihaLabel, vypujckaIdKnihaField);

        vypujckaDatumOd.getChildren().clear();
        vypujckaDatumOdLabel.setPrefWidth(widthLabel);
        vypujckaDatumOdField.setValue(LocalDate.now());
        vypujckaDatumOd.getChildren().addAll(vypujckaDatumOdLabel, vypujckaDatumOdField);

        vypujckaDatumDo.getChildren().clear();
        vypujckaDatumDoLabel.setPrefWidth(widthLabel);
        vypujckaDatumDoField.setValue(LocalDate.now());
        vypujckaDatumDo.getChildren().addAll(vypujckaDatumDoLabel, vypujckaDatumDoField);

         
       insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String sql = "INSERT INTO Vypujcka(idZakaznik, idKniha, datumOd, datumDo, skutecneVraceni) VALUES(?,?,?,?,?)";
                int Id = Integer.parseInt(vypujckaIdZakaznikField.getText());
                int Id2 = Integer.parseInt(vypujckaIdKnihaField.getText());
                try {
                    pst = c.prepareStatement(sql);
                    pst.setInt(1, Id);
                    pst.setInt(2, Id2);
                    pst.setString(3, vypujckaDatumOdField.getValue().format(DateTimeFormatter.ISO_DATE));
                    pst.setString(4, vypujckaDatumDoField.getValue().format(DateTimeFormatter.ISO_DATE));
                    pst.setString(5, "---");
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Do databáze byly vloženy hodnoty: " + vypujckaIdZakaznikField.getText() + ", "
                            + vypujckaIdKnihaField.getText() + ", " + vypujckaDatumOdField.getValue().format(DateTimeFormatter.ISO_DATE) + ", " + vypujckaDatumDoField.getValue().format(DateTimeFormatter.ISO_DATE));
                    getTable();
                    vypujckaIdZakaznikField.clear();
                    vypujckaIdKnihaField.clear();
//                    vypujckaDatumOdField.clear();
//                    vypujckaDatumDoField.clear();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
            }

        });
       

      
        searchForm.setAlignment(Pos.CENTER);
        searchForm.getChildren().clear();
        searchForm.getChildren().addAll(vypujckaIdZakaznik, vypujckaIdKniha, vypujckaDatumOd, vypujckaDatumDo);
        searchForm.setMaxWidth(250);
//         insertButton,img3, vypujckaDatumVraceni, loadButton
        leftSide.getChildren().clear();
        leftSide.getChildren().addAll(img, searchForm, insertButton);
        leftSide.setAlignment(Pos.TOP_CENTER);
        leftSide.setPadding(new Insets(20, 0, 0, 0));
        leftSide.setSpacing(20);
        leftSide.setMinWidth(300);


        vypujckaPanel.setAlignment(Pos.CENTER);
        vypujckaPanel.getChildren().add(leftSide);
    }
    
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Odstanit");
         
        ButtonCell(final TableView tblView){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>()
            {
 
                @Override
                public void handle(ActionEvent t)
                {
                    
                    Vypujcka deleteItem = table.getItems().get(getIndex());
                    int deleteId = deleteItem.getIdVypujcka();
                    String sql = "DELETE FROM Vypujcka WHERE idVypujcka = " + deleteId;
                    try
                    {
                       pst = c.prepareStatement(sql);
                       pst.execute();
                       getTable();
                       JOptionPane.showMessageDialog(null,"Byla odstraněna výpůjčka s id: " + deleteId);

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
