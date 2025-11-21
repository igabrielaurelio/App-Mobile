package com.example.quiz_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResultActivity extends AppCompatActivity {

    // Nome do arquivo onde salvamos o ranking
    private static final String PREFS_NAME = "QuizRanking";
    private static final String RANKING_KEY = "ranking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // --- Vinculação dos Componentes do Layout Hacker ---
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvClassification = findViewById(R.id.tvClassification);
        TextView tvRanking = findViewById(R.id.tvRanking);
        Button btnRestart = findViewById(R.id.btnRestart);
        Button btnReset = findViewById(R.id.btnReset); // O botão "rm -rf"

        // --- Recuperação dos Dados ---
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        // --- Exibição Estilo Terminal ---
        tvScore.setText("Build Result: " + score + "/" + totalQuestions + " passed");

        // --- Lógica de Classificação (Níveis de Programador) ---
        String classification;
        if (score <= 4) {
            classification = "Status: Junior Dev (Keep Coding!)";
        } else if (score <= 8) {
            classification = "Status: Full-Stack Dev";
        } else {
            classification = "Status: Senior Architect (Root Access)";
        }
        tvClassification.setText(classification);

        // --- Salva e Carrega o Ranking ---
        saveAndLoadRanking(userName, score, tvRanking);

        // --- Botão: Reiniciar (./restart.sh) ---
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, StartActivity.class);
            // Limpa o histórico para o app não voltar para o resultado ao apertar "Voltar"
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // --- Botão: Resetar (rm -rf ranking) ---
        btnReset.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // Apaga tudo
            editor.apply();

            // Atualiza o visual para parecer que o banco de dados foi limpo
            tvRanking.setText("> Database purged.\n> No records found.");

            // Feedback visual para o usuário
            Toast.makeText(ResultActivity.this, "System: Memory cleared successfully.", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveAndLoadRanking(String userName, int score, TextView tvRanking) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Recupera a lista atual ou cria uma nova se estiver vazia
        Set<String> rankingSet = prefs.getStringSet(RANKING_KEY, new HashSet<>());

        // Validação simples de nome
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Guest_User";
        }

        // Formato: "08:Nome" para facilitar a ordenação
        String formattedScore = String.format("%02d", score);
        rankingSet.add(formattedScore + ":" + userName);

        // Salva
        editor.putStringSet(RANKING_KEY, rankingSet);
        editor.apply();

        // --- Ordenação e Exibição ---
        List<String> sortedRanking = new ArrayList<>(rankingSet);
        // Ordena do maior para o menor
        Collections.sort(sortedRanking, Collections.reverseOrder());

        StringBuilder rankingText = new StringBuilder("> LEADERBOARD_LOG:\n\n");
        int maxEntriesToShow = 5;

        for (int i = 0; i < sortedRanking.size() && i < maxEntriesToShow; i++) {
            String entry = sortedRanking.get(i);
            String[] parts = entry.split(":", 2);
            if (parts.length == 2) {
                // Formato Hacker: [1] Nome ... 10 pts
                rankingText.append(String.format("[%d] %s ... %d pts\n",
                        (i + 1), parts[1], Integer.parseInt(parts[0])));
            }
        }

        tvRanking.setText(rankingText.toString());
    }
}