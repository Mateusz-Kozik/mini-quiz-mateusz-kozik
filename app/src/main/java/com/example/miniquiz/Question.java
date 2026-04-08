package com.example.miniquiz;

public class Question {
    String question;
    String answerA;
    String answerB;
    String answerC;
    String correctAnswer;
    public Question(String question, String a, String b, String c, String correct) {
        this.question = question;
        this.answerA = a;
        this.answerB = b;
        this.answerC = c;
        this.correctAnswer = correct;
    }
}
