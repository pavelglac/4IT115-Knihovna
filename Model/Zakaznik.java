package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Zakaznik extends Osoba {

	private SimpleIntegerProperty idZakaznik;
	private SimpleStringProperty email;
        
        
        public Zakaznik(int idZakaznik, String jmeno, String prijmeni, String email){
            super(jmeno, prijmeni);
            this.email = new SimpleStringProperty(email);
            this.idZakaznik = new SimpleIntegerProperty(idZakaznik);
        }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getIdZakaznik() {
        return idZakaznik.get();
    }

    public void setIdZakaznik(int idZakaznik) {
        this.idZakaznik.set(idZakaznik);
    }

}
