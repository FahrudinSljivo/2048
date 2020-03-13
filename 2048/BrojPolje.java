package javaProjekat;

import javax.swing.*;
import java.awt.*; //color, dimension

public class BrojPolje extends JComponent {
	
	/** 
	 *	velicina predstavlja velicinu (u pikselima) kvadrata gdje se nalazi polje, a granica je razmak medju poljima. Jos imamo velicinu fonta kao i tip fonta.
	 */
	
	static final int velicina = 100;
	static final int granica = velicina/10;
	static final int velicinaFonta = (int)(velicina*0.4);
	Font FONT = new Font("Consolas", Font.PLAIN, 20);
	
	int vrijednost;
	
	/** 
	 *	Seter za neko polje 
	 */
	
	void setVrijednost(int _vrijednost) {
		vrijednost = _vrijednost;
	}
	
	/** 
	 *	Geter za neko polje 
	 */
	
	int getVrijednost() {
		return vrijednost;
	}
	
	/** 
	 * dajemo vrijednost polju, postavljamo mu font koji smo definisali u pocetku kao i velicinu
	 */
	public BrojPolje(int _vrijednost) {
		vrijednost = _vrijednost;
		this.setFont(FONT);
		this.setPreferredSize(new Dimension(velicina, velicina));
	}
	
	public void paintComponent(Graphics g) {
		/**
		 * odredjujemo boju i granice citavog polja
		*/
		
		g.setColor(Color.white);
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		/**
		 * odredjujemo boju u zavisnosti od cifre koja se nalazi unutar kvadrata i granice kvadrata u polju (u kojem ce biti smjesten broj)
		 */
		Color boja;
		if (vrijednost == 0) {
			boja = Color.black;
		} else {
			int len = Integer.numberOfTrailingZeros(vrijednost);
			boja = Color.getHSBColor(len / 10.0f, 1.5f, 1.0f);
		}
		g.setColor(boja);
		g.fillRoundRect(granica, granica, velicina-granica-granica, velicina-granica-granica, velicina/3, velicina/3);
		/**
		 * odredjujemo boju teksta unutar kvadrata
		*/
		g.setColor(Color.red);
		g.drawString(String.valueOf(vrijednost), velicina/2, velicina/2);
	}
	
}
