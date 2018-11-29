package Hauptbahnhof;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable, IBeobachtbar{
	
	private Bahnhof _bahnhof;
	private int[] _gleiseWarteschlange;
	private int _anzahlGleise;
	private List<IBeobachter> _beobachter;
	
	public Simulation(int anzahlGleise) {
		_anzahlGleise = anzahlGleise;
		_gleiseWarteschlange = new int[anzahlGleise];
		_beobachter = new ArrayList<IBeobachter>();
	}
	
	@Override
	public void run() {
		Bahnhof bahnhof = new Bahnhof(120, 80, 10);
		Bahnhof._bahnhof = bahnhof;
		_bahnhof = bahnhof;
		bahnhof.erstelleZuege();
		
		int breite = 120;
		int tiefe = 80;
		anmelden(new Visualisierung(tiefe, breite));
		
		int zaehler = _anzahlGleise;
		
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

				System.out.println("Demo-Thread");
			}
			_gleiseWarteschlange = bahnhof.getGleiseWarteschlange();
			Lokfuehrer lokfuehrer = new Lokfuehrer(bahnhof, zaehler, _gleiseWarteschlange);
			Thread lokfuehrerThread = new Thread(lokfuehrer);
			lokfuehrerThread.start();

			zaehler++;
			
			for (IBeobachter beobachter: _beobachter) {
				beobachter.aktualisieren(this);
			}
		}
	}

	@Override
	public void anmelden(IBeobachter beobachter) {
		_beobachter.add(beobachter);
	}

	@Override
	public void abmelden(IBeobachter beobachter) {
		_beobachter.remove(beobachter);
	}

	@Override
	public Zug[] gibZustand() {
		return _bahnhof.getGleise();
	}
	
	public static void main(String[] args) {
		Thread simulationThread = new Thread(new Simulation(10));
		simulationThread.start();
	}
}
