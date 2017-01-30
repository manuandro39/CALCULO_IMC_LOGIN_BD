package com.example.mespada.logueo_calculo_imc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maquillalo on 23/01/2017.
 */

public class ListaAdapter extends BaseAdapter {
    private Context context;
    private String[] valor;
    private String[] estado;
    private int id_layout_celda;
    private Integer[] arrayImagenes;

    public ListaAdapter(Context cont, String[] valor_imc, String[] estado, Integer[] arrImagenes, int idCelda) {

        this.context = cont;
        this.valor = valor_imc;
        this.estado = estado;
        this.id_layout_celda = idCelda;
        this.arrayImagenes = arrImagenes;
    }

    @Override
    public int getCount() {
        return valor.length;
    }

    @Override
    public Object getItem(int pos) {
        return null;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int elem, View convertView, ViewGroup parent) {

        View cv = convertView;

        if (cv == null) { //Se infla la vista si es la primera vez que se va a mostrar
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            cv = layoutInflater.inflate(id_layout_celda, null);
        }

        //Creamos variables con los datos que hay que mostrar
        String infovalor = valor[elem];
        String infoestado = estado[elem];
        Integer infoimagenes = arrayImagenes[elem];

        //Creamos los objetos textView del XML fila
        TextView tv_info = (TextView) cv.findViewById(R.id.text_view_valor);
        TextView tv_info2 = (TextView) cv.findViewById(R.id.text_view_estado);
        ImageView iv_imagen = (ImageView) cv.findViewById(R.id.image);

        //Rellenamos el valor y estado con los valores
        tv_info.setText(infovalor);
        tv_info2.setText(infoestado);
        iv_imagen.setImageResource(infoimagenes);

        //Devolvemos el valor de la vista
        return cv;
    }
}
