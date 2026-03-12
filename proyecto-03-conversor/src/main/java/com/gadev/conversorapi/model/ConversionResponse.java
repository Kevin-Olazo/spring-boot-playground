package com.gadev.conversorapi.model;

public class ConversionResponse {

    private double valorOriginal;
    private String unidadOriginal;
    private double valorConvertido;
    private String unidadDestino;
    private String formula;

    public ConversionResponse(double valorOriginal, String unidadOriginal, double valorConvertido, String unidadDestino, String formula) {
        this.valorOriginal = valorOriginal;
        this.unidadOriginal = unidadOriginal;
        this.valorConvertido = valorConvertido;
        this.unidadDestino = unidadDestino;
        this.formula = formula;
    }

    public double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public String getUnidadOriginal() {
        return unidadOriginal;
    }

    public void setUnidadOriginal(String unidadOriginal) {
        this.unidadOriginal = unidadOriginal;
    }

    public double getValorConvertido() {
        return valorConvertido;
    }

    public void setValorConvertido(double valorConvertido) {
        this.valorConvertido = valorConvertido;
    }

    public String getUnidadDestino() {
        return unidadDestino;
    }

    public void setUnidadDestino(String unidadDestino) {
        this.unidadDestino = unidadDestino;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
