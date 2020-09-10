/*import java.util.LinkedHashMap;

public class Draft {
    private LinkedHashMap<String, Integer> draftPlayers = new LinkedHashMap<>();
    private Player p1;
    private Player p2;
    private int actualPlayer;

    public Draft(Player p1) {
        this.p1 = setPlayer(p1);
    }

    public Draft(Player p1, Player p2) {
        this.p1 = setPlayer(p1);
        this.p2 = setPlayer(p2);
    }

    private Player setPlayer(Player p) {
        this.draftPlayers.put(p.getName(), 0);
        this.actualPlayer = 1;
        return p;
    }

    // it should be getActualPlayer
    public Player actualPlayer() {
        return actualPlayer == 1 ? p1 : p2;
    }

    private void changePlayer() {
        if (draftPlayers.size() > 1) {
            if (actualPlayer == 1) {
                actualPlayer++;
            } else {
                actualPlayer--;
            }
        }
    }

    // change player should be private as it not using anywhere else
    // is you made is a private I think you can move a logic to change player if draftsPlayer > 1
    // isAchivedPointsIsMine in both if statements is run twice if it false - so you should call it only one time
    public void setPointsToDraft(Score points) {
        boolean isMultiPlayer = draftPlayers.size() > 1;
        // I do not like this name. areThisIsMyPoints too
        boolean isAchivedPointsIsMine = points.isAchivedPointsIsMine();

        if (isAchivedPointsIsMine) {
            handleSetPointsToDraft(points);
            changePlayer();
        }
        if (!isAchivedPointsIsMine && isMultiPlayer) {
            changePlayer();
            handleSetPointsToDraft(points);
        }
    }

    private void handleSetPointsToDraft(Score points) {
        int sum = draftPlayers.get(actualPlayer().getName()) + points.getPoints();
        draftPlayers.replace(actualPlayer().getName(), sum);
    }

    public void showResults() {
        System.out.println("Wyniki rozgrywki:");
        System.out.println(draftPlayers);
    }
}*/


import java.util.LinkedHashMap;

public class Draft {
    LinkedHashMap<String, Integer> draftPlayers = new LinkedHashMap<>();
    Player p1;
    Player p2;
    int actualPlayer;
    public Draft(Player p1){
        this.p1=p1;
        draftPlayers.put(p1.getName(), 0);
        actualPlayer=1;
    }
    public Draft(Player p1, Player p2){
        this.p1=p1;
        this.p2=p2;
        draftPlayers.put(p1.getName(), 0);
        draftPlayers.put(p2.getName(), 0);
        actualPlayer=1;
    }

    public Player actualPlayer(){
        if(actualPlayer==1){
            return p1;
        } else {
            return p2;
        }
    }

    public void changePlayer(){
        if(draftPlayers.size()>1){
            if(actualPlayer==1){
                actualPlayer++;
            } else{
                actualPlayer--;
            }
        }

    }

    public void setPointsToDraft(Score points){
        if(points.isAchivedPointsIsMine()){
            int sum = draftPlayers.get(actualPlayer().getName())+points.getPoints();
            draftPlayers.replace(actualPlayer().getName(), sum);
            changePlayer();
        }
        if (!points.isAchivedPointsIsMine() && (draftPlayers.size() > 1)) {
            changePlayer();
            int sum = draftPlayers.get(actualPlayer().getName())+points.getPoints();
            draftPlayers.replace(actualPlayer().getName(), sum);
        }

    }

    public void showResults(){
        System.out.println("Wyniki rozgrywki:");
        System.out.println(draftPlayers);
    }
}
