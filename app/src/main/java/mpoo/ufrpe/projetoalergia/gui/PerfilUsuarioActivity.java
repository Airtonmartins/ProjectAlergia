package mpoo.ufrpe.projetoalergia.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Pessoa;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.RemedioDTO;
import mpoo.ufrpe.projetoalergia.negocio.Sessao;

/**
 * Created by Airton on 11/11/2015.
 */
public class PerfilUsuarioActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static Context contexto;
    private ListView lstRemedio;
    private TextView txtNome;
    private TextView txtCpf;
    private TextView txtDataNascimento;
    private AdapterListRemedio adapterListView;
    private ImageView iv;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Button btnAddListaNegra;
    private Pessoa pessoa = Sessao.getInstancia().getPessoaLogada();

    /**
     * Metodo chamado ao iniciar a activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        contexto = this;

        lstRemedio = (ListView)findViewById(R.id.lstRemedios);
        txtNome = (TextView)findViewById(R.id.txtNome);
        txtCpf = (TextView)findViewById(R.id.txtCpf);
        txtDataNascimento = (TextView)findViewById(R.id.txtDataNascimento);
        iv = (ImageView) findViewById(R.id.imageView);

        iv.setImageURI(pessoa.getFoto());

        txtNome.setText(pessoa.getNome());
        txtCpf.setText(GuiUtil.formatCPF(pessoa.getCpf()));

        String dt = sdf.format(pessoa.getDataDeNascimento());
        txtDataNascimento.setText(dt);

        if (pessoa.getListaNegra() != null) {
            adapterListView = new AdapterListRemedio(this, pessoa.getListaNegra());
        } else {
            adapterListView = null;
        }

        lstRemedio.setAdapter(adapterListView);
        lstRemedio.setOnItemClickListener(this);

    }

    /**
     * Metodo elimina as janelas de perfil que forem para o background
     */
    protected void onPause() {
        super.onPause();
        finish();
    }

    /**
     * Metodo adicioa itens ao action bar se ele existir
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil, menu);

        return true;
    }

    /**
     * captura o clique que inicia activity perfil de usuario
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_home:
                Intent intent = new Intent(PerfilUsuarioActivity.this, PesquisaActivity.class);
                startActivity(intent);


                break;
            case R.id.action_sair:

                finish();

                break;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Intercepta cliques no adapterview e inicia o perfil de remedio activity
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        btnAddListaNegra = (Button)findViewById(R.id.btnAddListaNegra);

        RemedioDTO remedioDTO = adapterListView.getItem(position);

        Intent it = new Intent(this, PerfilRemedioActivity.class);

        it.putExtra("REMEDIO",remedioDTO.getId());
        setResult(Activity.RESULT_OK, it);

        startActivityForResult(it, 0);
    }

    /**
     *
     * @return          contexto local
     */
    public static Context getContexto() {
        return contexto;
    }

    /**
     * Captura clique para iniciar o perfil de remedio
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, PerfilRemedioActivity.class);
        startActivityForResult(it, 0);
    }
}
