package com.schoolmanager.english.domain.dtos.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record PersonDTO(
        UUID id,
        @NotBlank(message = "provide first_name")
        @Size(max = 30, message = "first_name max length is 30 characters")
        @JsonProperty("first_name")
        String firstName,
        @Size(max = 30, message = "last_name max length is 30 characters")
        @JsonProperty("last_name")
        String lastName,
        @NotBlank(message = "provide document")
        @CPF(message = "invalid cpf")
        String document,
        @NotBlank(message = "provide birthdate")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date is in format 'yyyy-MM-dd'")
        String birthdate,
        @NotBlank(message = "provide genre")
        @Size(max = 7, message = "genre max length is one character")
        String genre
) {
}
