package streams.aufgaben;

import java.util.Optional;

public interface TextStatistics {

    int getCountChars();

    long getCountSpecialChars();

    long getCountLetters();

    Optional<String> getLongestWord();
}
