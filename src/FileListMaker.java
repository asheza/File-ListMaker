import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileListMaker {
        static ArrayList<String> list = new ArrayList<>();
        static Scanner in = new Scanner(System.in);
        static boolean needsToBeSaved = false;
        static String currentFileName = null;

        public static void main(String[] args)
        {
            final String menu ="A - Add D - Delete I - Insert V - View M - Move O - Open S - Save C - Clear Q - Quit";
            boolean done = false;
            String cmd = "";

            do
            {
                displayList();

                cmd = SafeInput.getRegExString(in,menu,"[AaDdIiVvMmOoSsCcQq]");
                cmd = cmd.toUpperCase();

                System.out.println("");

                switch(cmd)
                {
                    case "A":
                        System.out.println("The command you selected is A. \n");
                        addItem();
                        break;

                    case "D":
                        System.out.println("The command you selected is D.\n");
                        deleteItem();
                        break;

                    case "I":
                        System.out.println("The command you selected is I.\n");
                        insertItem();
                        break;

                    case "V":
                        System.out.println("The command you selected is V.\n");
                        viewList();
                        break;

                    case "M":
                        System.out.println("The command you selected is M.\n");
                        moveItem();
                        break;

                    case "O":
                        if (needsToBeSaved) {
                            boolean saveChanges = SafeInput.getYNConfirm(in, "You have unsaved changes. Do you" +
                                    " want to save before opening a new list?");
                            if (saveChanges)
                            {
                                saveList();
                            }
                        }
                        openList();
                        break;

                    case "S":
                        System.out.println("The command you selected is S.\n");
                        saveList();
                        break;

                    case "C":
                        System.out.println("The command you selected is C.\n");
                        clearList();
                        break;

                    case "Q":

                        System.out.println("The command you selected is Q.\n");

                        boolean finalAnswer = SafeInput.getYNConfirm(in,"Do you wish to save the file?");

                        if (finalAnswer)
                        {
                            saveList();
                            System.out.println("Thank you for your input. Goodbye.");
                            System.exit(0);
                        }

                        if (!finalAnswer)
                        {
                            System.out.println("Thank you for your input. Goodbye.");
                            System.exit(0);
                        }
                        break;
                }

            }while(!done);
        }


    //Helper Method #1 - Displaying the List.
        private static void displayList()
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

            if(list.size() != 0)
            {
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%3d%25s\n", i + 1, list.get(i));
                }
            }
            else
            {
                System.out.println("***          List is empty. :(          ***");
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }

        //Helper Method #2 - Viewing the List.
        private static void viewList()
        {
            for(int x = 0; x < list.size(); x++)
            {
                System.out.println(list.get(x));
            }
        }

        //Helper Method #3 - Adding Item to the List.
        private static void addItem()
        {
            String choice = SafeInput.getNonZeroLenString(in,"Add your favorite Anime to the list ");
            list.add(choice + "\n");
            needsToBeSaved = true;
        }

        //Helper Method #4 - Deleting Item from the List.
        private static void deleteItem()
        {
            int removeChoice = SafeInput.getRangedInt(in,"Select number from the list you would like to remove ",
                    1,list.size());
            list.remove(removeChoice - 1); //the array is zero-based so subtract 1.
            needsToBeSaved = true;
        }

        //Helper Method #5 - Inserting Item into the List.
        private static void insertItem()
        {
            int indexChoice = SafeInput.getRangedInt(in,"At which number would you like to insert your object? ",
                    1,list.size());

            String insertChoice = SafeInput.getNonZeroLenString(in,"Add your favorite Anime to the list ");

            list.add(indexChoice - 1,insertChoice + "\n");
            needsToBeSaved = true;
        }

        //Helper Method #6 - Move an item in the List.
        private static void moveItem()
        {
            int fromIndex = SafeInput.getRangedInt(in, "Enter the index of the item to move: ", 1, list.size()) - 1;
            int toIndex = SafeInput.getRangedInt(in, "Enter the index where to move the item: ", 1, list.size()) - 1;

            String item = list.remove(fromIndex);
            list.add(toIndex, item);
            needsToBeSaved = true;
        }

        // Helper Method #7 - Opening a List File from Disk.
        private static void openList()
        {
            String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name to open (include .txt extension): ");
            File file = new File(fileName);
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                list.clear();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    list.add(line);
                }
                    needsToBeSaved = false;
                    currentFileName = fileName;
                    System.out.println("List loaded from " + fileName);
                }
                catch (IOException e)
                {
                    System.out.println("Error reading file: " + e.getMessage());
                }
        }

    // Helper Method #8 - Saving the Current List to Disk.
    private static void saveList() {
        if (currentFileName == null) {
            currentFileName = SafeInput.getNonZeroLenString(in, "Enter the file name to save" +
                    " the list (include .txt extension): ");
        }
        File file = new File(currentFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
            needsToBeSaved = false;
            System.out.println("List saved to " + currentFileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Helper Method #9 - Clearing the List.
    private static void clearList() {
        list.clear();
        needsToBeSaved = true;
    }
}