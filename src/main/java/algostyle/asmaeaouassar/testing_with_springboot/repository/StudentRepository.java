package algostyle.asmaeaouassar.testing_with_springboot.repository;

import algostyle.asmaeaouassar.testing_with_springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByFirstnameContaining(String firstname);
}
