package mpoo.ufrpe.projetoalergia.gui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.ufrpe.projetoalergia.R;


public class GuiUtil extends AppCompatActivity {

    public static final Uri FOTO_PADRAO = Uri.parse("android.resource://mpoo.ufrpe.projetoalergia/"+R.drawable.usuario_padrao);

    /* singleton */
    private static GuiUtil instancia = new GuiUtil();
    private GuiUtil(){}

    /**
     * Metodo que retorna instancao do singleton
     * @return
     */
    public static GuiUtil getInstancia() {
        return instancia;
    }

    /**
     * Metodo para mostrar mensagens para usuario
     * @param appCompatActivity         activity do tipo appcompat
     * @param messagem                  mensagem desejada
     */
    public static void showMessage(AppCompatActivity appCompatActivity,String messagem){
        Toast.makeText(appCompatActivity,messagem,Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que mostra mensagem de erro e aviso no componente da gui com dado invalido
     * @param editText              componente da gui com dado invalido
     * @param messagem              mensagem desejada
     */
    public static void showError(EditText editText, String messagem){
        editText.setError(messagem);
    }

    /**
     * Metodo que formata cpf com pontos e um hifem no digito verificador
     * @param cpf       cpf a ser formatado
     * @return          cpf formatado
     */
    public static String formatCPF(String cpf) {
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(cpf);
        if (matcher.matches())
            cpf = matcher.replaceAll("$1.$2.$3-$4");
        return cpf;
    }
}