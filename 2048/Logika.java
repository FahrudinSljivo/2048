package javaProjekat;

import java.util.Random;
import javax.swing.*;
import java.awt.*; //color, dimension

public class Logika {
	
		public final int kolone;
		public final int redovi;
		
		private int[][] matrica;
		
		 public void ispis() {
			 for (int i = 1; i <= redovi; i++) {
				 for (int j = 1; j <= kolone; j++) {
				 System.out.print(matrica[j][i]);
				 }
			 System.out.println();
			 }
		 }
		 
		 /**
		  * Inicijaliziramo plocu tako da imamo dodatan prazan red odozgo i odozdo, kao i praznu kolonu slijeva i zdesna.
		  * */
		
		 public Logika(final int _kolone, final int _redovi) throws IllegalArgumentException {
			 this.kolone = _kolone;
			 this.redovi = _redovi;
			 matrica = new int[kolone + 2][redovi + 2];
			 for (int j = 0; j < kolone + 2; j++) {
				 for (int i = 0; i < redovi + 2; i++) {
					 matrica[j][i] = 0;
				 }
			 }
		 }
		 
		 /**
		  * Vracamo vrijednost matrice brojeva sa trazenim koordinatama
		  * */
		
		 public int vratiVrijednostPolja(int kol, int red) throws IndexOutOfBoundsException {
			 if (kol < 0 || kol >= kolone || red < 0 || red >= redovi)
			 throw new IndexOutOfBoundsException();
			 return matrica[kol+1][red+1];
		 }
		
		 /**
		  * Postavljamo proslijedjenu vrijednost na koordinate proslijedjene takodjer kao parametri.
		  * */
		 
		 public void postaviVrijednostPolja(int kol, int red, int vrijednost) throws IndexOutOfBoundsException {
			 if (kol < 0 || kol >= kolone || red < 0 || red >= redovi)
			 throw new IndexOutOfBoundsException();
			 matrica[kol+1][red+1] = vrijednost;
		 }
		 
		 /**
		  * Funkcija koja sluzi za pomjeranje blokova prema gore. Idemo dvjema petljama i gledamo u koji red blokovi odozdo trenutno dolaze.
		  * U slucaju da smo u unutrasnjoj petlji u istom redu u koji dolaze blokovi, preskacemo tu iteraciju. Dalje, ako su dva susjedna
		  * bloka jednakih vrijednosti, gornju vrijednost uduplavamo, donju postavljamo na 0 te povecavamo vrijednost u_koji_red. Inace,
		  * pomjeramo blokove prema gore. 
		  * */
		 
		 public void pomjeriGore() {
			 int u_koji_red;
			 for (int j = 1; j <= kolone; j++) {
				 u_koji_red = 1;
				 for (int i = 2; i <= redovi; i++) {
					 if (u_koji_red == i || matrica[j][i] == 0) {
					 continue;
					 } else if (matrica[j][i] == matrica[j][u_koji_red]) {
						 matrica[j][u_koji_red] = matrica[j][u_koji_red] * 2;
						 matrica[j][i] = 0;
						 u_koji_red++;
					 } else {
						 if (matrica[j][u_koji_red] != 0) u_koji_red++;
						 if (u_koji_red != i) {
							 matrica[j][u_koji_red] = matrica[j][i];
							 matrica[j][i] = 0;
						 }
					 }
				 }
			 }
		}
		 
		 /**
		  * Analogno funkciji pomjeriGore.
		  * */

		 public void pomjeriDole() {
				 int u_koji_red;
				 for (int j = 1; j <= kolone; j++) {
					 u_koji_red = redovi;
					 for (int i = redovi - 1; i >= 1; i--) {
					 if (u_koji_red == i || matrica[j][i] == 0) {
						 continue;
					 } else if (matrica[j][i] == matrica[j][u_koji_red]) {
						 matrica[j][u_koji_red] = matrica[j][u_koji_red] * 2;
						 matrica[j][i] = 0;
						 u_koji_red--;
					 } else {
						 if (matrica[j][u_koji_red] != 0)
							 u_koji_red--;
						 if (u_koji_red != i) {
							 matrica[j][u_koji_red] = matrica[j][i];
							 matrica[j][i] = 0;
						 }
					 }
					 }
				 }
			 }

		 /**
		  * Analogno funkciji pomjeriGore.
		  * */
			 
			 public void pomjeriLijevo() {
				 int u_koju_kolonu;
				 for (int i = 1; i <= redovi; i++) {
					 u_koju_kolonu = 1;
					 for (int j = 2; j <= kolone; j++) {
						 if (u_koju_kolonu == j || matrica[j][i] == 0) {
						 continue;
						 } else if (matrica[j][i] == matrica[u_koju_kolonu][i]) {
							 matrica[u_koju_kolonu][i] = matrica[u_koju_kolonu][i] * 2;
							 matrica[j][i] = 0;
							 u_koju_kolonu++;
						 } 
						 else {
							 if (matrica[u_koju_kolonu][i] != 0)
								 u_koju_kolonu++;
							 if (u_koju_kolonu != j) {
								 matrica[u_koju_kolonu][i] = matrica[j][i];
								 matrica[j][i] = 0;
								 }
						 }
					 }
				}
			}

			 /**
			  * Analogno funkciji pomjeriGore.
			  * */

			public void pomjeriDesno() {
				 int u_koju_kolonu;
				 for (int i = 1; i <= redovi; i++) {
					 u_koju_kolonu = kolone;
					 for (int j = kolone - 1; j >= 1; j--) {
						 if (u_koju_kolonu == j || matrica[j][i] == 0) {
						 continue;
						 } 
						 else if (matrica[j][i] == matrica[u_koju_kolonu][i]) {
						 matrica[u_koju_kolonu][i] = matrica[u_koju_kolonu][i] * 2;
						 matrica[j][i] = 0;
						 u_koju_kolonu--;
						 } 
						 else {
							 if (matrica[u_koju_kolonu][i] != 0)
								 u_koju_kolonu--;
							 if (u_koju_kolonu != j) {
							 matrica[u_koju_kolonu][i] = matrica[j][i];
							 matrica[j][i] = 0;
							 }
						 }
					 }
				 }
			}
			
			/**
			 * Generisemo dva random broja izmedju 1 i 5 te ispitujemo da li je matrica na tim pozicijama jednaka 0. Ako jeste, alociramo
			 * to polje za 2 ili 4 u zavisnosti od slucajnog broja izmedju 0 i 1.
			 * */

			public boolean dodajDvojkuIliCetvorku() {
				int kolona;
				int red;
				Random random = new Random();
	
				if (matricaPuna()) {
					return false;
				}
	
				do {
					kolona = random.nextInt(kolone) + 1;
					red = random.nextInt(redovi) + 1;
				} while (matrica[kolona][red] != 0);
	
				int kojiBroj = random.nextInt(2);
				
				if (kojiBroj == 0) matrica[kolona][red] = 2;
				else matrica[kolona][red] = 4;
				
				return true;
			}
			
			/**
			 * Ako matrica nije puna i ako se neka dva susjedna polja mogu spojiti, igra nije gotova. Inace jeste.
			 * */

				 
			public boolean mozeLiSeIgrat() {
				// da li je ostalo praznih mjesta
				if (!matricaPuna()) return true;
				// da li se neka dva polja mogu spojit
				for (int j = 1; j <= kolone; j++) {
					 for (int i = 1; i <= redovi; i++) {
						 if (matrica[i][j] == matrica[i + 1][j]
						 || matrica[i][j] == matrica[i][j + 1]
						 || matrica[i][j] == matrica[i - 1][j]
						 || matrica[i][j] == matrica[i][j - 1]) return true;
					 }
				}
				
				return false;
			}
			
			/**
			 * Sabiramo vrijednosti svih polja matrice te sumu vracamo na zavrsetku igre.
			 * */

			public int rezultat() {
				int rez = 0;
				for (int j = 1; j <= kolone; j++) {
					for (int i = 1; i <= redovi; i++) {
							rez += matrica[j][i];
					}
				}
				return rez;
			}
			
			/**
			 * Sluzi kod odlucivanja da li je zavrsena igrica. Vraca true ako nema nigdje nule na ploci. False inace.
			 * */

			public boolean matricaPuna() {
				for (int j = 1; j <= kolone; j++) {
					for (int i = 1; i <= redovi; i++) {
						 if (matrica[j][i] == 0) {
							 return false;
						 }
					}
				}
				return true;
			}
						
				 
};
