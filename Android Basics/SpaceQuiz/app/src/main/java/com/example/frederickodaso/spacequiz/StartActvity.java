package com.example.frederickodaso.spacequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActvity extends AppCompatActivity {
    public final static String EXTRA_NAME = "com.example.frederickodaso.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startTheQuizButton = (Button) findViewById(R.id.start_the_quiz_button);
        startTheQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(v.getContext(), MainActivity.class);
                EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
                String name = nameEditText.getText().toString();
                quizIntent.putExtra(EXTRA_NAME, name);
                startActivity(quizIntent);

            }
        });
    }
}
