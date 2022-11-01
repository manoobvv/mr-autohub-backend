package com.trackvehicle.magicrabbit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleOnboardingRequest {
	
	private String message;
	
	private String vehicleName;
	
	private boolean success;
	
//	public VehicleOnboardingRequest(final boolean success, final String vehicleName, final String message) {
//	        this.success = success;
//	        this.vehicleName = vehicleName;
//	        this.message = message;
//	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
}
