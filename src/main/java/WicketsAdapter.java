import java.io.IOException;

public class WicketsAdapter {

    public String loadCensusData(SortField field, String csvFilePath) throws IOException {
        String numOfRecords = new IPLAdapter().loadCensusData(field, csvFilePath, IPLWicketsPOJO.class);
        return numOfRecords;
    }
}
