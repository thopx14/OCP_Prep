package threads.aufgaben.words;

import java.util.LinkedList;
import java.util.List;

public class Words {

    public static List<String> englishWords() {
        return EnglishWords.asLinkedList();
    }

    public static List<String> passwords() {
        List<String> list = new LinkedList<>();
        ResourceLoader.load( "10k_most_common_passwords.zip", list::add );
        return list;
    }
}
