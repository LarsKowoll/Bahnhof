package Hauptbahnhof;

public class Lokfuehrer extends Thread {

	Bahnhof _bahnhof;
	boolean fertig;
	int _zahl;

	public Lokfuehrer(Bahnhof bahnhof, int zahl) {
		_bahnhof = bahnhof;
		_zahl = zahl;
		fertig = false;
	}

	public void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}
		while (true) {
			if (!fertig) {
				if (_zahl % 2 == 0) {
					_bahnhof.zugAusfahren((int) (Math.random() * (8)));
				} else if (_zahl % 2 == 1) {
					Zug zug = new Zug(_zahl);
					_bahnhof.zugEinfahren(zug, (int) (Math.random() * (8)));
				}
				
				
			}
			break;
		}
	}

}
