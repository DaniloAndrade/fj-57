package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.fragment.MapFragment;

public class MostrarAlunosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_alunos);

        MapFragment mapFragment = new MapFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mostra_alunos_mapa,mapFragment)
                .commit();
    }

}
