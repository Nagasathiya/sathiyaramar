package com.quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication extends JFrame implements ActionListener {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JButton[] optionButtons;
    private JLabel questionLabel;
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining = 10; // 10 seconds for each question

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questions = new ArrayList<>();
        loadQuestions();

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener(this);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time remaining: " + timeRemaining);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timerLabel, BorderLayout.SOUTH);

        displayQuestion();
    }

    private void loadQuestions() {
        questions.add(new Question("Who invented java programming?", new String[]{"Guido van Rossum", "James Gosling", "Dennis Ritchard", "Bjarne Stoustrup"}, 1));
        questions.add(new Question("What is the extenstion of java code files?", new String[]{".js", ".txt", ".class", ".java"}, 3));
        questions.add(new Question("Which of the following is a superclass of every class in java?", new String[]{"ArrayList", "Abstract class", "Object Class", "String"}, 2));
        questions.add(new Question("Number of primitive data types in java?", new String[]{"6", "7", "8", "9"}, 2));
        questions.add(new Question("Total constructor string class hava?", new String[]{"3", "7", "13", "20"}, 2));
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.question);
            for (int i = 0; i < currentQuestion.options.length; i++) {
                optionButtons[i].setText(currentQuestion.options[i]);
            }
            startTimer();
        } else {
            displayScore();
        }
    }

    private void startTimer() {
        timeRemaining = 10; // Reset time for the new question
        timerLabel.setText("Time remaining: " + timeRemaining);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeRemaining--;
                timerLabel.setText("Time remaining: " + timeRemaining);
                if (timeRemaining <= 0) {
                    timer.cancel();
                    submitAnswer(-1); // Submit no answer
                }
            }
        }, 1000, 1000);
    }

    private void submitAnswer(int selectedOption) {
        timer.cancel();
        if (selectedOption == questions.get(currentQuestionIndex).correctAnswer) {
            score++;
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < optionButtons.length; i++) {
            if (e.getSource() == optionButtons[i]) {
                submitAnswer(i);
            }
        }
    }

    private void displayScore() {
        JOptionPane.showMessageDialog(this, "Your score is: " + score + "/" + questions.size());
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizApplication app = new QuizApplication();
            app.setVisible(true);
        });
    }
}
