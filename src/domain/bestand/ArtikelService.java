package domain.bestand;

import java.util.ArrayList;
import java.util.List;

import domain.verwaltung.Ereignis;

public class ArtikelService {

    Ereignis ereignis = new Ereignis();

    private List<Artikel> artikelListe = new ArrayList<>();

    public void fuegeArtikelHinzu(Artikel artikel) {
        artikelListe.add(artikel);
    }

    public List<Artikel> getAlleArtikel() {
        return artikelListe;
    }

    //anlegen neuer Artikel
    public Artikel artikelAnlegen(String kategorie_name, int id, String name, int bestand, float preis) {
        Artikel neuerArtikel = new Artikel(kategorie_name, id, name, bestand, preis);
        ereignis.ereignisErzeugen(neuerArtikel, name, bestand, preis);
        return neuerArtikel;
    }
    
    //bearbeiten bereits existierender Artikel
    public Artikel artikelBearbeiten(Artikel artikel, String name, int bestand, float preis) {
        

        if (name.equals("")) {
            name = artikel.getArtikel_name();
        }
        if (bestand == -1) {
            bestand = artikel.getBestand();
        }
        if (preis == -1) {
            preis = artikel.getPreis();
        }

        ereignis.ereignisErzeugen(artikel, name, bestand, preis);
        
        return new Artikel(artikel.getKategorie(), artikel.getArtikelID(), name, bestand, preis);
    }

    public void updateArtikel(Artikel neuerArtikel) {
        for (int i = 0; i < artikelListe.size(); i++) {
            if (artikelListe.get(i).getArtikelID() == neuerArtikel.getArtikelID()) {
                artikelListe.set(i, neuerArtikel);
                return;
            }
        }
    }
}
