
import java.io.IOException;

public class IPLAdapterFactory {
    public String getCensusAdaptor(SortField sortField, IPLAnalyser.IPL file, String csvFilePath) throws IOException {
            if (file.equals(IPLAnalyser.IPL.RUNS_CSV)) {
                return new RunsAdapter().loadCensusData(sortField,csvFilePath);
            } else if (file.equals(IPLAnalyser.IPL.WICKETS_CSV)) {
                return new WicketsAdapter().loadCensusData(sortField,csvFilePath);
            } else
                throw new IPLException("INVALID", IPLException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

