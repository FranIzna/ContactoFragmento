package com.example.chrno.contactofragmento.fragmentos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.*;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chrno.contactofragmento.*;
import com.example.chrno.contactofragmento.actividades.ActivitySecond;
import com.example.chrno.contactofragmento.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chrno on 02/12/2015.
 */
public class FragmentoLista extends Fragment {
    private List<Contacto> l=new ArrayList<>();
    private View fragView;
    private ListView lv;
    private Adaptador ad;
    private onFragmentoInteraccionListener listener;
    private static onFragmentoInteraccionListener listener2;
    private Agenda a;
    private Bundle saveInstance;
    public FragmentoLista() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView= inflater.inflate(R.layout.principal_port, container, false);
        saveInstance=savedInstanceState;
        inicio();
        return fragView;
    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo vistainfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int posicion = vistainfo.position;//cogemos la posicion del elemento pulsado en la vista
        switch(item.getItemId()){//acciones que hara nuestro menu contextual
            case R.id.action_edit:
                editar(posicion);
                return true;
            case R.id.action_remove:
                borrar(posicion);
                return true;
            default: return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {//creamos nuestro menu contextual
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add){
            añadir();
            return true;
        }
        if(id==R.id.action_ordena_asc) {
//            Collections.sort(agenda);
//            cl.notifyDataSetChanged();
            return true;
        }
        if(id==R.id.action_ordena_desc) {
//            Collections.sort(agenda, new ComparatorNombre());
//            cl.notifyDataSetChanged();
            return true;
        }
        if(id==R.id.leeCopiaTotal){
//            try {
//                agenda=f.leer(this,nombreTotal);
//                guarda.setVisibility(View.INVISIBLE);
////                construyeAdaptador(agenda);
////                actualizarYordena();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            }
        }
        if(id==R.id.creaCopiaTotal) {
//            try {
//                f.escribir(this, agenda, nombreTotal);

//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return true;
        }
//        if(id==R.id.leeCopiaIncremental){
//            try {
//                agenda=f.leer(this,nombreIncremental);
//                construyeAdaptador(agenda);
//                guarda.setVisibility(View.VISIBLE);
//
//                actualizarYordena();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            }
//        }
//        if(id==R.id.guardaIncremental) {
//            try {
//                f.escribir(this, agenda, nombreIncremental);
//                actualizarYordena();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if(id==R.id.sincronizaDatos) {
            System.out.println("EN CONSTRUCCION");
//            try {
//                sincroniza();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void datosPorDefecto(){
        List<String> telfs=new ArrayList<>();
        telfs.add("1234");
        telfs.add("5678");
        //BORRAR
        String s="/mnt/sdcard/Download/";
        l.add(new Contacto(0,"Fran",telfs,s+"url3.jpg"));
        l.add(new Contacto(1, "Ruben", telfs, s+"ur34l.jpg"));
        l.add(new Contacto(2, "Carmen", telfs, s+"url1234.jpg"));
        a.setAgenda(l);
    }

    private void inicio(){
        a=new Agenda(fragView.getContext());
//        l=a.getAgenda();
        datosPorDefecto();
        generaAdaptador();

        /******/
    }
    public void generaAdaptador(){
        if(saveInstance!=null){
            l=(List)saveInstance.getParcelableArrayList("lista");
            a.setAgenda(l);
        }else l=a.getAgenda();
        lv= (ListView) fragView.findViewById(R.id.lv);
        ad=new Adaptador(this.getActivity(),l);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onInteraccion(l.get(position));
            }
        });
        lv.setAdapter(ad);
        registerForContextMenu(fragView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("CONTEXT--_: "+context);
        if(context instanceof onFragmentoInteraccionListener){
            listener= (onFragmentoInteraccionListener) context;
        }else{
            throw new ClassCastException("Solo acepto OnFragmentoInteraccionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof onFragmentoInteraccionListener){
            listener= (onFragmentoInteraccionListener) activity;
            listener2=listener;
        }else{
            throw new ClassCastException("Solo acepto OnFragmentoInteraccionListener");
        }
    }
    private Context c;
    private static final int EDITAR=0,ANADIR=1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode == ANADIR) {
            Bundle b=data.getExtras();
            Contacto c=(Contacto)b.getSerializable("aux");
            l.add(c);
            a.setAgenda(l);
            generaAdaptador();
        }

        if(resultCode==Activity.RESULT_OK && requestCode == 3 ){
            Contacto aux=(Contacto)data.getExtras().getSerializable("aux");
            listener2.onInteraccion(aux);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {//guardar datos
        super.onSaveInstanceState(outState);
        ArrayList<Contacto> a=(ArrayList)l;
            outState.putParcelableArrayList("lista",(ArrayList) a);
//        for(Contacto c:a)
//            System.out.println("SAVE: "+c);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(savedInstanceState!=null){
//            l=(List)savedInstanceState.getParcelableArrayList("lista");
//            a.setAgenda(l);
//        }
//        for(Contacto c:l)
//            System.out.println("SACA: "+c);

    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener=null;
    }
    @Override
    public void onStart(){
        super.onStart();
        generaAdaptador();
    }
    public void tostada(Contacto s){
        Toast.makeText(fragView.getContext(), s.toString(), Toast.LENGTH_SHORT).show();
    }
    public void tostada(String s){
        Toast.makeText(fragView.getContext(),s,Toast.LENGTH_SHORT).show();
    }
    public void añadir(){
        Intent i = new Intent(fragView.getContext(), FormAdd.class);
        startActivityForResult(i, ANADIR);
    }
    public void editar(int pos){
        tostada("editar ");
    }
    public void borrar(int pos){
        tostada("borrar");
    }

    public void actSecond(Contacto aux){
//        System.out.println("ACT SECOND: */***/*//*//**//***/*//*//**//***/*//*//**//***/*//*//**//*");
//        System.out.println("esyhxjdx "+aux);
//        System.out.println("asdfadfhdf"+ActivitySecond.class);*/
        Intent i = new Intent(getActivity(),ActivitySecond.class);
        Bundle b=new Bundle();
        b.putSerializable("aux", aux);
        i.putExtras(b);

        startActivityForResult(i, 3);
    }
}

