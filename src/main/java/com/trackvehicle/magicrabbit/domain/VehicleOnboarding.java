package com.trackvehicle.magicrabbit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
// defining class name as Table name
@Table
public class VehicleOnboarding {
	
	@Id
	@Column(name = "on_board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int onBoardId;
	
	@Column
	private String vehicleModel;
	
	@Column
	private String vehicleName;
	
	@Column
	private String vehicleNumber;
	
	@Column
	private String ownerName;
	
	@Column
	private String ownerMobNo;
	
	@Column
	private String ownerEmail;
	
	@Column
	private String ownerAddress;
	
	@Column
	private String vehicleOnboardDate;
	
	@Column
	private String vehicleDeliveryDate;
	
	@Column
	private String vehicleDetailingInfo;
	
	@Column
	private boolean enableAutoService;

	public boolean isEnableAutoService() {
		return enableAutoService;
	}

	public void setEnableAutoService(boolean enableAutoService) {
		this.enableAutoService = enableAutoService;
	}

	public int getOnBoardId() {
		return onBoardId;
	}

	public void setOnBoardId(int onBoardId) {
		this.onBoardId = onBoardId;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerMobNo() {
		return ownerMobNo;
	}

	public void setOwnerMobNo(String ownerMobNo) {
		this.ownerMobNo = ownerMobNo;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getVehicleOnboardDate() {
		return vehicleOnboardDate;
	}

	public void setVehicleOnboardDate(String vehicleOnboardDate) {
		this.vehicleOnboardDate = vehicleOnboardDate;
	}

	public String getVehicleDeliveryDate() {
		return vehicleDeliveryDate;
	}

	public void setVehicleDeliveryDate(String vehicleDeliveryDate) {
		this.vehicleDeliveryDate = vehicleDeliveryDate;
	}

	public String getVehicleDetailingInfo() {
		return vehicleDetailingInfo;
	}

	public void setVehicleDetailingInfo(String vehicleDetailingInfo) {
		this.vehicleDetailingInfo = vehicleDetailingInfo;
	}

}
