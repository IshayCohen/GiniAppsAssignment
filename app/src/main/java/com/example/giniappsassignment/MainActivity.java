package com.example.giniappsassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NumbersViewModel viewModel;
    private NumberAdapter numberAdapter;
    private ArrayList<Integer> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(NumbersViewModel.class);
        numberAdapter = new NumberAdapter(numbers, this);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(numberAdapter);

        viewModel.getNumberList().observe(this, new Observer<List<Integer>>() {

            @Override
            public void onChanged(List<Integer> newNumbers) {
                numbers.clear();
                numbers.addAll(newNumbers);
                numberAdapter.notifyDataSetChanged();
            }
        });
    }
}

