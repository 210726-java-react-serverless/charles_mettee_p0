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

}
