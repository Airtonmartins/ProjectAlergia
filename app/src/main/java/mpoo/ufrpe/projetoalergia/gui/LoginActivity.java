package mpoo.ufrpe.projetoalergia.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.negocio.UsuarioNegocio;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextSenha;
    private Button btnLogar;
    private Button btnCadastrar;
    private ImageView img;
    private static Context contexto;
    private UsuarioNegocio usuarioNegocio;

    /**
     * Metodo que é chamado no inicio da activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contexto = this;
        editTextLogin = (EditText) findViewById(R.id.edtLogin);
        editTextSenha = (EditText) findViewById(R.id.edtSenha);
        img = (ImageView) findViewById(R.id.img);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextLogin.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();
                login(login, senha);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoMain = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intentGoMain);
            }
        });

    }

    /**
     * metodo que inicia o processo do login
     * @param login        login preenchido pelo usuario
     * @param senha        senha preenchida pelo usuario
     */
    private void login(String login, String senha){

        if(!validarLoginActivity(login,senha))
            return ;

        try {
            usuarioNegocio.fazerLogin(login, senha);
            Intent intentGoMain = new Intent(LoginActivity.this, PesquisaActivity.class);
            startActivity(intentGoMain);
            GuiUtil.showMessage(LoginActivity.this,"Login efetuado");
            finish();
        } catch (ProjetoAlergiaException e){
            GuiUtil.showMessage(LoginActivity.this,e.getMessage());
        }
    }

    /**
     * este metodo eh chamado logo que a janela da activity se faz visivel
     */
    @Override
    protected void onResume() {
        super.onResume();
        //se eu chamar antes da janela estar visivel (ou seja do  oncreate), o app ainda nao tem contexto e sqlhelper falha em instanciar
        //aqui forco a criacao do usuarioNegio que vai se atrelar ao contexto do app e nao da activity
        usuarioNegocio=UsuarioNegocio.getInstancia(getContexto());
    }

    /**
     * Adiciona menu action bar se presente
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    /**
     * Metodo valida os campos da gui
     * @param login     login do usuario
     * @param senha     senha do usuario
     * @return          se true, todos os campos contem informacoes validas
     */
    public boolean validarLoginActivity(String login, String senha){

        if((login==null || login.equals(""))&&(senha==null || senha.equals(""))){
            GuiUtil.showError(editTextLogin,"Login obrigatório");
            GuiUtil.showError(editTextSenha, "Senha Obrigatória");
            return false;
        }else if (login==null || login.equals("")){
            GuiUtil.showError(editTextLogin, "Login obrigatório");
            return false;
        }else if(senha==null || senha.equals("")){
            GuiUtil.showError(editTextSenha, "Senha Obrigatória");

            return false;
        }
        return true;
    }

    /**
     *
     * @return      contexto local
     */
    public static Context getContexto() {
        return contexto;
    }
    
}
