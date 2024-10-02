package algostyle.asmaeaouassar.testing_with_springboot.service;


import algostyle.asmaeaouassar.testing_with_springboot.dto.SchoolDto;
import algostyle.asmaeaouassar.testing_with_springboot.mapper.SchoolMapper;
import algostyle.asmaeaouassar.testing_with_springboot.mapper.StudentMapper;
import algostyle.asmaeaouassar.testing_with_springboot.repository.SchoolRepository;
import algostyle.asmaeaouassar.testing_with_springboot.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public SchoolDto createSchool(SchoolDto schoolDto){
        var school=schoolMapper.toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    public List<SchoolDto> getAllStudents(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }

    public void deleteSchoolById(int id){
        schoolRepository.deleteById(id);
    }
}
