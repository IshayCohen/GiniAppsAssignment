package com.example.giniappsassignment;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumbersViewModel extends ViewModel {
    private Api apiService = RetrofitClient.getInstance().getApiService();
    private MutableLiveData<ArrayList<Integer>> numberList = new MutableLiveData<>();

    public NumbersViewModel() {
        fetchNumbers();
    }

    public LiveData<ArrayList<Integer>> getNumberList() {
        return numberList;
    }

    private void fetchNumbers() {
        Call<NumbersResponse> call = RetrofitClient.getInstance().getApiService().getNumbers();
        call.enqueue(new Callback<NumbersResponse>() {
            @Override
            public void onResponse(Call<NumbersResponse> call, Response<NumbersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Numbers> numbersList = response.body().getNumbers();
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (Numbers numObj : numbersList) {
                        numbers.add(numObj.getNumber());
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        numbers.sort(null);
                    }
                    numberList.postValue(numbers);
                } else {
                    numberList.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<NumbersResponse> call, Throwable t) {
                numberList.postValue(new ArrayList<>());
            }
        });
    }

   }
