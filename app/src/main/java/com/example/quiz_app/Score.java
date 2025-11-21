package com.example.quiz_app;

public class Score {
    private int id;
    private String name;
    private int points;

    // Construtor vazio
    public Score() { }

    // Construtor para inserir novo (sem ID, o banco cria)
    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    // Construtor completo (para ler do banco)
    public Score(int id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}