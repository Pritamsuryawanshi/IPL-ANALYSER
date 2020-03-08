public class IPLDTO {

    public String player;
    public double average;
    public double strikeRates;
    public int fours;
    public int six;

    public IPLDTO(IPLFactsCSV iplFactsCSV) {
        player = iplFactsCSV.player;
        average = iplFactsCSV.averages;
        strikeRates = iplFactsCSV.strikeRates;
        fours = iplFactsCSV.fours;
        six = iplFactsCSV.six;
    }
}
