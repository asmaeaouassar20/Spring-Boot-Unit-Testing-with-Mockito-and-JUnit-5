package algostyle.asmaeaouassar.testing_with_springboot.repository;

import algostyle.asmaeaouassar.testing_with_springboot.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {

}
