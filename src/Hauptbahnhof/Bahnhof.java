package Hauptbahnhof;

import java.util.Random;

public class Bahnhof extends Thread {
	Zug[] Gleise = new Zug[8];
	Zug[] zugUnterwegs = new Zug[8];
	static Bahnhof _bahnhof;
	int zaehler;

	public Bahnhof() {

	}

	public synchronized void zugEinfahren(Zug zug, int gleisnummer) {
		try {
			sleep(500);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}
		if (Gleise[gleisnummer] == null && zug != null) {
			Gleise[gleisnummer] = zug;
			zugUnterwegs[gleisnummer] = null;
			System.out.println(zug + " Willkommen im Hamburger Hauptbahnhof");
		}
		
	}

	public synchronized Zug zugAusfahren(int gleisnummer) {
		try {
			sleep(500);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}

		Zug temp = null;
		if (Gleise[gleisnummer] != null) {
			temp = Gleise[gleisnummer];
			Gleise[gleisnummer] = null;
			System.out.println(temp + " fährt nach Hause. In Hamburg sagt man Tschüss");		
		}
		return temp;
	}

	@Override
	public void run() {
		for (int i = 0; i < Gleise.length; i++) {
			Zug zug = new Zug();
			Gleise[i] = zug;

		}
		try {
			sleep(500);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}
		while (true) {
			Lokfuehrer lokfuehrer = new Lokfuehrer(_bahnhof, zaehler);
			Thread lokfuehrerThread = new Thread(lokfuehrer);
			lokfuehrerThread.start();
			zaehler++;
			
		}
			
	}
	

}
