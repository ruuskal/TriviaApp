package triviaapp.domain;

public class Player {
    
    private int points;
    private String name;
    
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.points;
    }
    
}
