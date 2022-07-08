package com.example.eventapp.Services;

import com.example.eventapp.Model.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApi {
    @POST("/api/registers/")
    Call<Register> save(@Body Register register);

}
