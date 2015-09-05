package caelum.com.br.olamundo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by android5243 on 05/09/15.
 */
public class OlaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ola);


        Button botaoDiga = (Button) findViewById(R.id.bt_diga);
        final TextView textFalou = (TextView) findViewById(R.id.txt_falou);
        final EditText editDiga = (EditText) findViewById(R.id.edit_diga);



        botaoDiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable text = editDiga.getText();
                textFalou.setText(text);
            }
        });
    }
}
