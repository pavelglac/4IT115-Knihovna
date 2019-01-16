package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vypujcka {

    private SimpleIntegerProperty idVypujcka;
    private SimpleStringProperty datumOd;
    private SimpleStringProperty datumDo;
    private SimpleStringProperty skutecneVraceni;
    private SimpleStringProperty idZakaznik;
    private SimpleStringProperty idKniha;

    public Vypujcka(int idVypujcka, String idZakaznik, String idKniha, String datumOd, String datumDo, String skutecneVraceni) {
        this.idVypujcka = new SimpleIntegerProperty(idVypujcka);
        this.idZakaznik = new SimpleStringProperty(idZakaznik);
        this.idKniha = new SimpleStringProperty(idKniha);
        this.datumOd = new SimpleStringProperty(datumOd);
        this.datumDo = new SimpleStringProperty(datumDo);
        this.skutecneVraceni = new SimpleStringProperty(skutecneVraceni);
    }

    public int getIdVypujcka() {
        return idVypujcka.get();
    }

    public void setIdVypujcka(int idVypujcka) {
        this.idVypujcka.set(idVypujcka);
    }

    public String getIdZakaznik() {
        return idZakaznik.get();
    }

    public void setIdZakaznik(String idZakaznik) {
        this.idZakaznik.set(idZakaznik);
    }

    public String getIdKniha() {
        return idKniha.get();
    }

    public void setIdKniha(String idKnihy) {
        this.idKniha.set(idKnihy);
    }

    public String getDatumOd() {
        return datumOd.get();
    }

    public void setDatumOd(String datumOd) {
        this.datumOd.set(datumOd);
    }
    
    public String getDatumDo() {
        return datumDo.get();
    }

    public void setDatumDo(String datumDo) {
        this.datumDo.set(datumDo);
    }
    
    public String getSkutecneVraceni() {
        return skutecneVraceni.get();
    }

    public void setSkutecneVraceni(String skutecneVraceni) {
        this.skutecneVraceni.set(skutecneVraceni);
    }
    
    
    
    
    
    
    
    
    
}
