package com.example.quiz_app;

// Importa a interface 'List' do Java, que é usada para criar a lista de opções.
import java.util.List;

// Declaração da classe 'Question'. 'public' significa que ela pode ser acessada por outras classes.
public class Question {
    // --- Declaração dos Atributos (ou variáveis da classe) ---

    // 'private' significa que estas variáveis só podem ser acessadas diretamente de dentro desta classe.
    // Isso protege os dados e é uma boa prática de programação (encapsulamento).

    // Armazena o texto da pergunta (ex: "Qual a cor do céu?").
    private String text;
    // Armazena uma lista de textos que são as opções de resposta.
    private List<String> options;
    // Armazena o NÚMERO do índice (a posição) da resposta correta na lista de opções.
    private int correctAnswerIndex;

    // --- Construtor da Classe ---
    // O construtor é um método especial que é chamado quando você cria um novo objeto.
    // Ex: new Question("Texto da pergunta", listaDeOpcoes, 1);
    public Question(String text, List<String> options, int correctAnswerIndex) {
        // 'this.text' se refere à variável 'text' DA CLASSE (a de cima).
        // O 'text' sem o 'this' se refere ao parâmetro que foi passado para o construtor.
        // Esta linha atribui o valor recebido ao atributo da classe.
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // --- Métodos de Acesso (Getters) ---
    // Como os atributos são 'private', precisamos de métodos públicos para que
    // outras classes (como a MainActivity) possam LER seus valores.

    // Método que retorna o texto da pergunta.
    public String getText() {
        return text;
    }

    // Método que retorna a lista de opções.
    public List<String> getOptions() {
        return options;
    }

    // Método que retorna o índice da resposta correta.
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}