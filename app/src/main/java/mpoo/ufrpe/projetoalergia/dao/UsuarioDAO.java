package mpoo.ufrpe.projetoalergia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Pessoa;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Usuario;
import mpoo.ufrpe.projetoalergia.negocio.Sessao;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;

/**
 * Created by Allan on 16/10/2015.
 */
public class UsuarioDAO {

    private static Helper helper;
    private static RemedioDAO remedioDAO;
    private static Context contextoAtual;

    /* singleton */
    private static UsuarioDAO instancia = new UsuarioDAO();
    private UsuarioDAO() {}
    public static UsuarioDAO getInstancia(Context contexto)
    {
        UsuarioDAO.contextoAtual = contexto;
        UsuarioDAO.helper = new Helper(contexto);
        UsuarioDAO.remedioDAO = RemedioDAO.getInstancia(contexto);
        return instancia;
    }

    private SQLiteDatabase db;
    private Sessao sessao = Sessao.getInstancia();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     *
     * @param cpf       cpf a ser consultado
     * @return          pessoa que possui cpf desejado
     * @throws ProjetoAlergiaException   se houver falha na instanciação do usuário
     */
    public Pessoa retornaPessoaPorCpf(String cpf) throws ProjetoAlergiaException {
        Pessoa pessoa = null;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Helper.TABELA_PESSOA +
                " WHERE " + Helper.PESSOA_CPF + " = ? ", new String[]{cpf});
        if (cursor.moveToFirst()) {
            pessoa = criarPessoa(cursor);
        }
        db.close();
        cursor.close();
        return pessoa;
    }

    /**
     *
     * @param id    id da pessoa desejada
     * @return      pessoa que o id desejado
     * @throws ProjetoAlergiaException      Caso não consiga instanciar pessoa
     */
    public Pessoa buscarPessoaPorId(int id) throws ProjetoAlergiaException {
        Pessoa pessoa = null;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Helper.TABELA_PESSOA +
                " WHERE " + Helper.PESSOA_ID + " =? ", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            pessoa = criarPessoa(cursor);
        }
        db.close();

        cursor.close();
        return pessoa;
    }

    /**
     *
     * @param pessoa        pessoa a ser cadastrada no db.
     */
    public void cadastrarUsuario(Pessoa pessoa){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Helper.PESSOA_NOME, pessoa.getNome());
        values.put(Helper.PESSOA_CPF, pessoa.getCpf());
        values.put(Helper.PESSOA_DATA, simpleDateFormat.format(pessoa.getDataDeNascimento()));
        values.put(Helper.PESSOA_FOTO, pessoa.getFoto().toString());

        long fk_id_pessoa = db.insert(Helper.TABELA_PESSOA, null, values);

        values = new ContentValues();

        values.put(Helper.USUARIO_LOGIN, pessoa.getUsuario().getLogin());
        values.put(Helper.USUARIO_SENHA, pessoa.getUsuario().getSenha());
        values.put(Helper.USUARIO_PESSOA_ID, fk_id_pessoa);
        db.insert(Helper.TABELA_USUARIO, null, values);

        db.close();

    }


    /**
     *
     * @param cursor        cursor a ser usado na criação da pessoa
     * @return              objeto pessoa preenchido
     * @throws ProjetoAlergiaException      Caso não consiga instanciar pessoa.
     */
    private Pessoa criarPessoa(Cursor cursor) throws ProjetoAlergiaException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(cursor.getInt(0));
        pessoa.setNome(cursor.getString(1));
        pessoa.setCpf(cursor.getString(2));
        pessoa.setFoto(Uri.parse(cursor.getString(4)));

        String dateTime = cursor.getString(3);
        Date date;

        try { date = simpleDateFormat.parse(dateTime); }
        catch (ParseException e) {
            throw new ProjetoAlergiaException(e.getMessage());
        }

        pessoa.setDataDeNascimento(date);

        /* carrega listanegra de remedios na pessoa */
        pessoa.setListaNegra(remedioDAO.retornarListaNegraPorUid(pessoa.getId()));

        return pessoa;
    }


    /**
     *
     * @param id_remedio     adicionar id do remedio desejado na lista negra do usuario logado.
     */
    public void adicionarAlergia(int id_remedio){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Helper.ALERGIA_PESSOA_FK_ID, sessao.getUsuarioLogado().getId());
        values.put(Helper.ALERGIA_REMEDIO_FK_ID, id_remedio);
        db.insert(Helper.TABELA_ALERGIA, null, values);
        db.close();
    }

    /**
     *
     * @param id_remedio        remover remedio com id especifidado da lista negra do usuario logado.
     */
    public void removerAlergia(int id_remedio){
        db = helper.getWritableDatabase();
        db.delete(Helper.TABELA_ALERGIA, Helper.ALERGIA_REMEDIO_FK_ID + " =?", new String[]{String.valueOf(id_remedio)});
        db.close();
    }

    /**
     *
     * @param cursor     cursor a ser utilizado na criação do usuário
     * @return           objeto usuario preenchido
     */
    private Usuario criarUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setLogin(cursor.getString(1));
        usuario.setSenha(cursor.getString(2));

        return usuario;
    }

    /**
     *
     * @param login     login do usuario desejado
     * @return          usuario com o login desejado
     */
    public Usuario buscarUsuarioPorLogin(String login){
        SQLiteDatabase db;
        Usuario usuario = null;

        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Helper.TABELA_USUARIO +
                " WHERE " + Helper.USUARIO_LOGIN + " =?", new String[]{login});
        if (cursor.moveToFirst()){
            usuario = criarUsuario(cursor);
        }
        db.close();
        cursor.close();
        return usuario;

    }

    /**
     *
     * @return      total de usuarios cadastrados no sistema
     */
    public int contarTotalUsuarios() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(0) FROM " + Helper.TABELA_USUARIO+";", null);
        int quantidade;
        if ( cursor == null ) {
            quantidade=0;
        } else {
            cursor.moveToFirst();
            quantidade = cursor.getInt(0);
        }
        cursor.close();
        return quantidade;
    }
}
