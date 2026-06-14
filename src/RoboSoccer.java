
public class RoboSoccer extends Robot
{
    private int Collected;
    private int Penalty;

    public RoboSoccer()
    {

    }
    public RoboSoccer(String Registration , String Name , int AgeGroup , int Collected , int Penalty) throws InvalidInputException
    {
        super(Registration , Name , AgeGroup);
        setCollected(Collected);
        setPenalty(Penalty);
    }

    public int getCollected(){
        return Collected;
    }
    public int getPenalty(){
        return Penalty;
    }

    public void setCollected(int Collected){
        this.Collected = Collected;
    }
    public  void setPenalty(int Penalty){
        this.Penalty = Penalty;
    }

    @Override
    public int calculateScore()
    {
        return this.Collected - this.Penalty;
    }

    public String toString()
    {
        return String.format("%-10s %-10d %-10d %-10d" , super.toString() , this.Collected, this.Penalty , this.calculateScore());
    }




}