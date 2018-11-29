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

		boolean gleisGefunden = false;
		int gleis = 0;
		
		if (_zahl % 2 == 1) {
//			int wartendeZuege = 0;
//			for (int i = 0; i < _gleiseWarteschlange.length; i++) {
//				if (wartendeZuege < _gleiseWarteschlange[i]) {
//					wartendeZuege = _gleiseWarteschlange[i];
//					gleis = i;
//					gleisGefunden = true;
//				}
//			}
			if (!gleisGefunden) {
				gleis = (int) (Math.random() * _gleiseWarteschlange.length);
			}
			_bahnhof.zugAusfahren(gleis);
		} else if (_zahl % 2 == 0) {
//			int wartendeZuege = Integer.MAX_VALUE;
//			for (int i = 0; i < _gleiseWarteschlange.length; i++) {
//				if (wartendeZuege > _gleiseWarteschlange[i]) {
//					wartendeZuege = _gleiseWarteschlange[i];
//					gleis = i;
//					gleisGefunden = true;
//				}
//			}
			if (!gleisGefunden) {
				gleis = (int) (Math.random() * _gleiseWarteschlange.length);
			}
			Zug zug = new Zug(_zahl);
			_bahnhof.zugEinfahren(zug, gleis);
		}
		
		for (int i = 0; i < _gleiseWarteschlange.length; i++) {
			System.out.print(_gleiseWarteschlange[i] + " ");
		}
		System.out.println();
		
	}
}
