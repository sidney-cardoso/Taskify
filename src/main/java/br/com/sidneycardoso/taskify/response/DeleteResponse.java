package br.com.sidneycardoso.taskify.response;

public class DeleteResponse {
    private String message;
    private Long deletedId;

    public DeleteResponse(String message, Long deletedId) {
        this.message = message;
        this.deletedId = deletedId;
    }

    public DeleteResponse(String errorMessage) {
        this.message = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getdeletedId() {
        return deletedId;
    }

    public void setdeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }

}
