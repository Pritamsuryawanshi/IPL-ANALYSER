import com.google.gson.Gson;
import com.jarpackage.CSVBuilderFactory;
import com.jarpackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;



public class IPLLoader {
    Map<SortField, Comparator<IPLDTO>> sortMap;
    List<IPLDTO> IPLDTOList;
    Map<String, IPLDTO> censusMap = new HashMap<>();

    public IPLLoader() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.RUNS, Comparator.comparing(census -> census.player));
        this.sortMap.put(SortField.AVG, Comparator.comparing(census -> census.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRates));
        this.sortMap.put(SortField.FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
    }

    public String loadCensusData(SortField sortField, String csvFilePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLFactsCSV> CSVStateIterator = csvBuilder.getCSVIterator(reader, IPLFactsCSV.class);
            Iterable<IPLFactsCSV> csvIterable = () -> CSVStateIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLFactsCSV.class::cast)
                    .forEach(censusCSV -> censusMap.put(censusCSV.player, new IPLDTO(censusCSV)));
            if (censusMap == null || censusMap.size() == 0) {
                throw new IPLException("no census data", IPLException.ExceptionType.NO_CENSUS_DATA);
            }
            IPLDTOList = censusMap.values().stream().collect(Collectors.toList());
;            this.sort(this.sortMap.get(sortField).reversed());
            String sortedStateCensus = new Gson().toJson(this.IPLDTOList);
            return sortedStateCensus;
        }
    }

    private void sort(Comparator<IPLDTO> reversed) {
        for (int i = 0; i < IPLDTOList.size() - 1; i++) {
            for (int j = 0; j < IPLDTOList.size() - i - 1; j++) {
                IPLDTO census1 = IPLDTOList.get(j);
                IPLDTO census2 = IPLDTOList.get(j + 1);
                if (reversed.compare(census1, census2) > 0) {
                    IPLDTOList.set(j, census2);
                    IPLDTOList.set(j + 1, census1);
                }
            }
        }
    }
}


