import java.io.Serializable;


public abstract class Robot implements Serializable ,  Scoreable , Comparable<Robot>
{
    private String Registration;
    private String Name;
    private int AgeGroup;

    public Robot()
    {

    }

    public Robot(String Registration , String Name , int  AgeGroup) throws InvalidInputException
    {
        setRegistration(Registration);
        setName(Name);
        setAgeGroup(AgeGroup);
    }

    public String getRegistration(){
        return Registration;
    }
    public String getName(){
        return Name;

    }
    public int getAgeGroup()
    {
        return AgeGroup;
    }
    public void setRegistration(String Registration) throws InvalidInputException
    {
        Boolean isValidLength = true;
        Boolean isDigits = true;
        if(Registration.length() != 6)
        {
            isValidLength = false;

        }
        try
        {
            int num =Integer.parseInt(Registration.substring(1));

        }catch(Exception e)
        {
            isDigits = false;

        }
        if(!isValidLength)
        {
            throw(new InvalidInputException("Lenghth must be 6"));
        }
        else if(!isDigits)
        {
            throw(new InvalidInputException("positions 2 to 6 may only be digits"));
        }
        else if(!isValidLength && !isDigits)
        {
            throw(new InvalidInputException("Lenght must 6. Positions 2 to 6 may only be digits"));
        }
        else
        {
            this.Registration = Registration;
        }


    }
    public void setName(String Name){
        this.Name= Name;

    }
    public void setAgeGroup(int AgeGroup) throws InvalidInputException
    {
        if(AgeGroup < 1 || AgeGroup > 3)
        {
            throw(new InvalidInputException("Invalid age group " + AgeGroup +": Must be between 1 and 3"));
        }
        else
        {
            this.AgeGroup = AgeGroup;
        }

    }

    public int compareTo(Robot SecondRobot)
    {
        String Robo1 = String.format("%d%d" , this.AgeGroup , this.calculateScore());
        String Robo2 = String.format("%d%d" , SecondRobot.AgeGroup , SecondRobot.calculateScore());
        return Robo1.compareTo(Robo2);
    }

    public String toString()
    {
        return String.format("%-10s %-10s %-10d" , this.Registration , this.Name , this.AgeGroup);
    }


}