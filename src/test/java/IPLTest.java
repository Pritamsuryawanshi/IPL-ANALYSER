import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class IPLTest {
    private static final String IPL_CENSUS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    IPLAnalyser iplAnalyser = new IPLAnalyser();

    @Test
    public void givenIplCSVFile_ShouldReturnTopBattingAverages() throws IOException {
        String numOfRecords = iplAnalyser.LoadIplData(SortField.AVG,IPL_CENSUS_CSV_FILE_PATH);
        IPLDTO[] censusCSV = new Gson().fromJson(numOfRecords, IPLDTO[].class);
        Assert.assertEquals(83.2, censusCSV[0].average,0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnTopStrikingRates() throws IOException {
        String numOfRecords = iplAnalyser.LoadIplData(SortField.STRIKE_RATE,IPL_CENSUS_CSV_FILE_PATH);
        IPLDTO[] censusCSV = new Gson().fromJson(numOfRecords, IPLDTO[].class);
        Assert.assertEquals(333.33, censusCSV[0].strikeRates,0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMost4sAnd6s() throws IOException {
        String numOfRecords = iplAnalyser.LoadIplData(SortField.FOURS_AND_SIX,IPL_CENSUS_CSV_FILE_PATH);
        IPLDTO[] censusCSV = new Gson().fromJson(numOfRecords, IPLDTO[].class);
        Assert.assertEquals("Andre Russell", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxStrikeRateAndMost4sAnd6s() throws IOException {
        String numOfRecords = iplAnalyser.LoadIplData(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX,IPL_CENSUS_CSV_FILE_PATH);
        IPLDTO[] censusCSV = new Gson().fromJson(numOfRecords, IPLDTO[].class);
        Assert.assertEquals(204.81, censusCSV[0].strikeRates,0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxStrikeRateAndAverages() throws IOException {
        String numOfRecords = iplAnalyser.LoadIplData(SortField.AVG,IPL_CENSUS_CSV_FILE_PATH);
        IPLDTO[] censusCSV = new Gson().fromJson(numOfRecords, IPLDTO[].class);
        Assert.assertEquals(134.62, censusCSV[0].strikeRates,0.0);
    }
}