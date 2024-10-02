package algostyle.asmaeaouassar.testing_with_springboot.controller;

import algostyle.asmaeaouassar.testing_with_springboot.dto.SchoolDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.School;
import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import algostyle.asmaeaouassar.testing_with_springboot.repository.SchoolRepository;
import algostyle.asmaeaouassar.testing_with_springboot.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public SchoolDto createSchool(@Valid @RequestBody SchoolDto schoolDto){
        return schoolService.createSchool(schoolDto);
    }


    @GetMapping
    public List<SchoolDto> getAllSchools(){
        return this.schoolService.getAllStudents();
    }

    @DeleteMapping("/{school_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSchoolById(@PathVariable("school_id") int id){
        schoolService.deleteSchoolById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
        var errors=new HashMap<String,String>();
        exception.getBindingResult().getAllErrors().forEach(error->{
            var fieldName=((FieldError)error).getField();
            var errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception
    ){
       return new ResponseEntity<>("cannot delete the school since it is linked to students",HttpStatus.CONFLICT);
    }



}

/*
    var  est une fonctionnalité introduite en java 10
    pour simplifier la déclaration des variables
    il permet au compilateur de déduire automatiquement le type de la variable en fct de la valeur qui lui est assigné

 */