package Hauptbahnhof;

import java.awt.Color;
import java.util.Random;

public class Bahnhof {
	Zug[] gleise;
	int[] gleiseWarteschlange;
	static Bahnhof _bahnhof;
	int zaehler;

	Visualisierung ansicht;

	public Bahnhof(int tiefe, int breite, int anzahlGleise) {
		
		gleise = new Zug[anzahlGleise];
		gleiseWarteschlange = new int[anzahlGleise];
	}

	public synchronized void zugEinfahren(Zug zug, int gleisnummer) {

		if (gleise[gleisnummer] == null && zug != null) {
			gleise[gleisnummer] = zug;
			System.out.println(zug + " fährt auf Gleis " + gleisnummer + " ein. Willkommen im Hamburger Hauptbahnhof!");
			if (gleiseWarteschlange[gleisnummer] > 0) {
				gleiseWarteschlange[gleisnummer]--;
			}
		} else {
			gleiseWarteschlange[gleisnummer]++;
		}
	}

	public synchronized Zug zugAusfahren(int gleisnummer) {

		Zug temp = null;
		if (gleise[gleisnummer] != null) {
			temp = gleise[gleisnummer];
			gleise[gleisnummer] = null;
			System.out.println(temp + " fährt von Gleis " + gleisnummer + " nach Hause. In Hamburg sagt man Tschüss!");
			if (gleiseWarteschlange[gleisnummer] > 0) {
				gleiseWarteschlange[gleisnummer]--;
			}
		}
		return temp;
	}

	public void erstelleZuege() {
		for (int i = 0; i < gleise.length; i++) {
			Zug zug = new Zug(i);
			gleise[i] = zug;
		}
	}

	public Zug[] getGleise() {
		return gleise;
	}
	
	public int[] getGleiseWarteschlange() {
		return gleiseWarteschlange;
	}
}
