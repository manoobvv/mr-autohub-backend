package com.trackvehicle.magicrabbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackvehicle.magicrabbit.domain.VehicleOnboarding;



public interface VehicleDetailsRepository extends JpaRepository<VehicleOnboarding, Integer> {

}
