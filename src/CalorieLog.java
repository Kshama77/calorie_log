import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalorieLog {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Meal> meals = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("          CALORIE LOG");
        System.out.println("    Track meals and calories");
        System.out.println("================================");

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addMeal();
                    break;
                case "2":
                    viewMeals();
                    break;
                case "3":
                    showDailyTotal();
                    break;
                case "4":
                    deleteMeal();
                    break;
                case "5":
                    running = false;
                    System.out.println("Thanks for using CalorieLog. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("--------------- MENU ---------------");
        System.out.println("1. Add a meal");
        System.out.println("2. View all meals");
        System.out.println("3. View calories for a day");
        System.out.println("4. Delete a meal");
        System.out.println("5. Exit");
        System.out.println("------------------------------------");
    }

    private static void addMeal() {
        System.out.print("Meal name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Meal name cannot be empty.");
            return;
        }

        int calories = readPositiveInteger("Calories: ");
        if (calories < 0) {
            return;
        }

        LocalDate date = readDate("Date (YYYY-MM-DD), or press Enter for today: ");
        if (date == null) {
            return;
        }

        meals.add(new Meal(name, calories, date));
        System.out.println("Meal added successfully!");
    }

    private static int readPositiveInteger(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                System.out.println("Calories must be a positive whole number.");
                return -1;
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid whole number.");
            return -1;
        }
    }

    private static LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Please use YYYY-MM-DD.");
            return null;
        }
    }

    private static void viewMeals() {
        if (meals.isEmpty()) {
            System.out.println("No meals recorded yet. Add your first meal!");
            return;
        }

        System.out.println("\n--------------- YOUR MEALS ---------------");
        System.out.printf("%-5s %-22s %-12s %-12s%n", "No.", "Meal", "Calories", "Date");
        System.out.println("------------------------------------------");
        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            System.out.printf("%-5d %-22s %-12d %-12s%n",
                    i + 1, shorten(meal.getName(), 21), meal.getCalories(), meal.getDate());
        }
    }

    private static void showDailyTotal() {
        LocalDate date = readDate("Date (YYYY-MM-DD), or press Enter for today: ");
        if (date == null) {
            return;
        }

        int total = 0;
        int count = 0;
        for (Meal meal : meals) {
            if (meal.getDate().equals(date)) {
                total += meal.getCalories();
                count++;
            }
        }

        System.out.println("Date: " + date);
        System.out.println("Meals recorded: " + count);
        System.out.println("Total calories: " + total + " kcal");
    }

    private static void deleteMeal() {
        if (meals.isEmpty()) {
            System.out.println("There are no meals to delete.");
            return;
        }

        viewMeals();
        System.out.print("Enter the meal number to delete: ");
        try {
            int number = Integer.parseInt(scanner.nextLine().trim());
            if (number < 1 || number > meals.size()) {
                System.out.println("That meal number does not exist.");
                return;
            }
            Meal removed = meals.remove(number - 1);
            System.out.println("Deleted meal: " + removed.getName());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static String shorten(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }

    private static class Meal {
        private final String name;
        private final int calories;
        private final LocalDate date;

        public Meal(String name, int calories, LocalDate date) {
            this.name = name;
            this.calories = calories;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public int getCalories() {
            return calories;
        }

        public LocalDate getDate() {
            return date;
        }
    }
}
