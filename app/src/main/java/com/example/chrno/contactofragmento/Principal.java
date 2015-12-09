package com.example.chrno.contactofragmento;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;

import com.example.chrno.contactofragmento.fragmentos.FragmentoContacto;
import com.example.chrno.contactofragmento.fragmentos.FragmentoLista;
import com.example.chrno.contactofragmento.util.Contacto;
import com.example.chrno.contactofragmento.util.onFragmentoInteraccionListener;

public class Principal extends AppCompatActivity implements onFragmentoInteraccionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        /*****************/
        if(savedInstanceState!=null){

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /*****/
    @Override
    public void onInteraccion(Contacto aux) {
FragmentoContacto fragmentoContacto = (FragmentoContacto)getFragmentManager().
        findFragmentById(R.id.fragmentoContacto);

FragmentoLista fragmentoLista =(FragmentoLista)getFragmentManager().
        findFragmentById(R.id.fragment);

        if (fragmentoContacto == null || !fragmentoContacto.isInLayout()) {
            //Vertical
           fragmentoLista.actSecond(aux);
        }else{
            //Horizontal
            actualizaFoto(aux);
            fragmentoContacto.setDato(aux);
        }
    }
    public void mostrar(View v){
        tostada("zsdfg");
    }
    /*****/

    public void actualizaFoto(Contacto aux){
        ImageView iv=(ImageView) findViewById(R.id.ivFoto);
        String ruta=aux.getRutaFoto();
        if(!ruta.equals("")){
            File f=new File(ruta);
            Bitmap bitmap;
            if(f.exists()){
                bitmap= BitmapFactory.decodeFile(f.getAbsolutePath());
                iv.setImageBitmap(bitmap);
            }
        }else{
            iv.setImageResource(R.mipmap.ic_launcher);
        }
    }
    public void tostada(Contacto s){
        Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();
    }
    public void tostada(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
//    public void añadir(){
//        i = new Intent(this, FormAdd.class);
//        startActivityForResult(i, ANADIR);
//    }
//    public void editar(int pos){
//        tostada("editar ");
//    }
//    public void borrar(int pos){
//        tostada("borrar");
//    }
}
