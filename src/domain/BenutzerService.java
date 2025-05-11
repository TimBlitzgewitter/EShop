package domain;

import java.util.Map;

import domain.verwaltung.Benutzer;
import domain.verwaltung.KundenVerwaltung;

import java.util.HashMap;

public class BenutzerService {
    private Map<String, Benutzer> benutzerMap = new HashMap<>();
    private Benutzer eingeloggterBenutzer = null;


    public boolean registrierenKunde(String name, String benutzerkennung, String passwort, String adresse, int kundenID) {
        if (benutzerMap.containsKey(benutzerkennung)) {
            return false;
        }

        KundenVerwaltung neuerKunde = new KundenVerwaltung(name, benutzerkennung, passwort, adresse, kundenID);
        benutzerMap.put(benutzerkennung, neuerKunde);
        return true;
    }

    public Benutzer einloggen(String benutzerkennung, String passwort) {
        Benutzer benutzer = benutzerMap.get(benutzerkennung);
        if (benutzer != null && benutzer.pruefePasswort(passwort)) {
            eingeloggterBenutzer = benutzer;
            return benutzer;
        }
        return null;
    }

    public void ausloggen() {
        eingeloggterBenutzer = null;
    }

    public Benutzer getEingeloggterBenutzer() {
        return eingeloggterBenutzer;
    }
}