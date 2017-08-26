import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = (int) Math.floor(Math.random() * 5);

    private ArrayList<String> clues = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();
    
    public KnockKnockProtocol(){
    	try {
    		loadJokes();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    public void loadJokes() throws Exception{
		Scanner input = null;
		// open file jokes.txt
		
		input = new Scanner(Paths.get("jokes.txt"));
		
		String line;
		String clue;
		String answer;
		String[] split;
		
		while (input.hasNext()){
			line = input.nextLine();
			split = line.split("-");
			clue = split[0];
			answer = split[1];
			clues.add(clue);
			answers.add(answer);
		}

		// close file
		if (input != null){
			input.close();
		}
	
	}

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues.get(currentJoke);
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
			    "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues.get(currentJoke) + " who?")) {
                theOutput = answers.get(currentJoke) + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
			    clues.get(currentJoke) + 
			    " who?\"" + 
			    "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}
