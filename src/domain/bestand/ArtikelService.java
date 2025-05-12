package domain.bestand;

public class ArtikelService {
    //anlegen neuer Artikel
    public Artikel artikelAnlegen(String kategorie_name, int id, String name, int bestand, int preis) {
        return new Artikel(kategorie_name, id, name, bestand, preis);
    }
    //bearbeiten bereits existierender Artikel
    public void artikelBearbeiten(Artikel artikel, String name, int bestand, int preis) {
        artikel.setArtikel_name(name);
        artikel.setBestand(bestand);
        artikel.setPreis(preis);
    }
}
