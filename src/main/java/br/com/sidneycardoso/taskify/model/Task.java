package br.com.sidneycardoso.taskify.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @Column(length = 50, nullable = false, name = "task_name")
    private String taskName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "conclusion_date")
    private LocalDate conclusionDate;

    @Column(nullable = false, length = 14)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;
}