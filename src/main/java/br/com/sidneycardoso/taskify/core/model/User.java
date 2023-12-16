package br.com.sidneycardoso.taskify.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

}
