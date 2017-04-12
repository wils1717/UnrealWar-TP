/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.event.ComponentSystemEvent;
import javax.json.JsonArray;
import javax.json.JsonObject;
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
public class UserControllerTest {

    public UserControllerTest() {
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

    public void testEmptyUserControllerConstructor() {
        System.out.println("UserController(...)");
        String newPassword = null;
        String confirmPassword = null;
        boolean registered = false;
        boolean deleted = false;
        int incorrectPassChange = 0;
        boolean incorrectPassDelete = false;
        UserController instance = new UserController();
        assertEquals(newPassword, instance.getNewPassword());
        assertEquals(confirmPassword, instance.getConfirmPassword());
        assertEquals(registered, instance.isRegistered());
        assertEquals(deleted, instance.isDeleted());
        assertEquals(incorrectPassChange, instance.isIncorrectPassChange());
        assertEquals(incorrectPassDelete, instance.isIncorrectPassDelete());
    }

    /**
     * Test of clearFields method, of class UserController.
     */
    @Test
    public void testClearFields() {
        System.out.println("clearFields");
        UserController instance = new UserController();
        String expResult = "loginPage";
        String result = instance.clearFields();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConfirmPassword method, of class UserController.
     */
    @Test
    public void testGetConfirmPassword() {
        System.out.println("getConfirmPassword");
        UserController instance = new UserController();
        String expResult = null;
        String result = instance.getConfirmPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConfirmPassword method, of class UserController.
     */
    @Test
    public void testSetConfirmPassword() {
        System.out.println("setConfirmPassword");
        String confirmPassword = "";
        UserController instance = new UserController();
        instance.setConfirmPassword(confirmPassword);
    }

    /**
     * Test of getNewPassword method, of class UserController.
     */
    @Test
    public void testGetNewPassword() {
        System.out.println("getNewPassword");
        UserController instance = new UserController();
        String expResult = null;
        String result = instance.getNewPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNewPassword method, of class UserController.
     */
    @Test
    public void testSetNewPassword() {
        System.out.println("setNewPassword");
        String newPassword = "";
        UserController instance = new UserController();
        instance.setNewPassword(newPassword);
    }

    /**
     * Test of isIncorrectPassChange method, of class UserController.
     */
    @Test
    public void testIsIncorrectPassChange() {
        System.out.println("isIncorrectPassChange");
        UserController instance = new UserController();
        int expResult = 0;
        int result = instance.isIncorrectPassChange();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIncorrectPassChange method, of class UserController.
     */
    @Test
    public void testSetIncorrectPassChange() {
        System.out.println("setIncorrectPassChange");
        int incorrectPassChange = 0;
        UserController instance = new UserController();
        instance.setIncorrectPassChange(incorrectPassChange);
    }

    /**
     * Test of isIncorrectPassDelete method, of class UserController.
     */
    @Test
    public void testIsIncorrectPassDelete() {
        System.out.println("isIncorrectPassDelete");
        UserController instance = new UserController();
        boolean expResult = false;
        boolean result = instance.isIncorrectPassDelete();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIncorrectPassDelete method, of class UserController.
     */
    @Test
    public void testSetIncorrectPassDelete() {
        System.out.println("setIncorrectPassDelete");
        boolean incorrectPassDelete = false;
        UserController instance = new UserController();
        instance.setIncorrectPassDelete(incorrectPassDelete);
    }

    /**
     * Test of isPasswordChanged method, of class UserController.
     */
    @Test
    public void testIsPasswordChanged() {
        System.out.println("isPasswordChanged");
        UserController instance = new UserController();
        boolean expResult = false;
        boolean result = instance.isPasswordChanged();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPasswordChanged method, of class UserController.
     */
    @Test
    public void testSetPasswordChanged() {
        System.out.println("setPasswordChanged");
        boolean passwordChanged = false;
        UserController instance = new UserController();
        instance.setPasswordChanged(passwordChanged);
    }

    /**
     * Test of isRegistered method, of class UserController.
     */
    @Test
    public void testIsRegistered() {
        System.out.println("isRegistered");
        UserController instance = new UserController();
        boolean expResult = false;
        boolean result = instance.isRegistered();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRegistered method, of class UserController.
     */
    @Test
    public void testSetRegistered() {
        System.out.println("setRegistered");
        boolean registered = false;
        UserController instance = new UserController();
        instance.setRegistered(registered);
    }

    /**
     * Test of setDeleted method, of class UserController.
     */
    @Test
    public void testSetDeleted() {
        System.out.println("setDeleted");
        boolean deleted = false;
        UserController instance = new UserController();
        instance.setDeleted(deleted);
    }

    /**
     * Test of isDeleted method, of class UserController.
     */
    @Test
    public void testIsDeleted() {
        System.out.println("isDeleted");
        UserController instance = new UserController();
        boolean expResult = false;
        boolean result = instance.isDeleted();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsers method, of class UserController.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        List<User> users = null;
        UserController instance = new UserController();
        instance.setUsers(users);
    }

    /**
     * Test of setInstance method, of class UserController.
     */
    @Test
    public void testSetInstance() {
        System.out.println("setInstance");
        User instance_2 = null;
        UserController instance = new UserController();
        instance.setInstance(instance_2);
    }

    /**
     * Test of getUsernameById method, of class UserController.
     */
    @Test
    public void testGetUsernameById() {
        System.out.println("getUsernameById");
        int id = 0;
        UserController instance = new UserController();
        String expResult = null;
        String result = instance.getUsernameById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIdByUsername method, of class UserController.
     */
    @Test
    public void testGetUserIdByUsername() {
        System.out.println("getUserIdByUsername");
        String username = "";
        UserController instance = new UserController();
        int expResult = -1;
        int result = instance.getUserIdByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getById method, of class UserController.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        UserController instance = new UserController();
        User expResult = null;
        User result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getByUsername method, of class UserController.
     */
    @Test
    public void testGetByUsername() {
        System.out.println("getByUsername");
        String username = "";
        UserController instance = new UserController();
        User expResult = null;
        User result = instance.getByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getByIdJson method, of class UserController.
     */
    @Test
    public void testGetByIdJson() {
        System.out.println("getByIdJson");
        int id = 0;
        UserController instance = new UserController();
        JsonObject expResult = null;
        JsonObject result = instance.getByIdJson(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getByUsernameJson method, of class UserController.
     */
    @Test
    public void testGetByUsernameJson() {
        System.out.println("getByUsernameJson");
        String username = "";
        UserController instance = new UserController();
        JsonObject expResult = null;
        JsonObject result = instance.getByUsernameJson(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsersFromDB method, of class UserController.
     */
    @Test
    public void testGetUsersFromDB() {
        System.out.println("getUsersFromDB");
        UserController instance = new UserController();
        instance.getUsersFromDB();
    }

    /**
     * Test of addUser method, of class UserController.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        UserController instance = new UserController();
        instance.addUser();
    }

    /**
     * Test of editUserPassword method, of class UserController.
     */
    @Test
    public void testEditUserPassword() {
        System.out.println("editUserPassword");
        String username = null;
        String oldPassword = null;
        String newPassword = null;
        String confirmPassword = null;
        UserController instance = new UserController();
        instance.editUserPassword(username, oldPassword, newPassword, confirmPassword);
    }

    /**
     * Test of deleteUser method, of class UserController.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String username = "";
        String password = "";
        UserController instance = new UserController();
        instance.deleteUser(username, password);
    }

}
