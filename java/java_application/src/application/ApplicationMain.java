package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ApplicationMain {
	
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static final String ANSI_RED = "\u001B[31m";
	
	public static final String ANSI_GREEN = "\u001B[32m";
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		clear();
		out("Benvenuto nel gioco di cultura generale!");
		out("REGOLE:");
		out("- maiuscole e minuscole non vengono controllate");
		out("- non aggiungere punti alla fine delle frasi");
		out("- divertiti e impara!");
		out("PREMI INVIO PER CONTINUARE");
		in(scan);
		
		clear();
		
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
		
		List<String[]> resultList = new ArrayList<String[]>();
		
		for(Sentence s: sc.getSentences()) {
			String msg = "Frase numero "+(nTotal+1)+"\n\n";
			msg+=s.getSentenceText()+" "+getCensoredAnswer(s.getCorrectAnswer(),false);			
			msg+="\n\nCompletala:";		
			
			String userAnswer = notEmptyIn(scan,msg);
			
			
			
			if(s.isCorrect(userAnswer)) {
				nGuessed++;
				resultList.add(new String[]{s.getSentenceText(),s.getCorrectAnswer(),""});										
			}else {
				resultList.add(new String[]{s.getSentenceText(),s.getCorrectAnswer(),userAnswer});				
			}
			nTotal++;
			out("\n\nPremi invio per continuare!");
			in(scan);
			clear();
		}
		
		out(userName+" hai completato il gioco con un punteggio di:");
		
		float percent = (nGuessed * 100)/nTotal;
		
		out("Frasi completate correttamente: "+getAnsiPoints(nGuessed)+nGuessed+"/"+nTotal+" ("+percent+"%)"+ANSI_RESET);		
		
		out("\nRisultati:");
		
		int roundCounter = 1;
		
		for(String[] arr: resultList) {
			String msg = roundCounter+" : "+arr[0]+"... ";
			
			if(arr[2]=="") {
				msg+=arr[1]+" | "+ANSI_GREEN+" CORRETTA "+ANSI_RESET;
			}else {
				msg+=getCensoredAnswer(arr[1],true)+" | "+ANSI_RED+" SBAGLIATA "+ANSI_RESET+" , hai inserito: '"+arr[2]+"'";
			}
			out(msg);
			roundCounter++;
		}		
		
		scan.close();
		
	}
	
	public static String getAnsiPoints(int points) {
		
		if(points>5) {
			return ANSI_GREEN;
		}
		return ANSI_RED;
		
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
	
	public static String getCensoredAnswer(String answ,boolean randomCharactersShown) {
		
		String[] words = answ.split(" |'");
		
		String censoredAnswer = "";
		
		for(int i = 0 ; i < words.length; i++) {
			
			Random rand = new Random();
			int randValue = rand.nextInt(words[i].length());
			for(int j = 0 ; j < words[i].length(); j++) {
				if(j==randValue && randomCharactersShown) {
					censoredAnswer+=words[i].charAt(j);
					continue;
				}
				censoredAnswer+="_";
			}
			censoredAnswer+= " ";			
		}
		
		return censoredAnswer;
	}
	
	public static void out(String msg) {
		System.out.println(msg);
	}
	
	public static String in(Scanner sc) {
		return sc.nextLine();		
	}
	
	public final static void clear(){		
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");                
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {            	
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
	    
	} 
}
