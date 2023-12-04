package games;

import java.util.ArrayList;
import java.util.List;

public class NumberQuestions extends TriviaQuestions {
    public List<String> questions = new ArrayList<>();
    public List<String> answers = new ArrayList<>();

    public NumberQuestions(int score, int questionNumber) {
        super(score, questionNumber);
    }

    public static void generateQuestions(List<String> questions, List<String> answers) {
        questions.add("How many fingers does a standard human have?");
        answers.add("5");

        questions.add("What is 2 + 4?");
        answers.add("6");

        questions.add("Enter the number of the item that does not belong: 1. Ear buds, 2. Water bottle, 3. Ear plugs, 4. Ear muffs.");
        answers.add("2");
    }
}
