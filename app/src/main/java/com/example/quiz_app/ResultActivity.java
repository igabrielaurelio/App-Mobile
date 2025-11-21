package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    // Instância do Banco de Dados
    private QuizDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Inicializa o banco de dados
        dbHelper = new QuizDatabaseHelper(this);

        // --- Vinculação dos Componentes ---
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvClassification = findViewById(R.id.tvClassification);
        TextView tvRanking = findViewById(R.id.tvRanking);
        Button btnRestart = findViewById(R.id.btnRestart);
        Button btnReset = findViewById(R.id.btnReset);

        // --- Recuperação dos Dados ---
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        // Validação de nome
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Guest_User";
        }

        // --- Exibição Estilo Terminal ---
        tvScore.setText("Build Result: " + score + "/" + totalQuestions + " passed");

        // --- Lógica de Classificação ---
        String classification;
        if (score <= 4) {
            classification = "Status: Junior Dev (Keep Coding!)";
        } else if (score <= 8) {
            classification = "Status: Full-Stack Dev";
        } else {
            classification = "Status: Senior Architect (Root Access)";
        }
        tvClassification.setText(classification);

        // --- BANCO DE DADOS: Salvar e Carregar ---
        saveAndLoadRanking(userName, score, tvRanking);

        // --- Botão: Reiniciar ---
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, StartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // --- Botão: Resetar (Apagar Banco) ---
        btnReset.setOnClickListener(v -> {
            // Chama o método DELETE do banco
            dbHelper.deleteAllScores();

            // Atualiza o visual
            tvRanking.setText("> Database purged.\n> No records found.");
            Toast.makeText(ResultActivity.this, "System: Memory cleared successfully.", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveAndLoadRanking(String userName, int score, TextView tvRanking) {
        // 1. CREATE: Salva o resultado atual no banco
        Score newScore = new Score(userName, score);
        dbHelper.addScore(newScore);

        // 2. READ: Recupera a lista completa do banco (já ordenada)
        List<Score> rankingList = dbHelper.getAllScores();

        // 3. Exibe na tela
        StringBuilder rankingText = new StringBuilder("> LEADERBOARD_LOG:\n\n");
        int maxEntriesToShow = 5;

        for (int i = 0; i < rankingList.size() && i < maxEntriesToShow; i++) {
            Score s = rankingList.get(i);
            // Formato: [1] Nome ... 10 pts
            rankingText.append(String.format("[%d] %s ... %d pts\n",
                    (i + 1), s.getName(), s.getPoints()));
        }

        tvRanking.setText(rankingText.toString());
    }
}