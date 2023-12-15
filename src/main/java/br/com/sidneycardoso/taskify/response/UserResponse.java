package br.com.sidneycardoso.taskify.response;

import br.com.sidneycardoso.taskify.model.User;

public class UserResponse {
    private Iterable<User> users;
    private String errorMessage;

    public UserResponse(Iterable<User> users) {
        this.users = users;
    }

    public UserResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Iterable<User> getUsers() {
        return users;
    }

    public void setUsers(Iterable<User> users) {
        this.users = users;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
