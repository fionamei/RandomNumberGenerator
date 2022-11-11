package com.example.randomnumbergenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorActivity extends AppCompatActivity {

    public static final String MAX_KEY = "MAX_VAL";
    public static final int DEFAULT_VAL = 10;

    private int maxVal;
    private RecyclerView numbersRv;
    private Button newNumBtn;
    private Button newValBtn;
    private GeneratorAdapter adapter;
    private List<Integer> numbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        // gets the intent
        Intent i = getIntent();
        maxVal = i.getIntExtra(MAX_KEY, DEFAULT_VAL);

        findViews();
        setUpRv();
        setListeners();
    }

    private void findViews() {
        numbersRv = findViewById(R.id.numbersRv);
        newNumBtn = findViewById(R.id.newNumBtn);
        newValBtn = findViewById(R.id.newValBtn);
    }

    /**
     * setUpRv() - sets up the recycler view with all the components it needs:
     * the list, adapter and layout manager
     */
    private void setUpRv() {
        numbers = new ArrayList<>();
        adapter = new GeneratorAdapter(numbers, this);
        numbersRv.setAdapter(adapter);
        numbersRv.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * generateRandomNum() - function that generates a random number with our inputted maxVal
     * @return int representing random number
     */
    private int generateRandomNum() {
        Random rand = new Random();
        return rand.nextInt(maxVal);
    }

    private void setListeners(){
        setNewValListener();
        setNewNumListener();
    }

    /**
     * setNewValListener() - when the user clicks on this button, it will redirect them to
     * the home page to set a new upper bound value
     */
    private void setNewValListener() {
        newValBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GeneratorActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    /**
     * setNewNumListener() - when this button is clicked, it will generate a new random number,
     * add it to the list, and update the adapter, so that the adapter will update the view
     */
    private void setNewNumListener() {
        newNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = generateRandomNum();
                adapter.addItem(num);
                // scrolls to the position of the added number
                numbersRv.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }


}