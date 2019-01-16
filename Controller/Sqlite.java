/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author brcm00
 */
public class Sqlite {

    Connection c = null;
    Statement stmt = null;
    PreparedStatement zakaznici;
    PreparedStatement a;
    PreparedStatement b;
    PreparedStatement d;
    PreparedStatement e;
    PreparedStatement f;
    PreparedStatement g;
    PreparedStatement h;
    PreparedStatement i;
    PreparedStatement j;
    PreparedStatement k;

    public Sqlite() {
        //Zkouška spojení
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            System.out.println("Připojení k databázi OK");
            //closeConnection();
            //createTables();
            //fillTables();
        } catch (Exception e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }

    public void createTables() {
        //Vytváříme tabulky
        try {
            zakaznici = c.prepareStatement("create table OblastZamereni (\n"
                    + "idZamereni           INTEGER              not null,\n"
                    + "zNazev               VARCHAR(1024)        not null,\n"
                    + "primary key (idZamereni)\n"
                    + ");");

            zakaznici.executeUpdate();

            a = c.prepareStatement("create table Kniha (\n"
                    + "idKniha              INTEGER              not null,\n"
                    + "idZamereni           INTEGER,\n"
                    + "nazev                VARCHAR(1024)        not null,\n"
                    + "evidencniCislo       VARCHAR(1024)        not null,\n"
                    + "jazyk                VARCHAR(1024)        not null,\n"
                    + "stav                 VARCHAR(1024)        not null,\n"
                    + "autor                VARCHAR(1024)        not null,\n"
                    + "primary key (idKniha),\n"
                    + "foreign key (idZamereni)\n"
                    + "      references OblastZamereni (idZamereni)\n"
                    + ");");
            a.executeUpdate();

            b = c.prepareStatement("create unique index Kniha_PK on Kniha (\n"
                    + "idKniha ASC\n"
                    + ");");
            b.executeUpdate();

            d = c.prepareStatement("create  index zamereni_FK on Kniha (\n"
                    + "idZamereni ASC\n"
                    + ");");
            d.executeUpdate();
            e = c.prepareStatement("create unique index OblastZamereni_PK on OblastZamereni (\n"
                    + "idZamereni ASC\n"
                    + ");");
            e.executeUpdate();
            f = c.prepareStatement("create table Zakaznik (\n"
                    + "idZakaznik           INTEGER              not null,\n"
                    + "jmeno                VARCHAR(1024)        not null,\n"
                    + "prijmeni             VARCHAR(1024)        not null,\n"
                    + "email                VARCHAR(1024),\n"
                    + "primary key (idZakaznik)\n"
                    + ");");
            f.executeUpdate();
            g = c.prepareStatement("create table Vypujcka (\n"
                    + "idVypujcka           INTEGER              not null,\n"
                    + "idZakaznik           INTEGER              not null,\n"
                    + "idKniha              INTEGER              not null,\n"
                    + "datumOd              VARCHAR(1024)        not null,\n"
                    + "datumDo              VARCHAR(1024)        not null,\n"
                    + "skutecneVraceni      VARCHAR(1024),\n"
                    + "primary key (idVypujcka),\n"
                    + "foreign key (idKniha)\n"
                    + "      references Kniha (idKniha),\n"
                    + "foreign key (idZakaznik)\n"
                    + "      references Zakaznik (idZakaznik)\n"
                    + ");");
            g.executeUpdate();
            h = c.prepareStatement("create unique index Vypujcka_PK on Vypujcka (\n"
                    + "idVypujcka ASC\n"
                    + ");");
            h.executeUpdate();
            i = c.prepareStatement("create  index vypujceniKnihy_FK on Vypujcka (\n"
                    + "idKniha ASC\n"
                    + ");");
            i.executeUpdate();
            j = c.prepareStatement("create  index vypujceniZakaznika_FK on Vypujcka (\n"
                    + "idZakaznik ASC\n"
                    + ");");
            j.executeUpdate();

            k = c.prepareStatement("create unique index Zakaznik_PK on Zakaznik (\n"
                    + "idZakaznik ASC\n"
                    + ");");
            k.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nepodařilo se vytvořit tabulky DB");
        }

    }

    public void fillTables() {
        //Vkládáme prvnostní data do tabulek
        //NEFUNGUJE, Vkládá se 2x
        try {
            zakaznici = c.prepareStatement("INSERT OR IGNORE INTO Zakaznik(jmeno, prijmeni, email) VALUES (\"Milan\",\"Brchel\",\"asd\")");
            zakaznici.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nepodařilo se vytvořit tabulky DB");
        }
    }

    public void closeConnection() {
        try {
            c.close();
            System.out.println("Spojení s DB UKONČENO");
        } catch (Exception e) {
            System.out.println("Chyba: " + e.getMessage());
        }

    }

    public static Connection ConnectDb() {
        //Pro ostatní třídy
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:db.db");
            return c;

        } catch (Exception e) {
            System.out.println("Chyba: " + e.getMessage());
            return null;
        }
    }

}
