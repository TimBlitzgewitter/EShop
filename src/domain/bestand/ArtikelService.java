package domain.bestand;

import domain.verwaltung.Ereignis;

public class ArtikelService {
    Ereignis ereignis = new Ereignis();

    //anlegen neuer Artikel
    public Artikel artikelAnlegen(String kategorie_name, int id, String name, int bestand, float preis) {
        Artikel neuerArtikel = new Artikel(kategorie_name, id, name, bestand, preis);
        ereignis.ereignisErzeugen(neuerArtikel, name, bestand, preis);
        return neuerArtikel;
    }
    //bearbeiten bereits existierender Artikel
    public Artikel artikelBearbeiten(Artikel artikel, String name, int bestand, float preis) {
        ereignis.ereignisErzeugen(artikel, name, bestand, preis);
        
        return new Artikel(artikel.getKategorie(), artikel.getArtikelID(), name, bestand, preis);
    }
}
