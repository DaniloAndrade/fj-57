package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
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

import static android.widget.AdapterView.*;


public class ListaAlunosActivity extends ActionBarActivity {

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";
    private ListView listViewAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listViewAlunos = (ListView) findViewById(R.id.list_alunos);
        addListenerListView(listViewAlunos);
        registerForContextMenu(listViewAlunos);


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
        listViewAlunos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                Log.i("CLICK ITEM LIST", "Clicou no aluno: " + alunoSelecionado.toString());
                Toast.makeText(
                        ListaAlunosActivity.this, "Clicou no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra(ALUNO_SELECIONADO,alunoSelecionado);
                startActivity(intent);
            }
        });

        listViewAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CLICK LONGO ITEM LIST", "Clicou longo no aluno: " + parent.getItemAtPosition(position).toString());
                Toast.makeText(
                        ListaAlunosActivity.this, "Clicou longo no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                return false;
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
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listViewAlunos.getItemAtPosition(contextMenuInfo.position);

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        MenuItem ligar = menu.add("Ligar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunosActivity.this);
                                alunoDAO.deletar(aluno);

                                carregarAlunos(listViewAlunos);
                                alunoDAO.close();
                            }
                        }).setNegativeButton("Não",null).show();


                return false;
            }
        });
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
