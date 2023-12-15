package br.com.sidneycardoso.taskify.response;

import br.com.sidneycardoso.taskify.model.Task;

public class TaskResponse {
    private Iterable<Task> tasks;
    private String errorMessage;

    public TaskResponse(Iterable<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Iterable<Task> getTasks() {
        return tasks;
    }

    public void settasks(Iterable<Task> tasks) {
        this.tasks = tasks;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
