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
	
	public static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		printStartMenu();
		
		String userName = notEmptyIn("Inserisci il tuo nome:");		
		
		clear();
		
		out("Benvenuto/a "+userName);
		do {
			gameStart(userName);
		}while(playAgain());
		
		clear();
		exitProgram("Grazie per aver giocato con noi "+userName+"!");
		
		scan.close();
		
	}
	
	public static void gameStart(String userName) {
		//FILL THE SENTENCES CONTAINER WITH ALL THE SENTENCES
		SentencesContainer sc = null;
		try {
			sc = new SentencesContainer();
			sc.createSentences();
			sc.shuffleSentences();
		} catch (SQLException e) {
			exitProgram("Impossibile scaricare le domande! Riavviare l'applicazione!");
		}
		
		out("INIZIAMO!\n");
		
		int nGuessed = 0;
		int nTotal = 0;
		
		//LIST WITH THE RESULT OF EACH ROUND
		List<String[]> resultList = new ArrayList<String[]>();
		
		for(Sentence s: sc.getSentences()) {
			String msg = "Frase numero "+(nTotal+1)+"\n\n";
			msg+=s.getSentenceText()+" "+getCensoredAnswer(s.getCorrectAnswer(),false);			
			msg+="\n\nCompletala:";		
			
			String userAnswer = notEmptyIn(msg);
			
			if(s.isCorrect(userAnswer)) {
				nGuessed++;
				resultList.add(new String[]{s.getSentenceText(),s.getCorrectAnswer(),""});										
			}else {
				resultList.add(new String[]{s.getSentenceText(),s.getCorrectAnswer(),userAnswer});				
			}
			nTotal++;
			out("\n\nPremi invio per continuare!");
			in();
			clear();
		}
		
		
		printResultsMessage(userName,nGuessed,nTotal);				
		
		printRounds(resultList);
	}
	
	public static boolean playAgain() {
		out("\nPREMI INVIO PER CONTINUARE");
		in();		
		clear();
		
		String userAnswer;
		
		do{
			userAnswer = notEmptyIn("Vuoi giocare ancora? [s/n]").trim().toLowerCase();
			if(!userAnswer.equals("s") && !userAnswer.equals("n")) {
				clear();
				out("Input errato riprova!\n");
			}
		}while(!userAnswer.equals("s") && !userAnswer.equals("n"));
		
		if(userAnswer.equals("s")) {
			return true;
		}
			
		return false;		
	}
	
	public static void printResultsMessage(String userName,int nGuessed,int nTotal) {
		float percent = (nGuessed * 100)/nTotal;
		out(userName+" "+getCustomMessage(percent)+", hai completato il gioco con un punteggio di: "+getAnsiPoints(percent)+nGuessed+"/"+nTotal+" ("+percent+"%)"+ANSI_RESET);		
		
	}
	
	public static String getCustomMessage(float points) {
		return points > 50 ? "ben fatto" : "peccato";
	}
	
	public static void printRounds(List<String[]> resultList) {
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
	}
	
	
	public static String getAnsiPoints(float points) {
		
		if(points>50) {
			return ANSI_GREEN;
		}
		return ANSI_RED;
		
	}
	
	public static void printStartMenu() {
		clear();
		out("Benvenuto nel gioco di cultura generale!");
		out("REGOLE:");
		out("- maiuscole e minuscole non vengono controllate");
		out("- non aggiungere punti alla fine delle frasi");
		out("- divertiti e impara!");
		out("PREMI INVIO PER CONTINUARE");
		in();		
		clear();
	}
	
	public static void exitProgram(String msg) {
		out(msg);
		System.exit(0);
	}
	
	public static String notEmptyIn(String msg) {
		String userAnswer = "";
		while(userAnswer.equals("") || userAnswer.equals(" ")) {			
			out(msg);
			userAnswer = in();
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
	
	public static String in() {
		return scan.nextLine();		
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
