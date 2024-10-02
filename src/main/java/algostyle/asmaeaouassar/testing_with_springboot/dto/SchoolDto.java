package algostyle.asmaeaouassar.testing_with_springboot.dto;

import jakarta.validation.constraints.NotEmpty;

public record SchoolDto(
        @NotEmpty
        String name
) {
}
