package database.dao.adt;

import database.objects.Info;
import database.objects.Letter;
import database.objects.Word;

import java.util.List;
import java.util.Map;

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
     * Gets a map which maps all letters and how many times they
     * occurred in the message history of the user with the given user_id
     *
     * @param user_id The id of the user
     * @return The map of letters
     */
    Map<Character, Integer> getLetters(String user_id);

    /**
     * Gets a map which maps all words and how many times they
     * occurred in the message history of the user with the given user_id
     *
     * @param user_id The id of the user
     * @return The map of words
     */
    Map<String, Integer> getWords(String user_id);

    /**
     * Inserts the given info-object to the database
     *
     * @param info The info-object
     */
    void insert(Info info);

    /**
     * Insert the given list of letters and the given list of words
     * to the info-table associated with the user that has user_id
     * as id
     *
     * @param user_id The id of the user
     * @param letters The list of letter objects
     * @param words The list of word objects
     */
    void insert(String user_id, List<Letter> letters, List<Word> words);
}
