package com.xharma.parking.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ParkingException extends RuntimeException {

	private final String customMessage;
	public ParkingException(String customMessage) {
		this.customMessage = customMessage;
	}


}
