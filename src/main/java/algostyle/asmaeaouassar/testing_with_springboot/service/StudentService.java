package algostyle.asmaeaouassar.testing_with_springboot.service;

import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentDto;
import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentResponseDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import algostyle.asmaeaouassar.testing_with_springboot.mapper.StudentMapper;
import algostyle.asmaeaouassar.testing_with_springboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper=studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto){
        var student=studentMapper.toStudent(studentDto);
        // *****************  la note de l'etudiant ajoutée est tjrs null ?????????????????
        Student savedStudent=studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(Integer id){
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null); // Si un étudiant est trouvé, il est retourné. Sinon, la méthode retourne "null"
    }

    public List<StudentResponseDto> findStudentsByFirstname(String firstname){
        return studentRepository.findAllByFirstnameContaining(firstname)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudentById(Integer id){
        studentRepository.deleteById(id);
    }


    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }
}
