
public class LineFollower extends Robot
{
    private int Distance;
    private int Time;


    public LineFollower()
    {

    }

    public LineFollower(String Registration , String Name , int AgeGroup , int Distance , int Time) throws InvalidInputException
    {
        super(Registration , Name , AgeGroup);
        setDistance(Distance);
        setTime(Time);
    }

    public int getDistance(){
        return Distance;
    }
    public int getTime(){
        return Time;
    }
    public void setDistance(int Distance){
        this.Distance = Distance;
    }
    public void setTime(int Time)
    {
        this.Time = Time;
    }
    @Override
    public int calculateScore()
    {
        return this.Distance / this.Time;
    }

    public String toString()
    {
        return String.format("%-10s %-10d  %-10d %-10d" , super.toString() , this.Distance , this.Time , this.calculateScore());
    }


}