import java.util.Random;

public enum Difficulty_levels_of_the_game {
    Easy(10, "Легкий"),
    Medium(7, "Средний"),
    Hard(5, "Сложный");

    private final String name_of_level;
    private final int max_attempts;

    private static Random random = new Random();


    Difficulty_levels_of_the_game(int max_attempts, String name_of_level) {
        this.max_attempts = max_attempts;
        this.name_of_level = name_of_level;
    }

    public int get_max_attempts() {
        return max_attempts;
    }

    public String get_name_of_level() {
        return name_of_level;
    }

    public static Difficulty_levels_of_the_game get_random_level() {
        Difficulty_levels_of_the_game[] levels = values();
        int random_index = random.nextInt(levels.length);
        return levels[random_index];
    }


}

