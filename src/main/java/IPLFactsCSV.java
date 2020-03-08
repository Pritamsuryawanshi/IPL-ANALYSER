import com.opencsv.bean.CsvBindByName;
public class IPLFactsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double averages;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRates;

    @CsvBindByName(column = "4s", required = true)
    public int fours;
    @CsvBindByName(column = "6s", required = true)
    public int six;
}
