import com.google.gson.Gson;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {
    public enum IPL {
        RUNS_CSV, WICKETS_CSV;
    }


    public IPLAnalyser() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.RUNS, Comparator.comparing(census -> census.runs));
        this.sortMap.put(SortField.AVG, Comparator.comparing(census -> census.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRates));
        this.sortMap.put(SortField.FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_AVERAGES, Comparator.comparing(census -> census.strikeRates + census.average));
        this.sortMap.put(SortField.BOWLING_AVERAGE, Comparator.comparing(census -> census.bowlingAverage));
        this.sortMap.put(SortField.ECONOMY_RATE, Comparator.comparing(census -> census.economyRate));
        this.sortMap.put(SortField.FOUR_AND_FIVE_WICKET, Comparator.comparing(census -> census.fourWicket+census.fiveWicket));
        this.sortMap.put(SortField.WICKETS_AND_AVERAGE, Comparator.comparing(census -> census.wickets));
        this.sortMap.put(SortField.BATTING_AND_BOWLING_AVG, Comparator.comparing(census -> census.average+census.bowlingAverage));
    }

    List<IPLDTO> ipldtoList;
    Map<String, IPLDTO> iplMap = new HashMap<>();
    Map<SortField, Comparator<IPLDTO>> sortMap;


    public int LoadIplData(IPL CsvFile, String... csvFilePath) throws IOException {
        iplMap = new IPLAdapterFactory().getIPLAdaptor(CsvFile, csvFilePath);
        return iplMap.size();
    }

    public String sortedData(SortField sortField) throws IPLException {
        if (iplMap == null || iplMap.size() == 0) {
            throw new IPLException("no ipl data data", IPLException.ExceptionType.NO_IPL_DATA);
        }
        ipldtoList = iplMap.values().stream().collect(Collectors.toList());
/*
        System.out.println("data is "+iplMap);
*/
        this.sort(this.sortMap.get(sortField).reversed());
        String sortedStateCensus = new Gson().toJson(this.ipldtoList);
        return sortedStateCensus;
    }

    private void sort(Comparator<IPLDTO> censusComparator) {
        for (int i = 0; i < ipldtoList.size() - 1; i++) {
            for (int j = 0; j < ipldtoList.size() - i - 1; j++) {
                IPLDTO census1 = ipldtoList.get(j);
                IPLDTO census2 = ipldtoList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    ipldtoList.set(j, census2);
                    ipldtoList.set(j + 1, census1);
                }
            }
        }
    }

}
