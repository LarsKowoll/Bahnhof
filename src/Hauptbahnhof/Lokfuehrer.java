package Hauptbahnhof;

public class Lokfuehrer implements Runnable {

	Bahnhof _bahnhof;
	boolean fertig;
	int _zahl;

	public Lokfuehrer(Bahnhof bahnhof, int zahl) {
		_bahnhof = bahnhof;
		_zahl = zahl;
		fertig = false;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}

		if (_zahl % 2 == 0) {
			_bahnhof.zugAusfahren((int) (Math.random() * (8)));
		} else if (_zahl % 2 == 1) {
			Zug zug = new Zug(_zahl);
			_bahnhof.zugEinfahren(zug, (int) (Math.random() * (8)));
		}
		
		
	}
}
