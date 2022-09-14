package threads.aufgaben.words;

import io.aufgaben.FileUtils;

import java.nio.file.Path;
import java.util.List;

public class ForkJoinPoolMain {
    public static void main( String[] args ) {
        List<String> englishWords = Words.englishWords();
        WordsTester<String> wordsTester = new WordsTester<>( englishWords );
        System.out.println( wordsTester.getGreatestString() ); // zulus

        List<String> wordsUpperCase = wordsTester.toUpperCaseList();
        FileUtils.safeToFile( wordsUpperCase, Path.of( "words_upper_forkjoinpool.txt" ), false );

        List<String> passwords = Words.passwords();
        String passwordToTest = "12345";
        PasswordTester passwordTester = new PasswordTester( passwordToTest, passwords );
        if ( passwordTester.doesPasswordMatch() ) {
            System.out.println( "Passwort: " + passwordToTest + " found!" );
        } else {
            System.out.println( "Passwort: " + passwordToTest + " NOT found!" );
        }
    }
}
