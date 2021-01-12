package com.example.clublaribera_app.request;

import android.util.Log;

import com.example.clublaribera_app.modelos.Login;
import com.example.clublaribera_app.modelos.Msj;
import com.example.clublaribera_app.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import static com.example.clublaribera_app.MainActivity.PATH;

public class ApiClient {
    private static  MyApiInterface myApiInteface;

    public static MyApiInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH + "/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public interface MyApiInterface {

        @POST("usuarios/registrar")
        Call<Msj> registrarUsuario(@Body Usuario user);

        @POST("usuarios/login")
        Call<String> login(@Body Login login);
    }
}
