package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Kniha {

    private SimpleStringProperty nazev;
    private SimpleStringProperty evidencniCislo;
    private SimpleStringProperty jazyk;
    private SimpleIntegerProperty idKniha;
    private SimpleStringProperty stav;
    private SimpleStringProperty autor;
    private SimpleStringProperty idZamereni;

    public Kniha(int idKniha, String idZamereni, String nazev, String evidencniCislo, String jazyk, String stav, String autor) {
        this.idKniha = new SimpleIntegerProperty(idKniha);
        this.idZamereni = new SimpleStringProperty(idZamereni);
        this.nazev = new SimpleStringProperty(nazev);
        this.evidencniCislo = new SimpleStringProperty(evidencniCislo);
        this.jazyk = new SimpleStringProperty(jazyk);
        this.stav = new SimpleStringProperty(stav);
        this.autor = new SimpleStringProperty(autor);
    }

    public int getIdKniha() {
        return idKniha.get();
    }

    public void setIdKniha(int idKnihy) {
        this.idKniha.set(idKnihy);
    }
    
    public String getIdZamereni() {
        return idZamereni.get();
    }

    public void setIdVypujcka(String idZamereni) {
        this.idZamereni.set(idZamereni);
    }
    

    public String getNazev() {
        return nazev.get();
    }

    public void setNazev(String nazev) {
        this.nazev.set(nazev);
    }

    public String getEvidencniCislo() {
        return evidencniCislo.get();
    }

    public void setEvidencniCislo(String evidencniCislo) {
        this.evidencniCislo.set(evidencniCislo);
    }

    public String getJazyk() {
        return jazyk.get();
    }

    public void setJazyk(String jazyk) {
        this.jazyk.set(jazyk);
    }

    public String getStav() {
        return stav.get();
    }

    public void setStav(String stav) {
        this.stav.set(stav);
    }
    
    
    
    public String getAutor(){
        return autor.get();
    }
    
   
    
    
}
