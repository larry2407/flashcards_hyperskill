package flashcards;

import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        List<Map<String, String>> game = generateCards();
        //populateCards(game);
        //Card[] arr = createArrayOfCards(game);
        testPlayer(game);

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

    private static void testPlayer(List<Map<String, String>> game) {
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
    }

    private static void testPlayer(Card[] arr) {
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
    }

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
    private static List<Map<String, String>> generateCards(){
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
    }

    private static String getCurrentCardName(Map<String, String> cardToDefinition){
        String currentName = sc.nextLine();
        while(null != cardToDefinition && cardToDefinition.containsKey(currentName)){
            String quotedCurrentName = "\""+currentName+"\"";
            System.out.println("The card "+quotedCurrentName+" already exists. Try again:");
            currentName = sc.nextLine();
        }
        return currentName;
    }
    private static String getCurrentCardDefinition(Map<String, String> definitionToCard){
        String currentDefinition = sc.nextLine();
        while(null != definitionToCard && definitionToCard.containsKey(currentDefinition)){
            String quotedCurrentdefinition = "\""+currentDefinition+"\"";
            System.out.println("The definition "+quotedCurrentdefinition+" already exists. Try again:");
            currentDefinition = sc.nextLine();
        }
        return currentDefinition;
    }

    private static void populateCards(String[][] game){
        int l = game.length;//2 = name and definition
        int n = game[0].length; // number of cards
        for(int i=0; i<n; i++){
            for(int j=0; j<l; j++) {
                game[j][i] = populateEntity(game[j][i], i, j);
            }
        }
    }

    private static String populateEntity(String s, int i, int j) {
        String str = j==0 ? "The card #"+(i+1)+":" : "The definition of the card #"+(i+1)+":";
        //do{
            System.out.println(str);
            s = sc.nextLine();
       // }while(s.isEmpty() || s.isBlank());
        return s;
    }
}
// from http://www.dreadedsoftware.com/blog/2015/6/28/category-theory-cheat-sheet
// https://github.com/alhassy/CatsCheatSheet/blob/master/CheatSheet.pdf