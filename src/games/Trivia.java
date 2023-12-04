package games;

import java.util.Scanner;

public class Trivia {

    public static void playTrivia() {
        int gameScore = 0;
        int trackNumbers = 0;
        int trackWords = 0;
        Scanner scanner = new Scanner(System.in);
        String input;
        NumberQuestions numQuestions = new NumberQuestions(0, 0);
        WordQuestions wordQuestions = new WordQuestions(0, 0);
        NumberQuestions.generateQuestions(numQuestions.questions, numQuestions.answers);
        WordQuestions.generateQuestions(wordQuestions.questions, wordQuestions.answers);
        for (int i = trackNumbers; i < numQuestions.questions.size(); i++) {
            System.out.println(gameScore + " " + numQuestions.score + "Debug" + trackNumbers);
            numQuestions.askQuestion(numQuestions.questions, numQuestions.questionNumber);
            input = scanner.next();
            gameScore = numQuestions.score + wordQuestions.score;
            trackNumbers++;
            numQuestions.checkAnswer(numQuestions.answers, input, numQuestions.questionNumber, gameScore);
        }

        for (int i = trackWords; i < numQuestions.questions.size(); i++) {
            wordQuestions.askQuestion(wordQuestions.questions, wordQuestions.questionNumber);
            input = scanner.next();
            gameScore = numQuestions.score + wordQuestions.score;
            trackWords++;
            wordQuestions.checkAnswer(wordQuestions.answers, input, wordQuestions.questionNumber, gameScore);
        }

        if (gameScore == 5) {
            numQuestions.questions.add("How many toes do most dogs have on one of their back feet?");
            numQuestions.answers.add("4");
            numQuestions.questions.add("You make $75,000 a year. How many thousands is that?");
            numQuestions.answers.add("75");
            numQuestions.questions.add("How many wheels are on a car?");
            numQuestions.answers.add("5");

            wordQuestions.questions.add("Most new programmers have created a '_____ World' app. Fill in the blank. Lowercase only.");
            wordQuestions.answers.add("hello");
            wordQuestions.questions.add("You make $75,000 a year. What does the $ symbol stand for?");
            wordQuestions.answers.add("dollars");
            wordQuestions.questions.add("I chose orange soda instead of water because I like the taste of what?");
            wordQuestions.answers.add("sugar");

            for (int i = trackNumbers; i < numQuestions.questions.size(); i++) {
                System.out.println(gameScore + " " + numQuestions.score + "Debug" + trackNumbers);
                numQuestions.askQuestion(numQuestions.questions, numQuestions.questionNumber);
                input = scanner.next();
                gameScore = numQuestions.score + wordQuestions.score;
                trackNumbers++;
                numQuestions.checkAnswer(numQuestions.answers, input, numQuestions.questionNumber, gameScore);
            }

            for (int i = trackWords; i < numQuestions.questions.size(); i++) {
                wordQuestions.askQuestion(wordQuestions.questions, wordQuestions.questionNumber);
                input = scanner.next();
                gameScore = numQuestions.score + wordQuestions.score;
                trackWords++;
                wordQuestions.checkAnswer(wordQuestions.answers, input, wordQuestions.questionNumber, gameScore);
            }
        }
    }
}
