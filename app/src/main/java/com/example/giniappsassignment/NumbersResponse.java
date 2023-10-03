package com.example.giniappsassignment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NumbersResponse {
    @SerializedName("numbers")
    private ArrayList<Numbers> numbers;

    public ArrayList<Numbers> getNumbers() {
        return numbers;
    }
}
