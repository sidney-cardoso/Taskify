package br.com.sidneycardoso.taskify.core.response;

import br.com.sidneycardoso.taskify.core.model.Task;
import br.com.sidneycardoso.taskify.core.model.User;

public class RegistrationResponse {
    private User user;
    private Task task;
    private String errorMessage;

    public RegistrationResponse(User user) {
        this.user = user;
    }

    public RegistrationResponse(Task task) {
        this.task = task;
    }

    public RegistrationResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
