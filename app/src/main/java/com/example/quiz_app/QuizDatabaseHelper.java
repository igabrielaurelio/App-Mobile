package com.example.quiz_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseHelper extends SQLiteOpenHelper {

    // Informações do Banco
    private static final String DATABASE_NAME = "QuizBase.db";
    private static final int DATABASE_VERSION = 1;

    // Informações da Tabela
    private static final String TABLE_SCORES = "scores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_POINTS = "points";

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Comando SQL para criar a tabela
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_POINTS + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    // --- C: CREATE (Inserir pontuação) ---
    public void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, score.getName());
        values.put(COLUMN_POINTS, score.getPoints());

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    // --- R: READ (Ler todas as pontuações ordenadas) ---
    public List<Score> getAllScores() {
        List<Score> scoreList = new ArrayList<>();
        // Seleciona tudo e ordena por pontos decrescente (do maior para o menor)
        String selectQuery = "SELECT * FROM " + TABLE_SCORES + " ORDER BY " + COLUMN_POINTS + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                // Pega os dados das colunas pelo índice
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int pointsIndex = cursor.getColumnIndex(COLUMN_POINTS);

                // Verifica se as colunas existem (boa prática)
                if (idIndex != -1 && nameIndex != -1 && pointsIndex != -1) {
                    Score score = new Score();
                    score.setId(cursor.getInt(idIndex));
                    score.setName(cursor.getString(nameIndex));
                    score.setPoints(cursor.getInt(pointsIndex));
                    scoreList.add(score);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scoreList;
    }

    // --- U: UPDATE (Atualizar uma pontuação - Exemplo de requisito) ---
    public int updateScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, score.getName());
        values.put(COLUMN_POINTS, score.getPoints());

        // Atualiza onde o ID for igual ao ID do objeto passado
        return db.update(TABLE_SCORES, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(score.getId())});
    }

    // --- D: DELETE (Apagar tudo - "rm -rf") ---
    public void deleteAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Deleta todas as linhas da tabela
        db.delete(TABLE_SCORES, null, null);
        db.close();
    }

    // --- D: DELETE (Apagar um específico - Opcional) ---
    public void deleteScore(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}