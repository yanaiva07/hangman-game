
import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private final Dictionary dictionary;


    public Game() {
        this.scanner = new Scanner(System.in);
        this.dictionary = new Dictionary();

    }

    public void start_game() {
        boolean play_again = true;
        while (play_again) {
            Word_category category = select_category();
            Difficulty_levels_of_the_game level = select_level();


            Dictionary.Word_with_hint word_with_hint = dictionary.get_word_with_hint(category, level);
            String secret_word = word_with_hint.get_word();
            String hint = word_with_hint.get_hint();

            Game_process game = new Game_process(secret_word, category, level, hint);

            play_game(game);
            print_res_game(game);
            play_again = ask_user_play_again();
        }
        scanner.close();
    }

    private Word_category select_category() {
        System.out.println("ВЫБЕРИТЕ КАТЕГОРИЮ");
        Word_category[] categories = Word_category.values();
        for (int i = 0; i < categories.length; ++i) {
            System.out.println(String.format("%d. %s", i + 1, categories[i].get_category_name()));
        }
        System.out.println(String.format("%d. Случайная категория%n", categories.length + 1));
        while (true) {
            System.out.println("Ваш выбор (1-" + (categories.length + 1) + "): ");
            String input = scanner.nextLine().trim();
            try {

                int choice = Integer.parseInt(input);


                if (choice >= 1 && choice <= categories.length) {

                    return categories[choice - 1];
                }
                //проверка если выбрана случайная категория
                else if (choice == categories.length + 1) {
                    return Word_category.get_random_category();
                }
                //если число вне допустимого диапазона
                else {
                    System.out.println("Введите число от 1 до " + (categories.length + 1));
                }
            }

            catch (NumberFormatException e) {
                System.out.println("Введите число!");
            }
        }
    }

    private Difficulty_levels_of_the_game select_level() {
        System.out.println("ВЫБЕРИТЕ СЛОЖНОСТЬ");
        Difficulty_levels_of_the_game[] level = Difficulty_levels_of_the_game.values();
        for (int i = 0; i < level.length; i++) {
            System.out.println(String.format("%d. %s (%d попыток)", i + 1,  level[i].get_name_of_level(), level[i].get_max_attempts()));
        }
        System.out.printf("%d. Случайная сложность%n", level.length + 1);

        while (true) {
            System.out.print("Ваш выбор (1-" + (level.length + 1) + "): ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= level.length) {
                    return level[choice - 1];
                } else if (choice == level.length + 1) {
                    return Difficulty_levels_of_the_game.get_random_level();
                } else {
                    System.out.println("Введите число от 1 до " + (level.length + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            }
        }
    }

    private void play_game(Game_process game) {
        System.out.println("\nПОГНАЛИ ИГРАТЬ");

        System.out.println("КАТЕГОРИЯ: " + game.get_category().get_category_name());
        System.out.println("СЛОЖНОСТЬ: " + game.get_level().get_name_of_level());
        System.out.println("ДЛИНА СЛОВА: " + game.get_secret_word().length());

        //вывод приглашения для пользователя
        System.out.print("Нажмите Enter чтобы начать...");
        scanner.nextLine();


        while (!game.is_game_over()) {


            //отображение текущего состояния игры
            game_status(game);

            if (!game.is_hint_used()) {
                System.out.println("\nВы можете запросить подсказку, введя '?'");
            }

            //получение буквы от игрока
            char user_letter = get_user_guess(game);
            if (user_letter == '?') {
                if (!game.is_hint_used()) {
                    String hint = game.use_hint();
                    System.out.println("\n".repeat(3));
                    System.out.println("ПОДСКАЗКА: " + hint);
                    System.out.println("Осталось попыток: " + game.get_remaining_attempts());
                } else {
                    System.out.println("\nВы уже использовали подсказку!");
                }

                if (!game.is_game_over()) {
                    System.out.print("\nНажмите Enter для продолжения...");
                    scanner.nextLine();
                }
                continue;
            }

            //обработка попытки угадать букву
            boolean correct = game.guess_letter(user_letter);
            System.out.println("\n".repeat(3));
            game_status(game);

            //отображение результата
            if (correct) {
                System.out.println("\nПравильно! Буква '" + user_letter + "' есть в слове!");
            }else {
                System.out.println("\nЭХХХХХ, НЕВЕРНО! Буквы '" + user_letter + "' нет в слове.");
                System.out.println("Осталось попыток: " + game.get_remaining_attempts());
            }

            //если игра еще не окончена, то хочу пауза перед следующим ходом
            if (!game.is_game_over()) {
                System.out.print("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }


        System.out.println("\n".repeat(3));
        game_status(game);

    }
    //состояние игры
    private void game_status(Game_process game) {
        System.out.println(game.get_visualization_of_hangman());

        System.out.println("\n Слово: " + game.get_guessed_word());

        System.out.println("\n ИНФОРМАЦИЯ:");
        System.out.println("   Категория: " + game.get_category().get_category_name());
        System.out.println("   Сложность: " + game.get_level().get_name_of_level());
        System.out.println("   Ошибок: " + game.get_mistake_attempts_counter() +
                "/" + Visualization_of_hangman.get_counter_maxi_mistakes(game.get_level()));
        System.out.println("   Осталось попыток: " + game.get_remaining_attempts());

        if (game.is_hint_used()) {
            System.out.println("   Подсказка: использована");
        } else {
            System.out.println("   Подсказка: доступна (введите '?' для использования)");
        }

        char[] mistake_letters = game.get_mistake_letters();
        if (mistake_letters.length > 0) {
            System.out.print("   Неправильные буквы: ");
            for (char c : mistake_letters) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private char get_user_guess(Game_process game) {
        while (true) {
            System.out.println("ВВЕДИТЕ БУКВУ (или '?' для подсказки)");
            String input = scanner.nextLine().trim().toUpperCase();
            //проверка на пустой ввод
            if (input.isEmpty()) {
                System.out.println(" Пожалуйста, введите букву!");
                continue;
            }
            if (input.equals("?")) {
                return '?'; //специальный символ для подсказки
            }
            //проверка что ввели только одну букву
            if (input.length() > 1) {
                System.out.println(" Введите только одну букву!");
                continue;
            }

            //получение первого символа из строки
            char letter = input.charAt(0);

            if (!Character.isLetter(letter)) {
                System.out.println(" Введите букву!");
                continue;
            }

            if (!is_russian_letter(letter)) {
                System.out.println(" Введите русскую букву (А-Я)!");
                continue;
            }

            if (game.is_letter_already_guessed(letter)) {
                if (game.is_letter_correctly_guessed(letter)) {
                    System.out.println("ДРУГ! Буква '" + letter + "' уже была и она ПРАВИЛЬНАЯ!");
                } else if (game.is_letter_wrongly_guessed(letter)) {
                    System.out.println("ДРУГ! Буква '" + letter + "' уже была и она НЕПРАВИЛЬНАЯ!");
                } else {
                    System.out.println("ДРУГ! Буква '" + letter + "' уже была!");
                }
                System.out.println("Попробуй другую букву!");
                continue;
            }
            return letter;
        }
    }
    //хочу проверить что ввели русскую букву
    private boolean is_russian_letter(char c) {
        char upper_с = Character.toUpperCase(c);
        return (upper_с >= 'А' && upper_с <= 'Я') || upper_с == 'Ё';
    }



    private void print_res_game(Game_process game) {
        //хочу знать победил ли игрок
        if (game.is_won()) {
            System.out.println(" П О Б Е Д А !");
            System.out.println("Вы угадали слово: " + game.get_secret_word());
            System.out.println("Количество ошибок: " + game.get_mistake_attempts_counter());
        } else {
            System.out.println("ДА КАК ТАК-ТО!!!!! А Я В ТЕБЯ ВЕРИЛА.... грусть тоска печаль");
            System.out.println("П О Р А Ж Е Н И Е");
            System.out.println("Загаданное слово: " + game.get_secret_word());
        }

    }

    private boolean ask_user_play_again() {
        while (true) {  //бесконечный цикл, пока не получу приавльный ответ
            System.out.print("\nХотите сыграть еще раз? (да/нет): ");
            String input = scanner.nextLine().trim().toLowerCase();

            //проверка на пустой ввод
            if (input.isEmpty()) {
                System.out.println("Введите да или нет!");
                continue;
            }

            //проверка на "да"
            if (input.equals("да") || input.equals("yes") ) {
                return true;
            }

            //проверка на "нет"
            if (input.equals("нет") || input.equals("no")) {
                return false;
            }
            System.out.println("Пожалуйста, введите 'да' или 'нет'!");
        }
    }
}
