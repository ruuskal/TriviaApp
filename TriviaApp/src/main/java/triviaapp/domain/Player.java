package triviaapp.domain;

public class Player {
    
        
    private int points;
    private String name;
    
    public Player(String name) {
        this.name = name;
        this.points = 0;
    }
    
    public void addPoints(int pointsToAdd){
        this.points+=pointsToAdd;
    }
    
    public int getPoints(){
        return this.points;
    }
    
}
