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

    private EditText txtName;
    private EditText txtAddress;
    private EditText txtHobbies;

    private Person personIntent;

    private enum Type {
        ADD, ALTER;
    }

    private Type actionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        txtName = (EditText) findViewById(R.id.txt_nome);
        txtAddress = (EditText) findViewById(R.id.txt_endereco);
        txtHobbies = (EditText) findViewById(R.id.txt_hobbies);

        Intent intent = getIntent();
        String id = intent.getStringExtra(ListActivity.ID);
        String name = intent.getStringExtra(ListActivity.NAME);
        String address = intent.getStringExtra(ListActivity.ADDRESS);
        String hobbies = intent.getStringExtra(ListActivity.HOBBIES);

        personIntent = new Person(id, name, address, hobbies);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndpointsInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiEndpointsInterface.class);

        if (id == null) {
            actionType = Type.ADD;

        } else {
            actionType = Type.ALTER;
            txtName.setText(name);
            txtAddress.setText(address);
            txtHobbies.setText(hobbies);
        }
    }

    public void action(final View view) {

        String nome = txtName.getText().toString();
        String endereco = txtAddress.getText().toString();
        String hobbies = txtHobbies.getText().toString();

        if (nome.isEmpty() || endereco.isEmpty() || hobbies.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PersonActivity.this);
            builder.setMessage(R.string.fill_required).setTitle(R.string.attention).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            switch (actionType) {

                case ADD:
                    Person person = new Person(nome, endereco, hobbies);

                    Call<Person> call = apiService.postPerson(person);
                    call.enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            if (response.isSuccessful()) {
                                backToList(view);
                            }
                        }

                        @Override
                        public void onFailure(Call<Person> call, Throwable t) {
                            System.err.println(t.getMessage());
                        }
                    });

                    break;
                case ALTER:
                    personIntent.setName(txtName.getText().toString());
                    personIntent.setAddress(txtAddress.getText().toString());
                    personIntent.setHobbies(txtHobbies.getText().toString());

                    Call<Person> callChange = apiService.putPerson(personIntent.getId(), personIntent);
                    callChange.enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            if (response.isSuccessful()) {
                                backToList(view);
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


    public void backToList(View view) {
        Intent intent1 = new Intent(this, ListActivity.class);
        startActivity(intent1);
    }
}
