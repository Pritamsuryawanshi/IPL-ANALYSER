import com.opencsv.bean.CsvBindByName;

public class IPLWicketsPOJO {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economyRate;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWicket;

    @CsvBindByName(column = "4w", required = true)
    public int fourWicket;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;




}
