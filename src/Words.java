public class Words {

    private final String word;

    public Words(String word) throws NumberException {
         if(!word.matches("[a-źA-Ź]+"))
            throw new NumberException();

        this.word = word;
    }

    public String getWord() {
        return word;
    }



    public char getFirstLetter() {
        return word.charAt(0);
    }

    public char getLastLetter() {
        return word.charAt(word.length()-1);
    }


    @Override
    public String toString() {
        return word;
    }
}
