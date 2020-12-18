package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import triviaapp.domain.Player;


public class PlayerTest {
  
     @Test
     public void playerToStringIsCorrect() {
         Player p = new Player();
         p.setName("Testi");
         p.setPoints(20);
         assertEquals("Testi: 20", p.toString());
     }
     
     @Test
     public void addPointsaddsCorrectly() {
        Player p = new Player();
        p.setName("Testi");
        p.addPoints(10);
        p.addPoints(30);
        assertEquals(40, p.getPoints());
     }
}
