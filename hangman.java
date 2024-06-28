import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class hangman {
    private static final String[] HANGMAN_STAGES = {
        "\n" +
        "\n" +
        "\n" +
        "\n" +
        "\n" +
        "\n" +
        "\n" +
        "___\n",

        "  _______\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (_)\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (_)\n" +
        " |       |\n" +
        " |       |\n" +
        " |\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (_)\n" +
        " |      \\|\n" +
        " |       |\n" +
        " |\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (_)\n" +
        " |      \\|/\n" +
        " |       |\n" +
        " |\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (_)\n" +
        " |      \\|/\n" +
        " |       |\n" +
        " |      /\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (_)\n" +
        " |      \\|/\n" +
        " |       |\n" +
        " |      / \\\n" +
        " |\n" +
        "_|___\n",

        "  _______\n" +
        " |/      |\n" +
        " |      (x)\n" +
        " |      \\|/\n" +
        " |       |\n" +
        " |      / \\\n" +
        " |\n" +
        "_|___\n"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            displayWelcomeMessage();
            playGame(getWord(scanner), lives(scanner));
            playAgain = again(scanner);
        }

        System.out.println("Thank you for playing! Goodbye!");
    }

    private static void displayWelcomeMessage() {
        System.out.println("  _    _                                         _ ");
        System.out.println(" | |  | |                                       | |");
        System.out.println(" | |__| | __ _ _ __   __ _ _ __ ___   __ _ _ __ | |");
        System.out.println(" |  __  |/ _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\| |");
        System.out.println(" | |  | | (_| | | | | (_| | | | | | | (_| | | | |_");
        System.out.println(" |_|  |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_(_)");
        System.out.println("                      __/ |                        ");
        System.out.println("                     |___/                         ");
        System.out.println(" __________________________________");
        System.out.println("|           _______               |");
        System.out.println("|          |/      |              |");
        System.out.println("|          |      (_)             |");
        System.out.println("|          |      \\|/             |");
        System.out.println("|          |       |              |");
        System.out.println("|          |      / \\             |");
        System.out.println("|          |                      |");
        System.out.println("|        __|__________            |");
        System.out.println("|                                 |");
        System.out.println("|_________________________________|\n");
    }

    private static String getWord(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("Local Multiplayer Options");
            System.out.println("1. Play locally with a friend\n");
            System.out.println("Local single Options");
            System.out.println("2. Play Custom Word Library");
            System.out.println("3. Play with dictionary\n");
            System.out.println("Type 'exit' to quit the game.\n");
            System.out.println("----------------------------------\n");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().toLowerCase();
            if (input.equals("exit")) {
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            }

            try {
                choice = Integer.parseInt(input);
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                }
            } catch (NumberFormatException e) {
                // Not a valid number so go back fam
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            System.out.println("-----------------------------------------\n");
        }

        if (choice == 1) {
            System.out.println("Enter the word to be guessed:");
            String word = scanner.nextLine().toLowerCase();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("No Cheating Fam");
            return word;
        } else if (choice == 2) {
            return getRandomWord();
        } else {
            return getWordFromDictionary();
        }
    }

    private static int lives(Scanner scanner) {
        System.out.println("Enter number of lives (default is 8):");
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("exit")) {
            System.out.println("Exiting the game. Cya!");
            System.exit(0);
        }
        try {
            int lives = Integer.parseInt(input);
            if (lives >= 1 && lives <= 8) {
                return lives;
            } else {
                System.out.println("Lives = 8");
                return 8;
            }
        } catch (NumberFormatException e) {
            System.out.println("Lives = 8");
            return 8;
        }
    }
    //chang
    
    

    private static boolean again(Scanner scanner) {
        while (true) {
            System.out.println("\n\n\nWould you like to play again? (Y/N)\n\n\n");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if(input.equals("yes")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid choice. Please enter Yes(Y) or No(N).");
            }
        }
    }

    private static String getRandomWord() {
        String[] words = {"sumsa", "fun", "hangman", "Nihao"};
        int index = new Random().nextInt(words.length);
        return words[index];
    }

    private static String getWordFromDictionary() {
        List<String> words = new ArrayList<>();
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder(); //i cant lie stack overflow slaps
        decoder.onMalformedInput(CodingErrorAction.REPLACE);//no idea but all this helps with reading file
        //https://stackoverflow.com/questions/26268132/all-inclusive-charset-to-avoid-java-nio-charset-malformedinputexception-input
        decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("ukenglish.txt"), decoder))) {// had errors with the normal function so again stack overflow slaps
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words.get(new Random().nextInt(words.size()));
    }

    private static void playGame(String word, int lives) {
        char[] wordArray = word.toCharArray();
        char[] displayArray = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            displayArray[i] = '_';
        }

        boolean[] guessedLetters = new boolean[26];
        Scanner scanner = new Scanner(System.in);
        List<Character> incorrectGuesses = new ArrayList<>();

        while (lives > 0) {
            System.out.println("\nWord: " + new String(displayArray));
            System.out.println(HANGMAN_STAGES[9 - lives]);
            System.out.print("Incorrect guesses: ");
            for (char c : incorrectGuesses) {
                System.out.print(c + " ");
            }
            System.out.println("\nLives left: " + lives);
            System.out.print("Guess a letter (or type 'exit' to quit): ");

            String input = scanner.nextLine().toLowerCase();
            if (input.equals("exit")) {
                System.out.println("Exiting the game. Goodbye!");
                return;
            }
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Invalid input. Please enter a single letter.");
                System.out.println("------------------------------------------------------------");
                continue;
            }
            char guess = input.charAt(0);

            if (guessedLetters[guess - 'a']) {
                System.out.println("\n---------------------------------");
                System.out.println("You already guessed that letter!");
                System.out.println("---------------------------------");
                continue;
            }

            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (wordArray[i] == guess) {
                    displayArray[i] = guess;
                    correctGuess = true;
                }
            }

            if (correctGuess) {
                guessedLetters[guess - 'a'] = true;
            } else {
                incorrectGuesses.add(guess);
                guessedLetters[guess - 'a'] = true;
                lives--;
            }

            if (new String(displayArray).equals(word)) {
                System.out.println("\n\nCongratulations! The word was indeed: " + "\"" + word + "\"" + " !!!");
                System.out.println("  _______");
                System.out.println(" |/      ");
                System.out.println(" |      ");
                System.out.println(" |      ");
                System.out.println(" |       O");
                System.out.println(" |      \\|/");
                System.out.println(" |       |");
                System.out.println(" |      / \\");
                System.out.println(" |    I'm free!!!");
                System.out.println("_|___");


                return;
                
            }
            
        }

        System.out.println("Game over! The word was: " + word);
        System.out.println(HANGMAN_STAGES[9]);
        
    }
}
