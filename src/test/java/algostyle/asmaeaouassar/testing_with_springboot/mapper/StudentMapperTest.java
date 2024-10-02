package algostyle.asmaeaouassar.testing_with_springboot.mapper;

import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentDto;
import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentResponseDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    // first declare the service that we want to test
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp(){
        studentMapper=new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){

        // create the input which is studentDto
        StudentDto studentDto=new StudentDto("achraf","pr","asm@gmail.com",1);

        Student student=studentMapper.toStudent(studentDto);
        Assertions.assertEquals(studentDto.firstname(),student.getFirstname());
        Assertions.assertEquals(studentDto.lastname(),student.getLastname());
        Assertions.assertEquals(studentDto.email(),student.getEmail());

        assertNotNull(student.getSchool()); // verify first that the school is null before calling "getId()" method avoid nullPointerException
        Assertions.assertEquals(studentDto.schoolId(),student.getSchool().getId());

    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
        var exception=assertThrows(NullPointerException.class,()->studentMapper.toStudent(null));
        assertEquals("student dto should not be null",exception.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        // Given
        Student student=new Student("asmae","aouassar","asm@gmail.com",19.5F);

        // When
        StudentResponseDto studentResponseDto=studentMapper.toStudentResponseDto(student);

        // Then
        Assertions.assertEquals(student.getFirstname(),studentResponseDto.firstname());
        Assertions.assertEquals(student.getLastname(),studentResponseDto.lastname());
        Assertions.assertEquals(student.getEmail(),studentResponseDto.email());
    }
}

// "testing"  doesn't necessarily  need a real database
// "test isolation" : write "test" for "studentService"  in isolation of its dependencies : "studentMapper" and "studentRepository", we need to mock them using "mockito" framework