
import java.io.IOException;

public class IPLAnalyser {
    public enum IPL {
        RUNS_CSV, WICKETS_CSV;
    }

    public String LoadIplData(SortField sortField, String csvFilePath, IPL CsvFile) throws IOException {
        String censusMap = new IPLAdapterFactory().getCensusAdaptor(sortField, CsvFile, csvFilePath);
        return censusMap;
    }
}
