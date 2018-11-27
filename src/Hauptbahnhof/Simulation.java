package Hauptbahnhof;

public class Simulation implements Runnable, IBeobachtbar{
	
	private Bahnhof _bahnhof;
	
	@Override
	public void run() {
		Bahnhof bahnhof = new Bahnhof(120, 80);
		Bahnhof._bahnhof = bahnhof;
		_bahnhof = bahnhof;
		bahnhof.erstelleLokfuehrer();
		
		int breite = 120;
		int tiefe = 80;
		Visualisierung visualisierung = new Visualisierung(tiefe, breite);
		anmelden(visualisierung);
		
		int zaehler = 0;
		
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

				System.out.println("Demo-Thread");
			}
			Lokfuehrer lokfuehrer = new Lokfuehrer(bahnhof, zaehler);
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
