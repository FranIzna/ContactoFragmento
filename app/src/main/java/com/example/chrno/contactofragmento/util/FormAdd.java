package com.example.chrno.contactofragmento.util;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrno.contactofragmento.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chrno on 17/10/2015.
 */
public class FormAdd extends AppCompatActivity {
    private Intent i;
    private int id=-1;
    private TextView nombre,telf1;
    private TextView telf2,telf3,telf4;
    private ImageView ivEdita;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.form);
            ivEdita=(ImageView)findViewById(R.id.ivEditar);
            nombre=(TextView) findViewById(R.id.edNombre);
            telf1=(TextView) findViewById(R.id.edTelefono1);
            telf1.setHint(this.getString(R.string.min));
            telf2=(TextView) findViewById(R.id.edTelefono2);
            telf2.setHint(this.getString(R.string.opcional));
//            telf2.setVisibility(View.INVISIBLE);
            telf3=(TextView) findViewById(R.id.edTelefono3);
            telf3.setHint(this.getString(R.string.opcional));
//            telf3.setVisibility(View.INVISIBLE);
            telf4=(TextView) findViewById(R.id.edTelefono4);
            telf4.setHint(this.getString(R.string.opcional));
//            telf4.setVisibility(View.INVISIBLE);

            i=this.getIntent();

        }
    public static final int foto = 1;

    public void img(View v){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(intent, foto);
    }
    File img=new File("");
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == foto) {
//            Bitmap thumbnail = data.getParcelableExtra("data");
            Uri uri = data.getData();
            String[] fileColumn={MediaStore.Images.Media.DATA};
            Cursor c=getContentResolver().query(uri,fileColumn,null,null,null);
            c.moveToFirst();
            int indice=c.getColumnIndex(fileColumn[0]);
            String ruta=c.getString(indice);
            c.close();

            img=new File(ruta);
            if(img.exists()){
                Bitmap b= BitmapFactory.decodeFile(img.getAbsolutePath());
                ivEdita.setImageBitmap(b);
            }
        }
    }
        public void add(View v){
            String nom,tlf1,tlf2,tlf3,tlf4;
            List<String> nums=new ArrayList<>();

            nom=nombre.getText().toString();
            tlf1=telf1.getText().toString();

            if(!nom.isEmpty() && !tlf1.isEmpty()){
                nums.add(tlf1);
                if(telf2.length()>0){
                    tlf2=telf2.getText().toString();
                    nums.add(tlf2);
                } if(telf3.length()>0){
                    tlf3=telf3.getText().toString();
                    nums.add(tlf3);
                } if(telf4.length()>0){
                    tlf4=telf4.getText().toString();
                    nums.add(tlf4);
                }
//                id=Agenda.getUltId();
//                if(id == -1){
                    Random r=new Random();
                    id=r.nextInt(100)+1;
//                }

                Bundle b=new Bundle();
                Contacto aux=new Contacto();
                aux.setId(id);
                aux.setNombre(nom);
                aux.setTelefonos(nums);
                if(img.exists()){
                    System.out.println("RUTA: " + img.getAbsolutePath());
                    aux.setRutaFoto(img.getAbsolutePath());
                }
                b.putSerializable("aux", aux);

                i.putExtras(b);
                setResult(Activity.RESULT_OK, i);

//                Agenda.setContacto(new Contacto((long) id, nom, nums));
//                Principal.actualizar();

                finish();
            }else Toast.makeText(this, R.string.campoVacio,
                        Toast.LENGTH_SHORT).show();
        }
        public void cancelar(View v){
            finish();
        }

}

