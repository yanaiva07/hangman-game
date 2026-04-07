

public class Game_process {

    private String secret_word;
    private final Word_category category;
    private final Difficulty_levels_of_the_game level;

    //массив угаданных букв
    private char[] guessed_word; //массив чтобы видеть порядок угаданных букв в слове
    //который такой "______"
    private char[] guessed_letters; //массив чтобы видеть какие буквы уже угаданы

    private char[] mistake_letters; //массив с буквами которых в слове нету


    private int mistake_attempts_counter; //считаем количество неверных попыток

    private int mistake_counter; //считаем количество только уникальных НЕугаданных букв
    private int guessed_counter; //считаем количество только уникальных угаданных букв

    //состояние игры
    private boolean won;
    private boolean game_over;


    private String hint;
    private boolean hint_used; //флаг об использовании подсказок

    public Game_process(String secret_word, Word_category category, Difficulty_levels_of_the_game level, String hint) {
        this.secret_word = secret_word.toUpperCase();
        this.category = category;
        this.level = level;

        this.hint = hint;
        this.hint_used = false;

        fill_in_guessed_letters();
        fill_in_letter_array();

        this.mistake_attempts_counter = 0;
        this.mistake_counter = 0;
        this.guessed_counter = 0;

        this.won = false;
        this.game_over = false;

    }


    public String use_hint() {
        if (!hint_used) {
            hint_used = true;
            return hint;
        }
        return "Подсказка уже была использована!";
    }


    public boolean is_hint_used() {
        return hint_used;
    }


    private void fill_in_guessed_letters() {
        this.guessed_word = new char[secret_word.length()];
        for (int i = 0; i < secret_word.length(); i++) {
            this.guessed_word[i] = '_';
        }
    }

    private void fill_in_letter_array() {
        this.guessed_letters = new char[33];   //создаем массив для ПРАВИЛЬНЫХ букв
        this.mistake_letters = new char[33];     //создаем массив для НЕПРАВИЛЬНЫХ букв
    }


    private boolean is_letter_in_array(char letter, char[] array, int count) {
        for (int i = 0; i < count; i++) {
            if (array[i] == letter) {
                return true;
            }
        }
        return false;
    }

    public boolean guess_letter(char letter) {

        if (game_over) {
            return false;
        }
        char upper_case_letter = Character.toUpperCase(letter);
        if (is_letter_in_array(upper_case_letter, guessed_letters, guessed_counter)
                || is_letter_in_array(upper_case_letter, mistake_letters, mistake_counter)) {
            return false;
        }

        boolean correct_letter = false; //флаг для правильности угадывания
        for (int i = 0; i < secret_word.length(); ++i) {
            if (secret_word.charAt(i) == upper_case_letter) {
                guessed_word[i] = upper_case_letter;
                correct_letter = true;
            }
        }
        if (correct_letter) {
            guessed_letters[guessed_counter] = upper_case_letter;
            guessed_counter++;
            check_the_victory();
        }
        else {
            mistake_letters[mistake_counter] = upper_case_letter;
            mistake_counter++;
            mistake_attempts_counter++;
            check_the_lose();
        }
        return correct_letter;
    }
    private void check_the_victory() {
        for (char symb : guessed_word) {
            if (symb == '_') {
                return;
            }
        }
        game_over = true;
        won = true;
    }
    private void check_the_lose() {

        int maxi_mistakes = Visualization_of_hangman.get_counter_maxi_mistakes(level);
        //если попыток было больше чем их максимальоне число, то пользователь лох
        if (mistake_attempts_counter >= maxi_mistakes) {
            game_over = true;
            won = false;
        }
    }

    //теперь геттеры чтобы получать знаечния приватных полей
    public String get_secret_word() {
        return secret_word;
    }

    //для получения текущего уровня угаданного слова
    public String get_guessed_word() {
        StringBuilder result = new StringBuilder();

        for (char c : guessed_word) {
            result.append(c).append(' ');
        }

        return result.toString().trim();
    }

    public Word_category get_category() {
        return category;
    }

    public Difficulty_levels_of_the_game get_level() {
        return level;
    }

    public int get_mistake_attempts_counter() {
        return mistake_attempts_counter;
    }

    public int get_remaining_attempts() {
        return Visualization_of_hangman.get_counter_remaining_attempts(level, mistake_attempts_counter);
    }
    //возвращает копию массива не хочу чтобы был массив из 33 символомв
    public char[] get_mistake_letters() {

        char[] result = new char[mistake_counter];
        System.arraycopy(mistake_letters, 0, result, 0, mistake_counter);
        return result;
    }

    public boolean is_game_over() {
        return game_over;
    }

    public boolean is_won() {
        return won;
    }

    public String get_visualization_of_hangman() {
        return Visualization_of_hangman.get_visualization(level, mistake_attempts_counter);
    }

    public boolean is_letter_already_guessed(char letter) {
        char upper_letter = Character.toUpperCase(letter);

        //проверю в массиве с правильными буквами
        for (int i = 0; i < guessed_counter; i++) {
            if (guessed_letters[i] == upper_letter) {
                return true;
            }
        }

        //проверю в массиве с неправильными буквами
        for (int i = 0; i < mistake_counter; i++) {
            if (mistake_letters[i] == upper_letter) {
                return true;
            }
        }

        return false;
    }

    public boolean is_letter_correctly_guessed(char letter) {
        char upper_letter = Character.toUpperCase(letter);

        for (int i = 0; i < guessed_counter; i++) {
            if (guessed_letters[i] == upper_letter) {
                return true;
            }
        }

        return false;
    }

    public boolean is_letter_wrongly_guessed(char letter) {
        char upper_letter = Character.toUpperCase(letter);

        for (int i = 0; i < mistake_counter; i++) {
            if (mistake_letters[i] == upper_letter) {
                return true;
            }
        }

        return false;
    }
}



