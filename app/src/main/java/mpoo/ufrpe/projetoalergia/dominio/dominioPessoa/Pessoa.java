package mpoo.ufrpe.projetoalergia.dominio.dominioPessoa;

import android.net.Uri;

import java.util.Date;
import java.util.List;

import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.RemedioDTO;


public class Pessoa {

    private int id;
    private Usuario usuario;
    private String nome;
    private Date dataDeNascimento;
    private String cpf;
    private List<RemedioDTO> listaNegra;
    private Uri foto;

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public Pessoa(Usuario usuario, String nome, String cpf, Date dataDeNascimento, Uri foto) {
        this.usuario = usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.foto = foto;
    }

    public Pessoa(){

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<RemedioDTO> getListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(List<RemedioDTO> listaNegra) {
        this.listaNegra = listaNegra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
