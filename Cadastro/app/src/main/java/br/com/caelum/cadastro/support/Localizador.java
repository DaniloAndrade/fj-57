package br.com.caelum.cadastro.support;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android5243 on 03/10/15.
 */
public class Localizador {

    private final Geocoder geocoder;

    public Localizador(Context context) {
        geocoder = new Geocoder(context);
    }


    public LatLng getCoordenada(String endereco){
        try {
            List<Address> addresses = geocoder.getFromLocationName(endereco, 1);
            if (!addresses.isEmpty()){
                Address address = addresses.get(0);
                return new LatLng(address.getLatitude(),address.getLongitude());

            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
