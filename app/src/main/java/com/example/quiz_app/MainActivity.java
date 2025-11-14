package com.example.quiz_app;

// Importações de classes necessárias do Android para o aplicativo funcionar.
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // --- Declaração das Variáveis da Classe ---

    // Variáveis para os componentes da interface gráfica (elementos da tela).
    private TextView tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4;

    // Lista que vai armazenar todas as perguntas do quiz.
    private List<Question> questionList;
    // Variável para controlar qual pergunta está sendo exibida no momento. Começa em 0 (a primeira).
    private int currentQuestionIndex = 0;
    // Variável para guardar a pontuação do jogador. Começa em 0.
    private int score = 0;

    // --- NOVA VARIÁVEL ---
    // Variável para guardar o nome do usuário vindo da StartActivity
    private String userName;

    // --- Método Principal (executado quando a tela é criada) ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define qual arquivo de layout (XML) essa Activity vai usar.
        setContentView(R.layout.activity_main);

        // --- NOVO CÓDIGO ---
        // Pega o nome do usuário que foi enviado pela StartActivity
        userName = getIntent().getStringExtra("USER_NAME");

        // Associa as variáveis declaradas acima com os componentes visuais do arquivo XML.
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        // Chama os métodos para inicializar o quiz.
        loadQuestions(); // Carrega as perguntas na memória.
        showQuestion();  // Exibe a primeira pergunta na tela.

        // Configura o que acontece quando cada botão de opção é clicado.
        // A seta "->" indica uma função lambda, uma forma curta de escrever um listener.
        btnOption1.setOnClickListener(v -> checkAnswer(0)); // Se clicar no botão 1, checa a resposta de índice 0.
        btnOption2.setOnClickListener(v -> checkAnswer(1)); // Se clicar no botão 2, checa a resposta de índice 1.
        btnOption3.setOnClickListener(v -> checkAnswer(2)); // E assim por diante...
        btnOption4.setOnClickListener(v -> checkAnswer(3));
    }

    // --- Método para Carregar as Perguntas ---
    private void loadQuestions() {
        questionList = Arrays.asList(
                new Question(
                        "Qual a principal função da linguagem HTML?",
                        Arrays.asList("Estilizar páginas", "Estruturar o conteúdo de uma página web", "Criar a lógica do servidor", "Gerenciar bancos de dados"),
                        1
                ),
                new Question(
                        "O que é uma 'variável' em programação?",
                        Arrays.asList("Um erro no código", "Um comando de repetição", "Um espaço na memória para guardar um valor", "O nome do programa"),
                        2
                ),
                new Question(
                        "Qual comando é usado para repetir um bloco de código várias vezes?",
                        Arrays.asList("if / else", "Uma variável", "Um laço (loop), como 'for'", "Um comentário //"),
                        2
                ),
                new Question(
                        "Para que serve a tecnologia CSS?",
                        Arrays.asList("Para estilizar e dar design a páginas web", "Para guardar dados", "Para criar a estrutura da página", "Para definir a lógica de um app"),
                        0
                ),
                new Question(
                        "Como é chamado um erro em um programa de computador?",
                        Arrays.asList("Feature", "Bug", "Comentário", "Função"),
                        1
                ),
                new Question(
                        "Qual estrutura é usada para tomar decisões no código, como 'se isso for verdade, faça aquilo'?",
                        Arrays.asList("loop 'for'", "função ()", "variável", "condicional 'if'"),
                        3
                ),
                new Question(
                        "Que tipo de dado é usado para armazenar texto, como um nome de uma pessoa?",
                        Arrays.asList("Integer (inteiro)", "Boolean (booleano)", "String (texto)", "Float (flutuante)"),
                        2
                ),
                new Question(
                        "Qual o propósito de um comentário no código, iniciado com '//'?",
                        Arrays.asList("Ser executado pelo computador", "Aumentar a velocidade do programa", "Explicar o código para outros humanos", "Definir o título do programa"),
                        2
                ),
                new Question(
                        "Em programação, o que é um algoritmo?",
                        Arrays.asList("O nome de uma linguagem", "Um componente de computador", "Uma sequência de passos para resolver um problema", "Um erro de digitação"),
                        2
                ),
                new Question(
                        "Qual o resultado da operação '2 + 2' em quase todas as linguagens de programação?",
                        Arrays.asList("'22'", "3", "4", "Erro"),
                        2
                )
        );
    }

    // --- Método para Exibir uma Pergunta na Tela ---
    private void showQuestion() {
        // Pega a pergunta atual da lista, usando o índice "currentQuestionIndex".
        Question currentQuestion = questionList.get(currentQuestionIndex);
        // Atualiza o texto do TextView da pergunta.
        tvQuestion.setText(currentQuestion.getText());
        // Atualiza o texto de cada botão com as opções da pergunta atual.
        btnOption1.setText(currentQuestion.getOptions().get(0));
        btnOption2.setText(currentQuestion.getOptions().get(1));
        btnOption3.setText(currentQuestion.getOptions().get(2));
        btnOption4.setText(currentQuestion.getOptions().get(3));
    }

    // --- Método para Checar a Resposta do Usuário ---
    private void checkAnswer(int selectedAnswerIndex) {
        // Pega a pergunta atual para poder comparar as respostas.
        Question currentQuestion = questionList.get(currentQuestionIndex);

        // Compara o índice do botão clicado com o índice da resposta correta.
        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            // Se a resposta estiver correta...
            score++; // Aumenta a pontuação.
            // Mostra uma mensagem curta na tela dizendo "Resposta Certa!".
            Toast.makeText(this, "Resposta Certa!", Toast.LENGTH_SHORT).show();
        } else {
            // Se a resposta estiver errada...
            // Pega o texto da resposta correta para mostrar ao usuário.
            String correctAnswer = currentQuestion.getOptions().get(currentQuestion.getCorrectAnswerIndex());
            // Mostra uma mensagem curta dizendo qual era a resposta certa.
            Toast.makeText(this, "Errado! A resposta era " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        // Avança para a próxima pergunta.
        currentQuestionIndex++;

        // Verifica se ainda existem perguntas na lista.
        if (currentQuestionIndex < questionList.size()) {
            // Se sim, chama o método para mostrar a próxima pergunta.
            showQuestion();
        } else {
            // Se não, o quiz acabou! Chama o método para mostrar o resultado final.
            showResult();
        }
    }

    // --- Método para Mostrar a Tela de Resultado (MODIFICADO) ---
    private void showResult() {
        // Cria uma "intenção" (Intent) para navegar da tela atual (MainActivity) para a ResultActivity.
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        // Adiciona informações extras na Intent que serão enviadas para a próxima tela.
        intent.putExtra("SCORE", score); // Envia a pontuação final.
        intent.putExtra("TOTAL_QUESTIONS", questionList.size()); // Envia o número total de perguntas.

        // --- NOVO CÓDIGO ---
        // Envia o nome do usuário para a tela de resultado
        intent.putExtra("USER_NAME", userName);

        // Inicia a nova tela.
        startActivity(intent);
        // Finaliza a tela atual (MainActivity) para que o usuário não possa voltar para o quiz usando o botão "Voltar".
        finish();
    }
}