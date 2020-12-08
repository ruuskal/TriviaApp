package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import triviaapp.dao.FilePlayerDao;

public class FilePlayerDaoTest {
    

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    
    File file;
    FilePlayerDao playerDao;
   
    
    @Before
    public void setUp() throws IOException{
        file = tmpFolder.newFile("testFile.txt");
                
        playerDao = new FilePlayerDao(file.getAbsolutePath());

    }
  
     @Test
     public void writesAndReadsCorrectly() {
         playerDao.writeToFile("Krusty", 40);
         playerDao.writeToFile("Itchy", 60);
         assertEquals("Krusty;40\nItchy;60\n", playerDao.readFile(2));
     }
     
    
}