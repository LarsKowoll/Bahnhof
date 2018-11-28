package Hauptbahnhof;

public class Simulation implements Runnable, IBeobachtbar{
	
	private Bahnhof _bahnhof;
	private int[] _gleiseWarteschlange;
	private int _anzahlGleise;
	
	public Simulation(int anzahlGleise) {
		_anzahlGleise = anzahlGleise;
		_gleiseWarteschlange = new int[anzahlGleise];
	}
	
	@Override
	public void run() {
		Bahnhof bahnhof = new Bahnhof(120, 80, 10);
		Bahnhof._bahnhof = bahnhof;
		_bahnhof = bahnhof;
		bahnhof.erstelleLokfuehrer();
		
		int breite = 120;
		int tiefe = 80;
		Visualisierung visualisierung = new Visualisierung(tiefe, breite);
		anmelden(visualisierung);
		
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
			visualisierung.aktualisieren(this);
		}
	}

	@Override
	public void anmelden(IBeobachter beobachter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void abmelden(IBeobachter beobachter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Zug[] gibZustand() {
		return _bahnhof.getGleise();
	}

}
