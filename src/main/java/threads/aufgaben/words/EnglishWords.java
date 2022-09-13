package threads.aufgaben.words;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Stellt eine Sammlung mit englischen Wörter (ca. 58 Tausend) zur Verfügung.
 * Alle Wörter bestehen aus Kleinbuchstaben.
 */
public class EnglishWords {

    private static final String resource = "english_words_lowercase.zip";

    /**
     * @return LinkedList mit englischen Wörter.
     */
    public static List<String> asLinkedList() {
        List<String> list = new LinkedList<>();
        ResourceLoader.load( resource, list::add );
        return list;
    }

    /**
     * Lädt die englischen Wörter in eine Ziel-Collection.
     *
     * @param target Die Ziel-Collection, der die englischen Wörter hinzugefügt werden.
     */
    public static void loadInto( Collection<String> target ) {
        ResourceLoader.load( resource, target::add );
    }

    /**
     * Übergibt einem Consumer die englischen Wörter eins nach dem anderen.
     *
     * @param consumer der Consumer, dem die englische Wörter eins nach dem anderen übergeben werden.
     */
    public static void provideFor( Consumer<String> consumer ) {
        ResourceLoader.load( resource, consumer );
    }

}
