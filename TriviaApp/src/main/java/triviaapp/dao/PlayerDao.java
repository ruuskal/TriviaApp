/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaapp.dao;


public interface PlayerDao {
    
    String readFile(int i);
    
    void writeToFile(String s, int i);
}
