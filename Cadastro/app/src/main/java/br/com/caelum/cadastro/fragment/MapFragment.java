package br.com.caelum.cadastro.fragment;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.Localizador;

/**
 * Created by android5243 on 03/10/15.
 */
public class MapFragment extends SupportMapFragment {
    private  Localizador localizador;
    @Override
    public void onResume() {
        super.onResume();
        localizador = new Localizador(getActivity());
        LatLng coordenada = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");
        Log.i("MAP","Cordenadas da Caelum: "+ coordenada);

        centralizaNo(coordenada);
        carregaAlunos();
    }

    private void carregaAlunos() {
        AlunoDAO alunoDAO = new AlunoDAO(getActivity());
        List<Aluno> alunos = alunoDAO.getLista();
        for (Aluno aluno : alunos){

            LatLng coordenada = localizador.getCoordenada(aluno.getEndereco());
            if (coordenada != null){
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(aluno.getNome());
                //TODO Adicionar a foto do Aluno
                markerOptions.position(coordenada);
                getMap().addMarker(markerOptions);
            }
        }
        alunoDAO.close();

    }

    private void centralizaNo(LatLng coordenada) {
        GoogleMap map = getMap();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada,11));

    }
}
