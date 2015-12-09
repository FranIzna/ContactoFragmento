package com.example.chrno.contactofragmento.fragmentos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chrno.contactofragmento.R;
import com.example.chrno.contactofragmento.util.Contacto;

import java.io.File;

/**
 * Created by Chrno on 02/12/2015.
 */
public class FragmentoContacto extends Fragment {
    private Contacto aux;
    private View vistaFragmento;
    private TextView tvNombre,tvTelefonos;
    private ImageView iv;

    public FragmentoContacto(){
//        Log.v("CONSTRUCTOR", "FRAGMENTO CONTACTO");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vistaFragmento = inflater.inflate(R.layout.principal_land, container, false);
        tvNombre=(TextView)vistaFragmento.findViewById(R.id.tvNombre);
        tvTelefonos=(TextView)vistaFragmento.findViewById(R.id.tvTelefonos);
        iv=(ImageView)vistaFragmento.findViewById(R.id.ivFoto);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        /******/

        return vistaFragmento;
    }

    public void setDato(Contacto aux){
        this.aux=aux;
        tvNombre.setText(this.aux.getNombre());
        tvTelefonos.setText(this.aux.getStringTelefonos());
        File f=new File(aux.getRutaFoto());
        Bitmap b;
        if(f.exists()){
            b= BitmapFactory.decodeFile(f.getAbsolutePath());
        }else{
            b=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        iv.setImageBitmap(b);
    }
File img=new File("");
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode== 1){
//            Bitmap thumbnail = data.getParcelableExtra("data");
                Uri uri = data.getData();
                String[] fileColumn={MediaStore.Images.Media.DATA};
                Cursor c=vistaFragmento.getContext()
                        .getContentResolver().query(uri,fileColumn,null,null,null);
                c.moveToFirst();
                int indice=c.getColumnIndex(fileColumn[0]);
                String ruta=c.getString(indice);
                c.close();

                img=new File(ruta);
                if(img.exists()){
                    Bitmap b= BitmapFactory.decodeFile(img.getAbsolutePath());
                    iv.setImageBitmap(b);
                    aux.setRutaFoto(ruta);
                }
        }
    }
}
