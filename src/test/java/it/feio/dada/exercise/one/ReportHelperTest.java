package it.feio.dada.exercise.one;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Federico Iosue on 16/09/2016.
 */
public class ReportHelperTest {

	final String REQUESTS_LOG_PATH = "one/logfiles/requests.log";
	final String REPORT_PATH = "one/reports/ipaddr.csv";

	private File sourceLogFile;


	@Before
	public void init() {
		sourceLogFile = new File(getClass().getClassLoader().getResource(REQUESTS_LOG_PATH).getFile());
	}


	@Test
	public void testReadFromSource() throws IOException {
		List<LogRow> logRows = ReportHelper.readFromSource(sourceLogFile);
		assertNotNull(logRows);
		assertEquals(logRows.size(), 7);
		assertTrue(logRows.get(2).getBytes() == 217088);
	}


	@Test
	public void testConvertLogtoReportIncludedSuccessRequests() throws IOException {
		List<ReportRow> reportRows = ReportHelper.convertLogtoReport(ReportHelper.readFromSource(sourceLogFile),
				false);
		assertTrue(reportRows.size() == 4);
		boolean containsIpWithOnlySuccessRequests = false;
		for (ReportRow reportRow : reportRows) {
			containsIpWithOnlySuccessRequests = containsIpWithOnlySuccessRequests || reportRow.getIp().equals
					("192.168.0.57");
		}
		assertTrue(containsIpWithOnlySuccessRequests);
	}


	@Test
	public void testConvertLogtoReport() throws IOException {
		List<ReportRow> reportRows = ReportHelper.convertLogtoReport(ReportHelper.readFromSource(sourceLogFile), true);
		assertTrue(reportRows.size() == 3);
		ReportRow reportRow = reportRows.get(0);
		assertEquals("192.168.0.55", reportRow.getIp());
		assertTrue(288768 == reportRow.getTotalBytesSent());
	}


	@Test
	public void testBuildReport() throws IOException {
		File reportFile = new File(getClass().getClassLoader().getResource(REPORT_PATH).getFile());
		FileUtils.writeStringToFile(reportFile, null, Charset.defaultCharset());
		assertEquals(0, FileUtils.sizeOf(reportFile));
		ReportHelper.buildReport(sourceLogFile, reportFile);
		assertNotEquals(0, FileUtils.sizeOf(reportFile));
	}

}
