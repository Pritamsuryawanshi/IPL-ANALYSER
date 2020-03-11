import java.io.IOException;

public class RunsAdapter {
    public String loadCensusData(SortField field, String csvFilePath) throws IOException {
        String numOfRecords = new IPLAdapter().loadCensusData(field, csvFilePath, IPLRunsPOJO.class);
        return numOfRecords;
    }
}
