public class Score {
   int points=0;
   boolean myPoints=true;

    public int getPoints() {
        return points;
    }

    public boolean isAchivedPointsIsMine(){
        return myPoints;
    }
    public void setPlayersPoints(int points, boolean myPoints) {
        this.points=points;
        this.myPoints=myPoints;
    }
}
