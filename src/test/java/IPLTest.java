import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class IPLTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    IPLAnalyser iplAnalyser = new IPLAnalyser();

    @Test
    public void givenIplCSVFile_ShouldReturnTopBattingAverages() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.AVG);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(83.2, censusCSV[0].average, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnTopStrikingRates() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.STRIKE_RATE);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(333.33, censusCSV[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMost4sAnd6s() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.FOURS_AND_SIX);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Andre Russell", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxStrikeRateAndMost4sAnd6s() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(204.81, censusCSV[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxStrikeRateAndAverages() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.AVG);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(134.62, censusCSV[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxRunsAndAverages() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.RUNS);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("David Warner", censusCSV[0].player);
    }


    @Test
    public void givenIplCSVFile_ShouldReturnTopBowlingAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.BOWLING_AVERAGE);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }
    @Test
    public void givenIplCSVFile_ShouldReturnTopStrikingRatesOfTheBowlers() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.STRIKE_RATE);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersWithBestEconomyRates() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.ECONOMY_RATE);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Ben Cutting", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersHavingBestStrikingRatesWith4Wand5W() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.FOUR_AND_FIVE_WICKET);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(16.81, censusCSV[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersHavingBestStrikingRateAndAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.BOWLING_AVERAGE);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(120.0, censusCSV[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersHavingMaxWicketsAndAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        String sortedIPLData = iplAnalyser.sortedData(SortField.WICKETS_AND_AVERAGE);
        IPLDTO[] censusCSV = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Imran Tahir", censusCSV[0].player);
    }



}