/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author c0687799
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
     * Test for empty constructor, of class User
     */
    @Test
    public void testEmptyUserConstructor() {
        System.out.println("User(...)");
        int id = 0;
        String username = "";
        String passhash = "";
        int wins = 0;
        int losses = 0;
        User instance = new User();
        assertEquals(id, instance.getId());
        assertEquals(username, instance.getUsername());
        assertEquals(passhash, instance.getPasshash());
        assertEquals(wins, instance.getWins());
        assertEquals(losses, instance.getLosses());
    }

    /**
     * Test for full constructor, of class User
     */
    @Test
    public void testFullUserConstructor() {
        System.out.println("User(...)");
        int id = 0;
        String username = "";
        String passhash = "";
        int wins = 0;
        int losses = 0;
        User instance = new User();
        assertEquals(id, instance.getId());
        assertEquals(username, instance.getUsername());
        assertEquals(passhash, instance.getPasshash());
        assertEquals(wins, instance.getWins());
        assertEquals(losses, instance.getLosses());
    }

    @Test
    public void testSameNameDifferentIdIsNotEqual() {
        String username = "Skip";
        int id = 1;
        int id2 = 2;
        User instance = new User(1, "", "", 0, 0);
        instance.setUsername(username);
        instance.setId(id);
        User instance2 = new User(1, "", "", 0, 0);
        instance2.setUsername(username);
        instance2.setId(id2);
        assertNotEquals(instance, instance2);
    }

    /**
     * Test of getWins method, of class User.
     */
    @Test
    public void testGetWins() {
        System.out.println("getWins");
        User instance = new User(0, "", "", 0, 0);
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
        User instance = new User(0, "", "", 0, 0);
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
        User instance = new User(0, "", "", 0, 0);
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
        User instance = new User(0, "Skip", "", 0, 0);
        String expResult = "Skip";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "Skip";
        User instance = new User();
        instance.setUsername(username);
    }

    /**
     * Test of getPasshash method, of class User.
     */
    @Test
    public void testGetPasshash() {
        System.out.println("getPasshash");
        User instance = new User(0, "", "LetMeIn", 0, 0);
        String expResult = "LetMeIn";
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
