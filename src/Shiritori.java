import java.io.IOException;
import java.util.ArrayList;

public class Shiritori<T extends Words> {

    private final ArrayList<Words> words = new ArrayList<>();
    private boolean gameOver = false;
    private boolean areThisIsMyPoints = true;
    private boolean add = true;
    private int pointsContainer = 50;
    private final Dictionary dictionary;
    private final Score score;

    public Shiritori() throws IOException {
        dictionary = new Dictionary();
        score = new Score();
    }

    public void play(T actualWord) throws NumberException {

        boolean dict = dictionary.getDictionary().stream()
                .noneMatch(d -> d.equals(actualWord.toString()));
        if (dict) {
            System.out.println("Nie ma takiego słowa!");
            areThisIsMyPoints = false;
            pointsContainer = 25;
        }

        words.stream()
                .filter(w -> w.getWord().equals(actualWord.toString()))
                .findAny()
                .map(w -> {
                    System.out.println("Takie słowo już wystąpiło!");
                    areThisIsMyPoints = false;
                    pointsContainer = 25;
                    add = false;
                    return null;
                });
        words.stream()
                .filter(w -> words.size() > 0 && actualWord.getFirstLetter() != words.get(words.size() - 1).getLastLetter())
                .findAny()
                .map(w -> {
                    gameOver = true;
                    System.out.println("Pierwsza litera użytego słowa nie jest ostatnią literą słowa poprzedniego");
                    score.setPlayersPoints(100, false);
                    return null;
                });

        boolean illegalLastLetterTest = dictionary.getIllegalLastLettersTable().stream()
                .anyMatch(i -> i.equals(actualWord.getLastLetter()));

        if (gameStatus()) {
            if (illegalLastLetterTest) {
                if (add) {
                    words.add(actualWord);
                    System.out.println("Wyraz zakończony niedozwoloną literą. Dla kontynuacji wstawione a");
                    String a = "a";
                    words.add(new Words(a));
                    score.setPlayersPoints(pointsContainer, areThisIsMyPoints);
                }
            } else {
                if (add) {
                    words.add(actualWord);
                    score.setPlayersPoints(pointsContainer, areThisIsMyPoints);
                }
            }
            reset();
        }
    }

    public Score achivedPoints() {
        return score;
    }

    public void restart() {
        words.clear();
        System.out.println("Game restarted");
    }

    public void reset() {
        pointsContainer = 50;
        areThisIsMyPoints = true;
    }

    public void getWords() {
        System.out.println(words.toString());

    }

    public boolean gameStatus() {
        return !gameOver;
    }

}
