package com.screenmatch.calculos;

import com.screenmatch.models.Title;

public class CalculadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return this.tempoTotal;
    }

//    public void inclui(Filme f) {
//        this.tempoTotal += f.getDuracaoEmMinutos();
//    }
//
//    public void inclui(Serie s) {
//        this.tempoTotal += s.getDuracaoEmMinutos();
//    }

    public void inclui(Title title) {
        System.out.println("Adicionando duração em minutos de " + title);
        this.tempoTotal += title.getDuracaoEmMinutos();
    }
}
