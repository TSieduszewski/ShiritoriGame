import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {
    private final List<String> dictionary;
    private final List<Character> illegalLastLetters = new ArrayList<>(Arrays.asList('ą', 'ę', 'ń', 'ó', 'q', 'v', 'x', 'y'));
    public Dictionary() throws IOException {
        dictionary = Files.readAllLines(Paths.get("slowa.txt"));
    }

    public List<String> getDictionary(){
        return dictionary;
    }

    public List<Character> getIllegalLastLettersTable(){
        return illegalLastLetters;
    }
}
