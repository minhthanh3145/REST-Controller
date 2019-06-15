package evchargingstore.facade;

import org.springframework.beans.factory.annotation.Autowired;

import evchargingstore.custom.annotation.Facade;
import evchargingstore.entity.ChargingSession;
import evchargingstore.entity.ChargingSessionResponse;
import evchargingstore.entity.ChargingSessionStatisticsResponse;
import evchargingstore.service.ChargingSessionService;

@Facade
public class ChargingSessionServiceFacade {

	@Autowired
	private ChargingSessionService chargingSessionService;

	// TODO: Build a security service to authenticate the request, possibly
	// including some fail-early checks such as UUID validity check

	public ChargingSessionResponse submitChargingSession(ChargingSession sessionInfo) {
		return chargingSessionService.submitChargingSession(sessionInfo);
	}

	public ChargingSessionResponse suspendChargingSession(String id, ChargingSession sessionInfo) {
		sessionInfo.setId(id);
		return chargingSessionService.suspendChargingSession(sessionInfo);
	}

	public ChargingSessionResponse getChargingSession(String id) {
		return chargingSessionService.getChargingSession(id);
	}

	public ChargingSessionStatisticsResponse getChargingSessionStatistics() {
		return chargingSessionService.getChargingSessionStatistics();
	}

}
