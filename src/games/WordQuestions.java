package games;

import java.util.ArrayList;
import java.util.List;

public class WordQuestions extends TriviaQuestions {
    public List<String> questions = new ArrayList<>();
    public List<String> answers = new ArrayList<>();

    public WordQuestions(int score, int questionNumber) {
        super(score, questionNumber);
    }

    public static void generateQuestions(List<String> questions, List<String> answers) {
        questions.add("Soda can. Trash or recycle?");
        answers.add("recycle");

        questions.add("What starts with a 'p' and ends irate?");
        answers.add("pirate");

        questions.add("Enter the word that does not belong: java, html, python, lua, assembly, toyota, c, basic, javascript.");
        answers.add("toyota");
    }
}
