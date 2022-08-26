package streams.aufgaben;

import java.util.Comparator;
import java.util.Optional;

public class TextStatisticsCombined implements TextStatistics {

    private final TextStatistics stats1;
    private final TextStatistics stats2;

    private static final Comparator<String> CMP_STR_LEN = Comparator.comparing( String::length );

    public TextStatisticsCombined( TextStatistics stats1, TextStatistics stats2 ) {
        this.stats1 = stats1;
        this.stats2 = stats2;
    }

    @Override
    public int getCountChars() {
        return stats1.getCountChars() + stats2.getCountChars();
    }

    @Override
    public long getCountSpecialChars() {
        return stats1.getCountSpecialChars() + stats2.getCountSpecialChars();
    }

    @Override
    public long getCountLetters() {
        return stats1.getCountLetters() + stats2.getCountLetters();
    }

    @Override
    public Optional<String> getLongestWord() {
        Optional<String> word1 = stats1.getLongestWord();
        Optional<String> word2 = stats2.getLongestWord();

        if ( word1.isEmpty() ) {
            return word2;
        }

        if ( word2.isEmpty() ) {
            return word1;
        }

        if ( CMP_STR_LEN.compare( word1.get(), word2.get() ) > 0 ) {
            return word1;
        }

        return word2;
    }
}
