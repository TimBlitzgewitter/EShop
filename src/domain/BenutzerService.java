package domain;

import java.util.Map;

import domain.verwaltung.Benutzer;
import domain.verwaltung.KundenVerwaltung;
import domain.verwaltung.MitarbeiterVerwaltung;

import java.util.HashMap;

public class BenutzerService {
    private Map<String, Benutzer> benutzerMap = new HashMap<>(); //Map zum Speichern der Benutzer. Key ist Benutzerkennung, Value ist ein Benutzer-Objekt
    private Benutzer eingeloggterBenutzer = null; //referenziert den aktuell eingeloggten Benutzer. Wenn keiner eingeloggt ist, ist es null.


    public boolean registrierenKunde(String name, String benutzerkennung, String passwort, String adresse, int kundenID) {
        if (benutzerMap.containsKey(benutzerkennung)) {
            return false; //wenn Benutzerkennung bereits verwendet wird, ist die Registrierung nicht möglich
        }

        KundenVerwaltung neuerKunde = new KundenVerwaltung(name, benutzerkennung, passwort, adresse, kundenID);
        benutzerMap.put(benutzerkennung, neuerKunde);
        return true;
    }

    public boolean registriereMitarbeiter( String name, String benutzerkennung, String passwort, int mitarbeiterID) {
        if (!(eingeloggterBenutzer instanceof MitarbeiterVerwaltung)) { 
            return false; //wenn eingeloggterBenutzer kein Mitarbeiter ist, kann diese Methode nicht ausgeführt werden
        }
        if (benutzerMap.containsKey(benutzerkennung)) {
            return false; //wenn Benutzerkennung bereits vergeben, Registrierung nicht möglich
        }

        //wenn alles korrekt ist, wird ein neuer Mitarbeiter angelegt
        MitarbeiterVerwaltung neuerMitarbeiter = new MitarbeiterVerwaltung(name, benutzerkennung, passwort, mitarbeiterID);
        benutzerMap.put(benutzerkennung, neuerMitarbeiter);
        return true;
    }


    public Benutzer einloggen(String benutzerkennung, String passwort) { //Login mit gegebenen Eingabedaten
        Benutzer benutzer = benutzerMap.get(benutzerkennung);
        if (benutzer != null && benutzer.pruefePasswort(passwort)) {
            eingeloggterBenutzer = benutzer; //wenn Login erfolgreich war, ist dies der eingeloggte Benutzer
            return benutzer;
        }
        return null;
    }

    public void ausloggen() {
        eingeloggterBenutzer = null; //ausloggen
    }

    public Benutzer getEingeloggterBenutzer() {
        return eingeloggterBenutzer;
    }
}