package mpoo.ufrpe.projetoalergia.negocio;

import android.content.Context;

import java.util.List;

import mpoo.ufrpe.projetoalergia.dao.RemedioDAO;
import mpoo.ufrpe.projetoalergia.dao.UsuarioDAO;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Pessoa;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Usuario;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Componente;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;
import mpoo.ufrpe.projetoalergia.negocio.infra.Util;

/**
 * Created by Allan on 15/10/2015.
 */
public class UsuarioNegocio  {

    private static RemedioDAO daoRemedio;
    private static UsuarioDAO daoUsuario;

    /* singleton */
    private static UsuarioNegocio instancia = new UsuarioNegocio();

    private UsuarioNegocio() {}
    public static UsuarioNegocio getInstancia(Context context) {
        daoRemedio = RemedioDAO.getInstancia(context);
        daoUsuario = UsuarioDAO.getInstancia(context);
        return instancia;
    }



    private Sessao sessao = Sessao.getInstancia();

    /**
     *
     * @param pessoa            objeto da pessoa a ser validada
     * @param confirmarSenha    senha redigitidada para comparação
     * @throws ProjetoAlergiaException      Caso as verificacoes de negocio falhem.
     */
    public void validarCadastro(Pessoa pessoa, String confirmarSenha) throws ProjetoAlergiaException{

        Usuario usuarioDTO = daoUsuario.buscarUsuarioPorLogin(pessoa.getUsuario().getLogin());
        Pessoa pessoaDTO = daoUsuario.retornaPessoaPorCpf(pessoa.getCpf());
        StringBuilder builder = new StringBuilder();

        Util.validarStringEspaco(pessoa.getUsuario().getLogin(),"login");
        Util.validarStringEspaco(pessoa.getUsuario().getSenha(),"senha");
        Util.isCPF(pessoa.getCpf());

        if (usuarioDTO != null){
            builder.append("Esse usuario já está cadastrado");
        }else if(pessoaDTO!=null){
            builder.append("CPF inválido");
        } else if(!pessoa.getUsuario().getSenha().equals(confirmarSenha)) {
            builder.append("Senha não confere");
        }else{
            daoUsuario.cadastrarUsuario(pessoa);
        }
        if (builder.length() > 0){
            throw new ProjetoAlergiaException(builder.toString());
        }
    }

    /**
     * Metodo para inserir uma pessoa no cadastro
     * @param pessoa    pessoa a ser cadastrada
     */
    public void inserirUsuario(Pessoa pessoa){
        daoUsuario.cadastrarUsuario(pessoa);
    }

    /**
     *
     * @param componentes       Lista de componentes a serem verificados
     * @throws ProjetoAlergiaException          Caso algum componente já estava na lista negra.
     */
    public void possoTomar(List<Componente> componentes)throws ProjetoAlergiaException{

        for(Componente componenteListaNegra: daoRemedio.retornaComponentesDaListaNegra()){
            for(Componente componente: componentes){
                if(componente.getNome().toLowerCase().equals(componenteListaNegra.getNome().toLowerCase())){
                    throw new ProjetoAlergiaException("Você é alérgico a este remédio");
                }
            }
        }

    }

    /**
     *
     * @param idRemedio     id do remedio a ser adicionado na lista negra do usuario logado
     */
    public void adicionarAlergia(int idRemedio){
        daoUsuario.adicionarAlergia(idRemedio);
        sessao.getPessoaLogada().setListaNegra(daoRemedio.retornarListaNegraPorUid(sessao.getPessoaLogada().getId()));
    }

    /**
     *
     * @param idRemedio     id do remedio a ser removido da lista negra do usuario logado
     */
    public void removerAlergia(int idRemedio){
        daoUsuario.removerAlergia(idRemedio);
        sessao.getPessoaLogada().setListaNegra(daoRemedio.retornarListaNegraPorUid(sessao.getPessoaLogada().getId()));
    }

    /**
     *
     * @return         total de usuários cadastrados no sistema.
     */
    public int totalUsuarios() {
        return daoUsuario.contarTotalUsuarios();
    }

    /**
     *
     * @param id        id da pessoa desejada
     * @return          objeto pessoa
     * @throws ProjetoAlergiaException Caso pessoa não pode ser instanciada com sucesso.
     */
    public Pessoa pesquisarPessoaPorId(int id) throws ProjetoAlergiaException {
        Pessoa pessoa = null;
        pessoa = daoUsuario.buscarPessoaPorId(id);

        return pessoa;
    }

    /**
     *
     * @param login         login do usuario a ser realizado
     * @param senha         senha do usuario a ser verificada
     * @throws ProjetoAlergiaException      Caso verificações de negócio falhem.
     */
    public void fazerLogin(String login, String senha) throws ProjetoAlergiaException {
        Usuario usuario = daoUsuario.buscarUsuarioPorLogin(login);
        StringBuilder builder = new StringBuilder();
        Util.validarStringEspaco(login, "login");
        if (usuario == null){
            builder.append("usuario não existe");
        } else if (!usuario.getSenha().equals(senha)){
            builder.append("login ou senha incorretos");
        }

        if (builder.length() > 0){
            throw new ProjetoAlergiaException(builder.toString());
        }
        Sessao sessao = Sessao.getInstancia();
        sessao.setUsuarioLogado(usuario);
        sessao.setPessoaLogada(pesquisarPessoaPorId(usuario.getId()));
    }
}
