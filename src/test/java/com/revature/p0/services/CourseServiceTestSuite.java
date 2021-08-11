package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.models.User;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.UserSession;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CourseServiceTestSuite {

    CourseService sut;
    private CourseRepository mockUserRepo;

    @Before
    public void beforeEachTest() {
        mockUserRepo = Mockito.mock(CourseRepository.class);
        sut = new CourseService(mockUserRepo);
    }

    @After
    public void afterEachTest() {
        sut = null;
    }

    @Test
    public void isCourseValid_returnsTrue_givenValidCourse() {
        // Arrange
        Course validCourse = new Course("TEST", "2000", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult = sut.isCourseValid(validCourse);

        // Assert
        Assert.assertTrue("Expected course to be considered valid!", actualCourseResult);
    }

    @Test
    public void isCourseValid_returnsFalse_givenNullCourse() {
        // Arrange
        Course invalidCourse = null;

        // Act
        boolean actualCourseResult = sut.isCourseValid(invalidCourse);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseSubjectNullOrBlank() {
        // Arrange
        Course invalidCourse1 = new Course(null, "2000", "Test course", 25, 4, true);
        Course invalidCourse2 = new Course("", "2000", "Test course", 25, 4, true);
        Course invalidCourse3 = new Course("    ", "2000", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);
        boolean actualCourseResult2 = sut.isCourseValid(invalidCourse2);
        boolean actualCourseResult3 = sut.isCourseValid(invalidCourse3);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult2);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult3);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseSubjectLengthNotEqualFour() {
        // Arrange
        Course invalidCourse = new Course("MAT", "2000", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseSubjectWithLowercaseCharacters() {
        // Arrange
        Course invalidCourse1 = new Course("MATh", "2000", "Test course", 25, 4, true);
        Course invalidCourse2 = new Course("MAth", "2000", "Test course", 25, 4, true);
        Course invalidCourse3 = new Course("Math", "2000", "Test course", 25, 4, true);
        Course invalidCourse4 = new Course("math", "2000", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);
        boolean actualCourseResult2 = sut.isCourseValid(invalidCourse2);
        boolean actualCourseResult3 = sut.isCourseValid(invalidCourse3);
        boolean actualCourseResult4 = sut.isCourseValid(invalidCourse4);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult2);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult3);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult4);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseCodeNullOrBlank() {
        // Arrange
        Course invalidCourse1 = new Course("MATH", null, "Test course", 25, 4, true);
        Course invalidCourse2 = new Course("MATH", "", "Test course", 25, 4, true);
        Course invalidCourse3 = new Course("MATH", "     ", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);
        boolean actualCourseResult2 = sut.isCourseValid(invalidCourse2);
        boolean actualCourseResult3 = sut.isCourseValid(invalidCourse3);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult2);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult3);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseCodeLengthNotEqualFour() {
        // Arrange
        Course invalidCourse = new Course("MATH", "200", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseCodeNotInteger() {
        Course invalidCourse1 = new Course("MATH", "word", "Test course", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseTitleNullOrBlank() {
        // Arrange
        Course invalidCourse1 = new Course("MATH", "2000", null, 25, 4, true);
        Course invalidCourse2 = new Course("MATH", "2000", "", 25, 4, true);
        Course invalidCourse3 = new Course("MATH", "2000", "     ", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);
        boolean actualCourseResult2 = sut.isCourseValid(invalidCourse2);
        boolean actualCourseResult3 = sut.isCourseValid(invalidCourse3);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult2);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult3);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCourseTitleLengthLessThan5() {
        // Arrange
        Course invalidCourse1 = new Course("MATH", "2000", "Test", 25, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
    }

    @Test
    public void isCourseValid_returnsFalse_givenStudentLimitNonpositive() {
        // Arrange
        Course invalidCourse1 = new Course("MATH", "2000", "Course Title", 0, 4, true);
        Course invalidCourse2 = new Course("MATH", "2000", "Course Title", -1, 4, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);
        boolean actualCourseResult2 = sut.isCourseValid(invalidCourse2);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult2);
    }

    @Test
    public void isCourseValid_returnsFalse_givenCreditHoursNonpositive() {
        // Arrange
        Course invalidCourse1 = new Course("MATH", "2000", "Course Title", 24, 0, true);
        Course invalidCourse2 = new Course("MATH", "2000", "Course Title", 24, -1, true);

        // Act
        boolean actualCourseResult1 = sut.isCourseValid(invalidCourse1);
        boolean actualCourseResult2 = sut.isCourseValid(invalidCourse2);

        // Assert
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult1);
        Assert.assertFalse("Expected course to be considered invalid!", actualCourseResult2);
    }

    @Test
    public void addCourse_returnsSuccessfully_whenGivenValidCourse() {
        // Arrange
        Course expectedResult = new Course("MATH", "2000", "Example Course", 24, 4, true);
        Course validCourse = new Course("MATH", "2000", "Example Course", 24, 4, true);
        when(mockUserRepo.save(any())).thenReturn(expectedResult);

        // Act
        Course actualResult = sut.addCourse(validCourse);

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
        verify(mockUserRepo, times(1)).save(any());
    }

    @Test(expected = InvalidRequestException.class)
    public void addCourse_throwsException_whenGivenNullCourse() {
        // Arrange
        Course invalidCourse = null;

        // Act
        try {
            sut.addCourse(invalidCourse);
        } finally {
            // Assert
            verify(mockUserRepo, times(0)).save(any());
        }
    }

    @Test(expected = ResourcePersistenceException.class)
    public void addCourse_throwsException_whenCourseTitleAlreadyExists() {
        // Arrange
        Course existingUser = new Course("MATH", "2000", "Example Course", 24, 4, true);
        Course duplicate = new Course("CALC", "3000", "Example Course", 24, 4, true);
        when(mockUserRepo.findCourseByTitle(duplicate.getCourseTitle())).thenReturn(existingUser);

        // Act
        try {
            sut.addCourse(duplicate);
        } finally {
            // Assert
            verify(mockUserRepo, times(1)).findCourseByTitle(duplicate.getCourseTitle());
            verify(mockUserRepo, times(0)).save(duplicate);

        }
    }

    @Test(expected = ResourcePersistenceException.class)
    public void addCourse_throwsException_whenCourseSubjectAndCodeComboAlreadyExists() {
        // Arrange
        Course existingUser = new Course("MATH", "2000", "Example Course", 24, 4, true);
        Course duplicate = new Course("MATH", "2000", "Another Course", 24, 4, true);
        when(mockUserRepo.findCourseBySubjectAndCode(duplicate.getCourseSubject(), duplicate.getCourseCode())).thenReturn(existingUser);

        // Act
        try {
            sut.addCourse(duplicate);
        } finally {
            // Assert
            verify(mockUserRepo, times(1)).findCourseBySubjectAndCode(duplicate.getCourseSubject(), duplicate.getCourseCode());
            verify(mockUserRepo, times(0)).save(duplicate);
        }
    }

    @Test
    public void getAllCourses_returnsListOfCourses() {
        List<Course> courseList = sut.getAllCourses();
        Assert.assertNotNull(courseList);
    }

    @Test
    public void isStringUpdateValid_returnsTrue_GivenValidInput(){
        boolean valid1 = sut.isStringUpdateValid("courseSubject", "CALC");
        boolean valid2 = sut.isStringUpdateValid("courseCode", "1010");
        boolean valid3 = sut.isStringUpdateValid("courseTitle", "New Title");

        Assert.assertTrue(valid1);
        Assert.assertTrue(valid2);
        Assert.assertTrue(valid3);
    }

    @Test
    public void isStringUpdateValid_returnsFalse_GivenEmptyString(){
        boolean invalid1 = sut.isStringUpdateValid("courseSubject", "    ");
        boolean invalid2 = sut.isStringUpdateValid("courseCode", "");
        boolean invalid3 = sut.isStringUpdateValid("courseTitle", null);

        Assert.assertFalse(invalid1);
        Assert.assertFalse(invalid2);
        Assert.assertFalse(invalid3);
    }

    @Test
    public void isStringUpdateValid_returnsFalse_GivenCourseSubjectLengthNotEqualsFour(){
        boolean invalid1 = sut.isStringUpdateValid("courseSubject", "MAT");
        boolean invalid2 = sut.isStringUpdateValid("courseSubject", "CALCULUS");

        Assert.assertFalse(invalid1);
        Assert.assertFalse(invalid2);
    }

    @Test
    public void isStringUpdateValid_returnsFalse_GivenCourseSubjectContainsLowercaseCharacters(){
        boolean invalid1 = sut.isStringUpdateValid("courseSubject", "mATH");
        boolean invalid2 = sut.isStringUpdateValid("courseSubject", "MaTH");
        boolean invalid3 = sut.isStringUpdateValid("courseSubject", "MAtH");
        boolean invalid4 = sut.isStringUpdateValid("courseSubject", "MATh");

        Assert.assertFalse(invalid1);
        Assert.assertFalse(invalid2);
        Assert.assertFalse(invalid3);
        Assert.assertFalse(invalid4);
    }

    @Test
    public void isStringUpdateValid_returnsFalse_GivenCourseCodeLengthNotEqualsFour(){
        boolean invalid1 = sut.isStringUpdateValid("courseCode", "000");
        boolean invalid2 = sut.isStringUpdateValid("courseCode", "11111");

        Assert.assertFalse(invalid1);
        Assert.assertFalse(invalid2);
    }

    @Test
    public void isStringUpdateValid_returnsFalse_GivenCourseCodeNotParsableToInteger(){
        boolean invalid1 = sut.isStringUpdateValid("courseCode", "abcd");
        Assert.assertFalse(invalid1);
    }

    @Test
    public void isStringUpdateValid_returnsFalse_GivenCourseTitleLengthLessThanFive(){
        boolean invalid1 = sut.isStringUpdateValid("courseTitle", "Test");
        Assert.assertFalse(invalid1);
    }

    @Test
    public void isIntUpdateValid_returnsTrue_GivenValidInput(){
        boolean valid1 = sut.isIntUpdateValid(100);
        boolean valid2 = sut.isIntUpdateValid(1);

        Assert.assertTrue(valid1);
        Assert.assertTrue(valid2);
    }

    @Test
    public void isIntUpdateValid_returnsFalse_GivenNonPositiveInput(){
        boolean invalid1 = sut.isIntUpdateValid(0);
        boolean invalid2 = sut.isIntUpdateValid(-1);

        Assert.assertFalse(invalid1);
        Assert.assertFalse(invalid2);
    }

    @Test
    public void updateIntField_returnsTrue_GivenValidInput(){
        Course original = new Course("MATH", "3000", "Calculus II", 25, 4, true);

        boolean validUpdate1 = sut.updateIntField(original, "studentLimit", 23);
        boolean validUpdate2 = sut.updateIntField(original, "creditHours", 5);

        Assert.assertTrue(validUpdate1);
        Assert.assertTrue(validUpdate2);
    }

    @Test
    public void updateIntField_returnsFalse_GivenInvalidInput(){
        Course original = new Course("MATH", "3000", "Calculus II", 25, 4, true);

        boolean validUpdate1 = sut.updateIntField(original, "studentLimit", -1);
        boolean validUpdate2 = sut.updateIntField(original, "creditHours", -1);

        Assert.assertFalse(validUpdate1);
        Assert.assertFalse(validUpdate2);
    }

    @Test
    public void updateStringField_returnsTrue_GivenValidInput(){
        Course original = new Course("MATH", "3000", "Calculus II", 25, 4, true);

        boolean validUpdate1 = sut.updateStringField(original, "courseSubject", "CALC");
        boolean validUpdate2 = sut.updateStringField(original, "courseCode", "2000");
        boolean validUpdate3 = sut.updateStringField(original, "courseTitle", "Calculus I");

        Assert.assertTrue(validUpdate1);
        Assert.assertTrue(validUpdate2);
        Assert.assertTrue(validUpdate3);
    }

    @Test
    public void updateStringField_returnsFalse_GivenInvalidInput(){
        Course original = new Course("MATH", "3000", "Calculus II", 25, 4, true);

        boolean validUpdate1 = sut.updateStringField(original, "courseSubject", "    ");
        boolean validUpdate2 = sut.updateStringField(original, "courseCode", "    ");
        boolean validUpdate3 = sut.updateStringField(original, "courseTitle", "     ");

        Assert.assertFalse(validUpdate1);
        Assert.assertFalse(validUpdate2);
        Assert.assertFalse(validUpdate3);
    }

    @Test
    public void updateBooleanField_returnsTrue_GivenValidInput(){
        Course original = new Course("MATH", "3000", "Calculus II", 25, 4, true);

        boolean validUpdate1 = sut.updateBooleanField(original, "windowOpen", true);
        boolean validUpdate2 = sut.updateBooleanField(original, "windowOpen", false);

        Assert.assertTrue(validUpdate1);
        Assert.assertTrue(validUpdate2);
    }
}
