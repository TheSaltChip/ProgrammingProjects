package database.dao.adt;

import database.objects.Info;
import database.objects.LetterAmount;
import database.objects.WordAmount;

import java.util.List;

public interface InfoDAO {
    /**
     * Get all info objects from the database
     *
     * @return A list of all info-objects from the database
     */
    List<Info> get();

    /**
     * Attempts to get the info-object associated to a user with the given user_id
     *
     * @param user_id The id of the user
     * @return The info-object associated with the user if it exists
     */
    Info get(String user_id);

    /**
     * Attempts to fetch all LetterAmount object associated with the user with the given user_id
     *
     * @param user_id The id of the user
     * @return List of all LetterAmounts
     */
    List<LetterAmount> getLetters(String user_id);

    /**
     * Attempts to fetch all WordAmount objects associated with the user with the given user_id
     *
     * @param user_id The id of the user
     * @return List of all WordAmounts
     */
    List<WordAmount> getWords(String user_id);

    /**
     * Inserts the given info-object to the database
     *
     * @param info The info-object
     */
    void insert(Info info);

    /**
     * Updates list of letters and the list of words
     * to the given info-object
     *
     * @param info The info that the lists are going to be associated to
     * @param letterTimes The list of letter objects
     * @param words The list of word objects
     */
    void update(Info info, List<LetterAmount> letterTimes, List<WordAmount> words);
}
