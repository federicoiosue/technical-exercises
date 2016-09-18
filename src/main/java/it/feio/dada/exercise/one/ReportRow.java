package it.feio.dada.exercise.one;

/**
 * Created by Federico Iosue on 18/09/16.
 * <p>
 * POJO representing output report row data
 * <p>
 * a. IP Address
 * b. Number of requests
 * c. Percentage of the total amount of requests
 * d. Total Bytes sent
 * e. Percentage of the total amount of bytes
 */
public class ReportRow {

	private String ip;
	private Integer requestNumber;
	private float totalRequestsPercentage;
	private Integer totalBytesSent;
	private float totalBytesPercentage;


	public String getIp() {
		return ip;
	}


	public ReportRow setIp(String ip) {
		this.ip = ip;
		return this;
	}


	public Integer getRequestNumber() {
		return requestNumber;
	}


	public ReportRow setRequestNumber(Integer requestNumber) {
		this.requestNumber = requestNumber;
		return this;
	}


	public float getTotalRequestsPercentage() {
		return totalRequestsPercentage;
	}


	public ReportRow setTotalRequestsPercentage(float totalRequestsPercentage) {
		this.totalRequestsPercentage = totalRequestsPercentage;
		return this;
	}


	public Integer getTotalBytesSent() {
		return totalBytesSent;
	}


	public ReportRow setTotalBytesSent(Integer totalBytesSent) {
		this.totalBytesSent = totalBytesSent;
		return this;
	}


	public float getTotalBytesPercentage() {
		return totalBytesPercentage;
	}


	public ReportRow setTotalBytesPercentage(float totalBytesPercentage) {
		this.totalBytesPercentage = totalBytesPercentage;
		return this;
	}


	@Override
	public String toString() {
		return new StringBuilder(ip).append(",").append(requestNumber).append(",").append(totalRequestsPercentage)
				.append(",").append(totalBytesSent).append(",").append(totalBytesPercentage).toString();
	}
}
