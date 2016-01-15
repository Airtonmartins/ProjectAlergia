package mpoo.ufrpe.projetoalergia.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dominio.dominoRemedio.RemedioDTO;
import mpoo.ufrpe.projetoalergia.negocio.RemedioNegocio;
import mpoo.ufrpe.projetoalergia.negocio.UsuarioNegocio;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;

public class PesquisaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static Context contexto;
    private ArrayAdapter<String> adpRemedio;
    private RemedioNegocio remedioNegocio;
    private UsuarioNegocio usuarioNegocio;
    private ListView lstRemedio;
    private EditText edtPesquisa;
    private Button btnPesquisar;
    private CheckBox cbPesquisa;
    private Button scannerButton;
    private AdapterListRemedio adapterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pesquisa);

        contexto = this;

        lstRemedio = (ListView) findViewById(R.id.lstRemedios);
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);
        cbPesquisa = (CheckBox) findViewById(R.id.cbPesquisa);
        scannerButton = (Button) findViewById(R.id.ibCamera);

        lstRemedio.setOnItemClickListener(this);
        btnPesquisar.setOnClickListener(this);
        cbPesquisa.setOnClickListener(this);
        scannerButton.setOnClickListener(this);

        remedioNegocio = RemedioNegocio.getInstancia(contexto);
        usuarioNegocio = UsuarioNegocio.getInstancia(contexto);

        edtPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                autoCompletar();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_pesquisa, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_perfil:
                Intent intentGoMain = new Intent(PesquisaActivity.this, PerfilUsuarioActivity.class);
                startActivity(intentGoMain);

                break;

            case R.id.action_sair:

                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @return      contexto local
     */
    public static Context getContexto() {
        return contexto;
    }

    /**
     * Metodo que intercepta cliques na adapterview
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        RemedioDTO remedioDTO = adapterListView.getItem(position);

        Intent it = new Intent(this, PerfilRemedioActivity.class);
        it.putExtra("REMEDIO",remedioDTO.getId());
        setResult(Activity.RESULT_OK, it);

        startActivityForResult(it, 0);
    }

    /**
     * Metodo que intercepta o codigo de barra proveniente do scanner de codigo de barra
     * @param requestCode       codigo que passamos anteriormente no inicio da activity
     * @param resultCode
     * @param data      contem o codigo de barra nos extrar
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 12){
            if (resultCode == Activity.RESULT_OK){
                String codigoDeBarra = data.getExtras().getString("Codigo_de_Barra");
                edtPesquisa.setText(codigoDeBarra);
            }
        }
    }

    /**
     * aqui trata todos os clicks desta activity
     * @param v         Objeto da view que recebeu o clique
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lstRemedios: {
                Intent it = new Intent(this, PerfilRemedioActivity.class);
                startActivityForResult(it, 0);
                break;
            }

            case R.id.ibCamera: {
                Intent intent = new Intent(v.getContext(), BarcodeScanner.class);
                startActivityForResult(intent,12);

               break;
            }

            case R.id.btnPesquisar: {
                if(cbPesquisa.isChecked()) {
                    /* Entra nas sugestoes */
                    try {
                        adapterListView = new AdapterListRemedio(this,remedioNegocio.sugestoesFiltroColaborativo());
                    } catch (ProjetoAlergiaException e) {
                        GuiUtil.showMessage(this,e.getMessage());
                        adapterListView=null;
                        edtPesquisa.setText("");
                        cbPesquisa.setChecked(false);
                    }
                    if (adapterListView != null) {GuiUtil.showMessage(this,"Mostrando recomendações baseadas no seu perfil ...");}
                    cbPesquisa.setChecked(false);
                    lstRemedio.setAdapter(adapterListView);

                } else {
                    /* Entra na pesquisa avancada */
                    try {
                        adapterListView = new AdapterListRemedio(this,remedioNegocio.sugerirRemediosSimilares());
                    } catch (ProjetoAlergiaException e) {
                        GuiUtil.showMessage(this,e.getMessage());
                        adapterListView=null;
                        edtPesquisa.setText("");
                    }
                    if (adapterListView != null) {GuiUtil.showMessage(this,"Mostrando mais remedios similares ...");}
                    lstRemedio.setAdapter(adapterListView);
                }
                break;
            }

        }

    }

    /**
     * Metodo que entercepta o texto no do edtPesquisa e redireciona para chamdas de negocio corretas
     */
    public void autoCompletar() {
        String nome = edtPesquisa.getText().toString();
        adapterListView = null;
        if (nome.length() > 0) {
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(nome);
            if(matcher.find()) {

                List<RemedioDTO> listaRemedio = remedioNegocio.consultarRemediosCodigoDeBarraParcial(nome);
                adapterListView = new AdapterListRemedio(this,listaRemedio);

            }else{
            List<RemedioDTO> listaRemedio = remedioNegocio.consultarRemedioPorNomeParcial(nome);
            adapterListView = new AdapterListRemedio(this,listaRemedio);}
        }
        lstRemedio.setAdapter(adapterListView);
    }
}