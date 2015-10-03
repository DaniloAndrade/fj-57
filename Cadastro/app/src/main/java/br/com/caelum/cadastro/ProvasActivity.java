package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;

public class ProvasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        TipoDispositivo tipoDispositivo = TipoDispositivo
                .valueOf(getResources().getString(R.string.tipo_dispositivo));

        tipoDispositivo.configuraFragment(this);


    }


    enum TipoDispositivo{
        SMARTPHONE {
            @Override
            void configuraFragment(ProvasActivity provasActivity) {
                FragmentTransaction fragmentTransaction = provasActivity
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.provas_view, new ListaProvasFragment());
                fragmentTransaction.commit();
            }
        },
        TABLET {
            @Override
            void configuraFragment(ProvasActivity provasActivity) {
                FragmentTransaction fragmentTransaction = provasActivity
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .replace(R.id.provas_lista, new ListaProvasFragment())
                        .replace(R.id.provas_detalhes, new DetalhesProvaFragment());
                fragmentTransaction.commit();
            }
        };


        abstract void configuraFragment(ProvasActivity provasActivity) ;
    }

}
