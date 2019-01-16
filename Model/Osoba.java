package Model;

import javafx.beans.property.SimpleStringProperty;

public class Osoba {

    private SimpleStringProperty jmeno;
    private SimpleStringProperty prijmeni;
    

    public Osoba(String jmeno, String prijmeni) {
        this.jmeno = new SimpleStringProperty(jmeno);
        this.prijmeni = new SimpleStringProperty(prijmeni);
    }

    public String getJmeno() {
        return jmeno.get();
    }

    public void setJmeno(String jmeno) {
        this.jmeno.set(jmeno);
    }

    public String getPrijmeni() {
        return prijmeni.get();
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni.set(prijmeni);
    }

}
