package algostyle.asmaeaouassar.testing_with_springboot.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        String firstname,
        @NotEmpty (message="exception: le champs <lastname> ne doit pas etre vide")
        String lastname,
        String email,
        Integer schoolId
) {

}
