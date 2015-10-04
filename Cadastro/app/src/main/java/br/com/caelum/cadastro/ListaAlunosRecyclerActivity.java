package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.adapter.AlunoAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunosRecyclerActivity extends AppCompatActivity {

    private ListView listViewAlunos;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos_recycler);

        //listViewAlunos = (ListView) findViewById(R.id.list_alunos);
        //addListenerListView(listViewAlunos);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);



        View botaoNovo = findViewById(R.id.lista_alunos_floating_button);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosRecyclerActivity.this, FormularioActivity.class);
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
                        ListaAlunosRecyclerActivity.this, "Clicou no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        listViewAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CLICK LONGO ITEM LIST", "Clicou longo no aluno: " + parent.getItemAtPosition(position).toString());
                Toast.makeText(
                        ListaAlunosRecyclerActivity.this, "Clicou longo no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //carregarAlunos(listViewAlunos);


        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getLista();

        alunoDAO.close();


        AlunoAdapter alunoAdapter = new AlunoAdapter(alunos);

        recyclerView.setAdapter(alunoAdapter);
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
    }}
