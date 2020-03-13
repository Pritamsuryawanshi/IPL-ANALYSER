import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class IPLTest {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private IPLAnalyser iplAnalyser;
    String sortedIPLData;
    IPLDTO[] IPLCensus;

    @Before
    public void IPLTest() {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIplCSVFile_ShouldReturnTopBattingAverages() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.AVG);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(83.2, IPLCensus[0].average, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnTopStrikingRates() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.STRIKE_RATE);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(333.33, IPLCensus[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMost4sAnd6s() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.FOURS_AND_SIX);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Andre Russell", IPLCensus[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxStrikeRateAndMost4sAnd6s() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(204.81, IPLCensus[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxStrikeRateAndAverages() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.AVG);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(134.62, IPLCensus[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBatsmanWithMaxRunsAndAverages() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.RUNS);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("David Warner", IPLCensus[0].player);
    }


    @Test
    public void givenIplCSVFile_ShouldReturnTopBowlingAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.BOWLING_AVERAGE);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", IPLCensus[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnTopStrikingRatesOfTheBowlers() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.STRIKE_RATE);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", IPLCensus[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersWithBestEconomyRates() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.ECONOMY_RATE);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Ben Cutting", IPLCensus[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersHavingBestStrikingRatesWith4Wand5W() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.FOUR_AND_FIVE_WICKET);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(16.81, IPLCensus[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersHavingBestStrikingRateAndAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.BOWLING_AVERAGE);
        IPLDTO[] IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals(120.0, IPLCensus[0].strikeRates, 0.0);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnBowlersHavingMaxWicketsAndAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.WICKETS_CSV, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.WICKETS_AND_AVERAGE);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Imran Tahir", IPLCensus[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnPlayersHavingBestBattingAndBowlingAverage() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.BATTING_AND_BOWLING_AVG);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", IPLCensus[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturnAllRounderPlayer() throws IOException {
        iplAnalyser.LoadIplData(IPLAnalyser.IPL.RUNS_CSV, IPL_MOST_RUNS_CSV_FILE_PATH, IPL_MOST_WICKETS_CSV_FILE_PATH);
        sortedIPLData = iplAnalyser.sortedData(SortField.ALL_ROUNDER);
        IPLCensus = new Gson().fromJson(sortedIPLData, IPLDTO[].class);
        Assert.assertEquals("Marcus Stoinis", IPLCensus[0].player);
    }
}