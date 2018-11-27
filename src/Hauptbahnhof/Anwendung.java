package Hauptbahnhof;

public class Anwendung {
	
	public static void main(String[] args) {
		Thread simulationThread = new Thread(new Simulation());
		simulationThread.start();
	}
}
