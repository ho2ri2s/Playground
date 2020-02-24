package com.example.uianimation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {
    private TextView textView;
    private String DATA = "data";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        textView = findViewById(R.id.textview);

        Intent intent = getIntent();
        String data = intent.getStringExtra(DATA);
        textView.setText(data);
    }
}
