package Hauptbahnhof;

public class Anwendung {
	public static void main(String[] args) {
	Bahnhof bahnhof = new Bahnhof(120, 80);
	Bahnhof._bahnhof = bahnhof;
	bahnhof.erstelleLokfuehrer();
	}
}
