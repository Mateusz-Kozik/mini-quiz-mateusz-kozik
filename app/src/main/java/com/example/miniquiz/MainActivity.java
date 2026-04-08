package com.example.miniquiz;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    TextView questionText, scoreText;
    Button answerA, answerB, answerC, startButton, resetButton;

    List<Question> questions;
    List<Question> quizQuestions;

    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);

        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);

        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        createQuestions();

        startButton.setOnClickListener(v -> startQuiz());

        answerA.setOnClickListener(v -> checkAnswer(answerA.getText().toString()));
        answerB.setOnClickListener(v -> checkAnswer(answerB.getText().toString()));
        answerC.setOnClickListener(v -> checkAnswer(answerC.getText().toString()));

        resetButton.setOnClickListener(v -> resetQuiz());


    }

    void createQuestions() {
        questions = new ArrayList<>();

        questions.add(new Question("Które miasto jest stolicą Włoch:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questions.add(new Question("Które miasto jest stolicą Polski", "Warszawa", "Berlin", "Kijów", "Warszawa"));
        questions.add(new Question("Które miasto jest stolicą Finlandii", "Talin", "Helsinki", "Oslo", "Helsinki"));
        questions.add(new Question("Które miasto jest stolicą Niemiec", "Berlin", "Praga", "Madryt", "Berlin"));
        questions.add(new Question("Które miasto jest stolicą Francji", "Paryż", "Londyn", "Wiedeń", "Paryż"));
        questions.add(new Question("Które miasto jest stolicą Danii", "Ateny", "Lizbona", "Kopenhaga", "Kopenhaga"));
    }

    void startQuiz() {
        score = 0;
        currentQuestion = 0;

        Collections.shuffle(questions);
        quizQuestions = questions.subList(0, 5);

        findViewById(R.id.answersLayout).setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.GONE);
        startButton.setEnabled(false);

        showQuestion();
    }

    void showQuestion() {
        Question q = quizQuestions.get(currentQuestion);

        questionText.setText(q.question);
        answerA.setText(q.answerA);
        answerB.setText(q.answerB);
        answerC.setText(q.answerC);
    }

    void checkAnswer(String selected) {
        Question q = quizQuestions.get(currentQuestion);

        if (selected.equals(q.correctAnswer)) {
            score++;
        }

        scoreText.setText("Wynik: " + score);

        currentQuestion++;

        if (currentQuestion < 5) {
            showQuestion();
        } else {
            questionText.setText("Koniec quizu! Twój wynik: " + score + " / 5");
            findViewById(R.id.answersLayout).setVisibility(View.GONE);
        }
    }

    void resetQuiz() {
        score = 0;
        currentQuestion = 0;
        scoreText.setText("Wynik: 0");

        questionText.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
        startButton.setEnabled(true);
        findViewById(R.id.answersLayout).setVisibility(View.GONE);
    }
}