package com.tfb.ednevnik.model;

import java.util.List;

public class PredmetiWithOcjene {
    
    private Predmet predmet;
    private List<Ocjene> ocjene;
    private Double averageOcjena;
    public PredmetiWithOcjene(Predmet predmet, List<Ocjene> ocjene, Double averageOcjena) {
        this.predmet = predmet;
        this.ocjene = ocjene;
        this.averageOcjena = averageOcjena;
    }

    public PredmetiWithOcjene(){
        
    }
    public Predmet getPredmet() {
        return predmet;
    }
    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
    public List<Ocjene> getOcjene() {
        return ocjene;
    }
    public void setOcjene(List<Ocjene> ocjene) {
        this.ocjene = ocjene;
    }
    public Double getAverageOcjena() {
        return averageOcjena;
    }
    public void setAverageOcjena(Double averageOcjena) {
        this.averageOcjena = averageOcjena;
    }

    
}
