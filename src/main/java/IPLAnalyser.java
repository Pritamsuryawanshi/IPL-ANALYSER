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
        this.sortMap.put(SortField.RUNS, Comparator.comparing(IPL -> IPL.runs));
        this.sortMap.put(SortField.AVG, Comparator.comparing(IPL -> IPL.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(IPL -> IPL.strikeRates));
        this.sortMap.put(SortField.FOURS_AND_SIX, Comparator.comparing(IPL -> IPL.fours + IPL.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX, Comparator.comparing(IPL -> IPL.fours + IPL.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_AVERAGES, Comparator.comparing(IPL -> IPL.strikeRates + IPL.average));
        this.sortMap.put(SortField.BOWLING_AVERAGE, Comparator.comparing(IPL -> IPL.bowlingAverage));
        this.sortMap.put(SortField.ECONOMY_RATE, Comparator.comparing(IPL -> IPL.economyRate));
        this.sortMap.put(SortField.FOUR_AND_FIVE_WICKET, Comparator.comparing(IPL -> IPL.fourWicket + IPL.fiveWicket));
        this.sortMap.put(SortField.WICKETS_AND_AVERAGE, Comparator.comparing(IPL -> IPL.wickets));
        this.sortMap.put(SortField.BATTING_AND_BOWLING_AVG, Comparator.comparing(IPL -> IPL.average + IPL.bowlingAverage));
        this.sortMap.put(SortField.ALL_ROUNDER, Comparator.comparing(IPL -> IPL.runs * IPL.wickets));
    }

    List<IPLDTO> IPLDTOList;
    Map<String, IPLDTO> IPLMap = new HashMap<>();
    Map<SortField, Comparator<IPLDTO>> sortMap;


    public int LoadIplData(IPL CsvFile, String... csvFilePath) throws IOException {
        IPLMap = new IPLAdapterFactory().getIPLAdaptor(CsvFile, csvFilePath);
        return IPLMap.size();
    }

    public String sortedData(SortField sortField) throws IPLException {
        if (IPLMap == null || IPLMap.size() == 0) {
            throw new IPLException("no ipl data data", IPLException.ExceptionType.NO_IPL_DATA);
        }
        IPLDTOList = IPLMap.values().stream().
                collect(Collectors.toList());
        this.sort(this.sortMap.get(sortField).reversed());
        String sortedStateCensus = new Gson().toJson(this.IPLDTOList);
        return sortedStateCensus;
    }

    private void sort(Comparator<IPLDTO> censusComparator) {
        for (int i = 0; i < IPLDTOList.size() - 1; i++) {
            for (int j = 0; j < IPLDTOList.size() - i - 1; j++) {
                IPLDTO census1 = IPLDTOList.get(j);
                IPLDTO census2 = IPLDTOList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    IPLDTOList.set(j, census2);
                    IPLDTOList.set(j + 1, census1);
                }
            }
        }
    }
}
