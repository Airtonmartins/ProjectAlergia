package mpoo.ufrpe.projetoalergia.dao.script;

import android.database.sqlite.SQLiteDatabase;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dao.Helper;
import mpoo.ufrpe.projetoalergia.gui.GuiUtil;


public class ScriptPopularTabelaSQL {
    public static void insertRemedioDB(SQLiteDatabase db){

        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('ASS 100mg','Sanofi-Aventis Farmacêutica Ltda','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.ass_100mg+"','7896004710907')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('ADVI 200mg','Wyeth Indústria Farmacêutica LTDA','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.advil_200mg+"','789104504050')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Anador 500mg','Boehringer Ingelheim do Brasil Química e Farmacêutica LTDA','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.anador_500mg+"','7896026300704')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Atroveran Composto','Cosmed Indústria de Cosméticos e Medicamentos S.A.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.atroveran_composto+"','7891104191090')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Buscopan Composto','Boehringer Ingelheim do Brasil Química e Farmacêutica Ltda.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.buscopan_composto+"','7896026300117')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Cataflam D','Novartis Biociências SA','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.cataflam_d+"','7896261000445')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Coristina® D','Cosmed Indústria de Cosméticos e Medicamentos S.A.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.coristina_d+"','7891142136671')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Diclofenaco Resinato','Laboratório Teuto Brasileiro S/A.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.diclofenaco_resinato+"','7896112148593')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Ibuprofeno 50mg/mL','Brainfarma Indústria Química e Farmacêutica S.A.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.ibuprofeno_50mg+"','7898095343873')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Dipirona Sódica 500mg/mL','Geolab Indústria Farmacêutica S/A','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.diporona_sodica_500mg+"','7896422507134')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Dorflex','Sanofi-Aventis Farmacêutica Ltda.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.dorflex+"','7891058008628')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('EXCEDRIN','Novartis Biociências S.A.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.excedrin+"','7896261012059')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Tylenol 500mg','Janssen-Cilag Farmacêutica Ltda.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.tylenol_500mg+"','7891010986148')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Tylenol 750mg','Janssen-Cilag Farmacêutica Ltda.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.tylenol_750mg+"','7896212422524')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Neosaldina','Takeda Pharma Ltda.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.neosaldina+"','7896641803871')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Paracetamol','Medley Indústria Farmacêutica Ltda.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.paracetamol+"','7896422504911')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Sonrisal','GlaxoSmithKline','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.sonrisal+"','7896090611508')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Sonrisal® Limão','GlaxoSmithKline','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.sonrisal_limao+"','7896090604500')");
        db.execSQL("INSERT INTO " + Helper.TABELA_REMEDIO + "(" + Helper.REMEDIO_NOME + "," + Helper.REMEDIO_FABRICANTE +","+Helper.REMEDIO_URI_ICONE +","+Helper.REMEDIO_COD_BARRA + ") VALUES ('Torsilax®','Brainfarma Indústria Química e Farmacêutica S.A.','android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.torsilax+"','7896714257594')");
    }

    public static void inserirComponenteDB(SQLiteDatabase db){

        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('ácido acetilsalicílico')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('bicarbonato de sódio')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('carbonato de sódio')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('ácido cítrico')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('butilbrometo de escopolamina')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('cafeína')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('carisoprodol')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('citrato de orfenadrina')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('cloridrato de fenilefrina')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('diclofenaco ')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('dipirona')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('ibuprofeno')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('maleato de dexclorfeniramina')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('mucato de isometepteno')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('paracetamol')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('sulfato de hiosciamina')");
        db.execSQL("INSERT INTO "+ Helper.TABELA_COMPONENTE +"("+ Helper.COMPONENTE_NOME+") VALUES ('cloridrato de papaverina')");
    }
    public static void inserirRemedioComponenteDB(SQLiteDatabase db){

        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (1,1,100)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (12,2,200)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (11,3,433.03)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (17,4,30)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (11,4,250)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (16,4,0.9)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (5,5,10)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (11,5,250)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (10,6,44.3)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (1,7,400)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (13,7,1)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (9,7,10)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (6,7,30)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (10,8,44.3)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (12,9,50)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (11,10,500)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (11,11,300)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (8,11,35)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (7,11,50)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (15,12,500)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (6,12,65)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (15,13,500)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (15,14,700)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (11,15,300)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (14,15,30)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (6,15,30)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (15,16,750)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (2,17,1854)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (3,17,400)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (1,17,25)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (4,17,1413)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (2,18,1644)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (3,18,400)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (1,18,325)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (4,18,1507.8)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (7,19,125)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (10,19,50)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (15,19,300)");
        db.execSQL("INSERT INTO "+Helper.TABELA_REMEDIO_COMPONENTE+ "("+Helper.COMPONENTE_ID+","+Helper.REMEDIO_ID+","+Helper.REMEDIO_COMPONENTE_PESO+") VALUES (6,19,30)");
    }

    public static void inserirPessoasDB (SQLiteDatabase db) {

        String[] pessoas={"INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (1,'allan','35653610500','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (2,'teeh','17832562498','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (3,'airton','46304273339','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (4,'joao','37322135016','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (5,'gabriel','67726115270','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (6,'gabi','48711331330','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (7,'joana','21332616801','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (8,'carlos','85663773622','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (9,'tevez','84758074372','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (10,'tereza','29352263804','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (11,'amarcio','85752565219','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (12,'tiado','15802377208','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (13,'bruno','27337234309','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (14,'bruna','06611214372','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (15,'vitor','31001527518','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (16,'gleydson','62180325908','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (17,'weli','74518297237','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (18,'rafaela','58156835662','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (19,'bianca','72218655748','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (20,'fernanda','42575181208','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (21,'alice','12312707136','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (22,'sara','52206795469','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (23,'ester','97611758632','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (24,'rose','37983451474','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');",
        "INSERT INTO tabela_pessoa (_id_pessoa,nome_pessoa,cpf_pessoa,data_nascimento_pessoa,foto_pessoa) VALUES (25,'rafa','67254305977','12-03-1967','"+GuiUtil.FOTO_PADRAO+"');"};

        for(String pessoa:pessoas){
            db.execSQL(pessoa);
        }
    }

    public static void inserirUsuariosDB(SQLiteDatabase db) {

        String[] usuarios = {"INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (1,'allan','allan',1);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (2,'teeh','teeh',2);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (3,'airton','airton',3);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (4,'joao','joao',4);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (5,'gabriel','gabriel',5);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (6,'gabi','gabi',6);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (7,'joana','joana',7);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (8,'carlos','carlos',8);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (9,'tevez','tevez',9);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (10,'tereza','tereza',10);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (11,'amarcio','amarcio',11);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (12,'tiado','tiado',12);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (13,'bruno','bruno',13);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (14,'bruna','bruna',14);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (15,'vitor','vitor',15);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (16,'gleydson','gleydson',16);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (17,'weli','weli',17);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (18,'rafaela','rafaela',18);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (19,'bianca','bianca',19);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (20,'fernanda','fernanda',20);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (21,'alice','alice',21);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (22,'sara','sara',22);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (23,'ester','ester',23);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (24,'rose','rose',24);",
        "INSERT INTO `tabela_usuario` (_id_usuario,login_usuario,senha_usuario,id_pessoa_usuario) VALUES (25,'rafa','rafa',25);"};

        for(String usuario:usuarios){
            db.execSQL(usuario);
        }
    }

    public static void inserirAlergiaDB(SQLiteDatabase db) {
        String[] alergias ={"INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (1,1,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (2,1,4);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (3,1,7);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (4,2,3);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (5,2,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (6,3,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (7,3,19);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (8,3,5);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (9,3,3);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (10,3,2);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (11,4,3);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (12,4,8);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (13,4,7);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (14,5,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (15,6,6);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (16,6,9);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (17,6,10);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (18,6,19);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (19,6,5);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (20,6,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (21,7,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (22,7,19);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (23,8,4);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (24,8,3);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (25,8,10);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (26,8,12);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (27,9,9);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (28,9,10);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (29,9,11);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (30,10,5);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (31,10,2);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (32,10,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (33,10,6);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (34,10,9);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (35,11,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (36,12,6);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (37,12,13);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (38,12,15);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (39,12,17);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (40,12,19);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (41,13,12);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (42,13,14);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (43,13,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (44,13,16);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (45,14,5);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (46,14,6);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (47,14,14);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (48,15,3);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (49,16,17);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (50,17,18);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (51,18,18);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (52,18,17);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (53,18,19);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (54,19,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (55,19,6);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (56,20,3);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (57,20,1);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (58,21,2);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (59,22,2);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (60,23,2);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (61,24,5);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (62,25,9);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (63,25,11);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (64,25,7);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (65,25,19);",
        "INSERT INTO `tabela_alergia` (_id_alergia,id_fk_pessoa,id_fk_remedio) VALUES (66,25,13);"};

        for(String alergia:alergias){
            db.execSQL(alergia);
        }

    }
}
