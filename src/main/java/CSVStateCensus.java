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



public class CSVStateCensus {
    Map<SortField, Comparator<CensusDTO>> sortMap;
    List<CensusDTO> censusDTOList;
    Map<String, CensusDTO> censusMap = new HashMap<>();

    public CSVStateCensus() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.RUNS, Comparator.comparing(census -> census.player));
        this.sortMap.put(SortField.AVG, Comparator.comparing(census -> census.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(census -> census.strikeRates));
        this.sortMap.put(SortField.FOURS_AND_SIX, Comparator.comparing(census -> census.fours + census.six));
    }

    public String loadCensusData(SortField sortField, String csvFilePath) throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLFactsCSV> CSVStateIterator = csvBuilder.getCSVIterator(reader, IPLFactsCSV.class);
            Iterable<IPLFactsCSV> csvIterable = () -> CSVStateIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLFactsCSV.class::cast)
                    .forEach(censusCSV -> censusMap.put(censusCSV.player, new CensusDTO(censusCSV)));
            if (censusMap == null || censusMap.size() == 0) {
                throw new IPLException("no census data", IPLException.ExceptionType.NO_CENSUS_DATA);
            }
            censusDTOList = censusMap.values().stream().collect(Collectors.toList());
            this.sort(this.sortMap.get(sortField).reversed());
            String sortedStateCensus = new Gson().toJson(this.censusDTOList);
            return sortedStateCensus;
        }
    }

    private void sort(Comparator<CensusDTO> reversed) {
        for (int i = 0; i < censusDTOList.size() - 1; i++) {
            for (int j = 0; j < censusDTOList.size() - i - 1; j++) {
                CensusDTO census1 = censusDTOList.get(j);
                CensusDTO census2 = censusDTOList.get(j + 1);
                if (reversed.compare(census1, census2) > 0) {
                    censusDTOList.set(j, census2);
                    censusDTOList.set(j + 1, census1);
                }
            }
        }
    }
}


