package evchargingstore.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import evchargingstore.util.DateUtil.CustomDateDeserializer;
import evchargingstore.util.DateUtil.CustomDateSerializer;

@Entity
@Table(name = "chargingsession")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargingSession {
	@Id
	private String id;
	private Date startedAt;
	private Date suspendedAt;
	@Enumerated(EnumType.STRING)
	private ChargingSessionStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartedAt() {
		return startedAt;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setStartedAt(Date at) {
		this.startedAt = at;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getSuspendedAt() {
		return suspendedAt;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setSuspendedAt(Date at) {
		this.suspendedAt = at;
	}

	public ChargingSessionStatus getStatus() {
		return status;
	}

	public void setStatus(ChargingSessionStatus status) {
		this.status = status;
	}
}
