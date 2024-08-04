import java.util.Scanner;

public class SafeInput
{
    //Example #1 getNonZeroLenString

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": ");
            // don't edit prompt (remember this is a template!)
            // prompts are added from the MAIN file.
            retString = pipe.nextLine(); //"in" variable is being stored in "pipe".

        }while(retString.length() == 0); //meaning continue loop if there is NOTHING inserted

        return retString; //this is not declared void so you need a return statement.
    }

    //Example #2 getInt

    public static int getInt(Scanner pipe, String prompt)
    {
        int value = 0;
        String trash = "";
        boolean done = false;
        do {

            System.out.println("\n" + prompt);

            if (pipe.hasNextInt())
            {
                value = pipe.nextInt();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter in an int, not: " + trash);
            }
        } while (!done);

        return value;
    }

    //Example #3 getDouble

    public static double getDouble(Scanner pipe, String prompt)
    {
        double value2 = 0.0;
        String trash = "";
        boolean done = false;
        do {

            System.out.println("\n" + prompt);

            if (pipe.hasNextDouble())
            {
                value2 = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter in an double, not: " + trash);
            }
        } while (!done);

        return value2;
    }

    //Example #4 getRangedInt

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int rangeValue = 0;
        String trash = "";
        boolean done = false;
        do {

            System.out.println(prompt + low + " - " + high + ": ");

            if (pipe.hasNextInt())
            {
                rangeValue = pipe.nextInt();
                pipe.nextLine();
                if(rangeValue >= low && rangeValue <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a value in range: " + low + " - " + high + " not: " + rangeValue);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter in an int, not: " + trash);
            }
        } while (!done);

        return rangeValue;
    }

    //Example #5 getRangedDouble

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double rangeDouble = 0.0;
        String trash = "";
        boolean done = false;
        do {

            System.out.println(prompt + low + " - " + high + ": ");

            if (pipe.hasNextDouble())
            {
                rangeDouble = pipe.nextDouble();
                pipe.nextLine();
                if(rangeDouble >= low && rangeDouble <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("\n" + "You must enter a value in range: "
                            + low + " - " + high + " not: " + rangeDouble);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\n" + "You must enter in double, not: " + trash);
            }
        } while (!done);

        return rangeDouble;
    }

    //Example #6 getYNConfirm

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String trash = "";
        String continueYN = "";
        boolean done = true;

        do
        {
            System.out.print(prompt + " (Y/N): ");

            if (pipe.hasNextLine())
            {
                continueYN = pipe.nextLine();

                if (continueYN.equalsIgnoreCase("N")) {
                    System.out.println("You have said 'no'. You're done.");
                    return false;
                } else if (continueYN.equalsIgnoreCase("Y")) {
                    System.out.println("Continue on.");
                    return true;
                }
                else
                {
                    trash = pipe.nextLine();
                    System.out.println("You must enter in valid input 'Y' or 'N', not: " + trash);
                }
            }
        }while (done);

        return done;

    }

    //Example #7 getRegExString

    public static String getRegExString(Scanner pipe, String prompt, String regExPattern)
    {
        String value = "";
        boolean gotAValue = false;

        do
        {
            System.out.print(prompt + ": ");
            value = pipe.nextLine();

            if(value.matches(regExPattern))
            {
                gotAValue = true;
            }
            else
            {
                System.out.println("\nInvalid input: " + value);
            }
        }while(!gotAValue);

        return value;
    }

    //Example #8 PrettyHeader :)

    public static void prettyHeader (String msg)
    {
        int totalWidth = 60;
        int borderWidth = 3;

        for (int a = 0; a < totalWidth; a++) {
            System.out.print("*");
        }
        System.out.println();

        int msgLength = msg.length();
        int totalPad = totalWidth - (borderWidth * 2) - msgLength;
        int leftPad = totalPad / 2;
        int rightPad = totalPad - leftPad;

        for (int a = 0; a < borderWidth; a++)
        {
            System.out.print("*");
        }

        for (int a = 0; a < leftPad; a++)
        {
            System.out.print(" ");
        }

        System.out.print(msg);

        for (int a = 0; a < rightPad; a++)
        {
            System.out.print(" ");
        }

        for (int a = 0; a < borderWidth; a++)
        {
            System.out.print("*");
        }
        System.out.println();

        for (int a = 0; a < totalWidth; a++)
        {
            System.out.print("*");
        }
        System.out.println();
    }
}
