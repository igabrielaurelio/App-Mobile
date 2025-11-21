package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // --- Variáveis da Classe ---
    private TextView tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pega o nome do usuário vindo da StartActivity
        userName = getIntent().getStringExtra("USER_NAME");

        // Vincula os componentes da tela
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        // Inicializa o jogo
        loadQuestions();
        showQuestion();

        // Configura os cliques dos botões
        btnOption1.setOnClickListener(v -> checkAnswer(0));
        btnOption2.setOnClickListener(v -> checkAnswer(1));
        btnOption3.setOnClickListener(v -> checkAnswer(2));
        btnOption4.setOnClickListener(v -> checkAnswer(3));
    }

    // --- Método GIGANTE com 100+ Perguntas ---
    private void loadQuestions() {
        List<Question> allQuestions = new ArrayList<>();

        // --- HTML & Web ---
        allQuestions.add(new Question("Qual a principal função do HTML?", Arrays.asList("Estilizar", "Estruturar conteúdo", "Lógica", "Banco de dados"), 1));
        allQuestions.add(new Question("Para que serve o CSS?", Arrays.asList("Design e Estilo", "Armazenar dados", "Estrutura", "Compilar código"), 0));
        allQuestions.add(new Question("O que significa WWW?", Arrays.asList("World Web Wide", "Web World Wide", "World Wide Web", "Wide World Web"), 2));
        allQuestions.add(new Question("Qual tag HTML cria um parágrafo?", Arrays.asList("<p>", "<para>", "<text>", "<pg>"), 0));
        allQuestions.add(new Question("Qual tag HTML cria um link?", Arrays.asList("<link>", "<a>", "<url>", "<href>"), 1));
        allQuestions.add(new Question("O que é um 'browser'?", Arrays.asList("Um servidor", "Um editor de texto", "Um navegador web", "Uma linguagem"), 2));
        allQuestions.add(new Question("Qual destes não é um navegador?", Arrays.asList("Chrome", "Firefox", "Edge", "Windows"), 3));
        allQuestions.add(new Question("Em CSS, como mudamos a cor do texto?", Arrays.asList("font-color", "text-color", "color", "foreground"), 2));
        allQuestions.add(new Question("O que significa HTTP?", Arrays.asList("HyperText Transfer Protocol", "High Transfer Text Protocol", "Hyper Transfer Text Program", "Home Tool Transfer Protocol"), 0));
        allQuestions.add(new Question("Qual a versão mais moderna do HTML?", Arrays.asList("HTML 2", "HTML 4", "HTML X", "HTML 5"), 3));

        // --- Lógica de Programação ---
        allQuestions.add(new Question("O que é uma 'variável'?", Arrays.asList("Um erro", "Um loop", "Espaço na memória para valor", "Um comando de saída"), 2));
        allQuestions.add(new Question("Qual comando repete código?", Arrays.asList("if", "var", "loop / for", "string"), 2));
        allQuestions.add(new Question("O que é um algoritmo?", Arrays.asList("Uma peça de PC", "Sequência de passos para resolver um problema", "Um vírus", "Uma linguagem"), 1));
        allQuestions.add(new Question("O que é um 'bug'?", Arrays.asList("Um recurso novo", "Uma falha ou erro no código", "Um tipo de variável", "Um antivírus"), 1));
        allQuestions.add(new Question("Qual estrutura é usada para decisões?", Arrays.asList("for", "while", "if / else", "print"), 2));
        allQuestions.add(new Question("O que é um loop infinito?", Arrays.asList("Um loop que nunca para", "Um loop muito rápido", "Um loop que roda 1000 vezes", "Uma variável grande"), 0));
        allQuestions.add(new Question("Para que servem comentários?", Arrays.asList("Para o computador ler", "Para deixar o código mais rápido", "Para explicar o código para humanos", "Para colorir o editor"), 2));
        allQuestions.add(new Question("Qual o valor booleano para 'falso'?", Arrays.asList("0", "null", "false", "fake"), 2));
        allQuestions.add(new Question("O que é concatenação?", Arrays.asList("Apagar texto", "Juntar duas strings", "Dividir números", "Compilar código"), 1));
        allQuestions.add(new Question("O índice de um array começa em:", Arrays.asList("0", "1", "-1", "10"), 0));

        // --- Java & OOP ---
        allQuestions.add(new Question("Qual símbolo termina uma instrução Java?", Arrays.asList(".", ":", ";", ","), 2));
        allQuestions.add(new Question("Como imprime no console em Java?", Arrays.asList("print()", "System.out.println()", "console.log()", "echo"), 1));
        allQuestions.add(new Question("Qual tipo de dado armazena texto?", Arrays.asList("int", "boolean", "String", "char"), 2));
        allQuestions.add(new Question("Qual tipo de dado armazena inteiros?", Arrays.asList("String", "float", "int", "boolean"), 2));
        allQuestions.add(new Question("O que é uma Classe?", Arrays.asList("Um objeto pronto", "Um modelo/molde para objetos", "Uma variável", "Um método"), 1));
        allQuestions.add(new Question("O que é um Objeto?", Arrays.asList("Uma instância de uma classe", "Um arquivo de texto", "Um erro", "Um comando"), 0));
        allQuestions.add(new Question("O que significa 'public'?", Arrays.asList("Acesso restrito", "Acesso a qualquer classe", "Acesso apenas na pasta", "Acesso protegido"), 1));
        allQuestions.add(new Question("Qual método inicia um programa Java?", Arrays.asList("start()", "init()", "run()", "main()"), 3));
        allQuestions.add(new Question("O que é herança?", Arrays.asList("Quando uma classe ganha dinheiro", "Quando uma classe herda características de outra", "Quando deletamos um objeto", "Um erro de memória"), 1));
        allQuestions.add(new Question("Java é uma linguagem:", Arrays.asList("Estruturada", "Funcional pura", "Orientada a Objetos", "De baixo nível"), 2));

        // --- Hardware ---
        allQuestions.add(new Question("O que significa CPU?", Arrays.asList("Central Processing Unit", "Computer Personal Unit", "Central Power Unit", "Computer Processing User"), 0));
        allQuestions.add(new Question("Qual componente armazena dados permanentemente?", Arrays.asList("RAM", "Processador", "HD / SSD", "Placa de Vídeo"), 2));
        allQuestions.add(new Question("Qual a função da RAM?", Arrays.asList("Armazenar arquivos para sempre", "Armazenar dados temporários em uso", "Processar gráficos", "Resfriar o PC"), 1));
        allQuestions.add(new Question("O que é a GPU?", Arrays.asList("Unidade de Processamento Gráfico", "Gerenciador de Programas", "Grande Processador Unificado", "Gabinete Para Usuário"), 0));
        allQuestions.add(new Question("Qual destes é dispositivo de entrada?", Arrays.asList("Monitor", "Impressora", "Teclado", "Caixa de som"), 2));
        allQuestions.add(new Question("Qual destes é dispositivo de saída?", Arrays.asList("Mouse", "Microfone", "Monitor", "Webcam"), 2));
        allQuestions.add(new Question("O que significa SSD?", Arrays.asList("Super Speed Drive", "Solid State Drive", "System Storage Disk", "Silicon State Disk"), 1));
        allQuestions.add(new Question("O sistema binário usa quais números?", Arrays.asList("1 e 2", "0 e 9", "0 e 1", "1 e 10"), 2));
        allQuestions.add(new Question("Quantos bits formam um Byte?", Arrays.asList("4", "8", "16", "32"), 1));
        allQuestions.add(new Question("Qual a função da Placa Mãe?", Arrays.asList("Gerar energia", "Conectar todos os componentes", "Armazenar fotos", "Processar vídeo"), 1));

        // --- Android ---
        allQuestions.add(new Question("Qual linguagem é oficial do Android?", Arrays.asList("Swift", "Kotlin / Java", "PHP", "Ruby"), 1));
        allQuestions.add(new Question("O que é um APK?", Arrays.asList("Um processador", "Um arquivo de instalação Android", "Uma marca", "Um erro"), 1));
        allQuestions.add(new Question("Loja oficial de apps do Google?", Arrays.asList("App Store", "Google Market", "Play Store", "Android Store"), 2));
        allQuestions.add(new Question("O que é uma Activity?", Arrays.asList("Um banco de dados", "Uma tela da aplicação", "Um botão", "Uma imagem"), 1));
        allQuestions.add(new Question("Qual arquivo define permissões?", Arrays.asList("MainActivity.java", "colors.xml", "AndroidManifest.xml", "build.gradle"), 2));
        allQuestions.add(new Question("Onde ficam os layouts?", Arrays.asList("pasta java", "pasta res/layout", "pasta manifests", "pasta gradle"), 1));
        allQuestions.add(new Question("Para que serve o Logcat?", Arrays.asList("Desenhar telas", "Ver logs e erros", "Instalar o app", "Editar código"), 1));
        allQuestions.add(new Question("O que é o XML no Android?", Arrays.asList("Linguagem de programação", "Marcação para layouts", "Banco de dados", "Compilador"), 1));
        allQuestions.add(new Question("Nome do mascote Android?", Arrays.asList("Bugdroid", "Robot", "Andy", "Droid"), 0));
        allQuestions.add(new Question("Android é baseado em:", Arrays.asList("Windows", "Linux", "MacOS", "DOS"), 1));

        // --- Redes ---
        allQuestions.add(new Question("O que é um IP?", Arrays.asList("Internet Provider", "Internal Protocol", "Internet Protocol", "Image Processor"), 2));
        allQuestions.add(new Question("O que é Wi-Fi?", Arrays.asList("Internet com fio", "Rede sem fio", "Um computador", "Um site"), 1));
        allQuestions.add(new Question("O que significa LAN?", Arrays.asList("Local Area Network", "Large Area Network", "Long Access Node", "Link Area Net"), 0));
        allQuestions.add(new Question("Função do Roteador?", Arrays.asList("Criar imagens", "Encaminhar dados entre redes", "Armazenar sites", "Proteger vírus"), 1));
        allQuestions.add(new Question("O que é a Nuvem?", Arrays.asList("Servidores na internet", "Um satélite", "Um pen drive", "O clima"), 0));
        allQuestions.add(new Question("O que significa URL?", Arrays.asList("Uniform Resource Locator", "Ultra Rapid Link", "User Resource Link", "Union Rom List"), 0));
        allQuestions.add(new Question("Para que serve o DNS?", Arrays.asList("Criar sites", "Traduzir domínios em IPs", "Bloquear vírus", "Acelerar a internet"), 1));
        allQuestions.add(new Question("O que é Ping?", Arrays.asList("Um jogo", "Latência da rede", "Um vírus", "Um cabo"), 1));
        allQuestions.add(new Question("Protocolo de emails?", Arrays.asList("HTTP", "FTP", "SMTP", "SSH"), 2));
        allQuestions.add(new Question("O que é Firewall?", Arrays.asList("Parede de tijolos", "Segurança de rede", "Navegador", "Cabo de rede"), 1));

        // --- Segurança & Variedades ---
        allQuestions.add(new Question("O que é Backup?", Arrays.asList("Um vírus", "Cópia de segurança", "Programa pirata", "Peça do PC"), 1));
        allQuestions.add(new Question("O que é Phishing?", Arrays.asList("Pescaria", "Roubo de dados por engano", "Antivírus", "Senha"), 1));
        allQuestions.add(new Question("O que é Criptografia?", Arrays.asList("Moeda", "Proteger informações", "Gráfico", "Linguagem"), 1));
        allQuestions.add(new Question("Melhor prática para senhas?", Arrays.asList("Usar '123456'", "Datas de nascimento", "Longas e complexas", "Anotar no monitor"), 2));
        allQuestions.add(new Question("O que é Malware?", Arrays.asList("Hardware ruim", "Software malicioso", "Bom programa", "Site de compras"), 1));
        allQuestions.add(new Question("O que é Open Source?", Arrays.asList("Pago", "Código aberto", "Processador", "Internet grátis"), 1));
        allQuestions.add(new Question("Qual é Sistema Operacional?", Arrays.asList("Word", "Excel", "Linux", "Paint"), 2));
        allQuestions.add(new Question("O que é SQL?", Arrays.asList("Linguagem de Banco de Dados", "Jogo", "Som", "PC"), 0));
        allQuestions.add(new Question("Quem fundou a Microsoft?", Arrays.asList("Steve Jobs", "Bill Gates", "Mark Zuckerberg", "Elon Musk"), 1));
        allQuestions.add(new Question("Criador do Facebook?", Arrays.asList("Bill Gates", "Jeff Bezos", "Mark Zuckerberg", "Tim Cook"), 2));
        allQuestions.add(new Question("Empresa do iPhone?", Arrays.asList("Samsung", "Apple", "Nokia", "Sony"), 1));
        allQuestions.add(new Question("O que é 4K?", Arrays.asList("4000 cores", "Resolução ~4000px larg", "4 mm", "4 cabos"), 1));
        allQuestions.add(new Question("O que é Bluetooth?", Arrays.asList("Dente azul", "Conexão sem fio curta", "Cabo azul", "Satélite"), 1));
        allQuestions.add(new Question("O que é IA?", Arrays.asList("Robô", "Simulação de inteligência", "Processador", "Ficção"), 1));
        allQuestions.add(new Question("O que é GitHub?", Arrays.asList("Jogo", "Hospedagem de código", "Memória", "Vírus"), 1));
        allQuestions.add(new Question("Comando Git para salvar?", Arrays.asList("git push", "git pull", "git commit", "git save"), 2));
        allQuestions.add(new Question("O que é um Pixel?", Arrays.asList("Ponto na tela", "Peça do mouse", "Som", "Cor"), 0));
        allQuestions.add(new Question("O que é VR?", Arrays.asList("Video Real", "Realidade Virtual", "Virus Risk", "Very Rapid"), 1));
        allQuestions.add(new Question("Banco de dados NoSQL?", Arrays.asList("MySQL", "PostgreSQL", "MongoDB", "Oracle"), 2));
        allQuestions.add(new Question("O que é JSON?", Arrays.asList("Pessoa", "Formato de dados", "Linguagem", "Editor"), 1));
        allQuestions.add(new Question("O que é API?", Arrays.asList("Application Programming Interface", "Apple Program", "All People", "App Info"), 0));
        allQuestions.add(new Question("Imprimir em Python?", Arrays.asList("echo", "System.out", "print()", "printf"), 2));
        allQuestions.add(new Question("O que é Framework?", Arrays.asList("Dever de casa", "Estrutura base", "Monitor", "Teclado"), 1));
        allQuestions.add(new Question("O que é Frontend?", Arrays.asList("Banco de dados", "Parte visual", "Servidor", "Rede"), 1));
        allQuestions.add(new Question("O que é Backend?", Arrays.asList("Tela", "Lógica servidor/dados", "Mouse", "Design"), 1));
        allQuestions.add(new Question("Linguagem do React?", Arrays.asList("Java", "Python", "JavaScript", "C#"), 2));
        allQuestions.add(new Question("O que é IDE?", Arrays.asList("Integrated Development Environment", "Internet Data", "Disk Error", "Image Editor"), 0));
        allQuestions.add(new Question("O que é Full Stack?", Arrays.asList("Hambúrguer", "Dev Front e Back", "Erro", "PC potente"), 1));
        allQuestions.add(new Question("Atalho copiar?", Arrays.asList("Ctrl+V", "Ctrl+X", "Ctrl+C", "Alt+F4"), 2));
        allQuestions.add(new Question("Atalho colar?", Arrays.asList("Ctrl+C", "Ctrl+V", "Ctrl+Z", "Ctrl+P"), 1));
        allQuestions.add(new Question("Atalho desfazer?", Arrays.asList("Ctrl+Z", "Ctrl+Y", "Ctrl+D", "Ctrl+S"), 0));
        allQuestions.add(new Question("O que é spam?", Arrays.asList("Comida", "Lixo eletrônico", "Vírus", "Site"), 1));
        allQuestions.add(new Question("Linguagem compilada?", Arrays.asList("Python", "JavaScript", "C++", "HTML"), 2));
        allQuestions.add(new Question("Linguagem interpretada?", Arrays.asList("C", "C++", "Python", "Assembly"), 2));
        allQuestions.add(new Question("O que é SaaS?", Arrays.asList("Software as a Service", "System as Server", "Save as Source", "Storage Service"), 0));
        allQuestions.add(new Question("Pai da computação?", Arrays.asList("Bill Gates", "Alan Turing", "Steve Jobs", "Musk"), 1));
        allQuestions.add(new Question("Primeira programadora?", Arrays.asList("Ada Lovelace", "Marie Curie", "Grace Hopper", "Victoria"), 0));
        allQuestions.add(new Question("Sistema do iPhone?", Arrays.asList("Android", "Windows", "iOS", "Blackberry"), 2));
        allQuestions.add(new Question("O que é IoT?", Arrays.asList("Internet das Coisas", "Input Text", "Internal Tech", "Image Time"), 0));
        allQuestions.add(new Question("O que é Drone?", Arrays.asList("Carro", "Veículo aéreo não tripulado", "Robô", "Satélite"), 1));
        allQuestions.add(new Question("O que é 5G?", Arrays.asList("5 GB", "5ª geração móvel", "5 Games", "5 Google"), 1));
        allQuestions.add(new Question("O que é Servidor?", Arrays.asList("Garçom", "PC que fornece serviços", "Cabo", "Monitor"), 1));
        allQuestions.add(new Question("O que são Cookies?", Arrays.asList("Biscoitos", "Dados salvos pelo navegador", "Vírus", "Imagens"), 1));
        allQuestions.add(new Question("O que é Cache?", Arrays.asList("Dinheiro", "Memória rápida", "Caixa", "Erro"), 1));
        allQuestions.add(new Question("O que é VPN?", Arrays.asList("Virtual Private Network", "Video Network", "Virtual Public", "Voice Number"), 0));
        allQuestions.add(new Question("O que é Biometria?", Arrays.asList("Bio", "Autenticação física", "Senha", "Cartão"), 1));
        allQuestions.add(new Question("O que é Dark Mode?", Arrays.asList("Perigo", "Tema escuro", "Desligado", "Sem net"), 1));

        // --- ALEATORIEDADE ---
        // 1. Embaralha todas as perguntas
        Collections.shuffle(allQuestions);

        // 2. Pega apenas as 10 primeiras para este jogo
        if (allQuestions.size() > 10) {
            questionList = allQuestions.subList(0, 10);
        } else {
            questionList = allQuestions;
        }
    }

    private void showQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);

        // Formato Hacker: "Questão 1/10: ..."
        tvQuestion.setText((currentQuestionIndex + 1) + "/10: " + currentQuestion.getText());

        btnOption1.setText(currentQuestion.getOptions().get(0));
        btnOption2.setText(currentQuestion.getOptions().get(1));
        btnOption3.setText(currentQuestion.getOptions().get(2));
        btnOption4.setText(currentQuestion.getOptions().get(3));
    }

    private void checkAnswer(int selectedAnswerIndex) {
        Question currentQuestion = questionList.get(currentQuestionIndex);

        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            score++;
            Toast.makeText(this, "[SUCCESS] Resposta Correta!", Toast.LENGTH_SHORT).show();
        } else {
            String correctAnswer = currentQuestion.getOptions().get(currentQuestion.getCorrectAnswerIndex());
            Toast.makeText(this, "[ERROR] Correto era: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questionList.size()) {
            showQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questionList.size());
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
        finish();
    }
}