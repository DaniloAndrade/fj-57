package br.com.caelum.cadastro.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;


public class ListaProvasFragment extends Fragment {

    private ListView listaViewProvas;



//    public static ListaProvasFragment newInstance(String param1, String param2) {
//        ListaProvasFragment fragment = new ListaProvasFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public ListaProvasFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);
        listaViewProvas = (ListView) view.findViewById(R.id.lista_provas_listview);


        Prova prova = new Prova("20/06/2015","Matemática");
        prova.setTopicos(Arrays.asList("Álgebra linear","Cálculo","Estatística"));

        Prova prova2 = new Prova("25/07/2015","Português");
        prova2.setTopicos(Arrays.asList("Complemento nominal", "Orações subordinadas", "Análise sintática"));

        listaViewProvas.setAdapter(new ArrayAdapter<Prova>(
                getActivity(), android.R.layout.simple_list_item_1, Arrays.asList(prova, prova2)));

        setListener();

        return view;
    }

    private void setListener() {
        listaViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),"Prova selecionada: " + prova,Toast.LENGTH_LONG).show();
            }
        });
    }






}
