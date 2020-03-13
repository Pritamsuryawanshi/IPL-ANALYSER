import com.jarpackage.CSVBuilderFactory;
import com.jarpackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class IPLAdapter {
    Map<String, IPLDTO> iplMap = new HashMap<>();

    public <E> Map<String, IPLDTO> loadIPlData1(Class<E> IPlClass, String... csvFilePath) throws IOException {
        if (IPlClass.getName().equals("IPLRunsPOJO")) {
            iplMap = loadRunsData(IPLRunsPOJO.class, csvFilePath[0]);
        }

        if (IPlClass.getName().equals("IPLWicketsPOJO")) {
            iplMap = loadWicketsData(IPLWicketsPOJO.class, csvFilePath[0]);
        }

        if (csvFilePath.length > 1) {
            this.loadWicketsData(IPLWicketsPOJO.class, csvFilePath[1]);
        }
        return iplMap;
    }

    public <E> Map<String, IPLDTO> loadRunsData(Class<E> IPlClass, String csvFilePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> CSVStateIterator = csvBuilder.getCSVIterator(reader, IPlClass);
            Iterable<E> csvIterable = () -> CSVStateIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLRunsPOJO.class::cast)
                    .forEach(iplCSV -> iplMap.put(iplCSV.player, new IPLDTO(iplCSV)));
            if (iplMap == null || iplMap.size() == 0) {
                throw new IPLException("no census data", IPLException.ExceptionType.NO_IPL_DATA);
            }
            return iplMap;
        }
    }

    public <E> Map<String, IPLDTO> loadWicketsData(Class<E> IPlClass, String csvFilePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> CSVStateIterator = csvBuilder.getCSVIterator(reader, IPlClass);
            Iterable<E> csvIterable = () -> CSVStateIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLWicketsPOJO.class::cast)
                    .forEach(iplCSV -> iplMap.put(iplCSV.player, new IPLDTO(iplCSV)));
            if (iplMap == null || iplMap.size() == 0) {
                throw new IPLException("no census data", IPLException.ExceptionType.NO_IPL_DATA);
            }
            return iplMap;
        } catch (IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }


}


