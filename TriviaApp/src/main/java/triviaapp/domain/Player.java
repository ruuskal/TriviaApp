package triviaapp.domain;

/**
 * 
 * Pelaaja kuvaava luokka
 */

public class Player {
    
    private int points;
    private String name;
    
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
