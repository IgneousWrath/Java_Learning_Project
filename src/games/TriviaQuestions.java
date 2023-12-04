package games;

import java.util.ArrayList;
import java.util.List;


public class TriviaQuestions {
    public int score = 0;
    public int questionNumber;


    public TriviaQuestions(Integer score, Integer questionNumber){
        this.questionNumber = questionNumber;
        this. score = score;
    }

    public void checkAnswer(List<String> answers, String input, int questionNumber, int score){
        System.out.println("You answered: " + input + ". The correct answer was: " + answers.get(questionNumber) + ".");
        if (input.equals(answers.get(questionNumber))){
            System.out.println("You answered correctly!");
            this.score++;
            score++;
            System.out.println("Your score is now " + score + "!");
            this.questionNumber++;
        } else {
            System.out.println("You answered incorrectly. Sorry.");
            System.out.println("Your score remains " + score + ".");
            this.questionNumber++;
        }

    }
    public void askQuestion(List<String> questions, int questionNumber){
        int adjustedQuestion = questionNumber;
        adjustedQuestion++;
        System.out.println("Question number: " + adjustedQuestion + ".");
        System.out.println(questions.get(questionNumber));
    }
}
