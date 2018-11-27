package Hauptbahnhof;

public class Zug {
	
	private int _number;
	
	public Zug(int number) {
		_number = number;
	}
	
	@Override
	public String toString() {
		return "Zug " + _number;
	}
}
