package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


public class ListaAlunosActivity extends ActionBarActivity {

    private ListView listViewAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listViewAlunos = (ListView) findViewById(R.id.list_alunos);
        addListenerListView(listViewAlunos);


        View botaoNovo = findViewById(R.id.lista_alunos_floating_button);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addListenerListView(ListView listViewAlunos) {
        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CLICK ITEM LIST", "Clicou no aluno: " + parent.getItemAtPosition(position).toString());
                Toast.makeText(
                        ListaAlunosActivity.this, "Clicou no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        listViewAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CLICK LONGO ITEM LIST", "Clicou longo no aluno: " + parent.getItemAtPosition(position).toString());
                Toast.makeText(
                        ListaAlunosActivity.this, "Clicou longo no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAlunos(listViewAlunos);
    }

    private void carregarAlunos(ListView listViewAlunos) {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getLista();
        ArrayAdapter<Aluno> arrayAdapter =
                new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);
        listViewAlunos.setAdapter(arrayAdapter);
        alunoDAO.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
