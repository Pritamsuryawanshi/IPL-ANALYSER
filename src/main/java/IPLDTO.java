
public class IPLDTO {

    public String player;
    public double overs;
    public double average;
    public double bowlingAverage;
    public double strikeRates;
    public int fours;
    public int six;
    public int fourWicket;
    public int fiveWicket;
    public int wickets;
    public double economyRate;
    public int runs;


    public IPLDTO(IPLRunsPOJO iplRunsPOJO) {
        player = iplRunsPOJO.player;
        average = iplRunsPOJO.averages;
        strikeRates = iplRunsPOJO.strikeRates;
        fours = iplRunsPOJO.fours;
        six = iplRunsPOJO.six;
        runs = iplRunsPOJO.runs;

    }

    public IPLDTO(IPLWicketsPOJO iplWicketsPOJO) {
        overs = iplWicketsPOJO.overs;
        player = iplWicketsPOJO.player;
        bowlingAverage = iplWicketsPOJO.bowlingAverage;
        strikeRates = iplWicketsPOJO.strikeRate;
        economyRate = iplWicketsPOJO.economyRate;
        fourWicket = iplWicketsPOJO.fourWicket;
        fiveWicket = iplWicketsPOJO.fiveWicket;
        wickets = iplWicketsPOJO.wickets;
    }
}
