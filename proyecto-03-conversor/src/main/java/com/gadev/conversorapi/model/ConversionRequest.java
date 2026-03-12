package com.gadev.conversorapi.model;

public class ConversionRequest {

    private double valor;
    private String desde;
    private String hacia;

    public ConversionRequest(){}

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHacia() {
        return hacia;
    }

    public void setHacia(String hacia) {
        this.hacia = hacia;
    }
}
