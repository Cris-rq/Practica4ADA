package org.ada.va.impl;

public class testing{
    private final static Integer DIMENSION = 4;

    public static void main(String[] args) {
        NReinasVueltaAtras nr = new NReinasVueltaAtras(DIMENSION);
        nr.initTodas();

        for(Integer[] arr : nr.getSoluciones()){
            for(Integer in : arr){
                System.out.print(in + " ");
            }
            System.out.println();
        }
    }
}