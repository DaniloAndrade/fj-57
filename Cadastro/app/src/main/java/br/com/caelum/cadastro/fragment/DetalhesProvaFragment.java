package br.com.caelum.cadastro.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;


public class DetalhesProvaFragment extends Fragment {

    private Prova provaSelecionada;

    private TextView materia;
    private TextView data;
    private ListView topicos;

    private void buscaComponentes(View layout){
        this.materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        this.data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        this.topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void populaCamposComDados(Prova prova){
        if(prova != null){
            this.materia.setText(prova.getMateria());
            this.data.setText(prova.getData());
            this.topicos.setAdapter(new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos()));
        }
    }



    public DetalhesProvaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        if (getArguments() != null){
            this.provaSelecionada = (Prova) getArguments().getSerializable("Prova");

        }


        buscaComponentes(view);
        populaCamposComDados(provaSelecionada);

        return view;


    }





}
