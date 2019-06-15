package evchargingstore.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evchargingstore.entity.ChargingSession;
import evchargingstore.entity.ChargingSessionResponse;
import evchargingstore.entity.ChargingSessionStatisticsResponse;
import evchargingstore.entity.ChargingSessionStatus;
import evchargingstore.repository.ChargingSessionRepository;
import evchargingstore.util.DateUtil;

@Service
public class ChargingSessionService {

	@Autowired
	private ChargingSessionRepository chargingSessionRepository;

	public ChargingSessionResponse submitChargingSession(ChargingSession sessionInfo) {
		sessionInfo.setId(UUID.randomUUID().toString());
		sessionInfo.setStartedAt(sessionInfo.getStartedAt());
		sessionInfo.setStatus(ChargingSessionStatus.STARTED);
		ChargingSession savedInfo = chargingSessionRepository.save(sessionInfo);
		return new ChargingSessionResponse(savedInfo);
	}

	public ChargingSessionResponse suspendChargingSession(ChargingSession sessionInfo) {
		return chargingSessionRepository.findById(sessionInfo.getId()).map(infoIfExisted -> {
			if (infoIfExisted.getStatus() == ChargingSessionStatus.SUSPENDED) {
				return new ChargingSessionResponse("Session with this ID is already suspended !");
			}
			infoIfExisted.setSuspendedAt(sessionInfo.getSuspendedAt());
			infoIfExisted.setStatus(ChargingSessionStatus.SUSPENDED);
			ChargingSession savedInfo = chargingSessionRepository.save(infoIfExisted);
			return new ChargingSessionResponse(savedInfo);

		}).orElse(new ChargingSessionResponse("Session with this ID doesn't exist"));
	}

	public ChargingSessionResponse getChargingSession(String id) {
		return chargingSessionRepository.findById(id).map(info -> {
			return new ChargingSessionResponse(info);
		}).orElse(new ChargingSessionResponse("Session with this ID doesn't exist"));
	}

	public ChargingSessionStatisticsResponse getChargingSessionStatistics() {
		Date currentDate = new Date(System.currentTimeMillis() - 60000);
		int startedCount = chargingSessionRepository.countByStartedAt(DateUtil.format(currentDate));
		int suspendedCount = chargingSessionRepository.countBySuspendedAt(DateUtil.format(currentDate));
		return new ChargingSessionStatisticsResponse(startedCount, suspendedCount);
	}
}
