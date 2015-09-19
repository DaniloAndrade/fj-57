package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5243 on 12/09/15.
 */
public class AlunoDAO extends SQLiteOpenHelper{


    public static final String DATABASE = "CadastroCaelum";
    public static final int VERSION = 1;
    private static final String ALUNOS = "ALUNOS";
    public static final String TB_ALUNOS = "CREATE TABLE " + ALUNOS + " (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, telefone TEXT, email TEXT, endereco TEXT, site TEXT, nota REAL);";

    public AlunoDAO(Context context) {
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TB_ALUNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + ALUNOS;
        db.execSQL(sql);
        onCreate(db);
    }


    public void adiciona(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("telefone",aluno.getTelefone());
        values.put("email",aluno.getEmail());
        values.put("endereco",aluno.getEndereco());
        values.put("site",aluno.getSite());
        values.put("nota",aluno.getNota());

        getWritableDatabase().insert(ALUNOS,null,values);
    }

    public List<Aluno> getLista() {
        List<Aluno> alunos = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery("SELECT * FROM " + ALUNOS + ";", null);
            while (cursor.moveToNext()){
                Aluno aluno = new Aluno();
                aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
                aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));

                aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
                aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

                alunos.add(aluno);
            }
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }



        return alunos;

    }


    public void deletar(Aluno aluno){
        String[] params = {aluno.getId().toString()};
        getWritableDatabase().delete(ALUNOS,"id=?",params);
    }

    public void alterar(Aluno aluno){


        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("telefone",aluno.getTelefone());
        values.put("email",aluno.getEmail());
        values.put("endereco",aluno.getEndereco());
        values.put("site",aluno.getSite());
        values.put("nota",aluno.getNota());



        getWritableDatabase().update(ALUNOS,values,"id=?",new String[]{aluno.getId().toString()});
    }


    public void adicionaOuAltera(Aluno aluno){
        if (aluno.getId() != null){
            alterar(aluno);
        }else {
            adiciona(aluno);
        }
    }
}
