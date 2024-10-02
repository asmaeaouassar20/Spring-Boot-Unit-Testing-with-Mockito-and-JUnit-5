package algostyle.asmaeaouassar.testing_with_springboot.entity;

import jakarta.persistence.*;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;

    private String about;

    @OneToOne
    @JoinColumn(
            name="student_id"
    )
    private Student student;
}
