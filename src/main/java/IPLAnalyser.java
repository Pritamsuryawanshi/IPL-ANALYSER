import java.io.IOException;

public class IPLAnalyser {

    public String LoadIplData(SortField avg, String csvFilePath) throws IOException {
        String censusMap = new IPLLoader().loadCensusData(avg, csvFilePath);
        return censusMap;
    }
}
