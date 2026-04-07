import java.util.Random;

public enum Word_category {
    Animal("Животные"),
    Food("Еда"),
    Sports("Спорт");

    private static Random random = new Random();

    private final String category_name;

    private Word_category(String category_name) {
        this.category_name = category_name;
    }

    public String get_category_name() {
        return category_name;
    }

    public static Word_category get_random_category() {
        Word_category[] categories = values();
        int random_index = random.nextInt(categories.length);
        return categories[random_index];
    }

}
