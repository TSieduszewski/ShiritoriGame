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
