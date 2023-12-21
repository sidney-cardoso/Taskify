package br.com.sidneycardoso.taskify.core.model;

public enum Status {
    TO_DO("A fazer"),
    DOING("Fazendo"),
    DONE("Feito");

    private String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
