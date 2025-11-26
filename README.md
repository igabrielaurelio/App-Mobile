<img width="1916" height="1034" alt="{36EC27A2-60E7-48A3-8A1D-B0ECA072B5B9}" src="https://github.com/user-attachments/assets/8bb5c54f-06db-4902-b6ca-f88d056b9766" />

Aplicativo Android nativo desenvolvido como projeto final da disciplina de **Computação Móvel**. O app consiste em um Quiz interativo sobre tecnologia e programação, com persistência de dados, sistema de ranking e uma interface estilizada.

## Integrantes do Grupo
* **Gabriel Aurélio Vietehesky Silveira**
* **João Victor Alves Gotti**

---

## Objetivo do App
Oferecer uma ferramenta lúdica para estudantes e entusiastas de tecnologia testarem seus conhecimentos. O aplicativo desafia o usuário com perguntas aleatórias sobre diversos temas (Java, Hardware, Redes, Android), classifica seu nível de conhecimento (Junior, Full-Stack, Senior) e mantém um histórico de pontuações (Ranking).

---

## Tecnologias Utilizadas
* **Linguagem:** Java (JDK 11/17)
* **IDE:** Android Studio Ladybug | 2024.2.1
* **SDK Mínimo:** API 24 (Android 7.0)
* **SDK Alvo:** API 34/36 (Android 14/15)
* **Banco de Dados:** SQLite (Nativo do Android)
* **Interface:** XML com Material Design e Estilização Customizada (Tema "Dark IDE")

---

## Estrutura do Banco de Dados (SQLite)

O aplicativo utiliza a classe `SQLiteOpenHelper` para gerenciar um banco de dados local chamado `QuizBase.db`.

**Tabela:** `scores`

| Campo | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | `INTEGER PRIMARY KEY AUTOINCREMENT` | Identificador único da jogada |
| `name` | `TEXT` | Nome do jogador |
| `points` | `INTEGER` | Pontuação obtida (0 a 10) |

---

## Funcionalidades Implementadas (CRUD Completo)

O aplicativo atende aos requisitos de CRUD (Create, Read, Update, Delete) através da gestão do Ranking:

1.  **CREATE (Inserção):**
    * Ao finalizar o quiz, o nome do usuário e sua pontuação são salvos automaticamente no banco de dados SQLite.
    * *Código:* `dbHelper.addScore(newScore);` na `ResultActivity`.

2.  **READ (Leitura):**
    * A tela de resultado consulta o banco de dados para exibir o "Leaderboard" (Ranking).
    * Os dados são recuperados e ordenados de forma decrescente (maior pontuação primeiro).
    * *Código:* `dbHelper.getAllScores();` ordenado por `COLUMN_POINTS DESC`.

3.  **UPDATE (Atualização):**
    * A estrutura do banco (`QuizDatabaseHelper`) possui o método `updateScore()` implementado, permitindo futuras expansões para edição de nomes de usuários ou correção de pontuações.

4.  **DELETE (Exclusão):**
    * Funcionalidade de **"Resetar Ranking"** (botão vermelho estilo `rm -rf`).
    * Permite ao usuário limpar todo o histórico de pontuações do dispositivo.
    * *Código:* `dbHelper.deleteAllScores();`.

---

## Diferenciais e UX
* **Tema Hacker/IDE:** Interface escura inspirada em editores de código (VS Code/Dracula Theme), utilizando fontes monoespaçadas e cores neon (Roxo, Verde, Ciano).
* **Perguntas Dinâmicas:** Banco de dados interno com **mais de 100 perguntas**. A cada nova partida, o app sorteia aleatoriamente 10 questões, garantindo que o jogo não seja repetitivo.
* **Feedback Visual:** Mensagens de "Build Successful" ou logs de erro dependendo da ação do usuário.

---

## Prints do Aplicativo

| Tela Inicial (Login) | Tela do Quiz | Resultado e Ranking |
| :---: | :---: | :---: |
| *(Insira aqui o print da tela de Login)* | *(Insira aqui o print de uma pergunta)* | *(Insira aqui o print do Ranking)* |

---

## Como Executar
1.  Clone este repositório.
2.  Abra o projeto no **Android Studio**.
3.  Aguarde a sincronização do Gradle.
4.  Execute em um emulador (ex: Pixel 6 API 34).

---
*Projeto desenvolvido para a disciplina de Computação Móvel - 2025.*

