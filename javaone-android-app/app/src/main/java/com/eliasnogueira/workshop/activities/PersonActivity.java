package com.eliasnogueira.workshop.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eliasnogueira.workshop.R;
import com.eliasnogueira.workshop.api.ApiEndpointsInterface;
import com.eliasnogueira.workshop.bean.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonActivity extends AppCompatActivity {

    public Retrofit retrofit;
    ApiEndpointsInterface apiService;

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtHobbies;

    private Person personIntent;

    private enum Tipo {
        ADICIONAR, ALTERAR;
    }

    private Tipo tipoAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        txtNome = (EditText) findViewById(R.id.txt_nome);
        txtEndereco = (EditText) findViewById(R.id.txt_endereco);
        txtHobbies = (EditText) findViewById(R.id.txt_hobbies);

        Intent intent = getIntent();
        String id = intent.getStringExtra(ListActivity.ID);
        String nome = intent.getStringExtra(ListActivity.NAME);
        String endereco = intent.getStringExtra(ListActivity.ADDRESS);
        String hobbies = intent.getStringExtra(ListActivity.HOBBIES);

        personIntent = new Person(id, nome, endereco, hobbies);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndpointsInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiEndpointsInterface.class);

        if (id == null) {
            tipoAcao = Tipo.ADICIONAR;
            System.out.println("----- ADICIONAR -----");

        } else {
            tipoAcao = Tipo.ALTERAR;
            System.out.println("----- ALTERAR -----");
            txtNome.setText(nome);
            txtEndereco.setText(endereco);
            txtHobbies.setText(hobbies);
        }
    }

    public void acaoBotao(final View view) {

        String nome = txtNome.getText().toString();
        String endereco = txtEndereco.getText().toString();
        String hobbies = txtHobbies.getText().toString();

        if (nome.isEmpty() || endereco.isEmpty() || hobbies.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Preencha todos os campos!").setTitle("Atenção").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            switch (tipoAcao) {

                case ADICIONAR:
                    Person person = new Person(nome, endereco, hobbies);

                    Call<Person> call = apiService.postPerson(person);
                    call.enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            if (response.isSuccessful()) {
                                voltaParaLista(view);
                            }
                        }

                        @Override
                        public void onFailure(Call<Person> call, Throwable t) {
                            System.err.println(t.getMessage());
                        }
                    });

                    break;
                case ALTERAR:
                    personIntent.setName(txtNome.getText().toString());
                    personIntent.setAddress(txtEndereco.getText().toString());
                    personIntent.setHobbies(txtHobbies.getText().toString());

                    System.out.println(txtNome.getText().toString());
                    Call<Person> callAlterar = apiService.putPerson(personIntent.getId(), personIntent);
                    callAlterar.enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            if (response.isSuccessful()) {
                                voltaParaLista(view);
                            }
                        }

                        @Override
                        public void onFailure(Call<Person> call, Throwable t) {
                            System.err.println(t.getMessage());
                        }
                    });

                    break;
            }
        }
    }


    public void voltaParaLista(View view) {
        Intent intent1 = new Intent(this, ListActivity.class);
        startActivity(intent1);
    }
}
