package com.example.mespada.logueo_calculo_imc;

import android.content.Context;

/**
 * Created by maquillalo on 23/01/2017.
 */

public class Literales {

    private Context context;
    private String estado;

    public Literales(Context context) {
        this.context = context;
    }

    //Método que devuelve los estados nutricionales que se mostrarán en la tabla de valores
    public String [] ArrayEstados () {
        String [] array = {
                context.getResources().getString(R.string.desnutrido),
                context.getResources().getString(R.string.bajo_peso),
                context.getResources().getString(R.string.normal),
                context.getResources().getString(R.string.sobrepeso),
                context.getResources().getString(R.string.obesidad)
        };
        return array;
    }

    //Método que devuelve el estado que se mostrará junto con el IMC en la Activity donde se
    //muestra también el resultado del IMC (ResultActivity)
    public String MostrarEstado(double imc) {

        if(imc >= 31) {
            estado = context.getResources().getString(R.string.obesidad);
        } else if(imc >= 25 && imc < 31) {
            estado = context.getResources().getString(R.string.sobrepeso);
        } else if(imc >= 18.5 && imc < 25) {
            estado = context.getResources().getString(R.string.normal);
        } else if(imc >= 16 && imc < 18.5) {
            estado = context.getResources().getString(R.string.bajo_peso);
        } else {
            estado = context.getResources().getString(R.string.desnutrido);
        }
        return estado;
    }
}
