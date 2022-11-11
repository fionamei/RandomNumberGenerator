package com.example.randomnumbergenerator;

import static com.example.randomnumbergenerator.GeneratorActivity.MAX_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText upperEt;
    Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListener(this);
    }

    /**
     * findViews - assigns the variables we created to actual elements that exist in our .xml file
     */
    private void findViews() {
        upperEt = findViewById(R.id.upperEt);
        continueBtn = findViewById(R.id.continueBtn);
    }

    /**
     * setListener(activity) - sets the listener for when the button is clicked. If the user has inputted
     * something, then we redirect them to the next page, and pass in their upper bound value. Otherwise,
     * we display a short popup that tells the user to enter a valid number.
     */
    private void setListener(Activity activity) {
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upperEt.getText().length() == 0) {
                    // displays a short message
                    Toast.makeText(activity, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, GeneratorActivity.class);
                    i.putExtra(MAX_KEY, Integer.parseInt(upperEt.getText().toString()));
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}