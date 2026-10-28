# CalorieLog 🍎

A beginner-friendly Java console application to record meals and track daily calorie totals.

## Features

- Add a meal with its calorie count and date
- View all recorded meals
- Calculate the total calories recorded for a selected day
- Delete a meal
- Input validation for calories, dates, and menu choices

## Built With

- Java
- `ArrayList`
- Classes and objects
- Methods
- Loops and `switch`
- `Scanner` for user input
- `LocalDate` for dates

## Requirements

- Java 8 or later

## How to Run

1. Download and unzip this project.
2. Open a terminal in the `CalorieLog` folder.
3. Compile the Java file:

   ```bash
   javac -d out src/CalorieLog.java
   ```

4. Run the program:

   ```bash
   java -cp out CalorieLog
   ```

## Example

```text
================================
          CALORIE LOG
    Track meals and calories
================================
--------------- MENU ---------------
1. Add a meal
2. View all meals
3. View calories for a day
4. Delete a meal
5. Exit
------------------------------------
Choose an option:
```

## Important Note

This starter project stores meals in memory while the program is running. The meal list is cleared when you exit the application. It is a learning project, not a medical or nutrition tool.

## Future Improvements

- Save meals to a file
- Add meal categories such as breakfast, lunch, dinner, and snacks
- Show weekly calorie summaries
- Add a graphical user interface
