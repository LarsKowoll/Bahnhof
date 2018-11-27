package Hauptbahnhof;

import java.awt.Color;
import java.util.Random;

public class Bahnhof {
	Zug[] gleise = new Zug[8];
	Zug[] zugUnterwegs = new Zug[8];
	static Bahnhof _bahnhof;
	int zaehler;
	// Die Standardbreite f�r ein Feld.
	private static final int STANDARD_BREITE = 120;
	// Die Standardtiefe f�r ein Feld.
	private static final int STANDARD_TIEFE = 80;
	Visualisierung ansicht;

	public Bahnhof(int tiefe, int breite) {
		if (breite <= 0 || tiefe <= 0) {
			System.out.println("Abmessungen m�ssen gr��er als null sein.");
			System.out.println("Benutze Standardwerte.");
			tiefe = STANDARD_TIEFE;
			breite = STANDARD_BREITE;
		}

//		ansicht = new Visualisierung(tiefe, breite);
		zaehler = 0;
	}

	public synchronized void zugEinfahren(Zug zug, int gleisnummer) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}
		if (gleise[gleisnummer] == null && zug != null) {
			gleise[gleisnummer] = zug;
			zugUnterwegs[gleisnummer] = null;
			System.out.println(zug + " f�hrt auf Gleis " + gleisnummer + " ein. Willkommen im Hamburger Hauptbahnhof!");
		}

	}

	public synchronized Zug zugAusfahren(int gleisnummer) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}

		Zug temp = null;
		if (gleise[gleisnummer] != null) {
			temp = gleise[gleisnummer];
			gleise[gleisnummer] = null;
			System.out.println(temp + " f�hrt von Gleis " + gleisnummer + " nach Hause. In Hamburg sagt man Tsch�ss!");
		}
		return temp;
	}

	public void erstelleLokfuehrer() {
		for (int i = 0; i < gleise.length; i++) {
			Zug zug = new Zug(i);
			gleise[i] = zug;

		}

		
	}
	
	public Zug[] getGleise() {
		return gleise;
	}

}
