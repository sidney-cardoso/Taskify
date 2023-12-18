package br.com.sidneycardoso.taskify.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @Size(min = 3, max = 255)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;
}
