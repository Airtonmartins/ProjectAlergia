package mpoo.ufrpe.projetoalergia.dao.script;

/**
 * Created by Airton on 12/11/2015.
 */
public class ScriptTabelaSQL {
    public static String getTabelaPessoa() {

        StringBuilder sqlBilder = new StringBuilder();
        sqlBilder.append("CREATE TABLE  tabela_pessoa  (  ");
        sqlBilder.append("_id_pessoa   integer primary key autoincrement,   ");
        sqlBilder.append("nome_pessoa  text not null,  ");
        sqlBilder.append("cpf_pessoa   text not null unique,   ");
        sqlBilder.append("data_nascimento_pessoa date,");
        sqlBilder.append("foto_pessoa text not null);");
        return sqlBilder.toString();

    }

    public static String getTabelaUsuario() {

        StringBuilder userBild = new StringBuilder();
        userBild.append("CREATE TABLE  tabela_usuario ( ");
        userBild.append("_id_usuario   integer primary key autoincrement,   ");
        userBild.append("login_usuario  text not null unique,  ");
        userBild.append("senha_usuario  text not null, ");
        userBild.append("id_pessoa_usuario   integer,  ");
        userBild.append("foreign key ( id_pessoa_usuario ) references  tabela_pessoa ( _id_pessoa ) );");
        return userBild.toString();

    }

    public static String getTabelaRemedio() {

        StringBuilder sqlBildeer = new StringBuilder();
        sqlBildeer.append("CREATE TABLE  tabela_remedio ( ");
        sqlBildeer.append("_id_remedio  integer primary key autoincrement, ");
        sqlBildeer.append("nome_remedio text not null, ");
        sqlBildeer.append("fabricante_remedio text not null, ");
        sqlBildeer.append("uri_icone_remedio text not null, ");
        sqlBildeer.append("codigo_barra text not null);");

        return sqlBildeer.toString();
    }

    public static String getTabelaComponente() {

        StringBuilder sqlBildeer = new StringBuilder();
        sqlBildeer.append("CREATE TABLE  tabela_componente ( ");
        sqlBildeer.append("_id_componente  integer primary key autoincrement,  ");
        sqlBildeer.append("nome_componente text not null);");
        return sqlBildeer.toString();
    }

    public static String getTabelaRemedioComponente() {

        StringBuilder sqlBildeer = new StringBuilder();
        sqlBildeer.append("CREATE TABLE  tabela_remedio_componente  ( ");
        sqlBildeer.append("_id_remedio_componente   integer primary key autoincrement,  ");
        sqlBildeer.append("_id_componente   integer, ");
        sqlBildeer.append("_id_remedio   integer,  ");
        sqlBildeer.append("peso_remedio_componente   float not null, ");
        sqlBildeer.append("foreign key ( _id_componente  ) references  tabela_componente (_id_componente), ");
        sqlBildeer.append("foreign key ( _id_remedio ) references  tabela_remedio (_id_remedio));");
        return sqlBildeer.toString();
    }

    public static String getTabelaAlergia(){

        StringBuilder sqlBildeer = new StringBuilder();
        sqlBildeer.append("CREATE TABLE  tabela_alergia  ( ");
        sqlBildeer.append("_id_alergia  integer primary key autoincrement,  ");
        sqlBildeer.append("id_fk_pessoa  integer,  ");
        sqlBildeer.append("id_fk_remedio  integer,  ");
        sqlBildeer.append("foreign key (id_fk_pessoa) references  tabela_pessoa (_id_pessoa), ");
        sqlBildeer.append("foreign key (id_fk_remedio) references  tabela_remedio (_id_remedio));");
        return  sqlBildeer.toString();
    }
}


