package br.com.sidneycardoso.taskify.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotNull
    @Size(min = 3, max = 255)
    private String userName;

    @Email
    @NotNull
    @Size(min = 3, max = 255)
    private String userEmail;

    @NotNull
    @NotEmpty
    private String userPassword;
}
