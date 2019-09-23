package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Scanner keyboardInput = new Scanner(System.in);
    private static String action = "";
    private static Random rand = new Random();
    private static Map<String, String> cardToDefinition = new LinkedHashMap<>();
    private static Map<String, String> definitionToCard = new LinkedHashMap<>();
    private static List<Card> currentCardsGame = new ArrayList<>();
    private static List<String> logLines = new ArrayList<>();
    private static Map<String, Integer> mapMistakes = new HashMap<>();


    public static void main(String[] args) {

       String[] arguments = getCommandLineArgumentsSeventhStage(args);
       if(null != arguments[0] && !arguments[0].isEmpty()){
           importCards(arguments[0]);
       }
        do{
            selectAction();
            switch(action){
                case "add":
                    addCard();
                    break;
                case "remove":
                    removeCard();
                    break;
                case "import":
                    importCards();
                    break;
                case "export":
                    exportCards();
                    break;
                case "ask":
                    askCards();
                    break;
                case "log":
                    logCards();
                    break;
                case "hardest card":
                    hardestCards();
                    break;
                case "reset stats":
                    resetStatsCards();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    if(null != arguments[1] && !arguments[1].isEmpty()) {
                        exportCards(arguments[1]);
                    }
                    break;
                default:
                    break;
            }

        }while(!action.equals("exit"));

        //List<Map<String, String>> game = generateCards();
        //populateCards(game);
        //Card[] arr = createArrayOfCards(game);
        //testPlayer(game);

        /*
        Card firstStageCard = new Card("Metagraph", "Mathematics",
                "Category Theory",
                "Objects: a, b, c,...Arrows: f, g, h,...Domain: for each arrow, f, there is an object a = dom f...Codomain: for each arrow, f, there is an object b = cod f");

       // String toDisplay = "Card:\n"+firstStageCard.getName()+"\nDefinition:\n"+firstStageCard.getDefinition();
       // System.out.println(toDisplay);
        Card cat = new Card("cat", "Zoology", "Pet", "a purring animal");
        Card dog = new Card("dog", "Zoology", "Pet", "a barking animal");

        String input1 = sc.nextLine().trim();
        String input2 = sc.nextLine().trim();
        String input3 = sc.nextLine().trim();

        if(input1.equals(cat.getDefinition()) && input2.equals(cat.getName())){
            if(input3.equals(cat.getName())){
                System.out.println("Your answer is right!");
            }else{
                System.out.println("Your answer is wrong...");
            }
        }

        if(input1.equals(dog.getDefinition()) && input2.equals(dog.getName())){
            if(input3.equals(dog.getName())){
                System.out.println("Your answer is right!");
            }else{
                System.out.println("Your answer is wrong...");
            }
        }*/
    }

    private static void resetStatsCards() {
        for(Card c : currentCardsGame){
            c.setMistakes(0);
        }
        for (Map.Entry<String, Integer> entry : mapMistakes.entrySet()) {
            mapMistakes.put(entry.getKey(), 0);
        }
        saveAndPrintln("Card statistics has been reset.");
    }

    private static void hardestCards() {
        int max = -1;
        for(Integer m : mapMistakes.values()){
            max = m>max ? m : max;
        }
        if(null == mapMistakes || mapMistakes.isEmpty() || max==0){
            saveAndPrintln("There are no cards with errors (ignoring case).");
        }else{
            List<String> hardestCardsList = new ArrayList<>();
            for(String name : mapMistakes.keySet()){
                if(mapMistakes.get(name) == max){
                    hardestCardsList.add(name);
                }
            }
            int hardestListSize = hardestCardsList.size();
            if(hardestListSize == 1){
                String quotedName = "\""+hardestCardsList.get(0)+"\"";
                String hardestC = "The hardest card is "+quotedName+". You have "+max+" errors answering it.";
                saveAndPrintln(hardestC);
            }else if(hardestListSize >1){
                String output="";
                for(int i=0; i<hardestListSize-1; i++){
                    output+= "\""+hardestCardsList.get(i)+"\", ";
                }
                output+="\""+hardestCardsList.get(hardestListSize-1)+"\". ";
                String hardestC = "The hardest cards are "+output+"You have "+max+" errors answering it.";
                saveAndPrintln(hardestC);
            }
        }
    }

    private static void selectAction(){
        //System.out.println("Input the action (add, remove, import, export, ask, exit):");
        saveAndPrintln("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        action = keyboardInput.nextLine();
        logLines.add(action+"\n");
    }

    private static void addCard(){
        //System.out.println("The card:");
        saveAndPrintln("The card:");
        String cardName = keyboardInput.nextLine();
        logLines.add(cardName+"\n");

        int mistakes = 0;
        String quotedCurrentName = "\""+cardName+"\"";
        if(null != cardToDefinition && cardToDefinition.containsKey(cardName)){
            //System.out.println("The card "+quotedCurrentName+" already exists.");
            saveAndPrintln("The card "+quotedCurrentName+" already exists.");
            return;
        }
        //System.out.println("The definition of the card:");
        saveAndPrintln("The definition of the card:");
        String cardDefinition = keyboardInput.nextLine();
        logLines.add(cardDefinition+"\n");
        String quotedCurrentdefinition = "\""+cardDefinition+"\"";
        if(null != definitionToCard && definitionToCard.containsKey(cardDefinition)){
            //System.out.println("The definition "+quotedCurrentdefinition+" already exists.");
            saveAndPrintln("The definition "+quotedCurrentdefinition+" already exists.");
            return;
        }
        currentCardsGame.add(new Card(cardName, cardDefinition, mistakes));
        cardToDefinition.put(cardName, cardDefinition);
        definitionToCard.put(cardDefinition, cardName);
        mapMistakes.put(cardName, mistakes);

        //System.out.println("The pair ("+quotedCurrentName+":"+quotedCurrentdefinition+") has been added.");
        saveAndPrintln("The pair ("+quotedCurrentName+":"+quotedCurrentdefinition+") has been added.");
    }

    private static void removeCard(){
        //System.out.println("The card:");
        saveAndPrintln("The card:");
        String cardName = keyboardInput.nextLine();
        logLines.add(cardName+"\n");
        String quotedCurrentName = "\""+cardName+"\"";
        if(null != cardToDefinition && cardToDefinition.containsKey(cardName)) {
            for (Card c : currentCardsGame) {
                if (c.getName().equals(cardName)) {
                    currentCardsGame.remove(c);
                    break;
                }
            }
            String currentDef = cardToDefinition.get(cardName);
            cardToDefinition.remove(cardName);
            definitionToCard.remove(currentDef);
            mapMistakes.remove(cardName);
            //System.out.println("The card has been removed.");
            saveAndPrintln("The card has been removed.");
        }else{
            //System.out.println("Can't remove "+quotedCurrentName+": there is no such card.");
            saveAndPrintln("Can't remove "+quotedCurrentName+": there is no such card.");
            return;
        }
    }

    private static void importCards(){
        //System.out.println("File name:");
       saveAndPrintln("File name:");
        String enteredFilename = keyboardInput.nextLine();
        logLines.add(enteredFilename+"\n");
        File deserialization = new File(enteredFilename);
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(deserialization);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            //System.out.println("File not found.");
            saveAndPrintln("File not found.");
            return;
        }
        String currentName="";
        String currentDef="";
        int currentMistakes = 0;
        int lineCount = 0;
        while(fileInput.hasNextLine()){
            currentName = fileInput.nextLine();
            currentDef = fileInput.nextLine();
            currentMistakes = Integer.parseInt(fileInput.nextLine());
            lineCount+=3;
           // if(fileInput.hasNextLine()) {

            //    lineCount++;
           //}
            cardToDefinition.put(currentName,currentDef);
            definitionToCard.put(currentDef,currentName);
            currentCardsGame.add(new Card(currentName, currentDef, currentMistakes));
            mapMistakes.put(currentName, currentMistakes);
        }
        //System.out.println(lineCount/3 +" cards have been loaded (ignoring case).");
       saveAndPrintln(lineCount/3 +" cards have been loaded (ignoring case).");
        fileInput.close();
    }
    private static void importCards(String importFileName){
        //System.out.println("File name:");
       //saveAndPrintln("File name:");
       // String enteredFilename = keyboardInput.nextLine();
        logLines.add(importFileName+"\n");
        File deserialization = new File(importFileName);
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(deserialization);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            //System.out.println("File not found.");
            saveAndPrintln("File not found.");
            return;
        }
        String currentName="";
        String currentDef="";
        int currentMistakes = 0;
        int lineCount = 0;
        while(fileInput.hasNextLine()){
            currentName = fileInput.nextLine();
            currentDef = fileInput.nextLine();
            currentMistakes = Integer.parseInt(fileInput.nextLine());
            lineCount+=3;
           // if(fileInput.hasNextLine()) {

            //    lineCount++;
           //}
            cardToDefinition.put(currentName,currentDef);
            definitionToCard.put(currentDef,currentName);
            currentCardsGame.add(new Card(currentName, currentDef, currentMistakes));
            mapMistakes.put(currentName, currentMistakes);
        }
        //System.out.println(lineCount/3 +" cards have been loaded (ignoring case).");
       saveAndPrintln(lineCount/3 +" cards have been loaded (ignoring case).");
        fileInput.close();
    }

    private static void exportCards(){
        //System.out.println("File name:");
        saveAndPrintln("File name:");
        String enteredFilename = keyboardInput.nextLine();
        logLines.add(enteredFilename+"\n");
        File serialization = new File(enteredFilename);
        StringBuilder contentOfCardsToExport = new StringBuilder();
        int numberOfCards = cardToDefinition.size();
        for(Map.Entry<String,String> card : cardToDefinition.entrySet()){
            contentOfCardsToExport.append(card.getKey()).append("\n");
            contentOfCardsToExport.append(card.getValue()).append("\n");
            contentOfCardsToExport.append(mapMistakes.get(card.getKey())).append("\n");
        }
        try (FileWriter writer = new FileWriter(serialization)) {
            writer.write(contentOfCardsToExport.toString());
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
            logLines.add("An exception occurred\n");
            logLines.add(e.getMessage()+"\n");
        }
        //System.out.println(String.format("%d cards have been saved.", numberOfCards));
        saveAndPrintln(String.format("%d cards have been saved.", numberOfCards));
    }
    private static void exportCards(String exportFileName){
        //System.out.println("File name:");
        //saveAndPrintln("File name:");
        //String enteredFilename = keyboardInput.nextLine();
        logLines.add(exportFileName+"\n");
        File serialization = new File(exportFileName);
        StringBuilder contentOfCardsToExport = new StringBuilder();
        int numberOfCards = cardToDefinition.size();
        for(Map.Entry<String,String> card : cardToDefinition.entrySet()){
            contentOfCardsToExport.append(card.getKey()).append("\n");
            contentOfCardsToExport.append(card.getValue()).append("\n");
            contentOfCardsToExport.append(mapMistakes.get(card.getKey())).append("\n");
        }
        try (FileWriter writer = new FileWriter(serialization)) {
            writer.write(contentOfCardsToExport.toString());
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
            logLines.add("An exception occurred\n");
            logLines.add(e.getMessage()+"\n");
        }
        //System.out.println(String.format("%d cards have been saved.", numberOfCards));
        saveAndPrintln(String.format("%d cards have been saved.", numberOfCards));
    }

    private static void saveAndPrintln(String logElement) {
        System.out.println(logElement);
        logLines.add(logElement+"\n");
    }

    private static void logCards(){
        System.out.println("File name:");
        logLines.add("File name:"+"\n");
        String enteredFilename = keyboardInput.nextLine();
        File fullLog = new File(enteredFilename);
        StringBuilder contentOfCardsToExport = new StringBuilder();
        int numberOfCards = cardToDefinition.size();
        for(String line : logLines){
            contentOfCardsToExport.append(line);
        }
        try (FileWriter writer = new FileWriter(fullLog)) {
            writer.write(contentOfCardsToExport.toString());
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
        System.out.println("The log has been saved");
    }

    private static void askCards(){
        System.out.println("How many times to ask?");
        int numOfAsked = Integer.parseInt(keyboardInput.nextLine().trim());
        int l = cardToDefinition.size();
        //int[] randomCards = shuffleCards(l, numOfAsked);
        int[] randomCards = new int[numOfAsked];
        for(int i=0; i<numOfAsked; i++){
            randomCards[i] = rand.nextInt(l);
        }
        List<String> searchableKeys = new ArrayList<>(cardToDefinition.keySet());
        for(int i=0; i<numOfAsked ; i++){
            String currentCardName = searchableKeys.get(randomCards[i]);
            treatPlayersAnswer(currentCardName) ;
        }

    }

    private static void treatPlayersAnswer(String currentCardName) {
        String quoteCurrentCardName = "\"" + currentCardName + "\"";
        System.out.println("Print the definition of " + quoteCurrentCardName + ":");
        String defAns = keyboardInput.nextLine();
        if (defAns.equals(cardToDefinition.get(currentCardName))) {
            System.out.println("Correct answer.");
        } else {
            int currMist = mapMistakes.get(currentCardName);
            mapMistakes.put(currentCardName, currMist+1);
            String quotedCorrectDef = "\"" + cardToDefinition.get(currentCardName) + "\"";
            String output = "Wrong answer, The correct one is " + quotedCorrectDef + "(ignoring case)";
            String complement = ".";
            if (definitionToCard.containsKey(defAns) && !"UpdateMeFromImport".equals(defAns)) {
                String quotedChosenDefOf = "\"" + definitionToCard.get(defAns) + "\"";
                complement = ", you've just written the definition of " + quotedChosenDefOf + ".)";
            }
            System.out.println(output + complement);
        }
    }
    private static String[] getCommandLineArgumentsSeventhStage(String [] argsInput){
        String[] results = new String[2];
        for(int i=0; i<argsInput.length; i++){
            if(argsInput[i].equals("-import")){
                String importFile = argsInput[i+1].isEmpty() || argsInput[i+1].isBlank() || !argsInput[i+1].endsWith(".txt") ? "" : argsInput[i+1];
                results[0] = importFile;
            }else if(argsInput[i].equals("-export")) {
                String exportFile = argsInput[i + 1].isEmpty() || argsInput[i + 1].isBlank() || !argsInput[i + 1].endsWith(".txt") ? "" : argsInput[i + 1];
                results[1] = exportFile;
            }
        }
        return results;
    }
/*
    private static int[] shuffleCards(int l, int numOfAsked) {
        if(l < numOfAsked){
            numOfAsked=l;
        }
        List<Integer> indexList = new ArrayList<>();
        int[] ans = new int[numOfAsked];
        for(int i=0; i<l; i++){
            indexList.add(i);
        }
        Collections.shuffle(indexList);
        for(int j=0; j<numOfAsked; j++){
            ans[j] = indexList.get(j);
        }
        return ans;
    }
*/

 /*   private static void testPlayer(List<Map<String, String>> game) {
        Map<String, String> mapNames = game.get(0);
        Map<String, String> mapDefs = game.get(1);

        for(String name: mapNames.keySet()){
            String quotedName =  "\""+name+"\"";
            System.out.println("Print the definition of " + quotedName);
            String defAns = sc.nextLine();
            if(defAns.equals(mapNames.get(name))){
                System.out.println("Correct answer.");
            }else{
                String quotedCorrectDef = "\""+mapNames.get(name)+"\"";
                String output = "Wrong answer. (The correct one is "+quotedCorrectDef;
                String complement = ".)";
                if(mapDefs.containsKey(defAns)){
                    String quotedChosenDefOf = "\""+mapDefs.get(defAns)+"\"";
                    complement = ", you've just written the definition of "+quotedChosenDefOf+".)";
                }
                System.out.println(output+complement);
            }
        }
    }*/

 /*   private static void testPlayer(Card[] arr) {
        for(Card c : arr){
            String currentName = "\""+c.getName()+"\"";
            System.out.println("Print the definition of " + currentName);
            String answer = sc.nextLine();
            if(answer.equals(c.getDefinition())){
                System.out.println("Correct answer.");
            }else{
                String currentDef = "\""+c.getDefinition()+"\"";
                System.out.println("Wrong answer. (The correct one is "+currentDef+".)");
            }
        }
    }*/

    private static Card[] createArrayOfCards(String[][] game) {
        int l = game[0].length;
        Card[] arrCards = new Card[l];
        for(int i=0; i<l; i++){
            arrCards[i] = new Card();
            for(int j=0; j<game.length; j++) {
                if(j==0) {
                    arrCards[i].setName(game[j][i]);
                }else{
                    arrCards[i].setDefinition(game[j][i]);
                }
            }
        }
        return arrCards;
    }

/*
    private static String[][] generateCards(){
        String[][] ans = new String[2][];
        int n = 0; // number of cards
        System.out.println("Input the number of cards:");
        n = Integer.parseInt(sc.nextLine().trim());
        String[] cards = new String[n];
        String[] definitions = new String[n];
       ans[0] = cards;
       ans[1] = definitions;
       return ans;
    }
*/
/*    private static List<Map<String, String>> generateCards(){
        List<Map<String, String>> answer = new ArrayList<>();
        int n = 0; // number of cards
        System.out.println("Input the number of cards:");
        n = Integer.parseInt(sc.nextLine().trim());
        Map<String, String> cardToDefinition = new LinkedHashMap<>();
        Map<String, String> definitionToCard = new LinkedHashMap<>();
       for(int i=0; i<n; i++){
           System.out.println("The card #"+(i+1)+":)");
           String currentName = getCurrentCardName(cardToDefinition);
           System.out.println("The definition of the card #"+(i+1)+":)");
           String currentDef = getCurrentCardDefinition((definitionToCard));
           cardToDefinition.put(currentName, currentDef);
           definitionToCard.put(currentDef, currentName);
       }
       answer.add(cardToDefinition);
       answer.add(definitionToCard);
       return answer;
    }*/

/*    private static String getCurrentCardName(Map<String, String> cardToDefinition){
        String currentName = sc.nextLine();
        while(null != cardToDefinition && cardToDefinition.containsKey(currentName)){
            String quotedCurrentName = "\""+currentName+"\"";
            System.out.println("The card "+quotedCurrentName+" already exists. Try again:");
            currentName = sc.nextLine();
        }
        return currentName;
    }*/
/*    private static String getCurrentCardDefinition(Map<String, String> definitionToCard){
        String currentDefinition = sc.nextLine();
        while(null != definitionToCard && definitionToCard.containsKey(currentDefinition)){
            String quotedCurrentdefinition = "\""+currentDefinition+"\"";
            System.out.println("The definition "+quotedCurrentdefinition+" already exists. Try again:");
            currentDefinition = sc.nextLine();
        }
        return currentDefinition;
    }*/

 /*   private static void populateCards(String[][] game){
        int l = game.length;//2 = name and definition
        int n = game[0].length; // number of cards
        for(int i=0; i<n; i++){
            for(int j=0; j<l; j++) {
                game[j][i] = populateEntity(game[j][i], i, j);
            }
        }
    }*/

 /*   private static String populateEntity(String s, int i, int j) {
        String str = j==0 ? "The card #"+(i+1)+":" : "The definition of the card #"+(i+1)+":";
        //do{
            System.out.println(str);
            s = sc.nextLine();
       // }while(s.isEmpty() || s.isBlank());
        return s;
    }*/
}
// from http://www.dreadedsoftware.com/blog/2015/6/28/category-theory-cheat-sheet
// https://github.com/alhassy/CatsCheatSheet/blob/master/CheatSheet.pdf