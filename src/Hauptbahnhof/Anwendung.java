package Hauptbahnhof;

public class Anwendung {
	public static void main(String[] args) {
	Bahnhof bahnhof = new Bahnhof();
	Bahnhof._bahnhof = bahnhof;
	Thread bahnhofThread = new Thread(bahnhof);
	bahnhofThread.start();
	}
}
