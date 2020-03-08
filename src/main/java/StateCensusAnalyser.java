import java.io.IOException;

public class StateCensusAnalyser {

    public String loadCensusData(SortField avg, String csvFilePath) throws IOException {
        String censusMap = new CSVStateCensus().loadCensusData(avg, csvFilePath);
        return censusMap;
    }
}
