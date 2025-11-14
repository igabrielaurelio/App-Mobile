package com.example.quiz_app;

// --- NOVAS IMPORTAÇÕES ---
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
// --- FIM DAS NOVAS IMPORTAÇÕES ---

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

// --- NOVAS IMPORTAÇÕES (para o ranking) ---
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// --- FIM DAS NOVAS IMPORTAÇÕES ---

public class ResultActivity extends AppCompatActivity {

    // Nome do arquivo de SharedPreferences
    private static final String PREFS_NAME = "QuizRanking";
    private static final String RANKING_KEY = "ranking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // --- Vinculação (TextViews existentes) ---
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvClassification = findViewById(R.id.tvClassification);

        // --- NOVOS COMPONENTES ---
        TextView tvRanking = findViewById(R.id.tvRanking);
        Button btnRestart = findViewById(R.id.btnRestart);

        // --- Recuperação dos Dados (incluindo o NOME) ---
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME"); // Novo

        // --- Exibição da Pontuação (igual) ---
        tvScore.setText(userName + ", você acertou " + score + " de " + totalQuestions + "!");

        // --- Lógica de Classificação (igual) ---
        String classification;
        if (score <= 4) {
            classification = "Programador Júnior";
        } else if (score <= 8) {
            classification = "Programador Pleno";
        } else {
            classification = "Programador Sênior";
        }
        tvClassification.setText("Seu nível é: " + classification);

        // --- NOVO: LÓGICA DE RANKING ---
        saveAndLoadRanking(userName, score, tvRanking);

        // --- NOVO: AÇÃO DO BOTÃO RESTART ---
        btnRestart.setOnClickListener(v -> {
            // Cria uma intenção para a StartActivity (a tela de nome)
            Intent intent = new Intent(ResultActivity.this, StartActivity.class);
            // Flags para limpar o histórico de telas e começar de novo
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Fecha a tela de resultados
        });
    }

    /**
     * Salva o novo resultado e carrega o ranking atualizado no TextView.
     */
    private void saveAndLoadRanking(String userName, int score, TextView tvRanking) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Pega o ranking antigo. Usamos um Set<String>
        // Formato: "PONTOS:NOME" (ex: "10:Ana", "08:Bruno")
        // Usamos "08" (dois dígitos) para facilitar a ordenação alfabética
        Set<String> rankingSet = prefs.getStringSet(RANKING_KEY, new HashSet<>());

        // Adiciona o novo resultado
        String formattedScore = String.format("%02d", score); // Formata 5 para "05", 10 para "10"
        rankingSet.add(formattedScore + ":" + userName);

        // Salva o novo Set
        editor.putStringSet(RANKING_KEY, rankingSet);
        editor.apply();

        // --- Carrega e exibe o ranking ---

        // Converte o Set para List para poder ordenar
        List<String> sortedRanking = new ArrayList<>(rankingSet);
        // Ordena em ordem DECRESCENTE (do maior para o menor)
        Collections.sort(sortedRanking, Collections.reverseOrder());

        // Constrói o texto do ranking
        StringBuilder rankingText = new StringBuilder("--- RANKING ---\n\n");

        // Limita para mostrar, por exemplo, o Top 5
        int maxEntriesToShow = 5;
        for (int i = 0; i < sortedRanking.size() && i < maxEntriesToShow; i++) {
            String entry = sortedRanking.get(i);
            String[] parts = entry.split(":", 2); // Divide "10:Nome" em "10" e "Nome"

            // Adiciona na lista: "1. Nome - 10 pontos"
            rankingText.append((i + 1) + ". " + parts[1] + " - " + Integer.parseInt(parts[0]) + " pontos\n");
        }

        // Define o texto no TextView
        tvRanking.setText(rankingText.toString());
    }
}