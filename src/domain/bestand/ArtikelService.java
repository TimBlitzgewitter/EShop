package domain.bestand;

public class ArtikelService {

    public Artikel artikelAnlegen(String kategorie_name, int id, String name, int bestand, int preis) {
        return new Artikel(kategorie_name, id, name, bestand, preis);
    }

    public void artikelBearbeiten(Artikel artikel, String name, int bestand, int preis) {
        artikel.setArtikel_name(name);
        artikel.setBestand(bestand);
        artikel.setPreis(preis);
    }
}
