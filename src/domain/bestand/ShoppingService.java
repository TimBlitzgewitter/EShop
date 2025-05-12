package domain.bestand;

import java.util.HashMap;
import java.util.Map;

public class ShoppingService {

    private Map<Artikel, Integer> warenkorb = new HashMap<>();

    public void inWarenkorbLegen(Artikel artikel, int stueckzahl) {
        if (stueckzahl <= 0) {
            return;
        }
        warenkorb.put(artikel, warenkorb.getOrDefault(artikel, 0) + stueckzahl);
    }

    public void stueckzahlAendern(Artikel artikel, int neueStueckzahl) {
        if (!warenkorb.containsKey(artikel)) {
            return;
        }
        if (neueStueckzahl <= 0) {
            warenkorb.remove(artikel); //wenn Stueckzahl 0 wird, wird der Artikel entfernt
        } else {
            warenkorb.put(artikel, neueStueckzahl);
        }
    }

    public void warenkorbLeeren() {
        warenkorb.clear();
    }

    public void kaufen() {
        for (Map.Entry<Artikel, Integer> entry : warenkorb.entrySet()) {
            Artikel artikel = entry.getKey();
            int stueckzahl = entry.getValue();
            if (artikel.getBestand() >= stueckzahl) {
                artikel.setBestand(artikel.getBestand() - stueckzahl);
            }
        }
        warenkorb.clear();
    }

    public Map<Artikel, Integer> getWarenkorb() {
        return new HashMap<>(warenkorb);
    }
}
