public class Visualization_of_hangman {
    private static final String[] level_hard = {
            //0 ошибок
            """
                -----
                |   |
                    |
                    |
                    |
                    |
                ======
            """,
            //1 ошибка
            """
                -----
                |   |
                0   |
                    |
                    |
                    |
                ======
            """,
            //2
            """
                -----
                |   |
                0   |
                |   |
                    |
                    |
                ======
            """,
            //3
            """
                -----
                |   |
                0   |
               /|\\  |
                    |
                    |
                ======
            """,
            //4
            """
                -----
                |   |
                0   |
               /|\\  |
                 \\  |
                    |
                ======
            """,
            //5
            """
                -----
                |   |
                0   |
               /|\\  |
               / \\  |
                    |
                ======
            """,

    };
    private static final String[] level_medium = {
            //0 ошибок
            """
                -----
                |   |
                    |
                    |
                    |
                    |
                    |
                ======
            """,
            //1 ошибка
            """
                -----
                |   |
                0   |
                    |
                    |
                    |
                    |
                ======
            """,
            //2
            """
                -----
                |   |
                0   |
                |   |
                    |
                    |
                    |
                ======
            """,
            //3
            """
                -----
                |   |
                0   |
                |\\  |
                    |
                    |
                    |
                ======
            """,
            //4
            """
                -----
                |   |
                0   |
               /|\\  |
                    |
                    |
                    |
                ======
            """,
            //5
            """
                -----
                |   |
                0   |
               /|\\  |
                |   |
                    |
                    |
                ======
            """,
            //6
            """
                -----
                |   |
                0   |
               /|\\  |
                |   |
                 \\  |
                    |
                ======
            """,
            //7
            """
                -----
                |   |
                0   |
               /|\\  |
                |   |
               / \\  |
                    |
                ======
            """
    };

    private static final String[] level_easy = {
            //0 ошибок
            """
               -----
               |   |
                   |
                   |
                   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,
            //1
            """
               -----
               |   |
               0   |
                   |
                   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,
            //2
            """
               -----
               |   |
               0   |
               |   |
                   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,

            //3
            """
               -----
               |   |
               0   |
               |   |
               |   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,
            //4
            """
               -----
               |   |
               0   |
              /|   |
               |   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,

            //5
            """
               -----
               |   |
               0   |
              /|\\  |
                   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,

            //6
            """
               -----
               |   |
               0   |
              /|\\  |
               |   |
                   |
                   |
                   |
                   |
                   |
            =========
            """,
            //7
            """
               -----
               |   |
               0   |
              /|\\  |
               |   |
                \\  |
                   |
                   |
                   |
                   |
            =========
            """,
            //8
            """
               -----
               |   |
               0   |
              /|\\  |
               |   |
              / \\  |
                   |
                   |
                   |
                   |
            =========
            """,
            //9
            """
               -----
               |   |
               0   |
              /|\\  |
               |   |
              / \\  |
             /     |
                   |
                   |
                   |
            =========
            """,
            //10
            """
               -----
               |   |
               0   |
              /|\\  |
               |   |
              / \\  |
             /   \\ |
                   |
                   |
                   |
            =========
            """
    };

    public static String get_visualization(Difficulty_levels_of_the_game level, int counter_mistake) {
        String[] hangman_levels;
        if (level == Difficulty_levels_of_the_game.Easy) {
            hangman_levels = level_easy;
        }
        else if (level == Difficulty_levels_of_the_game.Medium) {
            hangman_levels = level_medium;
        }
        else {
            hangman_levels = level_hard;
        }
        if (counter_mistake < 0) {
            return hangman_levels[0];
        }
        if (counter_mistake >= hangman_levels.length) {
            return hangman_levels[hangman_levels.length - 1];
        }
        return hangman_levels[counter_mistake];
    }
    //хочу знать сколько максимально ошибок есть
    public static int get_counter_maxi_mistakes(Difficulty_levels_of_the_game level) {
        if (level == Difficulty_levels_of_the_game.Easy) {
            return level_easy.length - 1; //размер масива 11 (уровней) значит ошибок максимум 10
        }
        else if (level == Difficulty_levels_of_the_game.Medium) {
            return level_medium.length - 1;
        }
        else {
            return level_hard.length - 1;
        }
    }


    public static int get_counter_remaining_attempts(Difficulty_levels_of_the_game level, int counter_mistake) {
        int maxi_mistakes = get_counter_maxi_mistakes(level);
        int remaining_attempts = maxi_mistakes - counter_mistake;
        return remaining_attempts;
    }

}
