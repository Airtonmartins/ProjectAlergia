package mpoo.ufrpe.projetoalergia.gui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.Remedio;
import mpoo.ufrpe.projetoalergia.negocio.RemedioNegocio;
import mpoo.ufrpe.projetoalergia.negocio.UsuarioNegocio;
import mpoo.ufrpe.projetoalergia.negocio.infra.ComparatorComponentePeso;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;


public class PerfilRemedioActivity extends AppCompatActivity {

    private static Context contexto;
    private RemedioNegocio remedioNegocio;

    private TextView txtTextRemedio;
    private TextView txtTextFabricante;
    private TextView txtTextCodBarra;
    private ImageView iv;
    private ListView lstComponente;
    private AdapterListComponente adapterListView;
    private TextView txtComponente;
    private TextView txtPeso;
    private Button btnAddListaNegra;
    private Button imgPerigo;
    private Remedio remedio;
    private UsuarioNegocio usuarioNegocio;

    /**
     * Metodo chamado ao iniciar o activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_remedio);

        txtTextRemedio = (TextView)findViewById(R.id.txtNome);
        txtTextFabricante = (TextView)findViewById(R.id.txtCpf);
        txtTextCodBarra = (TextView)findViewById(R.id.textView2);
        iv = (ImageView)findViewById(R.id.imageView2);
        lstComponente = (ListView)findViewById(R.id.lstComponente);
        txtComponente = (TextView)findViewById(R.id.txtComponente);
        txtPeso = (TextView)findViewById(R.id.txtPeso);
        btnAddListaNegra = (Button)findViewById(R.id.btnAddListaNegra);
        imgPerigo = (Button)findViewById(R.id.imagemperigo);
        contexto = this;
        remedioNegocio = RemedioNegocio.getInstancia(contexto);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("REMEDIO");
        remedio = remedioNegocio.retornarRemedioPorId(id);
        usuarioNegocio = UsuarioNegocio.getInstancia(contexto);

        try{
            usuarioNegocio.possoTomar(remedio.getComponentes());
            imgPerigo.setBackgroundResource(R.drawable.backperigo);
        }catch(ProjetoAlergiaException e){
            GuiUtil.showMessage(PerfilRemedioActivity.this,e.getMessage());
            imgPerigo.setBackgroundResource(R.drawable.perigo);
        }

        adapterListView = new AdapterListComponente(this,remedio.getComponentes());
        lstComponente.setAdapter(adapterListView);
        txtTextRemedio.setText(remedio.getNome());
        txtTextFabricante.setText(remedio.getFabricante());
        txtTextCodBarra.setText(remedio.getCodigoDeBarra());
        iv.setImageURI(Uri.parse(remedio.getUriIcone()));

        if (remedioNegocio.checarRemedioNaListaNegra(remedio.getId())!= null) {
            btnAddListaNegra.setBackgroundResource(R.drawable.remove);
        }else {
            btnAddListaNegra.setBackgroundResource(R.drawable.add);
        }


        txtComponente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(remedio.getComponentes());
                lstComponente.setAdapter(adapterListView);
                }
        });

        txtPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(remedio.getComponentes(), new ComparatorComponentePeso());

                lstComponente.setAdapter(adapterListView);
            }
            });
        btnAddListaNegra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (remedioNegocio.checarRemedioNaListaNegra(remedio.getId())== null) {
                    btnAddListaNegra.setBackgroundResource(R.drawable.remove);
                    usuarioNegocio.adicionarAlergia(remedio.getId());
                    GuiUtil.showMessage(PerfilRemedioActivity.this, "Remédio adicionado na lista negra");

                } else {
                    usuarioNegocio.removerAlergia(remedio.getId());
                    btnAddListaNegra.setBackgroundResource(R.drawable.add);
                    GuiUtil.showMessage(PerfilRemedioActivity.this, "Remédio removido");
                }
            }
        });
    }

    /**
     * Adiciona itens ao action bar se ele estiver presente
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil_remedio, menu);

        return true;
    }

    /**
     * metodo que intercepta o clique no item do menu desejado
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_perfil:
                Intent intentGoMain = new Intent(PerfilRemedioActivity.this, PerfilUsuarioActivity.class);
                startActivity(intentGoMain);
                break;

            case R.id.action_home:
                Intent intent = new Intent(PerfilRemedioActivity.this, PesquisaActivity.class);
                startActivity(intent);
                break;

            case R.id.action_sair:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * finaliza as janelas que forem pro background
     */
    protected void onPause() {
        super.onPause();
        finish();
    }

    /**
     *
     * @return          retorna contexto local
     */
    public static Context getContexto() {
        return contexto;
    }

}







