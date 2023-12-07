package br.com.sidneycardoso.taskify.model;

public enum Status {
    TO_DO("A fazer"),
    DOING("Fazendo"),
    DONE("Feito");

    private String description;

    private Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
