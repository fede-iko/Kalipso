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
		
		String userName = notEmptyIn(scan,"Inserisci il tuo nome:");
		
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
			String msg = "Frase numero "+(nTotal+1)+"\n\n";
			msg+=s.getSentenceText();
			msg+="\n\nCompletala:";			
			if(s.isCorrect(notEmptyIn(scan,msg))) {
				nGuessed++;
				out("\nRisposta corretta!");				
			}else {				
				out("\nRisposta errata!");
				out("La risposta giusta era:\n");
				out(s.getCorrectAnswer());
			}
			nTotal++;
			out("\n\nPremi invio per continuare!");
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
	
	public static String notEmptyIn(Scanner sc,String msg) {
		String userAnswer = "";
		while(userAnswer.equals("") || userAnswer.equals(" ")) {			
			out(msg);
			userAnswer = in(sc);
			if(userAnswer.equals("") || userAnswer.equals(" ")) {
				clear();
				out("Inserisci un'input valido!");
			}
		}
		return userAnswer;
	}
	
	public static void out(String msg) {
		System.out.println(msg);
	}
	
	public static String in(Scanner sc) {
		return sc.nextLine();		
	}
	
	public final static void clear(){
		for(int i = 0 ; i < 100;i++) {
			System.out.println("\n");
		}		
	} 
}
