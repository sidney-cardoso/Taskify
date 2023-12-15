package br.com.sidneycardoso.taskify.dto;

import org.springframework.format.annotation.NumberFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import br.com.sidneycardoso.taskify.model.Status;

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
    @NumberFormat(pattern = "00/00/0000")
    private LocalDate conclusionDate;

    @NotNull
    private Status status;

}
