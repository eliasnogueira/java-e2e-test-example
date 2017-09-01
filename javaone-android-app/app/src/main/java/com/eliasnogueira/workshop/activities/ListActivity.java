package com.eliasnogueira.workshop.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.eliasnogueira.workshop.R;
import com.eliasnogueira.workshop.api.ApiEndpointsInterface;
import com.eliasnogueira.workshop.bean.Person;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public final static String ID = "com.eliasnogueira.workshop.ID";
    public final static String NAME = "com.eliasnogueira.workshop.NAME";
    public final static String ADDRESS = "com.eliasnogueira.workshop.ADDRESS";
    public final static String HOBBIES = "com.eliasnogueira.workshop.HOBBIES";

    private FloatingActionButton fab;
    private ListView listView;

    public Retrofit retrofit;
    private ApiEndpointsInterface apiService;
    private List<Person> listPerson;
    private ArrayAdapter<Person> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreCriacao(view);
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndpointsInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiEndpointsInterface.class);

        obtemLista();

        swipeRefreshLayout.setOnRefreshListener(this);
        /*
        Call<List<Person>> call = apiService.getPerson();

        call.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (!response.isSuccessful()) {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                try {
                    listPerson = response.body();

                    for (Person person : listPerson) {
                        System.out.println(person.toString());
                    }

                    adapter = new ArrayAdapter<Person>(ListActivity.this, android.R.layout.simple_expandable_list_item_1, listPerson);
                    listView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.err.println(t.getMessage());
            }
        });
        */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = listView.getItemAtPosition(i);
                String id = ((Person)obj).getId();
                String name = ((Person)obj).getName();

                abreEdicao(view, obj);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = listView.getItemAtPosition(i);
                Person pessoa = ((Person)obj);

                remove(view, pessoa);
                return true;
            }
        });
    }

    private void obtemLista() {
        Call<List<Person>> call = apiService.getPerson();
        call.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (!response.isSuccessful()) {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                try {
                    listPerson = response.body();

                    for (Person person : listPerson) {
                        System.out.println(person.toString());
                    }

                    adapter = new ArrayAdapter<Person>(ListActivity.this, android.R.layout.simple_expandable_list_item_1, listPerson);
                    listView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.err.println(t.getMessage());
            }
        });
    }

    public void abreEdicao(View view, Object obj) {
        Intent intent = new Intent(this, PersonActivity.class);

        String id = ((Person)obj).getId();
        String name = ((Person)obj).getName();
        String address = ((Person)obj).getAddress();
        String hobbies = ((Person)obj).getHobbies();

        intent.putExtra(ID, id);
        intent.putExtra(NAME, name);
        intent.putExtra(ADDRESS, address);
        intent.putExtra(HOBBIES, hobbies);

        startActivity(intent);
    }

    public void abreCriacao(View view) {
        Intent intent = new Intent(this, PersonActivity.class);

        String id = null;

        intent.putExtra(ID, id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                listView.invalidate();
                return true;
            }
        });

        return true;
    }

    public void remove(View view, final Person pessoa) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.attention);
        alertDialogBuilder.setMessage(R.string.remove_message);

        alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Call<Void> call = apiService.deletePerson(pessoa.getId());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        adapter.remove(pessoa);
                        adapter.notifyDataSetChanged();

                        showSucesso(true);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showSucesso(false);
                    }
                });
            }
        });

        alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    public void refresh(View view) {
        swipeRefreshLayout.setRefreshing(true);

        obtemLista();

        swipeRefreshLayout.setRefreshing(false);
    }

    private void showSucesso(boolean sucesso) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        if (sucesso) {
            alert.setTitle(R.string.success);
            alert.setMessage(R.string.remove_success);
        } else {
            alert.setTitle(R.string.error);
            alert.setMessage(R.string.remove_error);
        }

        alert.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alert.create();
        alert.show();
    }

    @Override
    public void onRefresh() {
        System.out.println("Entrou onRefresh");

        swipeRefreshLayout.setRefreshing(true);
        obtemLista();
        swipeRefreshLayout.setRefreshing(false);
        System.out.println("Saiu onRefresh");
    }
}
