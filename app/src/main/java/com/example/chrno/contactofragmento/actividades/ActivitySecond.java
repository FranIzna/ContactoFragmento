package com.example.chrno.contactofragmento.actividades;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.chrno.contactofragmento.Principal;
import com.example.chrno.contactofragmento.util.Contacto;
import com.example.chrno.contactofragmento.fragmentos.FragmentoContacto;
import com.example.chrno.contactofragmento.R;

import java.io.File;

public class ActivitySecond extends AppCompatActivity {
    private Contacto aux;
    private String dato;
    private ImageView iv;
    private final int ACTIVIDAD=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null) {
            aux = (Contacto) savedInstanceState.getSerializable("aux");

        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            setContentView(R.layout.activity_second);
            aux = (Contacto) getIntent().getExtras().getSerializable("aux");
            FragmentoContacto fragmento = (FragmentoContacto) getFragmentManager().findFragmentById(R.id.fragment2);

            fragmento.setDato(aux);

        }else {

            Intent i=new Intent(this, Principal.class);
            Bundle b=new Bundle();
//            System.out.println("AUX: "+aux);
            b.putSerializable("aux",aux);

            i.putExtras(b);
            setResult(RESULT_OK,i);
//            startActivityForResult(i, ACTIVIDAD);
            finish();
            //Regresar el valor a la actividad anterior
            //Con un staractivityForResult
            //Y le pasamos valor
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("aux",aux);
    }
}

