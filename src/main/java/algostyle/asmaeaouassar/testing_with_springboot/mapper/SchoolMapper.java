package algostyle.asmaeaouassar.testing_with_springboot.mapper;

import algostyle.asmaeaouassar.testing_with_springboot.dto.SchoolDto;
import algostyle.asmaeaouassar.testing_with_springboot.entity.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
    public School toSchool(SchoolDto schoolDto){
        return new School(schoolDto.name());
    }
}
