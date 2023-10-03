package com.example.giniappsassignment;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("raw/8wJzytQX")
    Call<NumbersResponse> getNumbers();
}
