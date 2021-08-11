package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.models.Student;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.models.User;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.repositories.StudentCourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.exceptions.ResourcePersistenceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentCourseServiceTestSuite {

    StudentCourseService sut;
    private StudentCourseRepository mockStudentCourseRepo;
    private CourseRepository mockCourseRepo;
    private UserRepository mockUserRepo;

    @Before
    public void beforeEachTest() {
        mockStudentCourseRepo = Mockito.mock(StudentCourseRepository.class);
        mockCourseRepo = Mockito.mock(CourseRepository.class);
        mockUserRepo = Mockito.mock(UserRepository.class);
        sut = new StudentCourseService(mockStudentCourseRepo);
    }

    @After
    public void afterEachTest() {
        sut = null;
    }

    @Test
    public void registerCourse_returnsSuccessfully_givenValidStudentCourse() {
        // Arrange
        User user = new Student("valid", "valid", "valid@valid.com", "valid", "valid");
        user.setId("studentId");
        when(mockUserRepo.save(any())).thenReturn(user);
        User validStudent = mockUserRepo.save(user);

        Course course = new Course("TEST", "2000", "Test course", 25, 4, true);
        course.setId("courseId");
        when(mockCourseRepo.save(any())).thenReturn(course);
        Course validCourse = mockCourseRepo.save(course);

        when(mockStudentCourseRepo.getCourseById(any())).thenReturn(validCourse);

        StudentCourse validSc = new StudentCourse("studentId", "courseId");
        StudentCourse expectedSc = new StudentCourse(validStudent.getId(), validCourse.getId());

        when(mockStudentCourseRepo.save(any())).thenReturn(expectedSc);

        StudentCourse actualSc = sut.registerCourse(validSc);

        Assert.assertEquals(expectedSc, actualSc);
        verify(mockStudentCourseRepo, times(1)).save(any());
    }

    @Test(expected = ResourcePersistenceException.class)
    public void registerCourse_throwsException_givenStudentAlreadyRegisteredForCourse() {
        StudentCourse existing = new StudentCourse("studentId", "courseId");
        StudentCourse duplicate = new StudentCourse("studentId", "courseId");
        when(mockStudentCourseRepo.findByStudentIdAndCourseId(duplicate.getStudentId(), duplicate.getCourseId())).thenReturn(existing);

        // Act
        try {
            sut.registerCourse(duplicate);
        } finally {
            // Assert
            verify(mockStudentCourseRepo, times(1)).findByStudentIdAndCourseId(duplicate.getStudentId(), duplicate.getCourseId());
            verify(mockStudentCourseRepo, times(0)).save(duplicate);
        }
    }

    @Test
    public void cancelCourse_returnsTrue_givenValidStudentIdAndCourseId() {

        Course course = new Course("TEST", "2000", "Test course", 25, 4, true);
        course.setId("courseId");

        StudentCourse original = new StudentCourse("studentId", "courseId");
        when(mockStudentCourseRepo.getCourseById(any())).thenReturn(course);
        sut.registerCourse(original);

        boolean cancelled = sut.cancelCourse(original.getStudentId(), original.getCourseId());

        Assert.assertTrue(cancelled);

    }

    @Test
    public void getRegisteredCourses_returnsSuccessfully() {
        StudentCourse existing = new StudentCourse("studentId", "courseId");

        List<StudentCourse> availableCourseList = sut.getRegisteredCourses("courseId");

        Assert.assertNotNull(availableCourseList);
    }
}
