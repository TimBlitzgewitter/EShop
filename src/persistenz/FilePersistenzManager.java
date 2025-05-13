package persistenz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import domain.bestand.Artikel;

public class FilePersistenzManager implements PersistenzManager{


    private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(
					new BufferedWriter(
						new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
	}

	/**
	 * Methode zum Einlesen der Buchdaten aus einer externen Datenquelle.
	 * Das Verfügbarkeitsattribut ist in der Datenquelle (Datei) als "t" oder "f"
	 * codiert abgelegt.
	 * 
	 * @return Buch-Objekt, wenn Einlesen erfolgreich, false null
	 */
	public Artikel ladeArtikel() throws IOException {
		// Titel einlesen
		String idString = liesZeile();
		if (idString == null) {
			// keine Daten mehr vorhanden
			return null;
		}
        // ... und von String in int konvertieren
        int id = Integer.parseInt(idString);

		// Nummer einlesen ...
		String name = liesZeile();
		
		// Bestand
		String bestandString = liesZeile();
        int bestand = Integer.parseInt(bestandString);

        // Preis
        String preisString = liesZeile();
        float preis = Float.parseFloat(preisString);
		
        return new Artikel(id, name, bestand, preis);
	}

	/**
	 * Methode zum Schreiben der Buchdaten in eine externe Datenquelle.
	 * Das Verfügbarkeitsattribut wird in der Datenquelle (Datei) als "t" oder "f"
	 * codiert abgelegt.
	 * 
	 * @param b Buch-Objekt, das gespeichert werden soll
	 * @return true, wenn Schreibvorgang erfolgreich, false sonst
	 */

	public boolean speichereArtikel(Artikel artikel) throws IOException {
		
        schreibeZeile(Integer.valueOf(artikel.getArtikelID()).toString());
        schreibeZeile(artikel.getArtikel_name());
        schreibeZeile(Integer.valueOf(artikel.getBestand()).toString());
        schreibeZeile(Integer.valueOf(artikel.getPreis()).toString());

		return true;
	}

	/*
	 *  Wenn später mal eine Kundenverwaltung ergänzt wird:

	public Kunde ladeKunde() throws IOException {
		// TODO: Implementieren
		return null;
	}

	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO: Implementieren
		return false;
	}

	*/
	
	/*
	 * Private Hilfsmethoden
	 */
	
	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}
    

}
