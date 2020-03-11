
public class IPLDTO {

    public String player;
    public double overs;
    public double average;
    public double strikeRates;
    public int fours;
    public int six;


    public IPLDTO(IPLRunsPOJO iplRunsPOJO) {
        player = iplRunsPOJO.player;
        average = iplRunsPOJO.averages;
        strikeRates = iplRunsPOJO.strikeRates;
        fours = iplRunsPOJO.fours;
        six = iplRunsPOJO.six;
    }

    public IPLDTO(IPLWicketsPOJO iplWicketsPOJO) {
        overs = iplWicketsPOJO.overs;
        player = iplWicketsPOJO.player;
        average = iplWicketsPOJO.average;
    }
}
