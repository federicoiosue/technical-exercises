package it.feio.dada.exercise.one;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;


/**
 * Created by Federico Iosue on 16/09/2016.
 */
public class ReportHelper {

	public static final int HTTP_STATUS_SUCCESS = 200;


	public static void buildReport(File logCsvFile, File reportFile) throws IOException {
		List<ReportRow> reportRows = convertLogtoReport(readFromSource(logCsvFile), true);
		for (ReportRow reportRow : reportRows) {
			FileUtils.writeStringToFile(reportFile, reportRow.toString() + System.lineSeparator(), Charset.defaultCharset(), true);
		}
	}


	static List<LogRow> readFromSource(File csvFile) throws IOException {
		List<LogRow> result = new ArrayList<LogRow>();
		CSVParser parser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.EXCEL.withDelimiter(';'));
		for (CSVRecord csvRecord : parser) {
			LogRow logRow = new LogRow().setTimestamp(csvRecord.get(0)).setBytes(Integer.valueOf(csvRecord.get(1)))
					.setHttpStatus(Integer.valueOf(csvRecord.get(2))).setRemoteIp(csvRecord.get(3));
			result.add(logRow);
		}
		return result;
	}


	static List<ReportRow> convertLogtoReport(List<LogRow> logRows, boolean excludeSuccessRequests) {
		List<ReportRow> result = new ArrayList<ReportRow>();
		float totalRequestsSent = 0;
		float totalBytesSent = 0;
		Map<String, ReportRow> workingReportMap = new HashMap<String, ReportRow>();
		for (LogRow logRow : logRows) {
			if (excludeSuccessRequests && logRow.getHttpStatus() == HTTP_STATUS_SUCCESS) {
				continue;
			}
			if (workingReportMap.containsKey(logRow.getRemoteIp())) {
				ReportRow reportRow = workingReportMap.get(logRow.getRemoteIp());
				reportRow.setRequestNumber(reportRow.getRequestNumber() + 1).setTotalBytesSent(reportRow
						.getTotalBytesSent() + logRow.getBytes());
				workingReportMap.put(logRow.getRemoteIp(), reportRow);
			} else {
				ReportRow reportRow = new ReportRow().setIp(logRow.getRemoteIp()).setRequestNumber(1)
						.setTotalBytesSent(logRow.getBytes());
				workingReportMap.put(logRow.getRemoteIp(), reportRow);
			}
			totalRequestsSent++;
			totalBytesSent += logRow.getBytes();
		}
		for (String ip : workingReportMap.keySet()) {
			ReportRow reportRow = workingReportMap.get(ip);
			reportRow.setTotalBytesPercentage(reportRow.getTotalBytesSent() / totalBytesSent * 100)
					.setTotalRequestsPercentage(reportRow.getRequestNumber() / totalRequestsSent * 100);
			workingReportMap.put(ip, reportRow);
			result.add(reportRow);
		}
		Collections.sort(result, new Comparator<ReportRow>() {
			public int compare(ReportRow o1, ReportRow o2) {
				return o1.getRequestNumber() > o2.getRequestNumber() ? -1 : 1;
			}
		});
		return result;
	}

}
