import com.opencsv.bean.CsvBindByName;
public class IPLWicketsPOJO {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "Avg", required = true)
    public double average;
}
