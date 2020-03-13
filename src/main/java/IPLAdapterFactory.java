
import java.io.IOException;
import java.util.Map;

public class IPLAdapterFactory {
    public Map<String, IPLDTO> getIPLAdaptor(IPLAnalyser.IPL file, String... csvFilePath) throws IOException {
        if (file.equals(IPLAnalyser.IPL.RUNS_CSV)) {
            return new RunsAdapter().loadCensusData(csvFilePath);
        } else if (file.equals(IPLAnalyser.IPL.WICKETS_CSV)) {
            return new WicketsAdapter().loadCensusData(csvFilePath);
        } else
            throw new IPLException("INVALID", IPLException.ExceptionType.IPL_FILE_PROBLEM);
    }
}

