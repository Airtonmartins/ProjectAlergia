package mpoo.ufrpe.projetoalergia.gui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Pessoa;
import mpoo.ufrpe.projetoalergia.dominio.dominioPessoa.Usuario;
import mpoo.ufrpe.projetoalergia.negocio.UsuarioNegocio;

public class CadastroActivity extends AppCompatActivity {

    private boolean isUpdating;
    private int positioning[] = { 0, 1, 2, 3, 5, 6, 7, 9, 10, 11, 13, 14 };
    private EditText editPessoaNome;
    private EditText editPessoaCPF;
    private EditText editUsuarioLogin;
    private EditText editUsuarioSenha;
    private EditText editUsuarioSenhaConfirmar;
    private EditText edtPessoaDataDeNascimentoCadastro;
    private ImageView imageView;
    private Date dataNascimento;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Button btnCadastrar;
    private UsuarioNegocio usuarioNegocio;
    private static Context contexto;
    public static final int IMAGEM_INTERNA = 12;
    public static final int TIRAR_FOTO = 1;
    Uri FOTO = GuiUtil.FOTO_PADRAO;

    /**
     * Metodo que é chamado assim que activity é inciada
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        contexto = this;

        editPessoaNome = (EditText) findViewById(R.id.edtNomePessoa);
        editPessoaCPF = (EditText) findViewById(R.id.edtCpf);
        editUsuarioLogin= (EditText) findViewById(R.id.edtLoginCadastro);
        editUsuarioSenha = (EditText) findViewById(R.id.edtSenhaCadastro);
        editUsuarioSenhaConfirmar = (EditText) findViewById(R.id.edtConfirmarSenhaCadastro);
        edtPessoaDataDeNascimentoCadastro = (EditText) findViewById(R.id.edtDataDeNascimentoCadastro);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setImageURI(GuiUtil.FOTO_PADRAO);

        final int maxNumberLength = 11;
        editPessoaCPF.setKeyListener(keylistenerNumber);
        editPessoaCPF.setText("     -   ");
        editPessoaCPF.setSelection(1);
        editPessoaCPF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String current = s.toString();
				/*
				 * Ok, here is the trick... calling setText below will recurse
				 * to this function, so we set a flag that we are actually
				 * updating the text, so we don't need to reprocess it...
				 */
                if (isUpdating) {
                    isUpdating = false;
                    return;

                }

                String number = current.replaceAll("[^0-9]*", "");
                if (number.length() > 11)
                    number = number.substring(0, 11);
                int length = number.length();

                String paddedNumber = padNumber(number, maxNumberLength);

                String part1 = paddedNumber.substring(0, 3);
                String part2 = paddedNumber.substring(3, 6);
                String part3 = paddedNumber.substring(6, 9);
                String part4 = paddedNumber.substring(9, 11);

                String cpf1 = part1 + "." + part2 + "." + part3 + "-" + part4;

				/*
				 * Set the update flag, so the recurring call to
				 * afterTextChanged won't do nothing...
				 */
                isUpdating = true;
                editPessoaCPF.setText(cpf1);
                editPessoaCPF.setSelection(positioning[length]);
            }
        });

        btnCadastrar = (Button) findViewById(R.id.btnCadastrarCadastro);

        ExibeDataListerner listerner = new ExibeDataListerner();
        edtPessoaDataDeNascimentoCadastro.setOnClickListener(listerner);
        edtPessoaDataDeNascimentoCadastro.setOnFocusChangeListener(listerner);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editPessoaNome.getText().toString().trim();

                String cpf = editPessoaCPF.getText().toString().trim();
                String login = editUsuarioLogin.getText().toString().trim();
                String senha = editUsuarioSenha.getText().toString().trim();
                String senhaConfirmar = editUsuarioSenhaConfirmar.getText().toString().trim();
                String dataDeNascimento = edtPessoaDataDeNascimentoCadastro.getText().toString().trim();
                ImageView iv = (ImageView)findViewById(R.id.imageView);

                Usuario usuario = new Usuario(login,senha);
                Pessoa pessoa = new Pessoa(usuario,nome,formatarCpf(cpf),dataNascimento,FOTO);
                cadastro(pessoa, senhaConfirmar);
            }
        });
    }

    /**
     * Metodo é iniciado assim que a janela se torna visivel
     */
    @Override
    public void onResume() {
        super.onResume();
        //atribuindo aqui pois no oncreate o app ainda nao tem contexto.
        if (usuarioNegocio == null) {
            usuarioNegocio = UsuarioNegocio.getInstancia(contexto);
        }
    }

    /**
     * Metodo que inicia o cadastro da pessoa
     * @param pessoa            pessoa a ser cadastrada
     * @param confirmarSenha    senha a ser validada
     */
    public void cadastro(Pessoa pessoa,String confirmarSenha){

                    if(!validarCadastroVazioActivity(pessoa,confirmarSenha)){
                        return;
                    }
                    if(!validarCampoCpf(pessoa.getCpf())){
                        return;
                    }
                    try{
                        usuarioNegocio.validarCadastro(pessoa, confirmarSenha);
//                        Intent intentGoLogin = new Intent(CadastroActivity.this, LoginActivity.class);
//                        startActivity(intentGoLogin);
                        GuiUtil.showMessage(CadastroActivity.this,"Usuário cadastrado");
                        finish();
                    }catch (Exception e){
                        GuiUtil.showMessage(CadastroActivity.this,e.getMessage());
                    }
    }

    /**
     * Metodo que remove todos os pontos e o hifen da string cpf
     * @param cpf       cpf a ser filtrada
     * @return          cpf filtrada
     */
    public String formatarCpf(String cpf){
        if (cpf.contains(".") | cpf.contains("-")) {
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
        }
        return cpf;
    }

    /**
     * Metodo que valida se os campos do objeto pessoa não estão vazios
     * @param pessoa            pessoa a ser verificada
     * @param confirmarSenha    senha a ser verificada
     * @return                  se true, todos os campos estao preenchidos
     */
    public boolean validarCadastroVazioActivity( Pessoa pessoa , String confirmarSenha){
        if((pessoa.getNome() == null || pessoa.getNome().equals(""))&&(pessoa.getCpf() == null || pessoa.getCpf().equals(""))&&(pessoa.getUsuario().getLogin()==null
                || pessoa.getUsuario().getLogin().equals(""))&&(pessoa.getUsuario().getSenha()==null || pessoa.getUsuario().getSenha().equals(""))&&(confirmarSenha==null ||
                confirmarSenha.equals("")) && (pessoa.getDataDeNascimento()==null)) {

            GuiUtil.showError(editPessoaNome, "Insira o nome");
            GuiUtil.showError(editPessoaCPF, "Insira o CPF");
            GuiUtil.showError(editUsuarioLogin, "Insira o Login");
            GuiUtil.showError(editUsuarioSenha, "Insira a senha");
            GuiUtil.showError(editUsuarioSenhaConfirmar, "Confirme a senha");
            GuiUtil.showError(edtPessoaDataDeNascimentoCadastro,"Selecione a data de nascimento");
            return false;
        }else if(pessoa.getNome() == null || pessoa.getNome().equals("")){
            GuiUtil.showError(editPessoaNome, "Insira o nome");
            return false;
        }else if(pessoa.getCpf() == null || pessoa.getCpf().equals("")) {
            GuiUtil.showError(editPessoaCPF, "Insira o CPF");
            return false;
        }else if(pessoa.getDataDeNascimento()==null){
            GuiUtil.showError(edtPessoaDataDeNascimentoCadastro,"Selecione a data de nascimento");
            return false;
        }else if (pessoa.getUsuario().getLogin()==null || pessoa.getUsuario().getLogin().equals("")){
            GuiUtil.showError(editUsuarioLogin, "Insira o login");
            return false;
        }else if(pessoa.getUsuario().getSenha()==null || pessoa.getUsuario().getSenha().equals("")){
            GuiUtil.showError(editUsuarioSenha, "Insira a senha");
            return false;
        }
        return true;
    }

    /**
     * Metodo que valida o campo cpf para cpf conhecidamente invalidos
     * @param cpf           cpf a ser verificado
     * @return              se true, cpf esta prenchido normalmente, se false cpf invalido detectado
     */
    public boolean validarCampoCpf(String cpf) {

        if (cpf.length() != 11) {
            GuiUtil.showError(editPessoaCPF, "O CPF deve conter apenas 11 digitos!");
            return false;

        }
        if (cpf == "00000000000" || cpf.equals("00000000000")||cpf == "11111111111" || cpf.equals("11111111111") ||
                cpf == "22222222222" || cpf.equals("22222222222") || cpf == "33333333333" || cpf.equals("33333333333") ||
                cpf == "44444444444" || cpf.equals("44444444444") || cpf == "55555555555" || cpf.equals("55555555555")
                ||cpf == "66666666666" || cpf.equals("66666666666") || cpf == "77777777777" || cpf.equals("77777777777")
                ||cpf == "88888888888" || cpf.equals("99999999999")){
            GuiUtil.showError(editPessoaCPF, "CPF invalido");
            return false;
        }

        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(cpf);
        if(!matcher.find()) {
            GuiUtil.showError(editPessoaCPF, "O CPF deve conter apenas numeros!");
            return false;
        }
        return true;
    }

    /**
     * Metodo que adiciona itens ao action bar se ele estiver presente
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//
//    }

    /**
     * metodo que acessa diretemente o datepicker e seta o dia atual, mes e ano.
     */
    private void exibeData() {
        Calendar calendar= Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this,new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    /**
     * Intercepta o momento que o Calendar precisa ter sua data atualizada
     */
    private class ExibeDataListerner implements View.OnClickListener, View.OnFocusChangeListener
    {
        @Override
        public void onClick(View v) {
            exibeData();
        }
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus)
                exibeData();
        }
    }

    /**
     * Inner Class Responsavel por setar a data a ser mostrada no Calendar
     */
    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Calendar data = Calendar.getInstance();

            data.set(year, monthOfYear, dayOfMonth);

            dataNascimento = data.getTime();

            String dt = simpleDateFormat.format(dataNascimento);

            edtPessoaDataDeNascimentoCadastro.setText(dt);
        }
    }

    /**
     * Metodo que inicia a activity resoponsavel pela captura de uma foto usando camera do telefone
     * @param view
     */
    public void tirarFoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String nomeArquivo = Environment.getExternalStorageDirectory()+"/"+System.currentTimeMillis()+".jpg";
        intent.putExtra(MediaStore.EXTRA_OUTPUT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
        startActivityForResult(intent, TIRAR_FOTO);
    }

    /**
     * Metodo que inicia o media browser para o usuario selecionar uma foto
     * @param view
     */
    public void pegarImg(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGEM_INTERNA);
    }

    /**
     * Metodo que intercepta o resultado da camera ou da carga da foto pelo media browser do telefone
     * @param requestCode       codigo passado ao iniciar a activity
     * @param resultCode
     * @param data              dados retornados pela activity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == TIRAR_FOTO){
            if(resultCode == RESULT_OK){
                String[] projection = new String[]{
                        MediaStore.Images.ImageColumns._ID,
                        MediaStore.Images.ImageColumns.DATA,
                        MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.ImageColumns.DATE_TAKEN,
                        MediaStore.Images.ImageColumns.MIME_TYPE
                };
                final Cursor cursor = getContentResolver()
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                                null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
                if (cursor.moveToFirst()){
                    Uri uri = Uri.parse(cursor.getString(1));
                    //GuiUtil.FOTO_TEMP = uri;
                    FOTO = uri;
                    imageView.setImageURI(uri);
                }
            }
        }
        if(requestCode == IMAGEM_INTERNA){
            if(resultCode == RESULT_OK){
                getContentResolver();
                Uri imagemSelecionada = data.getData();
                imageView.setImageURI(imagemSelecionada);
                FOTO = imagemSelecionada;
            }else{
                imageView.setImageURI(GuiUtil.FOTO_PADRAO);
                FOTO = GuiUtil.FOTO_PADRAO;
            }
        }
    }

    /**
     * Metodo que preenche com espacos o restante da string ao digitar o cpf
     * @param number
     * @param maxLength
     * @return
     */
    protected String padNumber(String number, int maxLength) {
        String padded = new String(number);
        for (int i = 0; i < maxLength - number.length(); i++)
            padded += " ";
        return padded;

    }

    private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

    /**
     * Inner class que intercepta a entrada de numeros
     */
    private class KeylistenerNumber extends NumberKeyListener {

        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER
                    | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9'};
        }
    }
}
