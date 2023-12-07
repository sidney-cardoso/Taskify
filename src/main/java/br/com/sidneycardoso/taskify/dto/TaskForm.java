package br.com.sidneycardoso.taskify.dto;

import java.time.LocalDate;

import br.com.sidneycardoso.taskify.model.Status;

public class TaskForm {
    private Long idTask;
    private String taskName;
    private String description;
    private LocalDate conclusionDate;
    private Status status;

    public TaskForm() {
    }

    public TaskForm(Long idTask, String taskName, String description, LocalDate conclusionDate, Status status) {
        this.idTask = idTask;
        this.taskName = taskName;
        this.description = description;
        this.conclusionDate = conclusionDate;
        this.status = status;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
