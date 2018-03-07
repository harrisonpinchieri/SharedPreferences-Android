package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textoNome;
    private Button   botaoSalvar;
    private TextView textoExibicao;

//    ao utilizar o final, o valor ArquivoPreferencia  não pode  ser mudado. Cria-se uma constante que o valor não pode ser alterado.
    private static final String ARQUIVO_PREFERENCIA =  "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        textoNome = (EditText) findViewById(R.id.textoNomeId);
        textoExibicao = (TextView) findViewById(R.id.textoExibicaoId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

//              permite editar o arquivo SharedPreferences.edit() e está associando ao editor e ao utilizar o objeto editor, podemos editar o arquivo
                SharedPreferences.Editor editor = sharedPreferences.edit();


//              vai verificar se o textNome está vazio
                if(textoNome.getText().toString().equals("")){

                    Toast.makeText(MainActivity.this,"Por favor, preencher o nome",Toast.LENGTH_SHORT).show();
                }else{

                    editor.putString("nome", textoNome.getText().toString());

//              ao utilizar esse método, é confimado que a informação pode ser salva no arquivo
                    editor.commit();

                    textoExibicao.setText("Olá! " + textoNome.getText().toString());

                }



            }
        });

        //Recuperar os dados salvos. Foi criado

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
//        verifica se no sharedepreferences contém a chave "nome"
        if(sharedPreferences.contains("nome")){

//            o getString recebe 2 parametros o primeiro é string que no caso é o "nome" e a segunda, é um valor default caso não seja encontrado o "nome"
            String nomeUsuario = sharedPreferences.getString("nome","usuário não definido");

            textoExibicao.setText("Olá! "+nomeUsuario);

        }else{

           textoExibicao.setText("olá,usuário não definido.");

        }





    }
}
