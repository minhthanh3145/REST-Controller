package evchargingstore.entity;

public class ChargingSessionStatisticsResponse {
	private int startedCount;
	private int suspendedCount;

	public ChargingSessionStatisticsResponse(int startedCount, int suspendedCount) {
		this.setStartedCount(startedCount);
		this.setSuspendedCount(suspendedCount);
	}
	
	public ChargingSessionStatisticsResponse() {

	}

	public int getSuspendedCount() {
		return suspendedCount;
	}

	public void setSuspendedCount(int suspendedCount) {
		this.suspendedCount = suspendedCount;
	}

	public int getStartedCount() {
		return startedCount;
	}

	public void setStartedCount(int startedCount) {
		this.startedCount = startedCount;
	}
}
