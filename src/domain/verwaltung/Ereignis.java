package domain.verwaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.bestand.Artikel;

public class Ereignis {

    final static String EINLAGERN = "Einlagern";
    final static String AUSLAGERN = "Auslagern";
    final static String RENAME = "Namensaenderung";
    final static String PRICECHANGE = "Preisaenderung";
    final static String MORE = "Artikelbearbeitung";


    private String type;
    private LocalDate date;
    private String beschreibung;

    private static List<Ereignis> ereignisse = new ArrayList<>();

    public Ereignis(LocalDate date, String type, String beschreibung){

        this.date = date;
        this.type = type;
        this.beschreibung = beschreibung;

        ereignisse.add(this);

    }

    public Ereignis() {

    }

    public String getType() {
        return type;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public String getBeschreibung() {
        return beschreibung;
    }

    public static List<Ereignis> getAlleEreignisse() {
        return ereignisse;
    }

    public String toString() {
        return "Datum: " + date + "\n" +
               "Typ: " + type + "\n" +
               "Beschreibung: " + beschreibung + "\n" +
               "----------------------------------------";
    }

    public Ereignis ereignisErzeugen(Artikel artikel, String name, int bestand, float preis) {
        String beschreibung;
        String type = "Neuer Artikel";
        date = LocalDate.now();
        StringBuilder stringBuilder = new StringBuilder("Artikel ID: " + artikel.getArtikelID() + "; ");

        if (artikel.getArtikel_name() != name) {
            stringBuilder.append("Name: " + artikel.getArtikel_name() + " --> " + name + "; ");
            type = RENAME;
        }

        if (artikel.getBestand() != bestand) {
            stringBuilder.append("Bestand: " + artikel.getBestand() + " --> " + bestand + "; ");

            if (type.equals("Neuer Artikel")) {
                if (artikel.getBestand() < bestand) {
                    type = EINLAGERN;
                } else {
                    type = AUSLAGERN;
                }
            } else {
                type = MORE;
            }
        }

        if (artikel.getPreis() != preis) {
            stringBuilder.append("Preis: " + artikel.getPreis() + " --> " + preis + "; ");
            if (type.equals("Neuer Artikel")) {
               type = PRICECHANGE; 
            } else type = MORE;
            
        }

        if (type.equals("Neuer Artikel")) {
            beschreibung = "Neuer Artikel wurde angelegt: " + "ID: " + artikel.getArtikelID()+ "; Name: " + 
            artikel.getArtikel_name() + "; Bestand: " + artikel.getBestand() + "; Preis: " + artikel.getPreis() + ";";
        } else {
            beschreibung = stringBuilder.toString();
        }

        return new Ereignis(date, type, beschreibung);
    }

    public Ereignis ereignisErzeugen(KundenVerwaltung kunde, int anzahl, float price) {

        String type = "Kauf";
        date = LocalDate.now();
        String beschreibung = "Kunde " + kunde.getKundenID() + " | " + kunde.getName() + " hat" + anzahl + " Gegenstände gekauft";

        return new Ereignis(date, type, beschreibung);
    }

}
