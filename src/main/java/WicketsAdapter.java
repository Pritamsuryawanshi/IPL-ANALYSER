import java.io.IOException;
import java.util.Map;

public class WicketsAdapter {

    public Map<String, IPLDTO> loadCensusData(String... csvFilePath) throws IOException {
        Map<String, IPLDTO> iplMap = new IPLAdapter().loadIPlData1(IPLWicketsPOJO.class, csvFilePath);
        return iplMap;
    }
}
