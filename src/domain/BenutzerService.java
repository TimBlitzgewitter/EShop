package domain;

import java.util.Map;
import java.util.HashMap;
import domain.verwaltung.Benutzer;
import domain.verwaltung.KundenVerwaltung;
import domain.verwaltung.MitarbeiterVerwaltung;

public class BenutzerService {
    private Map<String, Benutzer> benutzerMap = new HashMap<>();
    private Benutzer eingeloggterBenutzer = null;

    // Konstruktor mit Testdaten die initialiserrrr
    public BenutzerService() {
        initTestdaten();
    }

    private void initTestdaten() {
        // Test-Mitarbeiter
        MitarbeiterVerwaltung admin = new MitarbeiterVerwaltung("System Admin", "admin", "admin123", 1);
        benutzerMap.put(admin.getBenutzerkennung(), admin);

        // Test-Kunde
        KundenVerwaltung kunde = new KundenVerwaltung("Max Mustermann", "kunde", "kunde123", 
                                                    "Musterstraße 1, 12345 Berlin", 1001);
        benutzerMap.put(kunde.getBenutzerkennung(), kunde);
    }

    public boolean registrierenKunde(String name, String benutzerkennung, String passwort, String adresse, int kundenID) {
        if (benutzerMap.containsKey(benutzerkennung)) {
            return false;
        }

        KundenVerwaltung neuerKunde = new KundenVerwaltung(name, benutzerkennung, passwort, adresse, kundenID);
        benutzerMap.put(benutzerkennung, neuerKunde);
        return true;
    }

    public boolean registriereMitarbeiter(String name, String benutzerkennung, String passwort, int mitarbeiterID) {
        if (!(eingeloggterBenutzer instanceof MitarbeiterVerwaltung)) { 
            return false;
        }
        if (benutzerMap.containsKey(benutzerkennung)) {
            return false;
        }

        MitarbeiterVerwaltung neuerMitarbeiter = new MitarbeiterVerwaltung(name, benutzerkennung, passwort, mitarbeiterID);
        benutzerMap.put(benutzerkennung, neuerMitarbeiter);
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
    
