import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Shiritori Game v.1.1
v. 0.1:
- uruchomienie podstawowych funkcjonalności gry. Można wpisywać słowa, gra dodaje je do listy i sprawdza warunki gry
- gra wychwytuje spacje i duże litery. Spacje likwiduje, duże litery zamienia na małe
v. 0.2:
- gra wychwytuje liczby oraz znaki i zwraca jako wyjątek (kontynuowany w catch)
v. 0.21:
- poprawka kodu: w klasie Word usunięte pola firstLetter i lastLetter, bo InelliJ podpowiedział, że są zbędne
- poprawka błędnego funkcjonowania mechanizmu gry w klasie Shiritori: wyciągnięto drugi warunek (prawdzający ostatnią literę słowa n
i pierwszą słowa n+1) z pętli for, bo to powodowało, że program sprawdzał każdy wyraz na liście, w efekcie czego gra kończyła działanie
po trzecim wpisaniu słowa
v. 0.22
- korekty kodu na podstawie sugestii IntelliJ
v.0.3:
- dodano słownik języka polskiego
- wprowadzono kontrolę liter zakazanych na końcu wyrazu - dla kontynuacji gry na razie gra wstawia literę a do zbioru
v. 1.0
- dodano możliwość wyboru liczby graczy (maks 2) i przypisania im imion
v.1.1
- poprawa systemu dodawania punktów
 */
public class MainGame {

    public static void main(String[] args) throws IOException {
        String word;
        boolean choose = true;
        Player p1;
        Player p2;
        Shiritori<Words> game = new Shiritori<>();
        Draft draft;
        System.out.println("Witaj w Shiritori Game! Grasz sam (wciśnij 1), czy z drugą osobą (wciśniej 2)?");

        do {
            Scanner number = new Scanner(System.in);
            int numberOfPlayers=0;
            try {
                numberOfPlayers = number.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Użyj cyfr!");
            }

            switch (numberOfPlayers) {
                case 1:
                    p1 = new Player();
                    System.out.println("A więc grasz sam");
                    p1.setName();
                    draft = new Draft(p1);
                    choose = false;
                    break;
                case 2:
                    p1 = new Player();
                    p2 = new Player();
                    System.out.println("A więc gracie we dwóch");
                    System.out.println("Gracz numer 1:");
                    p1.setName();
                    System.out.println("Gracz numer 2:");
                    p2.setName();
                    draft = new Draft(p1, p2);
                    choose = false;
                    break;
                default:
                    System.out.println("Nie wybrano poprawnie liczby graczy. Spróbuj jeszcze raz");
                    draft = null;
                    break;
            }
        } while (choose);

        while (game.gameStatus()) {
            try {
                System.out.println(draft.actualPlayer().getName() + " podaj słowo (wpisując \"restart\" zerujesz słownik)");
                Scanner enteredWord = new Scanner(System.in);
                word = enteredWord.nextLine().toLowerCase().replaceAll("\\s+", "");
                if(word.equals("restart")){
                    game.restart();
                }
                game.play(new Words(word));
                game.getWords();
                if (game.gameStatus())
                    draft.setPointsToDraft(game.achivedPoints());
            } catch (NumberException e) {
                System.out.println(e.getMessage());
            }

        }
        draft.showResults();
    }
}
