package com.example.dietamacro;

import androidx.annotation.NonNull;

public class Cibo {

    private String id;
    private String nome;
    private int carboidrati;
    private int proteine;
    private int grassi;
    private int calorie;

    public Cibo() {}

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCarboidrati() {
        return carboidrati;
    }

    public void setCarboidrati(int carboidrati) {
        this.carboidrati = carboidrati;
    }

    public int getProteine() {
        return proteine;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
    }

    public int getGrassi() {
        return grassi;
    }

    public void setGrassi(int grassi) {
        this.grassi = grassi;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
