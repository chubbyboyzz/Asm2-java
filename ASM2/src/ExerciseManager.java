import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ExerciseManager {
    private List<Exercise> exercises;
    private Scanner scanner;
    private static final String FILE_NAME = "exercises.txt";

    public ExerciseManager() {
        exercises = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadExercisesFromFile();
    }

    public void addExercise() {
        String code, name, muscleGroup;
        int duration = 0, difficulty = 0;

        // Input validation loop for exercise code
        while (true) {
            System.out.println("Enter exercise code: ");
            code = scanner.nextLine().trim();
            if (code.isEmpty()) {
                System.out.println("Exercise code cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        // Input validation loop for exercise name
        while (true) {
            System.out.println("Enter exercise name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Exercise name cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        // Input validation loop for muscle group
        while (true) {
            System.out.println("Enter muscle group: ");
            muscleGroup = scanner.nextLine().trim();
            if (muscleGroup.isEmpty()) {
                System.out.println("Muscle group cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        // Input validation loop for duration
        while (true) {
            System.out.println("Enter duration (in minutes): ");
            String durationInput = scanner.nextLine().trim();
            try {
                duration = Integer.parseInt(durationInput);
                if (duration <= 0) {
                    System.out.println("Duration must be a positive number. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for duration.");
            }
        }

        // Input validation loop for difficulty
        while (true) {
            System.out.println("Enter difficulty (1-10): ");
            String difficultyInput = scanner.nextLine().trim();
            try {
                difficulty = Integer.parseInt(difficultyInput);
                if (difficulty < 1 || difficulty > 10) {
                    System.out.println("Difficulty must be between 1 and 10. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for difficulty.");
            }
        }

        Exercise exercise = new Exercise(code, name, muscleGroup, duration, difficulty);
        exercises.add(exercise);
        saveExercisesToFile();
        System.out.println("Exercise added successfully!");
    }

    public void removeExercise() {
        System.out.println("Enter exercise code to remove: ");
        String code = scanner.nextLine();
        exercises.removeIf(exercise -> exercise.getCode().equals(code));
        saveExercisesToFile();
        System.out.println("Exercise removed successfully!");
    }

    public void sortExercisesByDifficulty() {
        exercises.sort(Comparator.comparingInt(Exercise::getDifficulty));
        System.out.println("Exercises sorted by difficulty in ascending order:");
        exercises.forEach(System.out::println);
    }

    public void findExerciseByCodeOrName() {
        System.out.println("Enter exercise code or name to find: ");
        String input = scanner.nextLine();
        boolean found = false;
        for (Exercise exercise : exercises) {
            if (exercise.getCode().equals(input) || exercise.getName().equalsIgnoreCase(input)) {
                System.out.println(exercise);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Exercise not found.");
        }
    }

    public void findExercisesByMinimumDifficulty() {
        System.out.println("Enter minimum difficulty: ");
        int minDifficulty = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        exercises.stream()
                .filter(exercise -> exercise.getDifficulty() >= minDifficulty)
                .forEach(System.out::println);
    }

    private void saveExercisesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Exercise exercise : exercises) {
                writer.write(exercise.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving exercises to file: " + e.getMessage());
        }
    }

    private void loadExercisesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Exercise exercise = Exercise.fromFileString(line);
                exercises.add(exercise);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading exercises from file: " + e.getMessage());
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nExercise Management System");
            System.out.println("1. Add Exercise");
            System.out.println("2. Remove Exercise");
            System.out.println("3. List Exercise Minimum hard to hard rank");
            System.out.println("4. Find Exercise by Code or Name");
            System.out.println("5. Find Exercises by Minimum Difficulty");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addExercise();
                case 2 -> removeExercise();
                case 3 -> sortExercisesByDifficulty();
                case 4 -> findExerciseByCodeOrName();
                case 5 -> findExercisesByMinimumDifficulty();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ExerciseManager manager = new ExerciseManager();
        manager.displayMenu();
    }
}
