package domain.bestand;

public class Artikel extends Kategorie{

    private int artikelID;
    private String artikel_name;
    private int bestand;
    private int preis;

    Artikel(String kategorie_name, int id, String artikel_name, int bestand, int preis) {
        super(kategorie_name);
        this.artikelID = id;
        this.artikel_name = artikel_name;
        this.bestand = bestand;
        this.preis = preis;
    }

    public int getArtikelID() {
        return artikelID;
    }

    public int getBestand() {
        return bestand;
    }

    public String getArtikel_name() {
        return artikel_name;
    }

    public int getPreis() {
        return preis;
    }

    void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    void setBestand(int bestand) {
        this.bestand = bestand;
    }

    void setArtikel_name(String artikel_name) {
        this.artikel_name = artikel_name;
    }

    void setPreis(int preis) {
        this.preis = preis;
    }
}
