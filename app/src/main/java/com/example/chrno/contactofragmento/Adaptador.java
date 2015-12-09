package com.example.chrno.contactofragmento;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chrno.contactofragmento.util.Contacto;

import java.io.File;
import java.util.List;

/**
 * Created by 2dam on 30/09/2015.
 */
public class Adaptador extends ArrayAdapter<Contacto> {

    private Context contexto;//la actividad que va a contener el listview
    private int recurso;//el layout que se usa para rellenar cada uno de los item del liestview
    private List<Contacto> lista;//lista de valores que va a mosrtrar
    private LayoutInflater i;

    public Adaptador(Context contexto, List<Contacto> lista) {
        super(contexto, R.layout.item,lista);

        this.contexto=contexto;
        this.recurso=R.layout.item;
        this.lista=lista;
        //Genera el espacio y lo llena con el layout recurso
        i = (LayoutInflater) contexto.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        //colocamos i en el constructor para ahorrar recursos y lo declaramos de instancia
    }
    static class ViewHolder{
        private TextView tv,tv2;
        private ImageView iv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();//Se crea una clase para almacenar cosas
            convertView = i.inflate(recurso, null);

            vh.tv = (TextView) convertView.findViewById(R.id.tvNombre);
            vh.tv2= (TextView) convertView.findViewById(R.id.tvTelefonos);
            vh.iv=(ImageView) convertView.findViewById(R.id.imageView3);
//            ImageView iv=(ImageView)getActivity().findViewById(R.id.imageView3);
            convertView.setTag(vh);
        }else{ vh= (ViewHolder) convertView.getTag(); }

        Contacto aux=lista.get(position);

        vh.tv.setText(aux.getNombre());
        vh.tv2.setText(aux.getStringTelefonos());
//        File f=new File(aux.getRutaFoto());
//        Bitmap b;
//        if(f.exists()){
//            b= BitmapFactory.decodeFile(f.getAbsolutePath());
//        }else{
//            b=BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.ic_launcher);
//        }
//        vh.iv.setImageBitmap(b);
        return convertView;
    }

}
