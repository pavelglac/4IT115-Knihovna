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
import com.sun.prism.impl.Disposer.Record;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class VKniha {

    Connection c = Sqlite.ConnectDb();
    PreparedStatement pst = null;
    ResultSet rs = null;
    private TableView<Kniha> table;
    private OblastZamereni oblastZamereni;

    private TableColumn<Kniha, String> idKnihaCol;
    private TableColumn<Kniha, String> idZamereniCol;
    private TableColumn<Kniha, String> nazevCol;
    private TableColumn<Kniha, String> evidecniCisloCol;
    private TableColumn<Kniha, String> jazykCol;
    private TableColumn<Kniha, String> stavCol;
    private TableColumn<Kniha, String> autorCol;
    
    HBox knihaPanel = new HBox();
    private BorderPane rightSide = new BorderPane();
    private VBox leftSide = new VBox();

    private Label knihaIdZamereniLabel = new Label("ID Zaměření");
    private Label knihaNazevLabel = new Label("Název");
    private Label knihaEvidencniCisloLabel = new Label("Evidenční číslo");
    private Label knihaJazykLabel = new Label("Jazyk");
    private Label knihaStavLabel = new Label("Stav");
    private Label knihaZanrLabel = new Label("Žánr ID");
    private Label knihaAutorLabel = new Label("Autor");

    private TextField knihaIdZamereniField = new TextField();
    private TextField knihaNazevField = new TextField();
    private TextField knihaEvidencniCisloField = new TextField();
    private TextField knihaJazykField = new TextField();
    private TextField knihaStavField = new TextField();
    private TextField knihaZanrField = new TextField();
    private TextField knihaAutorField = new TextField();

    private Label knihaIdDeleteLabel = new Label("ID knihy");
    private TextField knihaIdDeleteField = new TextField();
    private HBox knihaIdDelete = new HBox();
    private Button deleteButton = new Button("Smazat");

    private HBox knihaIdZamereni = new HBox();
    private HBox knihaNazev = new HBox();
    private HBox knihaEvidencniCislo = new HBox();
    private HBox knihaJazyk = new HBox();
    private HBox knihaStav = new HBox();
    private HBox knihaZanr = new HBox();
    private HBox knihaAutor = new HBox();

    private VBox searchForm = new VBox();
    private VBox deleteForm = new VBox();
    
    private Button insertButton = new Button("Vložit");

    private ObservableList<Kniha> data;
    private VBox searchFormRight = new VBox();
    ImageView img2;
    private Button reloadButton = new Button("Aktualizovat");
    
    

    ComboBox comboBox = new ComboBox(data);
    
    
    
    
    
    

    public HBox getVKniha() {
        knihaPanel.getChildren().clear();
        setLeftSide();
        setRightSide();
        return knihaPanel;
    }

    private void setRightSide() {
        getTable();
        knihaPanel.getChildren().add(rightSide);
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
           
           ResultSet rs = c.createStatement().executeQuery("select Kniha.idKniha, OblastZamereni.zNazev, Kniha.nazev, Kniha.evidencniCislo, "
                   + "Kniha.jazyk, Kniha.stav, Kniha.autor "
                   + "FROM Kniha "
                   + "JOIN OblastZamereni using(idZamereni)");
            
            while (rs.next()) {
                data.add(new Kniha(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7)));
            }
  
            TableColumn idKnihaCol = new TableColumn("idKniha");
            idKnihaCol.setMinWidth(60);
            idKnihaCol.setCellValueFactory(
                    new PropertyValueFactory<>("idKniha"));
            
            TableColumn idZamereniCol = new TableColumn("idZamereni");
            idZamereniCol.setMinWidth(80);
            idZamereniCol.setCellValueFactory(
                    new PropertyValueFactory<>("idZamereni"));

            TableColumn nazevCol = new TableColumn("nazev");
            nazevCol.setMinWidth(100);
            nazevCol.setCellValueFactory(
                    new PropertyValueFactory<>("nazev"));

            TableColumn evidecniCisloCol = new TableColumn("evidencniCislo");
            evidecniCisloCol.setMinWidth(80);
            evidecniCisloCol.setCellValueFactory(
                    new PropertyValueFactory<>("evidencniCislo"));

            TableColumn jazykCol = new TableColumn("jazyk");
            jazykCol.setMinWidth(60);
            jazykCol.setCellValueFactory(
                    new PropertyValueFactory<>("jazyk"));

            TableColumn stavCol = new TableColumn("stav");
            stavCol.setMinWidth(100);
            stavCol.setCellValueFactory(
                    new PropertyValueFactory<>("stav"));

            TableColumn autorCol = new TableColumn("autor");
            autorCol.setMinWidth(100);
            autorCol.setCellValueFactory(
                    new PropertyValueFactory<>("autor"));
            
            
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
            table.getColumns().addAll(idKnihaCol, idZamereniCol, nazevCol, evidecniCisloCol, jazykCol, stavCol, autorCol);
            table.getColumns().add(col_action);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        searchFormRight.setAlignment(Pos.TOP_CENTER);
        searchFormRight.getChildren().clear();
        searchFormRight.getChildren().addAll(img2, table);
        searchFormRight.setPadding(new Insets(20, 0, 0, 0));
        searchFormRight.setSpacing(20);
        rightSide.setCenter(searchFormRight);

    }

    private void setLeftSide() {

        int widthLabel = 80;

        ImageView img = new ImageView(new Image(VVypujcka.class.
                getResourceAsStream("/DATA/form-icon.png"), 90, 102, false, false));

        knihaIdZamereni.getChildren().clear();
        knihaIdZamereniLabel.setPrefWidth(widthLabel);
        knihaIdZamereni.getChildren().addAll(knihaIdZamereniLabel, knihaIdZamereniField);
        
        knihaNazev.getChildren().clear();
        knihaNazevLabel.setPrefWidth(widthLabel);
        knihaNazev.getChildren().addAll(knihaNazevLabel, knihaNazevField);

        knihaEvidencniCislo.getChildren().clear();
        knihaEvidencniCisloLabel.setPrefWidth(widthLabel);
        knihaEvidencniCislo.getChildren().addAll(knihaEvidencniCisloLabel, knihaEvidencniCisloField);

        knihaJazyk.getChildren().clear();
        knihaJazykLabel.setPrefWidth(widthLabel);
        knihaJazyk.getChildren().addAll(knihaJazykLabel, knihaJazykField);

        knihaStav.getChildren().clear();
        knihaStavLabel.setPrefWidth(widthLabel);
        knihaStav.getChildren().addAll(knihaStavLabel, knihaStavField);

        knihaAutor.getChildren().clear();
        knihaAutorLabel.setPrefWidth(widthLabel);
        knihaAutor.getChildren().addAll(knihaAutorLabel, knihaAutorField);

        /*
        comboBox.setMaxHeight(30);
        comboBox.setMaxWidth(90);
        comboBox.setOnAction (e ->{
            try {
                String query = "select zNazev from OblastZamereni";
                pst = c.prepareStatement(query);
                pst.setString(1, (String)comboBox.getSelectionModel().getSelectedItem());
                rs = pst.executeQuery();
                
                while(rs.next()){
                    knihaIdZamereniField.setText(rs.getString("idZamereni"));

                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(VKniha.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        */

        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String sql = "INSERT INTO Kniha(IdZamereni, nazev, evidencniCislo, jazyk, stav, autor) VALUES(?,?,?,?,?,?)";
                int Id = Integer.parseInt(knihaIdZamereniField.getText());
                try {
                    pst = c.prepareStatement(sql);
                    pst.setInt(1, Id);
                    pst.setString(2, knihaNazevField.getText());
                    pst.setString(3, knihaEvidencniCisloField.getText());
                    pst.setString(4, knihaJazykField.getText());
                    pst.setString(5, knihaStavField.getText());
                    pst.setString(6, knihaAutorField.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Do databáze byly vloženy hodnoty: " + knihaIdZamereniField.getText() + ", "
                            + knihaNazevField.getText() + ", " + knihaEvidencniCisloField.getText() + ", " + knihaJazykField.getText() + ", "
                            + knihaStavField.getText() + ", " + knihaAutorField.getText());
                    getTable();
                    knihaIdZamereniField.clear();
                    knihaNazevField.clear();
                    knihaEvidencniCisloField.clear();
                    knihaJazykField.clear();
                    knihaStavField.clear();
                    knihaAutorField.clear();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
            }

        });
 
        searchForm.setAlignment(Pos.TOP_CENTER);
        searchForm.getChildren().clear();
        searchForm.getChildren().addAll(knihaIdZamereni, knihaNazev, knihaEvidencniCislo, knihaJazyk, knihaStav, knihaAutor, insertButton);
        searchForm.setMaxWidth(250);
        leftSide.getChildren().clear();
        leftSide.getChildren().addAll(img, searchForm, insertButton);
        leftSide.setMinWidth(300);
        leftSide.setAlignment(Pos.TOP_CENTER);
        //leftSide.setCenter(searchForm);
        leftSide.setPadding(new Insets(20, 0, 0, 0));
        leftSide.setSpacing(20);

        knihaPanel.setAlignment(Pos.CENTER);
        knihaPanel.getChildren().add(leftSide);
    }

    //Define the button cell
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Odstanit");
         
        ButtonCell(final TableView tblView){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>()
            {
 
                @Override
                public void handle(ActionEvent t)
                {
                    
                    Kniha deleteItem = table.getItems().get(getIndex());
                    int deleteId = deleteItem.getIdKniha();
                    String sql = "DELETE FROM Kniha WHERE idKniha = " + deleteId;
                    
                    try
                    {

                       pst = c.prepareStatement(sql);
                       pst.execute();
                       getTable();
                       JOptionPane.showMessageDialog(null,"Byla odstaněna kniha s id: " + deleteId);

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
