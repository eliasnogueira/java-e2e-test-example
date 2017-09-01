package com.eliasnogueira.workshop.api;

import com.eliasnogueira.workshop.bean.Person;

import java.util.List;

import javax.security.auth.callback.Callback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiEndpointsInterface {

    // production
    //public static String URL = "http://eliasnogueira.com/adaptworks/att/workshop/api/";

    // local
    public static String URL = "http://10.0.3.2:4567/";

    @GET("api/v1/person/{id}")
    Call<Person> getPerson(@Path("id") String id);

    @GET("api/v1/person")
    Call<List<Person>> getPerson();

    @POST("api/v1/person")
    Call<Person> postPerson(@Body Person personpoww);

    @PUT("api/v1/person/{id}")
    Call<Person> putPerson(@Path("id") String id, @Body Person person);

    @DELETE("api/v1/person/{id}")
    Call<Void> deletePerson(@Path("id") String id);
}
