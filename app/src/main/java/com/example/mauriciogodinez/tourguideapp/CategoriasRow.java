package com.example.mauriciogodinez.tourguideapp;


public class CategoriasRow {
    private Integer imagenRow;
    private String titleRow;
    private String contentRow;
    private Integer numberRow;

    public CategoriasRow(Integer imagenRow, String titleRow, String contentRow, Integer numberRow) {
        this.imagenRow = imagenRow;
        this.titleRow = titleRow;
        this.contentRow = contentRow;
        this.numberRow = numberRow;
    }

    public CategoriasRow() {
        this.imagenRow = 0;
        this.titleRow = "";
        this.contentRow = "";
        this.numberRow = 0;
    }

    public Integer getImagenRow() {
        return imagenRow;
    }

    public void setImagenRow(Integer imagenRow) {
        this.imagenRow = imagenRow;
    }

    public String getTitleRow() {
        return titleRow;
    }

    public void setTitleRow(String titleRow) {
        this.titleRow = titleRow;
    }

    public String getContentRow() {
        return contentRow;
    }

    public void setContentRow(String contentRow) {
        this.contentRow = contentRow;
    }

    public Integer getNumberRow() {
        return numberRow;
    }

    public void setNumberRow(Integer numberRow) {
        this.numberRow = numberRow;
    }
}
