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

    /**
     * Test of getUsername method, of class Login.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Login instance = new Login();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Login.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Login instance = new Login();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Login.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Login instance = new Login();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Login.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Login instance = new Login();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLoggedIn method, of class Login.
     */
    @Test
    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentUser method, of class Login.
     */
    @Test
    public void testGetCurrentUser() {
        System.out.println("getCurrentUser");
        Login instance = new Login();
        User expResult = null;
        User result = instance.getCurrentUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class Login.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        Login instance = new Login();
        String expResult = "";
        String result = instance.login();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    /**
     * Test for blank password at login
     */
    @Test
    public void testBlankPasswordShouldReturnErrorMessage() {
        String username = "bob";
        String password = "";
        Login instance = new Login();
        String expResult = "ERROR: Invalid Password";
        String result = instance.doLogin(username, password);
        assertEquals(expResult, result);        
    }
    
    /**
     * Test for blank username at login
     */
    @Test
    public void testBlankUsernameShouldReturnErrorMessage() {
        String username = "";
        String password = "bob";
        Login instance = new Login();
        String expResult = "ERROR: Invalid Username";
        String result = instance.doLogin(username, password);
        assertEquals(expResult, result);
    }    
}
