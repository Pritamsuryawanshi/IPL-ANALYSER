import java.io.IOException;

public class IPLAnalyser {

    public String loadCensusData(SortField avg, String csvFilePath) throws IOException {
        String censusMap = new IPLLoader().loadCensusData(avg, csvFilePath);
        return censusMap;
    }
}
