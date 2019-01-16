package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OblastZamereni {

    private SimpleStringProperty nazev;
    private SimpleIntegerProperty idZamereni;
    

    public OblastZamereni(int idZamereni, String nazev) {
        this.idZamereni = new SimpleIntegerProperty(idZamereni);
        this.nazev = new SimpleStringProperty(nazev);

    }
    
    public String getNazev() {
        return nazev.get();
    }

    public void setNazev(String nazev) {
        this.nazev.set(nazev);
    }

    public int getIdZamereni() {
        return idZamereni.get();
    }

    public void setIdZamereni(int idZamereni) {
        this.idZamereni.set(idZamereni);
    }
    
    public String settNazev(String nazev){
        this.nazev.set(nazev);
        return nazev;
    }

}
