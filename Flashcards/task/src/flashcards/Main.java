package flashcards;

public class Main {
    public static void main(String[] args) {
        Card firstStageCard = new Card("Metagraph", "Mathematics",
                "Category Theory",
                "Objects: a, b, c,...Arrows: f, g, h,...Domain: for each arrow, f, there is an object a = dom f...Codomain: for each arrow, f, there is an object b = cod f");

        String toDisplay = "Card:\n"+firstStageCard.getName()+"\nDefinition:\n"+firstStageCard.getDefinition();
        System.out.println(toDisplay);
    }
}
// from http://www.dreadedsoftware.com/blog/2015/6/28/category-theory-cheat-sheet
// https://github.com/alhassy/CatsCheatSheet/blob/master/CheatSheet.pdf