package Hauptbahnhof;

public class Lokfuehrer implements Runnable {

	Bahnhof _bahnhof;
	int _zahl;
	int[] _gleiseWarteschlange;

	public Lokfuehrer(Bahnhof bahnhof, int zahl, int[] gleiseWarteschlange) {
		_bahnhof = bahnhof;
		_zahl = zahl;
		_gleiseWarteschlange = gleiseWarteschlange;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			System.out.println("Demo-Thread");
		}

		if (_zahl % 2 == 0) {
			int gleis = 0;
			for (int i = 0; i < _gleiseWarteschlange.length; i++) {
				if (gleis < _gleiseWarteschlange[i]) {
					gleis = _gleiseWarteschlange[i];
				}
			}
			_bahnhof.zugAusfahren(gleis);
		} else if (_zahl % 2 == 1) {
			int gleis = Integer.MAX_VALUE;;
			for (int i = 0; i < _gleiseWarteschlange.length; i++) {
				if (gleis > _gleiseWarteschlange[i]) {
					gleis = _gleiseWarteschlange[i];
				}
			}
			Zug zug = new Zug(_zahl);
			_bahnhof.zugEinfahren(zug, gleis);
		}
		
		
	}
}
