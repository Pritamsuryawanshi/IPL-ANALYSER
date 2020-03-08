import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class IPLTest {
    private static final String IPL_CENSUS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIplCSVFile_ShouldReturnTopBattingAverages() throws IOException {
        IPLAnalyser censusAnalyser = new IPLAnalyser();
        String numOfRecords = censusAnalyser.loadCensusData(SortField.AVG,IPL_CENSUS_CSV_FILE_PATH);
        IPLDTO[] censusCSV = new Gson().fromJson(numOfRecords, IPLDTO[].class);
        Assert.assertEquals(83.2, censusCSV[0].average,0.0);
    }


}