package mpoo.ufrpe.projetoalergia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Componente;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Remedio;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.RemedioDTO;
import mpoo.ufrpe.projetoalergia.negocio.Sessao;


/**
 * Created by Allan on 17/10/2015.
 */
public class RemedioDAO {

    private static Helper helper;
    private SQLiteDatabase db;

    private Sessao sessao = Sessao.getInstancia();

    /* singleton */
    private static RemedioDAO instancia = new RemedioDAO();
    private RemedioDAO(){}

    /**
     *
     * @return  retorna singleton de RemedioDAO
     */
    public static RemedioDAO getInstancia(Context contexto) {
        RemedioDAO.helper = new Helper(contexto);
        return instancia;
    }

    /**
     *
     * @param nome  parte do nome de um remédio a ser localizado
     * @return      Lista de remedios contendo partes do nome procurado
     */
    public List<RemedioDTO> buscarRemediosPorNomeParcial(String nome) {

        db = helper.getReadableDatabase();

        List<RemedioDTO> listaRemedios = new ArrayList<RemedioDTO>();

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_ID+", "+
                Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_NOME+", "+
                Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_URI_ICONE+" FROM " + Helper.TABELA_REMEDIO + " WHERE "
                +helper.REMEDIO_NOME+" LIKE ?", new String[] {"%"+nome+"%"});

        RemedioDTO remedioDTO = null;
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                remedioDTO = criarUmRemedioDTO(cursor);
                listaRemedios.add(remedioDTO);
            }
        }
        cursor.close();
        return listaRemedios;
    }

    /**
     *
     * @param codigoDeBarra     sequencia ou parte da sequencia do codigo de barra a ser procurado
     * @return                  lista de Remedios contendo parte da sequencia ou a sequencia a ser procuardo
     */
    public List<RemedioDTO> buscarRemediosCodigoBarraParcial(String codigoDeBarra){
        db = helper.getReadableDatabase();

        List<RemedioDTO> listarRemedios = new ArrayList<RemedioDTO>();

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_ID+", "+
                Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_NOME+", "+
                Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_URI_ICONE+" FROM " + Helper.TABELA_REMEDIO + " WHERE "
                +helper.REMEDIO_COD_BARRA+" LIKE ?", new String[] {"%"+codigoDeBarra+"%"});

        RemedioDTO remedioDTO = null;
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                remedioDTO = criarUmRemedioDTO(cursor);
                listarRemedios.add(remedioDTO);
            }
        }
        cursor.close();
        return listarRemedios;

    }

    /**
     *
     * @param codigoDeBarra     sequencia de codigo de barra a ser procurado
     * @return                  remedio que possui o codigo de barra desejado
     */
    public Remedio pesquisarRemedioPorCodigoDeBarra (String codigoDeBarra){

        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_ID+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_NOME+", "+Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_FABRICANTE+", "+Helper.TABELA_COMPONENTE+"."+
                Helper.COMPONENTE_ID+", "+Helper.TABELA_COMPONENTE+"."+
                Helper.COMPONENTE_NOME+", "+Helper.TABELA_REMEDIO_COMPONENTE+
                "."+Helper.REMEDIO_COMPONENTE_PESO+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_URI_ICONE+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_COD_BARRA+" FROM " + Helper.TABELA_REMEDIO_COMPONENTE + " INNER JOIN " + Helper.TABELA_COMPONENTE + " ON (" +
                Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.COMPONENTE_ID + " = " + Helper.TABELA_COMPONENTE + "." + Helper.COMPONENTE_ID + ") INNER JOIN " +
                Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.REMEDIO_ID+ " = " +
                Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE "+Helper.TABELA_REMEDIO+"."+ Helper.REMEDIO_COD_BARRA+" =?;", new String[]{codigoDeBarra});

        return criarUmRemedio(cursor);

    }

    /**
     *
     * @param id     id do remedio a ser pesquisado
     * @return       retorna o remedio que possui o id desejado
     */
    public Remedio pesquisarRemedioPorId(int id){

        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_ID+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_NOME+", "+Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_FABRICANTE+", "+Helper.TABELA_COMPONENTE+"."+
                Helper.COMPONENTE_ID+", "+Helper.TABELA_COMPONENTE+"."+
                Helper.COMPONENTE_NOME+", "+Helper.TABELA_REMEDIO_COMPONENTE+
                "."+Helper.REMEDIO_COMPONENTE_PESO+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_URI_ICONE+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_COD_BARRA+" FROM " + Helper.TABELA_REMEDIO_COMPONENTE + " INNER JOIN " + Helper.TABELA_COMPONENTE + " ON (" +
                Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.COMPONENTE_ID + " = " + Helper.TABELA_COMPONENTE + "." + Helper.COMPONENTE_ID + ") INNER JOIN " +
                Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.REMEDIO_ID+ " = " +
                Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE "+Helper.TABELA_REMEDIO+"."+ Helper.REMEDIO_ID+" =?;", new String[]{String.valueOf(id)});

        return criarUmRemedio(cursor);

    }

    /**
     *
     * @param id        id do remedio a ser pesquisado
     * @return          retorna remedio no tipo RemedioDTO que contem o id desejado
     */
    public RemedioDTO pesquisarRemedioDTOPorId(int id){

        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_ID+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_NOME+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_URI_ICONE+" FROM " + Helper.TABELA_REMEDIO_COMPONENTE + " INNER JOIN " + Helper.TABELA_COMPONENTE + " ON (" +
                Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.COMPONENTE_ID + " = " + Helper.TABELA_COMPONENTE + "." + Helper.COMPONENTE_ID + ") INNER JOIN " +
                Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.REMEDIO_ID+ " = " +
                Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE "+Helper.TABELA_REMEDIO+"."+ Helper.REMEDIO_ID+" =?;", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        return criarUmRemedioDTO(cursor);

    }

    /**
     *
     * @param nome      nome do remedio desejado
     * @return          retorna remedio que possui nome desejado
     */
    public Remedio pesquisarRemedioPorNome(String nome){
        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_ID+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_NOME+", "+Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_FABRICANTE+", "+Helper.TABELA_COMPONENTE+"."+
                Helper.COMPONENTE_ID+", "+Helper.TABELA_COMPONENTE+"."+
                Helper.COMPONENTE_NOME+", "+Helper.TABELA_REMEDIO_COMPONENTE+
                "."+Helper.REMEDIO_COMPONENTE_PESO+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_URI_ICONE+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_COD_BARRA+" FROM " + Helper.TABELA_REMEDIO_COMPONENTE + " INNER JOIN " + Helper.TABELA_COMPONENTE + " ON (" +
                Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.COMPONENTE_ID + " = " + Helper.TABELA_COMPONENTE + "." + Helper.COMPONENTE_ID + ") INNER JOIN " +
                Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.REMEDIO_ID+ " = " +
                Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE "+Helper.TABELA_REMEDIO+"."+ Helper.REMEDIO_NOME+" =?;", new String[]{nome});

        return criarUmRemedio(cursor);

    }

    /**
     *
     * @param cursor        cursor que sera usado para montar o remedio
     * @return              retorna objeto remedio preenchido
     */
    public Remedio criarUmRemedio(Cursor cursor){
        Remedio remedio = null;

        if(cursor.getCount()>0) {
            remedio = new Remedio();

            List<Componente> componentes = new ArrayList<Componente>();
            while (cursor.moveToNext()){
                    remedio.setId(cursor.getInt(0));
                    remedio.setNome(cursor.getString(1));
                    remedio.setFabricante(cursor.getString(2));
                    Componente componente = new Componente();
                    componente.setId(cursor.getInt(3));
                    componente.setNome(cursor.getString(4));
                    componente.setPeso(Float.parseFloat(cursor.getString(5)));
                    componentes.add(componente);
                    remedio.setUriIcone(cursor.getString(6));
                    remedio.setCodigoDeBarra(cursor.getString(7));
            }
            remedio.setComponentes(componentes);

        }
        return remedio;
    }

    /**
     *
     * @param uid   id do usuario para o qual sera retornada a lista negra de remedios
     * @return      lista de remedios que o usuario uid possui
     */
    public List<RemedioDTO> retornarListaNegraPorUid(int uid){

        db = helper.getReadableDatabase();

        List<RemedioDTO> listaRemedios = null;

        Cursor cursor = db.rawQuery("SELECT "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_ID+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_NOME+", "+Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_URI_ICONE + " FROM " + Helper.TABELA_ALERGIA +
                " INNER JOIN " + Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_ALERGIA + "." + Helper.ALERGIA_REMEDIO_FK_ID +
                " = " + Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE "+Helper.TABELA_ALERGIA+"."+ Helper.ALERGIA_PESSOA_FK_ID+
                " =? ORDER BY "+Helper.ALERGIA_PESSOA_FK_ID +" ASC;", new String[]{String.valueOf(uid)});

        RemedioDTO remedioDTO = null;
        if(cursor.getCount() > 0){
            listaRemedios = new ArrayList<RemedioDTO>();
            while(cursor.moveToNext()){
                remedioDTO = criarUmRemedioDTO(cursor);
                listaRemedios.add(remedioDTO);
            }
        }
        return listaRemedios;
    }

    /**
     *
     * @param cursor        cursor que sera usado para montar um RemedioDTO
     * @return              remedio no tipo RemedioDTO preenchido
     */
    public RemedioDTO criarUmRemedioDTO(Cursor cursor) {

        RemedioDTO remedioDTO = null;

        if(cursor.getCount()>0) {
            remedioDTO = new RemedioDTO();
            Log.i("Remedio--->>", cursor.getString(1));
            Log.i("IdRemedio--->>", String.valueOf(cursor.getInt(0)));
            Log.i("UriRemedio--->>", cursor.getString(2));

            remedioDTO.setId(cursor.getInt(0));
            remedioDTO.setNome(cursor.getString(1));
            remedioDTO.setUriIcone(Uri.parse(cursor.getString(2)));
        }

        return remedioDTO;
    }

    /**
     *
     * @param id_remedio        id do remedio a ser procurado na lista negra do usuario logado
     * @return                  remedio no tipo RemedioDTO possuindo id desejado
     */
    public RemedioDTO retornaRemedioListaNegraUsuarioLogado(int id_remedio) {

        RemedioDTO remedioDTO = null;
        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + Helper.TABELA_REMEDIO + "." +
                Helper.REMEDIO_ID + ", "+ Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_NOME+ ", "+ Helper.TABELA_REMEDIO+"."+
                Helper.REMEDIO_URI_ICONE+" FROM " + Helper.TABELA_ALERGIA +
                " INNER JOIN " + Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_ALERGIA + "." + Helper.ALERGIA_REMEDIO_FK_ID +
                " = " + Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE " + Helper.TABELA_ALERGIA + "." + Helper.ALERGIA_PESSOA_FK_ID +
                " =? AND " + Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID +
                " =?;", new String[]{String.valueOf(sessao.getUsuarioLogado().getId()),String.valueOf(id_remedio)});
        if (cursor.moveToFirst()) {
            remedioDTO = criarUmRemedioDTO(cursor);
        }
        return remedioDTO;
    }

    /**
     *
     * @return         Lista de componentes que existem na lista negra do usuario logado
     */
    public List<Componente> retornaComponentesDaListaNegra() {
        db = helper.getReadableDatabase();
        int idUsuarioLogado = sessao.getUsuarioLogado().getId();
        List<Componente> listaNegraComponentes = new ArrayList<Componente>();
        List<RemedioDTO> listaRemedioDTO = new ArrayList<RemedioDTO>();
        Cursor cursor = db.rawQuery("SELECT " + Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ","+
                Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_NOME+", "+
                Helper.TABELA_REMEDIO+"."+Helper.REMEDIO_URI_ICONE+" FROM " + Helper.TABELA_ALERGIA +
                " INNER JOIN " + Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_ALERGIA + "." + Helper.ALERGIA_REMEDIO_FK_ID +
                " = " + Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE " + Helper.TABELA_ALERGIA + "." + Helper.ALERGIA_PESSOA_FK_ID +
                " =? ;", new String[]{String.valueOf(idUsuarioLogado)});

        RemedioDTO remedioDTO = null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                remedioDTO = criarUmRemedioDTO(cursor);
                listaRemedioDTO.add(remedioDTO);
            }
        }

        for (RemedioDTO remedioDTO1 : listaRemedioDTO) {

            cursor = db.rawQuery("SELECT " + Helper.TABELA_COMPONENTE + "." +
                    Helper.COMPONENTE_NOME + " FROM " + Helper.TABELA_REMEDIO_COMPONENTE + " INNER JOIN " + Helper.TABELA_COMPONENTE + " ON (" +
                    Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.COMPONENTE_ID + " = " + Helper.TABELA_COMPONENTE + "." + Helper.COMPONENTE_ID + ") INNER JOIN " +
                    Helper.TABELA_REMEDIO + " ON (" + Helper.TABELA_REMEDIO_COMPONENTE + "." + Helper.REMEDIO_ID + " = " +
                    Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + ") WHERE " + Helper.TABELA_REMEDIO + "." + Helper.REMEDIO_ID + " =?;", new String[]{String.valueOf(remedioDTO1.getId())});

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Componente componente = new Componente();
                    componente.setNome(cursor.getString(0));
                    listaNegraComponentes.add(componente);
                }
            }
        }
        return listaNegraComponentes;
    }

    /**
     *
     * @return      Retorna o total de remedios cadastrados
     */
    public int contarTotalRemedios() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(0) FROM " + Helper.TABELA_REMEDIO+";", null);
        int quantidade;
        if ( cursor == null ) {
            quantidade=0;
        } else {
            cursor.moveToFirst();
            quantidade = cursor.getInt(0);
        }
        return quantidade;
    }

    /**
     *
     * @return      retorna quantidade total dos componentes cadastrados
     */
    public int contarTotalComponentes() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(0) FROM " + Helper.TABELA_COMPONENTE+";", null);
        int quantidade;
        if ( cursor == null ) {
            quantidade=0;
        } else {
            cursor.moveToFirst();
            quantidade = cursor.getInt(0);
        }
        return quantidade;
    }
}
