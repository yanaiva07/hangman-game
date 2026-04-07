import java.util.Random;

public class Dictionary {
    private Random random = new Random();

    private String[] animals_easy = {
            "ЛЕВ", "КОТ", "ЗАЯЦ"
    };
    private String[] animals_easy_hints = {
            "Царь зверей",
            "Домашний питомец",
            "Грызун с длинными ушами"
    };
    private String[] animals_medium = {
            "ЗЕБРА", "ОЛЕНЬ", "ЖИРАФ"
    };
    private String[] animals_medium_hints = {
            "Полосатая лошадь из Африки",
            "Есть у деда Мороза",
            "Высокое животное с длинной шеей"
    };
    private String[] animals_hard = {
            "МЕДВЕДЬ", "НОСОРОГ", "БЕГЕМОТ"
    };
    private String[] animals_hard_hints = {
            "Любит поспать зимой",
            "Животное с рогом на носу",
            "Млекопитающее с большой пастью"
    };

    private String[] food_easy = {
            "СУП", "СУШИ", "ТОРТ"
    };
    private String[] food_easy_hints = {
            "Жидкое горячее блюдо",
            "Японское блюдо из риса",
            "Сладкий десерт на день рождения"
    };
    private String[] food_medium = {
            "ЛЕМОН", "ПИЦЦА", "ОМЛЕТ"
    };
    private String[] food_medium_hints = {
            "Кислый фрукт",
            "Итальянское блюдо",
            "Блюдо из яиц, жаренное на сковороде"
    };
    private String[] food_hard = {
            "КОЛБАСА", "ШОКОЛАД", "ПЕЛЬМЕНИ"
    };
    private String[] food_hard_hints = {
            "Мясной продукт",
            "Десерт из какао-бобов",
            "Тесто с мясной начинкой"
    };


    private String[] sports_easy = {
            "БЕГ", "БОКС", "ЙОГА"
    };
    private String[] sports_easy_hints = {
            "Кардио нагрузка",
            "Вид борьбы",
            "Гимнастика для спокойствия"
    };
    private String[] sports_medium = {
            "ДАРТС", "ГОЛЬФ", "ФУТБОЛ"
    };
    private String[] sports_medium_hints = {
            "Метание в круглую мишень",
            "Игра с клюшками и мячом",
            "КРИШТИАНУ РОНАЛДО SUUUUUUUUU"
    };
    private String[] sports_hard = {
            "ПЛАВАНИЕ", "БАСКЕТБОЛ", "ГИМНАСТИКА"
    };
    private String[] sports_hard_hints = {
            "Вид спорта в воде",
            "Игра с мячом и кольцом",
            "Акробатика"
    };
    public static class Word_with_hint {
        private String word;
        private String hint;

        public Word_with_hint(String word, String hint) {
            this.word = word;
            this.hint = hint;
        }

        public String get_word() {
            return word;
        }

        public String get_hint() {
            return hint;
        }
    }
    public Word_with_hint get_word_with_hint(Word_category category, Difficulty_levels_of_the_game level) {
        int index;
        String word = "";
        String hint = "";

        if (category == Word_category.Animal) {
            if (level == Difficulty_levels_of_the_game.Easy) {
                index = random.nextInt(animals_easy.length);
                word = animals_easy[index];
                hint = animals_easy_hints[index];
            }
            else if (level == Difficulty_levels_of_the_game.Medium) {
                index = random.nextInt(animals_medium.length);
                word = animals_medium[index];
                hint = animals_medium_hints[index];
            }
            else {
                index = random.nextInt(animals_hard.length);
                word = animals_hard[index];
                hint = animals_hard_hints[index];
            }
        }
        else if (category == Word_category.Food) {
            if (level == Difficulty_levels_of_the_game.Easy) {
                index = random.nextInt(food_easy.length);
                word = food_easy[index];
                hint = food_easy_hints[index];
            }
            else if (level == Difficulty_levels_of_the_game.Medium) {
                index = random.nextInt(food_medium.length);
                word = food_medium[index];
                hint = food_medium_hints[index];
            }
            else {
                index = random.nextInt(food_hard.length);
                word = food_hard[index];
                hint = food_hard_hints[index];
            }
        }
        else if (category == Word_category.Sports) {
            if (level == Difficulty_levels_of_the_game.Easy) {
                index = random.nextInt(sports_easy.length);
                word = sports_easy[index];
                hint = sports_easy_hints[index];
            }
            else if (level == Difficulty_levels_of_the_game.Medium) {
                index = random.nextInt(sports_medium.length);
                word = sports_medium[index];
                hint = sports_medium_hints[index];
            }
            else {
                index = random.nextInt(sports_hard.length);
                word = sports_hard[index];
                hint = sports_hard_hints[index];
            }
        }

        return new Word_with_hint(word, hint);
    }





}
