/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.json.JsonObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author c0665354
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getWins method, of class User.
     */
    @Test
    public void testGetWins() {
        System.out.println("getWins");
        User instance = new User();
        int expResult = 0;
        int result = instance.getWins();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setWins method, of class User.
     */
    @Test
    public void testSetWins() {
        System.out.println("setWins");
        int wins = 0;
        User instance = new User();
        instance.setWins(wins);
        
    }

    /**
     * Test of getLosses method, of class User.
     */
    @Test
    public void testGetLosses() {
        System.out.println("getLosses");
        User instance = new User();
        int expResult = 0;
        int result = instance.getLosses();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setLosses method, of class User.
     */
    @Test
    public void testSetLosses() {
        System.out.println("setLosses");
        int losses = 0;
        User instance = new User();
        instance.setLosses(losses);
        
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        User instance = new User();
        instance.setId(id);
        
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User();
        String expResult = null;
        String result = instance.getUsername();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        User instance = new User();
        instance.setUsername(username);
      
    }

    /**
     * Test of getPasshash method, of class User.
     */
    @Test
    public void testGetPasshash() {
        System.out.println("getPasshash");
        User instance = new User();
        String expResult = null;
        String result = instance.getPasshash();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setPasshash method, of class User.
     */
    @Test
    public void testSetPasshash() {
        System.out.println("setPasshash");
        String passhash = "";
        User instance = new User();
        instance.setPasshash(passhash);
     
    }

    
    
    
}
