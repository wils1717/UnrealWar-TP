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

    /**
     * Test of empty Login constructor method, of class Login
     */
    @Test
    public void testEmptyLoginConstructor() {
        System.out.println("Login(...)");
        String username = null;
        String password = null;
        boolean loggedIn = false;
        boolean validUser = true;
        User currentUser = null;
        Login instance = new Login();
        assertEquals(username, instance.getUsername());
        assertEquals(password, instance.getPassword());
        assertEquals(loggedIn, instance.isLoggedIn());
        assertEquals(validUser, instance.isValidUser());
        assertEquals(currentUser, instance.getCurrentUser());
    }

    
    /**
     * Test of logout method, of class Login.
     */
    @org.junit.Test
    public void testLogout() {
        System.out.println("logout");
        Login instance = new Login();
        String expResult = "loginPage";
        String result = instance.logout();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteUser method, of class Login.
     */
    @org.junit.Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        Login instance = new Login();
        String expResult = "deleteUserPage";
        String result = instance.deleteUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method, of class Login.
     */
    @org.junit.Test
    public void testRegister() {
        System.out.println("register");
        Login instance = new Login();
        String expResult = "registrationPage";
        String result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsers method, of class Login.
     */
    @org.junit.Test
    public void testSetUsers() {
        System.out.println("setUsers");
        List<User> users = null;
        Login instance = new Login();
        instance.setUsers(users);
    }

    /**
     * Test of setCurrentUser method, of class Login.
     */
    @org.junit.Test
    public void testSetCurrentUser() {
        System.out.println("setCurrentUser");
        User currentUser = null;
        Login instance = new Login();
        instance.setCurrentUser(currentUser);
    }

    /**
     * Test of getCurrentUser method, of class Login.
     */
    @org.junit.Test
    public void testGetCurrentUser() {
        System.out.println("getCurrentUser");
        Login instance = new Login();
        User expResult = null;
        User result = instance.getCurrentUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidUser method, of class Login.
     */
    @org.junit.Test
    public void testIsValidUser() {
        System.out.println("isValidUser");
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.isValidUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValidUser method, of class Login.
     */
    @org.junit.Test
    public void testSetValidUser() {
        System.out.println("setValidUser");
        boolean validPass = false;
        Login instance = new Login();
        instance.setValidUser(validPass);
    }

    /**
     * Test of isLoggedIn method, of class Login.
     */
    @org.junit.Test
    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoggedIn method, of class Login.
     */
    @org.junit.Test
    public void testSetLoggedIn() {
        System.out.println("setLoggedIn");
        boolean loggedIn = false;
        Login instance = new Login();
        instance.setLoggedIn(loggedIn);
    }

    /**
     * Test of getUsername method, of class Login.
     */
    @org.junit.Test
    public void testGetUsername() {
        System.out.println("username");
        Login instance = new Login();
        String expResult = null;
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Login.
     */
    @org.junit.Test
    public void testSetUsername() {
        System.out.println("username");
        String username = null;
        Login instance = new Login();
        instance.setUsername(username);
    }

    /**
     * Test of getPassword method, of class Login.
     */
    @org.junit.Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Login instance = new Login();
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Login.
     */
    @org.junit.Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Login instance = new Login();
        instance.setPassword(password);
    }

    /**
     * Test of getUsersFromDB method, of class Login.
     */
    @org.junit.Test
    public void testGetUsersFromDB() {
        System.out.println("getUsersFromDB");
        Login instance = new Login();
        instance.getUsersFromDB();
    }

}
