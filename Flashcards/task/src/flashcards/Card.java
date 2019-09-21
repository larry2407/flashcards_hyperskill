package flashcards;

public class Card {
    private String name;
    private String subject;
    private String theme;
    private String definition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Card(String name, String definition){
        this.name = name;
        this.definition = definition;
    }

    public Card(String name, String subject, String theme, String definition) {
        this(name,definition);
        this.subject = subject;
        this.theme = theme;
    }

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", theme='" + theme + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
