package com.example.giniappsassignment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> numbers;
    private LayoutInflater inflater;

    // Constants to represent view types
    private static final int VIEW_TYPE_ZERO_SUM = 0;
    private static final int VIEW_TYPE_NON_ZERO_SUM = 1;

    public NumberAdapter(List<Integer> numbers, Context context) {
        this.numbers = numbers;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == VIEW_TYPE_ZERO_SUM) {
            itemView = inflater.inflate(R.layout.item_number_zero_sum, parent, false);
            return new ZeroSumViewHolder(itemView);
        } else {
            itemView = inflater.inflate(R.layout.item_number_non_zero_sum, parent, false);
            return new NonZeroSumViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Integer number = numbers.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_ZERO_SUM) {
            ZeroSumViewHolder zeroSumViewHolder = (ZeroSumViewHolder) holder;
            zeroSumViewHolder.textViewNumber.setText(String.valueOf(number));
        } else {
            NonZeroSumViewHolder nonZeroSumViewHolder = (NonZeroSumViewHolder) holder;
            nonZeroSumViewHolder.textViewNumber.setText(String.valueOf(number));
        }
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public int getItemViewType(int position) {
        Integer currentNumber = numbers.get(position);

        for (int i = 0; i < numbers.size(); i++) {
            if (i != position) {
                Integer otherNumber = numbers.get(i);
                if (currentNumber + otherNumber == 0) {
                    return VIEW_TYPE_ZERO_SUM;
                }
            }
        }

        return VIEW_TYPE_NON_ZERO_SUM;
    }
    private boolean isSumZero(int position) {

        int sum = numbers.get(position);
        return sum == 0;
    }

      static class ZeroSumViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber;

        ZeroSumViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
        }
    }

    static class NonZeroSumViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber;

        NonZeroSumViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
        }
    }
}

