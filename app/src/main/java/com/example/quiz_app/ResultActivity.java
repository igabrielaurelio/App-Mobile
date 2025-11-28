package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private QuizDatabaseHelper dbHelper;
    private ListView lvRanking;
    private ArrayAdapter<Score> adapter;
    private List<Score> rankingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Inicializa o banco de dados
        dbHelper = new QuizDatabaseHelper(this);

        // --- Vinculação dos Componentes ---
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvClassification = findViewById(R.id.tvClassification);
        lvRanking = findViewById(R.id.lvRanking);
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

        // --- BANCO DE DADOS: Salvar Resultado Atual ---
        Score newScore = new Score(userName, score);
        dbHelper.addScore(newScore);

        // --- Carregar e Exibir Ranking ---
        updateRankingList();

        // --- EVENTO DE CLIQUE: ABRE O MENU DE OPÇÕES (EDITAR ou EXCLUIR) ---
        lvRanking.setOnItemClickListener((parent, view, position, id) -> {
            Score selectedScore = rankingList.get(position);
            showOptionsDialog(selectedScore);
        });

        // --- Botão: Reiniciar ---
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, StartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // --- Botão: Resetar (Apagar Tudo) ---
        btnReset.setOnClickListener(v -> {
            // Pergunta antes de apagar tudo
            new AlertDialog.Builder(this)
                    .setTitle("Confirmar Reset")
                    .setMessage("Tem certeza que deseja apagar TODO o histórico?")
                    .setPositiveButton("Apagar Tudo", (dialog, which) -> {
                        dbHelper.deleteAllScores();
                        updateRankingList();
                        Toast.makeText(ResultActivity.this, "System: Memory cleared.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    // --- Atualiza a Lista na Tela ---
    private void updateRankingList() {
        rankingList = dbHelper.getAllScores();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rankingList);
        lvRanking.setAdapter(adapter);
    }

    // --- 1. Menu de Escolha (Editar ou Excluir) ---
    private void showOptionsDialog(Score score) {
        String[] options = {"Editar Nome", "Excluir este Registro"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opções: " + score.getName());
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                // Usuário clicou em Editar
                showEditDialog(score);
            } else if (which == 1) {
                // Usuário clicou em Excluir
                showDeleteDialog(score);
            }
        });
        builder.show();
    }

    // --- 2. Janela de Edição (UPDATE) ---
    private void showEditDialog(Score score) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Nome");

        final EditText input = new EditText(this);
        input.setText(score.getName());
        builder.setView(input);

        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String newName = input.getText().toString();
            if (!newName.isEmpty()) {
                score.setName(newName);
                dbHelper.updateScore(score); // Atualiza no banco
                updateRankingList(); // Atualiza na tela
                Toast.makeText(this, "Nome atualizado!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    // --- 3. Janela de Exclusão Individual (DELETE ONE) ---
    private void showDeleteDialog(Score score) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Registro")
                .setMessage("Deseja remover a pontuação de " + score.getName() + "?")
                .setPositiveButton("Sim, Excluir", (dialog, which) -> {
                    dbHelper.deleteScore(score.getId()); // Deleta apenas este ID
                    updateRankingList(); // Atualiza lista
                    Toast.makeText(this, "Registro excluído.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Não", null)
                .show();
    }
}