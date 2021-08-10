package com.revature.p0.services;

import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.models.User;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.UserSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserService sut;

    private UserRepository mockUserRepo;
    private UserSession mockUserSession;

    @Before
    public void beforeEachTest() {
        mockUserRepo = Mockito.mock(UserRepository.class);
        mockUserSession = Mockito.mock(UserSession.class);
        sut = new UserService(mockUserRepo, mockUserSession);
    }

    @After
    public void afterEachTest() {
        sut = null;
    }

    @Test
    public void isUserValid_returnsTrue_givenValidUser() {
        // Arrange
        User validStudentUser = new Student("valid", "valid", "valid@valid.com", "valid", "valid");
        User validFacultyUser = new FacultyMember("valid", "valid", "valid@valid.com", "valid", "valid");

        // Act
        boolean actualStudentResult = sut.isUserValid(validStudentUser);
        boolean actualFacultyResult = sut.isUserValid(validFacultyUser);

        // Assert
        Assert.assertTrue("Expected student user to be considered valid!", actualStudentResult);
        Assert.assertTrue("Expected faculty user to be considered valid!", actualFacultyResult);
    }

    @Test
    public void isUserValid_returnsFalse_givenNullUser() {
        // Arrange
        User invalidUser1 = null;

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);

        // Assert
        Assert.assertFalse("User cannot be null!", actualResult1);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithNullOrEmptyFirstName() {

        // Arrange
        User invalidUser1 = new Student(null, "valid", "valid@valid.com", "valid", "valid");
        User invalidUser2 = new Student("", "valid", "valid@valid.com", "valid", "valid");
        User invalidUser3 = new Student("        ", "valid", "valid@valid.com", "valid", "valid");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);
        boolean actualResult2 = sut.isUserValid(invalidUser2);
        boolean actualResult3 = sut.isUserValid(invalidUser3);

        // Assert
        Assert.assertFalse("User first name cannot be null!", actualResult1);
        Assert.assertFalse("User first name cannot be an empty string!", actualResult2);
        Assert.assertFalse("User first name cannot be only whitespace!", actualResult3);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithNullOrEmptyLastname() {
        // Arrange
        User invalidUser1 = new Student("valid", null, "valid", "valid", "valid");
        User invalidUser2 = new Student("valid", "", "valid", "valid", "valid");
        User invalidUser3 = new Student("valid", "        ", "valid", "valid", "valid");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);
        boolean actualResult2 = sut.isUserValid(invalidUser2);
        boolean actualResult3 = sut.isUserValid(invalidUser3);

        // Assert
        Assert.assertFalse("User last name cannot be null!", actualResult1);
        Assert.assertFalse("User last name cannot be an empty string!", actualResult2);
        Assert.assertFalse("User last name cannot be only whitespace!", actualResult3);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithNullOrEmptyEmail() {
        // Arrange
        User invalidUser1 = new Student("valid", "valid", null, "valid", "valid");
        User invalidUser2 = new Student("valid", "valid", "", "valid", "valid");
        User invalidUser3 = new Student("valid", "valid", "        ", "valid", "valid");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);
        boolean actualResult2 = sut.isUserValid(invalidUser2);
        boolean actualResult3 = sut.isUserValid(invalidUser3);

        // Assert
        Assert.assertFalse("User email cannot be null!", actualResult1);
        Assert.assertFalse("User email cannot be an empty string!", actualResult2);
        Assert.assertFalse("User email cannot be only whitespace!", actualResult3);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithEmailWithoutAtSign() {
        // Arrange
        User invalidUser = new Student("valid", "valid", "valid", "valid", "valid");

        // Act
        boolean actualResult = sut.isUserValid(invalidUser);

        // Assert
        Assert.assertFalse("User email must contain an '@' symbol!", actualResult);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithNullOrEmptyUsername() {
        // Arrange
        User invalidUser1 = new Student("valid", "valid", "valid@valid.com", null, "valid");
        User invalidUser2 = new Student("valid", "valid", "valid@valid.com", "", "valid");
        User invalidUser3 = new Student("valid", "valid", "valid@valid.com", "        ", "valid");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);
        boolean actualResult2 = sut.isUserValid(invalidUser2);
        boolean actualResult3 = sut.isUserValid(invalidUser3);

        // Assert
        Assert.assertFalse("User username cannot be null!", actualResult1);
        Assert.assertFalse("User username cannot be an empty string!", actualResult2);
        Assert.assertFalse("User username cannot be only whitespace!", actualResult3);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithUsernameLessThanFiveCharacters() {
        // Arrange
        User invalidUser1 = new Student("valid", "valid", "valid@valid.com", "abcd", "valid");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);

        // Assert
        Assert.assertFalse("User username cannot contain fewer than five characters!", actualResult1);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithNullOrEmptyPassword() {
        // Arrange
        User invalidUser1 = new Student("valid", "valid", "valid@valid.com", "valid", null);
        User invalidUser2 = new Student("valid", "valid", "valid@valid.com", "valid", "");
        User invalidUser3 = new Student("valid", "valid", "valid@valid.com", "valid", "        ");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);
        boolean actualResult2 = sut.isUserValid(invalidUser2);
        boolean actualResult3 = sut.isUserValid(invalidUser3);

        // Assert
        Assert.assertFalse("User password cannot be null!", actualResult1);
        Assert.assertFalse("User password cannot be an empty string!", actualResult2);
        Assert.assertFalse("User password cannot be only whitespace!", actualResult3);
    }

    @Test
    public void isUserValid_returnsFalse_givenUserWithPasswordLessThanFiveCharacters() {
        // Arrange
        User invalidUser1 = new Student("valid", "valid", "valid@valid.com", "valid", "abcd");

        // Act
        boolean actualResult1 = sut.isUserValid(invalidUser1);

        // Assert
        Assert.assertFalse("User password cannot contain fewer than five characters!", actualResult1);
    }

    @Test
    public void register_returnsSuccessfully_whenGivenValidUse() {
        // Arrange
        User expectedResult = new Student("valid", "valid", "valid@valid.com", "valid", "valid");
        User validUser = new FacultyMember("valid", "valid", "valid@valid.com", "valid", "valid");
        when(mockUserRepo.save(any())).thenReturn(expectedResult);

        // Act
        User actualResult = sut.register(validUser);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
        verify(mockUserRepo, times(1)).save(any());
    }

    @Test
    public void register_returnsSuccessfully_whenGivenValidUse() {
        // Arrange
        User expectedResult = new Student("valid", "valid", "valid@valid.com", "valid", "valid");
        User validUser = new FacultyMember("valid", "valid", "valid@valid.com", "valid", "valid");
        when(mockUserRepo.save(any())).thenReturn(expectedResult);

        // Act
        User actualResult = sut.register(validUser);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
        verify(mockUserRepo, times(1)).save(any());
    }

}
