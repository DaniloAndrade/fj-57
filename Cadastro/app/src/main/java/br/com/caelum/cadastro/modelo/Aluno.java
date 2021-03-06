package br.com.caelum.cadastro.modelo;

import java.io.Serializable;

/**
 * Created by android5243 on 12/09/15.
 */
public class Aluno implements Serializable{


    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private String email;
    private String caminhoFoto;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getNota() {
        return nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString() {
        return "Aluno : {" +
                "id :" + id +
                ", nome: '" + nome + '\'' +
                ", telefone :'" + telefone + '\'' +
                ", endereco :'" + endereco + '\'' +
                ", site : '" + site + '\'' +
                ", nota : " + nota +
                ", email :'" + email + '\'' +
                '}';
    }

    public interface ListenerForAluno{
        public void execute();
    }


    public void executeSeTemEmail(ListenerForAluno listener){
        if(email != null && !email.isEmpty()){
            listener.execute();
        }
    }

    public void executeSeTemTelefone(ListenerForAluno listener){
        if(telefone != null && !telefone.isEmpty()){
            listener.execute();
        }
    }

    public void executeSeTemSite(ListenerForAluno listener){
        if(site != null && !site.isEmpty()){
            listener.execute();
        }
    }

    public void executeSeTemEndereco(ListenerForAluno listener){
        if(endereco != null && !endereco.isEmpty()){
            listener.execute();
        }
    }
}
