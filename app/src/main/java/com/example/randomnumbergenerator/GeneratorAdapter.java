package com.example.randomnumbergenerator;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GeneratorAdapter extends RecyclerView.Adapter<GeneratorAdapter.ViewHolder> {

    private List<Integer> numbers;
    private Activity activity;

    public GeneratorAdapter(List<Integer> numbers, Activity activity) {
        this.numbers = numbers;
        this.activity = activity;
    }

    /**
     * onCreateViewHolder: when the ViewHolder is created, we set the view of each item to be
     * the layout number_item.xml, and wrap it around the ViewHolder
     */
    @NonNull
    @Override
    public GeneratorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.number_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder: binds the number at given position to the ViewHolder
     * @param holder: a ViewHolder that you have created
     * @param position: the position of the value in the data
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int randomNum = numbers.get(position);
        holder.bind(randomNum);
    }

    /**
     * IMPORTANT function that the adapter won't work without, it returns the size of data
     */
    @Override
    public int getItemCount() {
        return numbers.size();
    }

    /**
     * addItem: adds a number to the dataset, and tells adapter you have changed it
     * @param randomNum: integer representing the random number generated
     */
    public void addItem(int randomNum) {
        numbers.add(randomNum);
        notifyItemChanged(getItemCount() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numberTv = itemView.findViewById(R.id.numberTv);
        }

        /**
         * When the adapter binds the data to the view, we want to set the text inside the layout
         * to the number that is generated
         */
        public void bind(int randomNum) {
            numberTv.setText(Integer.toString(randomNum));
        }
    }
}
