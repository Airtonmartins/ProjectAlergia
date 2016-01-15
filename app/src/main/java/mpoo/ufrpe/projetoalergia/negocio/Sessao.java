package mpoo.ufrpe.projetoalergia.negocio;

import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Pessoa;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Usuario;


public class Sessao {
    /* singleton */
    private static Sessao instancia = new Sessao();
    private Sessao(){}
    public static Sessao getInstancia() {
        return instancia;
    }

    private Usuario usuarioLogado;
    private Pessoa pessoaLogada = null;

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa pessoa) {
        this.pessoaLogada = pessoa;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }


}
