package pl.wojciech.smol.jakatomelodia;

import org.junit.Test;

import static org.junit.Assert.*;

public class checkIfQuestionsAreUnique {

    @Test
    public void areQuestionsFromPopCategoryUnique (){

        Game popGame;

        for (int i = 0; i < 50; i++) {
            boolean areQuestionsUnique = true;
            popGame = new Game(Question.Category.POP);

            for (int j = 0; j < popGame.getmQuestionsGame().size() - 1; j++) {
                if (popGame.getmQuestionsGame().get(j).equals(popGame.getmQuestionsGame().get(j+1)))
                    areQuestionsUnique = false;
            }

            assertTrue(areQuestionsUnique);
        }
    }
}
