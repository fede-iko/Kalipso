package application;

import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationMain {	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		out("Benvenuto nel gioco di cultura generale!");
		out("REGOLE:");
		out("- maiuscole e minuscole non vengono controllate");
		out("- non aggiungere punti alla fine delle frasi");
		out("- divertiti e impara!");
		out("PREMI INVIO PER CONTINUARE");
		in(scan);
		
		out("Inserisci il tuo nome:");
		String userName = in(scan);
		
		clear();
		
		out("Benvenuto "+userName);
		
		SentencesContainer sc = null;
		try {
			sc = new SentencesContainer();
			sc.createSentences();
			sc.shuffleSentences();
		} catch (SQLException e) {
			exitProgram("Impossibile scaricare le domande!");
		}
		
		out("INIZIAMO!\n");
		
		int nGuessed = 0;
		int nTotal = 0;
		for(Sentence s: sc.getSentences()) {
			out("Frase numero "+(nTotal+1));
			out(s.getSentenceText());
			out("Completala:");
			if(s.isCorrect(in(scan))) {
				nGuessed++;
				out("Risposta corretta!");				
			}else {				
				out("Risposta errata!");
				out("La risposta giusta era:");
				out(s.getCorrectAnswer());
			}
			nTotal++;
			out("Premi invio per continuare!");
			in(scan);
			clear();
		}
		
		out(userName+" hai completato il gioco!");
		
		out("Risultato:");
		
		float percent = (nGuessed * 100)/nTotal;
		
		out("Frasi completate correttamente: "+nGuessed+"/"+nTotal+" ("+percent+"%)");
		
		
		scan.close();
		
	}
	
	public static void exitProgram(String msg) {
		out(msg);
		System.exit(0);
	}
	
	
	public static void out(String msg) {
		System.out.println(msg);
	}
	
	public static String in(Scanner sc) {
		return sc.nextLine();		
	}
	
	public final static void clear(){  
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
	} 
}
