package evchargingstore.repository;

import org.springframework.stereotype.Repository;

import evchargingstore.entity.ChargingSession;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ChargingSessionRepository extends CrudRepository<ChargingSession, String> {

	@Query(value = "select count(id) from chargingsession where started_at >= :startedAt", nativeQuery = true)
	public int countByStartedAt(@Param("startedAt") String startedAt);

	@Query(value = "select count(id) from chargingsession where suspended_at >= :suspendedAt", nativeQuery = true)
	public int countBySuspendedAt(@Param("suspendedAt") String suspendedAt);
}