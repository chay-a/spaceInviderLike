package campus.valence;

public class Main {
    public static void main(String[] args) {
        String cheatKey = System.getProperty("cheat");
        Integer cheat = 0;
        if (cheatKey != null) {
            cheat = Integer.valueOf(cheatKey);
        }
        SpaceCampus game = new SpaceCampus(cheat);
        game.launch();
    }
}
