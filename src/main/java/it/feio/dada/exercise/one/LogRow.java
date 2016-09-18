package it.feio.dada.exercise.one;

/**
 * Created by Federico Iosue on 18/09/16.
 * <p>
 * POJO representing imported row from logs
 * <p>
 * 1. TIMESTAMP: the moment when the event occurred.
 * 2. BYTES: the number of bytes sent to a client.
 * 3. STATUS: HTTP response status.
 * 4. REMOTE_ADDR: IP address of the client.
 */
public class LogRow {

	private String timestamp;
	private Integer bytes;
	private Integer httpStatus;
	private String remoteIp;


	public String getTimestamp() {
		return timestamp;
	}


	public LogRow setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		return this;
	}


	public Integer getBytes() {
		return bytes;
	}


	public LogRow setBytes(Integer bytes) {
		this.bytes = bytes;
		return this;
	}


	public Integer getHttpStatus() {
		return httpStatus;
	}


	public LogRow setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
		return this;
	}


	public String getRemoteIp() {
		return remoteIp;
	}


	public LogRow setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
		return this;
	}
}
