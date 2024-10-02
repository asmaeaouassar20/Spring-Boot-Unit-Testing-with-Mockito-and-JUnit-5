package algostyle.asmaeaouassar.testing_with_springboot.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="first_name",length = 20)
    private String firstname;
    @Column(name="last_name",length = 20)
    private String lastname;
    @Column(unique = true)
    private String email;
    private Float grade;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;


    public Student(String firstname,String lastname,String email,Float grade){
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.grade=grade;
    }
}
