package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Autor extends Osoba {

    private SimpleStringProperty narodnost;
    private SimpleIntegerProperty idAutor;
    private Kniha[] kniha; //K čemu?

    public Autor(String jmeno, String prijmeni, String narodnost, int idAutor) {
        super(jmeno, prijmeni);
        this.narodnost = new SimpleStringProperty(narodnost);
        this.idAutor = new SimpleIntegerProperty(idAutor);

    }

    public String getNarodnost() {
        return narodnost.get();
    }

    public void setNarodnost(String narodnost) {
        this.narodnost.set(narodnost);
    }

    public int getIdAutor() {
        return idAutor.get();
    }

    public void setIdAutor(int idAutor) {
        this.idAutor.set(idAutor);
    }

}
