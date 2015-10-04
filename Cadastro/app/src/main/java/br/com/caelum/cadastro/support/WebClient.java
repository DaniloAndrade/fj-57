package br.com.caelum.cadastro.support;

import android.os.AsyncTask;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by android5243 on 26/09/15.
 */
public class WebClient{

    private static final String URL = "http://www.caelum.com.br/mobile";

    public String post(String json){
        try {
            HttpPost post = new HttpPost(URL);
            post.setEntity(new StringEntity(json));
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json" );
            DefaultHttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(post);

            String res = EntityUtils.toString(response.getEntity());

            return res;
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

}
