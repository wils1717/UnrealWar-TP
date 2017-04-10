/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
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
public class LoginTest {
    
    public LoginTest() {
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

    @Test
    public void testBlankPasswordShouldReturnErrorMessage() {
        String username = "bob";
        String password = "";
        Login instance = new Login();
        String expResult = "ERROR: Invalid Password";
        String result = instance.doLogin(username, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBlankUsernameShouldReturnErrorMessage() {
        String username = "";
        String password = "n";
        Login instance = new Login();
        String expResult = "ERROR: Invalid Username";
        String result = instance.doLogin(username, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetUsername() {
        System.out.println("username");
        Login instance = new Login();
        String expResult = null;
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUsername() {
        System.out.println("username");
        String username = "Noob";
        Login instance = new Login();
        instance.setUsername(username);
        String expResult = instance.getUsername();
        assertEquals(expResult, username);

    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Login instance = new Login();
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);

    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "abc3421";
        Login instance = new Login();
        instance.setPassword(password);
        String expResult = instance.getPassword();
        assertEquals(expResult, password);

    }

    @Test
    public void testLoggedn() {
        System.out.println("getLoggedIn");
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);

    }

    @Test
    public void testEmptyConstructor() {
        System.out.println("Login(...)");
        String username = null;
        String password = null;
        boolean loggedIn = false;

        Login instance = new Login();
        assertEquals(username, instance.getUsername());
        assertEquals(password, instance.getPassword());
        assertEquals(loggedIn, instance.isLoggedIn());
    }

}
