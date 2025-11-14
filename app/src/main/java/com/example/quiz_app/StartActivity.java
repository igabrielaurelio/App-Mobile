package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start); // <-- Isso aponta para o layout do Passo 3

        etName = findViewById(R.id.etName);
        btnStartQuiz = findViewById(R.id.btnStartQuiz);

        btnStartQuiz.setOnClickListener(v -> {
            String userName = etName.getText().toString().trim();
            if (userName.isEmpty()) {
                Toast.makeText(StartActivity.this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
                finish();
            }
        });
    }
}