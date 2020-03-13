package javaProjekat;

import javax.swing.*;
import java.awt.*; //color, dimension

public class Igra extends JPanel {
	
	/** 
	 * Ova klasa nam pomaze samo da prikazemo trenutno stanje na ekran (mozemo njenu instancu ubacit u instancu klase glavniPanel, tj. u glavni prozor), dok se sve promjene zbivaju u klasi Logika.
	 */
	
	int kolone, redovi;
	BrojPolje matricaBrojeva[][];
	
	void inicijalizacija(int _kolone, int _redovi) {
		/**
		 * Inicijaliziramo sve elemente matrica sa nulama. 
		 */
		kolone = _kolone;
		redovi = _redovi;
		setLayout(new GridLayout(redovi, kolone));
		matricaBrojeva = new BrojPolje[kolone][redovi];
		for (int i = 0; i < redovi; i++) {
			 for(int j = 0; j < kolone; j++) {
				 matricaBrojeva[j][i] = new BrojPolje(0);
				 add(matricaBrojeva[j][i]);
			 }
		}
	}
	
	/**
	 * Konstruktor klase koji poziva funkciju za inicijalizovanje, klasicni seter i geter.
	 */
	
	public Igra (int _kolone, int _redovi) {
		inicijalizacija(_kolone, _redovi);
	}
	
	void setBroj (int x, int y, int _vrijednost) {
		matricaBrojeva[x][y].setVrijednost(_vrijednost);
	}
	
	int getBroj (int x, int y) {
		return matricaBrojeva[x][y].getVrijednost();
	}
	
 }
