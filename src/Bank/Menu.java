package Bank;

import java.util.Scanner;



/**
 * Represents a console-based menu for user interaction.
 * Provides a title and a list of menu items for the user to choose from.
 */
public class Menu {
    private String items[];
    private String title;
    private Scanner input;

    /**
     * Constructs a new Menu with the specified title and list of menu items.
     *
     * @param title The title of the menu.
     * @param data  An array containing the list of menu items.
     */
    public Menu(String title, String data[]) {
        this.title = title;
        this.items = data;
        this.input = new Scanner(System.in);
    }

    /**
     * Displays the menu and prompts the user for their selection.
     * Returns the user's selection as an integer.
     *
     * @return An integer representing the user's menu selection.
     */
    public int getUserChoice() {
        display();
        System.out.print("Enter Selection: ");
        int value = input.nextInt();
        return value;
    }

    /**
     * Displays the menu's title and list of menu items to the console.
     */
    private void display() {
        System.out.println(title);
        for (int i = 0; i < title.length(); i++) {
            System.out.print("+");
        }
        System.out.println();
        System.out.println(items[0]);

        for (int opt = 1; opt < items.length; opt++) {
            System.out.println(opt + ". " + items[opt]);
        }
        System.out.println();
    }
}

