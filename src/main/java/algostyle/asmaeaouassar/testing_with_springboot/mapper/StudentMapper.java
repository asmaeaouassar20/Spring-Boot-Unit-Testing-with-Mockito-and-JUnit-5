package algostyle.asmaeaouassar.testing_with_springboot.mapper;


import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentDto;
import algostyle.asmaeaouassar.testing_with_springboot.dto.StudentResponseDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.School;
import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstname(),student.getLastname(),student.getEmail());
    }


    public Student toStudent(StudentDto studentDto){
        if(studentDto==null){
            throw new NullPointerException("student dto should not be null");
        }
        var student=new Student();
        student.setFirstname(studentDto.firstname());
        student.setLastname(studentDto.lastname());
        student.setEmail(studentDto.email());

        var school=new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);
        // ****************** ?????????????????????????????????????????????????????????
        return student;
    }

}
