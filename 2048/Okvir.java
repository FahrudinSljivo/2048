package javaProjekat;


import javax.swing.*;
import java.awt.*; //color, dimension
import java.awt.event.*;



public class Okvir extends JFrame {
	
	/**
	 * Definisemo atribute glavniPanel (prozor gdje cemo smjestiti plocu 4x4 kao i dugmad za pomjeranje), igraPanel na kojoj cemo prikazat trenutnu situaciju, atribut igra koji je tipa Logika gdje su definisane operacije pomjeranja blokova,
	 * dodavanja dvojke ili cetvorke u svakom potezu na random mjesto, ispitivanje da li je igra zavrsena, racunanje rezultata itd. Takodjer, imamo i cetiri instance tipa JButton koje nam predstavljaju tipke pomocu kojih pomjeramo blokove.
	 */
	
	JPanel glavniPanel, dugmad;
	
	Igra igraPanel;
	
	Logika igra;
	
	JButton tipkaLijevo, tipkaDesno, tipkaGore, tipkaDole;
	
	public Okvir () {
		/**
		 *  Pozivamo konstruktor bazne klase.
		 * */
		super();
		glavniPanel = new JPanel();
		
		igra = new Logika(4,4);
		
		igraPanel = new Igra(igra.kolone,igra.redovi);
		
		/**
		 * dugmad JPanel cemo ubaciti u glavniPanel i u njemu ce biti dugmad kojima cemo pomjerati blokove. Postavljamo mu boju pozadine na zeleno
		 * i layout postavljamo GridBagLayout koji sluzi da poravnamo elemente horizontalno ili vertikalno. Ovaj layout ne zahtijeva da elementi
		 * budu iste velicine te pomocu njega mozemo poredati elemente kako mi zelimo. Ono sto je nama u ovom slucaju korisno jeste da,
		 * komponente JPanela za koji vazi GridBagLayout, mozemo preko objekta klase GridBagConstraints poredati kako mi zelimo. Tako smo ovdje
		 * pozicionirali lijevu tipku u nultu kolonu i prvi red, desnu tipku u drugu kolonu i prvi red, gornju tipku u prvu kolonu i nulti red,
		 * te donju tipku u prvu kolonu i drugi red.
		 * */
		
		dugmad = new JPanel();
		
		dugmad.setBackground(Color.green);
		
		dugmad.setLayout(new GridBagLayout());
		
		GridBagConstraints pozicije = new GridBagConstraints();
		
		tipkaLijevo = new JButton("Pomjeri lijevo");
		pozicije.gridx = 0; pozicije.gridy = 1;
		dugmad.add(tipkaLijevo, pozicije);
		tipkaDesno = new JButton("Pomjeri desno");
		pozicije.gridx = 2; pozicije.gridy = 1;
		dugmad.add(tipkaDesno, pozicije);
		tipkaGore = new JButton("Pomjeri gore");
		pozicije.gridx = 1; pozicije.gridy = 0;
		dugmad.add(tipkaGore, pozicije);
		tipkaDole = new JButton("Pomjeri dole");
		pozicije.gridx = 1; pozicije.gridy = 2;
		dugmad.add(tipkaDole, pozicije);
		
		
		/**
		 * Definisemo kako ce biti rasporedjene stvari unutar prozora glavnog prozora (igraPanel i dugmad ce biti stackana jedna na drugim).
		 * Dodajemo prvo plocu za igranje a potom i drugi JPanel dugmad u glavni panel. Taj glavni panel najzad dodajemo u prozor (JFrame),
		 * odnosno u this objekat.
		 * */
		
		BoxLayout bp = new BoxLayout(glavniPanel, BoxLayout.Y_AXIS);
		
		glavniPanel.setLayout(bp);
		
		glavniPanel.add(igraPanel);
		
		glavniPanel.add(dugmad);
		
		this.add(glavniPanel);
		
		
		/**
		 * Za pocetak generisemo dvije dvice ili cetvorke kako bi zapoceli igru a potom azuriramo i ispisujemo (repaint) brojeve.
		 * */
		
		igra.dodajDvojkuIliCetvorku();
		igra.dodajDvojkuIliCetvorku();
		
		azurirajIgru();
		
		
		/**
		 * Definisemo 4 action listenera za svako dugme koji ce pozivati odgovarajuce funkcije (definisane u klasi logika) i potom azurirati i ispisati brojeve. Ove action listenere cemo kasnije pridruziti tipkama.
		 * 
		 * */
		
		ActionListener tipkanjeLijevo = new ActionListener() {
			public void actionPerformed(ActionEvent akcija) {
				
				igra.pomjeriLijevo();
				igra.dodajDvojkuIliCetvorku();
				
				azurirajIgru();
				
				if (igra.mozeLiSeIgrat() == false) krajIgre();
				
			}
		};
		
		ActionListener tipkanjeDesno = new ActionListener() {
			public void actionPerformed(ActionEvent akcija) {
				
				igra.pomjeriDesno();
				igra.dodajDvojkuIliCetvorku();
				
				azurirajIgru();
				
				if (igra.mozeLiSeIgrat() == false) krajIgre();
				
			}
		};
		
		ActionListener tipkanjeGore = new ActionListener() {
			public void actionPerformed(ActionEvent akcija) {
				
				igra.pomjeriGore();
				igra.dodajDvojkuIliCetvorku();
				
				azurirajIgru();
				
				if (igra.mozeLiSeIgrat() == false) krajIgre();
				
			}
		};
		
		ActionListener tipkanjeDole = new ActionListener() {
			public void actionPerformed(ActionEvent akcija) {
				
				igra.pomjeriDole();
				igra.dodajDvojkuIliCetvorku();
				azurirajIgru();
				
				
				if (igra.mozeLiSeIgrat() == false) krajIgre();
				
			}
		};
		
		tipkaLijevo.addActionListener(tipkanjeLijevo);
		tipkaDesno.addActionListener(tipkanjeDesno);
		tipkaGore.addActionListener(tipkanjeGore);
		tipkaDole.addActionListener(tipkanjeDole);
		
	}
	
	/**
	 * Funkcija koju pozivamo prilikom pocetka igre ili svaki put kada se pomjeri blok. U njoj kupimo vrijednosti iz instance klase Logika (igra)
	 * i kopiramo u igraPanel kako bi prikazali te vrijednosti na ekran. Na kraju ispisujemo na ekran trenutno stanje.
	 * */
	
public void azurirajIgru() {
	
	for (int i = 0; i < igra.redovi; i++) {
		for (int j = 0; j < igra.kolone; j++)  {
			int k = igra.vratiVrijednostPolja(j, i);
			igraPanel.setBroj(j, i, k);
		}
	}
	
	igraPanel.repaint();
	
}	

/**
 * Funkcija za kraj igre gdje nakon sto zavrsi igra prozorcic iskace i pita nas da li zelimo da nastavimo igru. 
 * Ako da onda se poziva funkcija novaIgra (definisana ispod - u njoj u sustini inicijalizujemo ponovo plocu sa nulama i dodajemo 2 dvojke/cetvorke
 * na random mjesta i onda azuriramo i ispisujemo plocu), a ako pritisnemo na tipku kraj, izlazimo iz programa 
 * (ovaj broj sam stavio bezveze jer http statusni kod 200 znaci da je sve ok, iako to nema nikakve veze sa ovim). 
 * Koristena je metoda showOptionDialog kojoj prosljedjujemo parametre redom: this komponentu koja predstavlja roditeljsku komponentu
 * ovog prozorcica - tj. okvir. Tekst unutar prozorcica, naziv prozorcica, broj mogucih opcija kao i njihov tip, tip poruke, ikonu koju zelimo
 * staviti, niz odakle uzimamo opcije te defaultna opcija
 * */
	
public void krajIgre() {
		
		String[] novaIgraKraj;
		novaIgraKraj = new String[] {"Nova igra", "Kraj"};
		
		int rezultat = JOptionPane.showOptionDialog (
				 this,
				 "Kraj igre.\nVas rezultat je " + igra.rezultat(),
				 "Kraj igre!",
				 JOptionPane.YES_NO_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 null,
				 novaIgraKraj,
				 novaIgraKraj[0]
		);
		
		if (rezultat == JOptionPane.YES_OPTION) novaIgra();
		else System.exit(200);
	}
	
/**
 * Inicijaliziramo novu plocu, dodajemo na dva slucajna mjesta na ploci 2 ili 4 te azuriramo brojeve na igraPanelu. Na kraju ispisujemo.
 * */

	public void novaIgra() {
		
		igra = new Logika(4,4);
		
		igra.dodajDvojkuIliCetvorku();
		igra.dodajDvojkuIliCetvorku();
		
		for (int i = 0; i < igra.redovi; i++) {
			for (int j = 0; j < igra.kolone; j++)  {
				int k = igra.vratiVrijednostPolja(j, i);
				igraPanel.setBroj(j, i, k);
			}
		}
		
		igraPanel.repaint();
		

	}
	
	
	
	
	
	
	public static void main(String[] args) { 

		Okvir okvir = new Okvir();
		
		
		 
		okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okvir.pack();
		okvir.setLocationRelativeTo(null);
		
		okvir.setVisible(true);
	}
	
}
