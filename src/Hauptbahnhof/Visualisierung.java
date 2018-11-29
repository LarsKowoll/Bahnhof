package Hauptbahnhof;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;
import javax.swing.event.*;

public class Visualisierung extends JFrame implements IBeobachter {
	private final String BESETZTE_GLEISE_PREFIX = "Besetzte Gleise: ";
	private JLabel besetzteGleise;
	private Feldansicht feldansicht;

	/**
	 * Erzeuge eine Ansicht mit der gegebenen Breite und Höhe.
	 * 
	 * @param hoehe  die Höhe der Simulation
	 * @param breite die Breite der Simulation
	 */
	public Visualisierung(int hoehe, int breite) {
		
		setTitle("Simulation des Hamburger Hauptbahnhofs");
		besetzteGleise = new JLabel(BESETZTE_GLEISE_PREFIX, JLabel.CENTER);

		setLocation(20, 50);

		feldansicht = new Feldansicht(hoehe, breite);

		Container inhalt = getContentPane();
		inhalt.add(feldansicht, BorderLayout.CENTER);
		inhalt.add(besetzteGleise, BorderLayout.SOUTH);
		pack();
		setVisible(true);

	}

	public void zeigeStatus(Zug[] gleise) {
		feldansicht.zeichnenVorbereiten();

		for (int i = 0; i < gleise.length; i++) {
			feldansicht.zeichneGleise(5 * i + 19, 10, Color.black);

		}
		feldansicht.zeichneBahnhof(19, 10, Color.black, gleise.length);

		int belegteGleise = 0;
		for (int i = 0; i < gleise.length; i++) {
			if (gleise[i] != null) {
				feldansicht.zeichneZuege(5 * i + 20, 10, Color.red);
				belegteGleise++;
			} else {
				feldansicht.zeichneZuege(5 * i + 20, 10, Color.white);
			}
		}
		besetzteGleise.setText(BESETZTE_GLEISE_PREFIX + belegteGleise);
		feldansicht.repaint();
	}

	/**
	 * Liefere eine grafische Ansicht eines rechteckigen Feldes. Dies ist eine
	 * geschachtelte Klasse (eine Klasse, die innerhalb einer anderen Klasse
	 * definiert ist), die eine eigene grafische Komponente fuer die
	 * Benutzungsschnittstelle definiert. Diese Komponente zeigt das Feld an. Dies
	 * ist fortgeschrittene GUI-Technik - Sie oennen sie fuer Ihr Projekt
	 * ignorieren, wenn Sie wollen.
	 */
	private class Feldansicht extends JPanel {
		private final int DEHN_FAKTOR = 6;

		private int feldBreite, feldHoehe;
		private int xFaktor, yFaktor;
		Dimension groesse;
		private Graphics g;
		private Image feldImage;

		/**
		 * Erzeuge eine neue Komponente zur Feldansicht.
		 */
		public Feldansicht(int hoehe, int breite) {
			feldHoehe = hoehe;
			feldBreite = breite;
			groesse = new Dimension(0, 0);
		}

		/**
		 * Der GUI-Verwaltung mitteilen, wie groß wir sein wollen. Der Name der Methode
		 * ist durch die GUI-Verwaltung festgelegt.
		 */
		public Dimension getPreferredSize() {
			return new Dimension(feldBreite * DEHN_FAKTOR, feldHoehe * DEHN_FAKTOR);
		}

		/**
		 * Bereite eine neue Zeichenrunde vor. Da die Komponente in der Größe
		 * geändert werden kann, muss der Maßstab neu berechnet werden.
		 */
		public void zeichnenVorbereiten() {
			if (!groesse.equals(getSize())) { // Groesse wurde geaendert...
				groesse = getSize();
				feldImage = feldansicht.createImage(groesse.width, groesse.height);
				g = feldImage.getGraphics();

				xFaktor = groesse.width / feldBreite;
				if (xFaktor < 1) {
					xFaktor = DEHN_FAKTOR;
				}
				yFaktor = groesse.height / feldHoehe;
				if (yFaktor < 1) {
					yFaktor = DEHN_FAKTOR;
				}
			}
		}

		/**
		 * Zeichne an der gegebenen Position ein Rechteck mit der gegebenen Farbe.
		 */
		public void zeichneZuege(int x, int y, Color farbe) {
			g.setColor(farbe);
			g.fillRect(x * xFaktor, y * yFaktor, 5, 40);
		}

		public void zeichneGleise(int x, int y, Color farbe) {
			g.setColor(farbe);
			g.drawRect(x * xFaktor, y * yFaktor, 20, 50);
		}

		public void zeichneBahnhof(int x, int y, Color farbe, int gleise) {
			g.setColor(farbe);
			g.drawRect(x * xFaktor, y * yFaktor, 29 * gleise, 70);
		}

		/**
		 * Die Komponente für die Feldansicht muss erneut angezeigt werden. Kopiere das
		 * interne Image in die Anzeige. Der Name der Methode ist durch die
		 * GUI-Verwaltung festgelegt.
		 */
		public void paintComponent(Graphics g) {
			if (feldImage != null) {
				Dimension aktuelleGroesse = getSize();
				if (groesse.equals(aktuelleGroesse)) {
					g.drawImage(feldImage, 0, 0, null);
				} else {
					// Groesse des aktuellen Images anpassen.
					g.drawImage(feldImage, 0, 0, aktuelleGroesse.width, aktuelleGroesse.height, null);
				}
			}
		}
	}

	@Override
	public void aktualisieren(IBeobachtbar beobachtbar) {
		zeigeStatus(beobachtbar.gibZustand());
	}
}
