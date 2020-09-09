import java.util.Scanner;

public class Player {
    String name;

    public Player() {

    }

    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("Podaj imię gracza:");
        Scanner player = new Scanner(System.in);
        this.name = player.nextLine();
        System.out.println("Wprowadzono gracza: " + getName());

    }


}
