import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Application
{
    public static void main(String[] args)
    {
        final int size = 25;
        Robot [] arrRobot = new Robot[size];
        int count = ReadFromFile(arrRobot , "Robotscores.txt");
        Display(arrRobot , count );

        arrRobot = Arrays.copyOf(arrRobot , count);
        Arrays.sort(arrRobot);

        DisplayAfterSorting(arrRobot , count);
        serializeToFile(arrRobot , count);
        deserializeFromFile();

        //int count2 = ReadFromFile(arrRobot, "Invalid.txt");
    }

    public static int ReadFromFile(Robot[] arrRobot , String fileName)
    {
        int count = 0;

        try
        {
            FileReader file = new FileReader(fileName);
            Scanner input = new Scanner(file);
            while(input.hasNext())
            {
                String Line = input.nextLine();
                String[] info = Line.split("@");

                String Registration = info[0];
                String Name = info[1];
                int AgeGroup = Integer.parseInt(info[2]);

                if(Registration.startsWith("L"))
                {
                    int Distance = Integer.parseInt(info[3]);
                    int  Time  = Integer.parseInt(info[4]);
                    try
                    {
                        arrRobot[count] = new LineFollower(Registration , Name, AgeGroup , Distance , Time);
                        count++;
                    }catch(InvalidInputException e)
                    {
                        System.out.println(e.getMessage());
                    }

                }
                else if(Registration.startsWith("R"))
                {
                    int Collected = Integer.parseInt(info[3]);
                    int Penalty  = Integer.parseInt(info[4]);
                    try
                    {
                        arrRobot[count] = new RoboSoccer(Registration , Name, AgeGroup , Collected , Penalty);
                        count++;
                    }catch(InvalidInputException e)
                    {
                        System.out.println(e.getMessage());
                    }

                }
                else
                {
                    System.out.println("\n The Registration is invlid");
                }

            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("\nError while reading the file" );

        }

        return count;
    }

    public static void Display(Robot[] arrRobot , int count )
    {

        int totalCollected =0;
        int totalPenaliets = 0;

        System.out.println("\nLineFollower Robots");
        System.out.printf("%n%-10s %-10s %-10s %-10s %-10s %-10s" , "Registration" , "Name" , "Age" , "Distance" , "Time" , "Score");
        // System.out.println("\n");
        for(int x =0;x<count;x++)
        {
            if(arrRobot[x]  instanceof LineFollower)
            {
                LineFollower CurrentOne= (LineFollower)arrRobot[x];
                System.out.printf("%n%-10s" , CurrentOne.toString());
            }
        }
        System.out.println("\nRoboSoccer Robots");
        System.out.printf("%n%-10s %-10s %-10s %-10s %-10s %-10s" , "Registration" , "Name" , "Age" , "Collected" , "Penalties" , "Score");
        for(int x =0;x<count;x++)
        {
            if(arrRobot[x]  instanceof RoboSoccer)
            {
                RoboSoccer CurrentOne  = (RoboSoccer)arrRobot[x];
                totalCollected += CurrentOne.getCollected();
                totalPenaliets += CurrentOne.getPenalty();
                System.out.printf("%n%-10s" ,CurrentOne.toString());
            }
        }


        System.out.println("\nTotal balls collected: "  + totalCollected);
        System.out.println("Total penalties: " + totalPenaliets);
    }

    public static void DisplayAfterSorting(Robot[] arrRobot , int count)
    {
        System.out.println("\n Aftert sorting");
        System.out.printf("%n%-10s %-10s %-10s %-10s" , "Registration"  , "Name" , "Age" , "Score");
        for(int x =0;x<count;x++)
        {
            System.out.printf("%n%-10s %-10s %-10d %-10d" , arrRobot[x].getRegistration() , arrRobot[x].getName() , arrRobot[x].getAgeGroup() , arrRobot[x].calculateScore());
        }
    }

    public static void serializeToFile(Robot[] arrRobot , int count)
    {
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("scores.ser"));
            for(int x =0;x<count;x++)
            {
                output.writeObject(arrRobot[x]);
            }
            System.out.println("\nObjects has been serialized");
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            throw(new RuntimeException(e));
        }
        catch(Exception e)
        {
            throw(new RuntimeException(e));
        }

    }

    public static void deserializeFromFile()
    {
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("scores.ser"));
            System.out.println("\nList of Robots");
            while(true)
            {
                try
                {
                    Robot arrRobot = (Robot)input.readObject();
                    System.out.println(arrRobot.getRegistration()  + " " + arrRobot.getName());
                }
                catch(EOFException e)
                {
                    System.out.println("End of file has been reached");
                    break;
                }

            }
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            throw(new RuntimeException(e));
        }
    }



}