package com.example.quiz_app;

// Importações de classes do Android necessárias para a Activity funcionar.
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

// Declaração da classe ResultActivity, que representa a tela de resultado.
public class ResultActivity extends AppCompatActivity {

    // --- Método Principal (executado quando esta tela é criada) ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Associa esta classe ao seu arquivo de layout visual (activity_result.xml).
        setContentView(R.layout.activity_result);

        // --- Vinculação dos Componentes Visuais ---
        // Associa as variáveis Java com os componentes TextView do arquivo XML.
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvClassification = findViewById(R.id.tvClassification);

        // --- Recuperação dos Dados Enviados ---
        // Pega os dados que a MainActivity enviou através da Intent.
        // O método getIntExtra() busca um valor inteiro.
        // "SCORE" é a "chave" para encontrar o valor.
        // 0 é um valor padrão caso a chave "SCORE" não seja encontrada.
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        // --- Exibição da Pontuação ---
        // Monta a string de texto com a pontuação e a exibe no TextView 'tvScore'.
        // Exemplo: "Você acertou 7 de 10!"
        tvScore.setText("Você acertou " + score + " de " + totalQuestions + "!");

        // --- Lógica de Classificação ---
        // Declara uma variável de texto para guardar a classificação.
        String classification;
        // Estrutura condicional para definir o nível do jogador com base na pontuação.
        if (score <= 4) {
            // Se a pontuação for de 0 a 4...
            classification = "Programador Júnior";
        } else if (score <= 8) {
            // Se a pontuação for de 5 a 8...
            classification = "Programador Pleno";
        } else {
            // Se for maior que 8 (9 ou 10)...
            classification = "Programador Sênior";
        }

        // --- Exibição da Classificação ---
        // Monta a string de texto com a classificação e a exibe no TextView 'tvClassification'.
        // Exemplo: "Seu nível é: Programador Pleno"
        tvClassification.setText("Seu nível é: " + classification);
    }
}