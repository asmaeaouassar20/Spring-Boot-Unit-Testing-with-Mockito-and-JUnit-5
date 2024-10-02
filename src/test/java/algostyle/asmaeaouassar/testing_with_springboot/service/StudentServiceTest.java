package algostyle.asmaeaouassar.testing_with_springboot.service;

import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentDto;
import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentResponseDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import algostyle.asmaeaouassar.testing_with_springboot.mapper.StudentMapper;
import algostyle.asmaeaouassar.testing_with_springboot.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    // the service we want to test
    @InjectMocks
    private StudentService studentService;

    // dependencies that we need to mock
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student(){
        // Given
        StudentDto studentDto=new StudentDto("achraf","pr","as@gmail.com",1);
        Student student=new Student("achraf","pr","as@gmail.com",14.4F);
        Student savedStudent=new Student("achraf","pr","as@gmail.com",14.4F);
       // savedStudent.setId(1);

        // mock the calls
        Mockito.when(studentMapper.toStudent(studentDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("achraf","pr","as@gmail.com"));

        // When
        StudentResponseDto studentResponseDto=studentService.saveStudent(studentDto);

        // Then
        assertEquals(studentDto.firstname(),studentResponseDto.firstname());
        assertEquals(studentDto.lastname(),studentResponseDto.lastname());
        assertEquals(studentDto.email(),studentResponseDto.email());

           // verify that save() method is called once
        Mockito.verify(studentMapper,Mockito.times(1)).toStudent(studentDto);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
        Mockito.verify(studentMapper,Mockito.times(1)).toStudentResponseDto(student);

    }

    @Test
    public void should_return_all_students(){
        // Given
        List<Student> listStudents=new ArrayList<>();
        Student student1=new Student("asmae","aouassar","as@gmail.com",19.5F);
        Student student2=new Student("achraf","pr","ach@gmail.com",14F);
        Student student3=new Student("ali","aouss","alli@gmail.com",18F);
        listStudents.add(student1);
        listStudents.add(student2);
        listStudents.add(student3);


        // Mock calls
        Mockito.when(studentRepository.findAll()).thenReturn(listStudents);
        Mockito.when(studentMapper.toStudentResponseDto(student1)).thenReturn(new StudentResponseDto("asmae","aouassar","as@gmail.com"));
        Mockito.when(studentMapper.toStudentResponseDto(student2)).thenReturn(new StudentResponseDto("achraf","pr","ach@gmail.com"));
        Mockito.when(studentMapper.toStudentResponseDto(student3)).thenReturn(new StudentResponseDto("ali","aouss","alli@gmail.com"));

        //When
        List<StudentResponseDto> result=studentService.getAllStudents();

        // Then
        assertEquals(result.size(),listStudents.size());
        Mockito.verify(studentRepository,Mockito.times(1)).findAll();

    }

    @Test
    public void should_find_student_by_id(){
        // Given
        Student student=new Student("asmae","aouassar","as@gmail.com",18.75F);
        Integer id=4;

        // Mock calls
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto("asmae","aouassar","as@gmail.com"));

        // When
        StudentResponseDto studentResponseDto=studentService.findStudentById(id);

        // Then
        assertEquals(studentResponseDto.firstname(),student.getFirstname());
        assertEquals(studentResponseDto.lastname(),student.getLastname());
        assertEquals(studentResponseDto.email(),student.getEmail());

        Mockito.verify(studentRepository,Mockito.times(1)).findById(id);
    }

    @Test
    public void should_return_students_by_firstname(){
        // Given
        String firstname="as";

        List<Student> students=new ArrayList<>();
        Student student1=new Student("asmae","aouassar","soma@gmail.com",18.75F);
        Student student2=new Student("assile","sora","salli@gmail.com",17.5F);
        students.add(student1);
        students.add(student2);

        // Mock calls
        Mockito.when(studentRepository.findAllByFirstnameContaining(firstname)).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(student1)).thenReturn(new StudentResponseDto("asmae","aouassar","soma@gmail.com"));
        Mockito.when(studentMapper.toStudentResponseDto(student2)).thenReturn(new StudentResponseDto("assile","sora","salli@gmail.com"));

        // When
        var studentResponseDtoList=studentService.findStudentsByFirstname(firstname);


        // Then
        assertEquals(studentResponseDtoList.size(),students.size());
        Mockito.verify(studentRepository,Mockito.times(1)).findAllByFirstnameContaining(firstname);

    }
}