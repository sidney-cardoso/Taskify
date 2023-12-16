package br.com.sidneycardoso.taskify.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import br.com.sidneycardoso.taskify.core.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskForm {

    @NotNull
    @Size(min = 5, max = 50)
    private String taskName;

    @NotNull
    private String description;

    @NotNull
    @NotBlank(message = "Date must not be blank")
    private String conclusionDate;

    @NotNull
    private Status status;

}
