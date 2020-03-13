import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunsAdapter {

    Map<String, IPLDTO> iplMap = new HashMap<>();


    public Map<String, IPLDTO> loadCensusData(String... csvFilePath) throws IOException {
        iplMap = new IPLAdapter().loadIPlData1(IPLRunsPOJO.class, csvFilePath);
        return iplMap;
    }
}

