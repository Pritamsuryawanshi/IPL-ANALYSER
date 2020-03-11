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


public class IPLAdapter {
    Map<SortField, Comparator<IPLDTO>> sortMap = new HashMap<>();
    List<IPLDTO> IPLDTOList;
    Map<String, IPLDTO> iplMap = new HashMap<>();


    public IPLAdapter() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.RUNS, Comparator.comparing(census -> census.player));
        this.sortMap.put(SortField.AVG, Comparator.comparing(census -> census.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRates));
        this.sortMap.put(SortField.FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
        this.sortMap.put(SortField.STRIKING_RATES_WITH_AVERAGES, Comparator.comparing(census -> census.strikeRates + census.average));
        this.sortMap.put(SortField.OVERS, Comparator.comparing(census -> census.overs));
        this.sortMap.put(SortField.AVG, Comparator.comparing(census -> census.average));

    }

    //  public <E> Map<String, CensusDTO> loadCensusData(Class<E> CensusCSVClass, String... csvFilePath)
    public <E> String loadCensusData(SortField sortField, String csvFilePath, Class<E> CensusCSVClass) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> CSVStateIterator = csvBuilder.getCSVIterator(reader, CensusCSVClass);
            Iterable<E> csvIterable = () -> CSVStateIterator;

            if (CensusCSVClass.getName().equals("IPLRunsPOJO")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLRunsPOJO.class::cast)
                        .forEach(censusCSV -> iplMap.put(censusCSV.player, new IPLDTO(censusCSV)));
            }

            if (CensusCSVClass.getName().equals("IPLWicketsPOJO")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLWicketsPOJO.class::cast)
                        .forEach(censusCSV -> iplMap.put(censusCSV.player, new IPLDTO(censusCSV)));
            }

            if (iplMap == null || iplMap.size() == 0) {
                throw new IPLException("no census data", IPLException.ExceptionType.NO_CENSUS_DATA);
            }

            IPLDTOList = iplMap.values().stream().collect(Collectors.toList());
            this.sort(this.sortMap.get(sortField).reversed());
            String sortedData = new Gson().toJson(this.IPLDTOList);
            return sortedData;
        }
    }

    private void sort(Comparator<IPLDTO> reversed) {
        for (int i = 0; i < IPLDTOList.size() - 1; i++) {
            for (int j = 0; j < IPLDTOList.size() - i - 1; j++) {
                IPLDTO census1 = (IPLDTO) IPLDTOList.get(j);
                IPLDTO census2 = (IPLDTO) IPLDTOList.get(j + 1);
                if (reversed.compare(census1, census2) > 0) {
                    IPLDTOList.set(j, census2);
                    IPLDTOList.set(j + 1, census1);
                }
            }
        }
    }
}


