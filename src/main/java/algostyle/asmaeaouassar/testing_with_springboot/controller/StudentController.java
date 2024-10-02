package algostyle.asmaeaouassar.testing_with_springboot.controller;

import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentDto;
import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentResponseDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import algostyle.asmaeaouassar.testing_with_springboot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDto addStudent(@Valid @RequestBody StudentDto studentDto){
        return this.studentService.saveStudent(studentDto);
    }
    // @Valide : activer la validation automatique des objets envoyés en tant que corps de requete HTTP
    // L'objet "StudentDto" est validé avant meme que la méthode ne s'exécute
    // cette validation repose sur les contraintes définies dans la classe "StudentDto"


    @GetMapping
    public List<StudentResponseDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{student_id}")
    public StudentResponseDto getStudentById(@PathVariable("student_id") int id){
        return studentService.findStudentById(id);
    }

    @GetMapping("/search/{student_firstname}")
    public List<StudentResponseDto> getStudentsByFirstname(@PathVariable("student_firstname") String firstname){
        return studentService.findStudentsByFirstname(firstname);
    }

    @DeleteMapping("/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("student_id") int id){
        studentService.deleteStudentById(id);
    }

    @DeleteMapping
    public void deleteAllStudents(){
        studentService.deleteAllStudents();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        var errors=new HashMap<String,String>();
        exception.getBindingResult().getAllErrors().forEach(error->{
            var fieldName=((FieldError)error).getField();
            var errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
