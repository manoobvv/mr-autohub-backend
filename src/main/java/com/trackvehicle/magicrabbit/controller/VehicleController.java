package com.trackvehicle.magicrabbit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trackvehicle.magicrabbit.domain.VehicleOnboarding;
import com.trackvehicle.magicrabbit.domain.VehicleOnboardingRequest;
import com.trackvehicle.magicrabbit.services.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
    
    @PostMapping("/onBoard")
    public ResponseEntity<VehicleOnboardingRequest> vehicleOnboard(@RequestBody final VehicleOnboarding vehicleOnboarding){
    	final VehicleOnboardingRequest vehicleOnboardingRequest = new VehicleOnboardingRequest();
    	vehicleService.saveVehicleDetails(vehicleOnboarding);
    	if(vehicleOnboarding.isEnableAutoService()){
    		vehicleService.createMail(vehicleOnboarding);
    	}
    	return ResponseEntity.ok(vehicleOnboardingRequest);
    }
    
    @GetMapping("/fetchAll")
    public List<VehicleOnboarding> getVehicleDetails(){
    	final List<VehicleOnboarding> books = new ArrayList<VehicleOnboarding>();
		return this.vehicleService.getVehicleDetails();
    	
    }
    
    @PostMapping("/scheduleEmail/{onboardId}")
    public ResponseEntity<?> saveEmailSchedule(@PathVariable("onboardId") final int onboardId){
    	VehicleOnboarding vehicleOnboarding= this.vehicleService.getVehicleDetailsById(onboardId);
    	vehicleService.createMail(vehicleOnboarding);
    	//return null;
		return null;
    	
    }

}
