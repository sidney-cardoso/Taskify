package br.com.sidneycardoso.taskify.core.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "id_task")
    private Long idTask;

    @Column(length = 50, name = "task_name")
    @NotBlank(message = "A task name is required!")
    private String taskName;

    @Column
    @NotBlank(message = "A task description is required!")
    private String description;

    @Column(name = "conclusion_date")
    @NotBlank(message = "A date is required")
    private LocalDate conclusionDate;

    @Column(nullable = false, length = 14)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;
}