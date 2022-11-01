package com.trackvehicle.magicrabbit.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trackvehicle.magicrabbit.domain.ScheduleEmailRequest;
import com.trackvehicle.magicrabbit.domain.ScheduleEmailResponse;
import com.trackvehicle.magicrabbit.domain.VehicleOnboarding;
import com.trackvehicle.magicrabbit.job.EmailJob;
import com.trackvehicle.magicrabbit.repository.VehicleDetailsRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Autowired
	private Scheduler scheduler;

	
	public void saveVehicleDetails(final VehicleOnboarding vehicleOnboarding){
		this.vehicleDetailsRepository.save(vehicleOnboarding);
	}
	
	public List<VehicleOnboarding> getVehicleDetails(){
		final List<VehicleOnboarding> vehicles = new ArrayList<VehicleOnboarding>();
		this.vehicleDetailsRepository.findAll().forEach(vehicle->vehicles.add(vehicle));
		return vehicles;
		
	}
	
	public VehicleOnboarding getVehicleDetailsById(final int onBoardId){
		return this.vehicleDetailsRepository.findById(onBoardId).get();	
	}
	
	public void createMail(final VehicleOnboarding vehicleOnboarding){
		
		ScheduleEmailRequest scheduleEmailRequest =  new ScheduleEmailRequest();
		String mailBody = "This is a automatic mail for the upcomig service. Details are below: ";
		mailBody = mailBody+"<br><br>";
		mailBody = mailBody+"Model : "+vehicleOnboarding.getVehicleModel()+"<br>";
		mailBody = mailBody+"Name : "+vehicleOnboarding.getVehicleName()+"<br>";
		mailBody = mailBody+"Vehicle No. : "+vehicleOnboarding.getVehicleNumber()+"<br>";
		mailBody = mailBody+"Owner Name : "+vehicleOnboarding.getOwnerName()+"<br>";
		mailBody = mailBody+"Owner MobNo. : "+vehicleOnboarding.getOwnerMobNo()+"<br>";
		mailBody = mailBody+"Onboard date : "+vehicleOnboarding.getVehicleOnboardDate()+"<br>";
		mailBody = mailBody+"Deatiling Info : "+vehicleOnboarding.getVehicleDetailingInfo()+"<br>";
		scheduleEmailRequest.setEmail("arjunarjunme@gmail.com");
		scheduleEmailRequest.setSubject(vehicleOnboarding.getVehicleModel()+' '+vehicleOnboarding.getVehicleNumber());
		scheduleEmailRequest.setBody(mailBody);
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date;
		LocalDateTime ldt=null;
		ZoneId zKolkata=null;
		try {
			date = format.parse(vehicleOnboarding.getVehicleDeliveryDate());
			ldt = LocalDateTime.ofInstant(date.toInstant(),
                    ZoneId.systemDefault());
			zKolkata = ZoneId.of( "Asia/Kolkata" ) ;
			scheduleEmailRequest.setDateTime(LocalDateTime.now().plusMonths(5));
			scheduleEmailRequest.setTimeZone(zKolkata);
			creatingEmailRequest(scheduleEmailRequest);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private ResponseEntity<ScheduleEmailResponse> creatingEmailRequest(ScheduleEmailRequest scheduleEmailRequest) {	
		
		 try {
	            final ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailRequest.getDateTime(),
	                    scheduleEmailRequest.getTimeZone());
	            if (dateTime.isBefore(ZonedDateTime.now())) {
	                final ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
	                        "dateTime must be after current time");
	                return ResponseEntity.badRequest().body(scheduleEmailResponse);
	            }

	            final JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
	            final Trigger trigger = buildJobTrigger(jobDetail, dateTime);
	            this.scheduler.scheduleJob(jobDetail, trigger);

	            final ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(true,
	                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");
	            return ResponseEntity.ok(scheduleEmailResponse);
	        } catch (final SchedulerException ex) {
	          //  logger.error("Error scheduling email", ex);

	            final ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
	                    "Error scheduling email. Please try later!");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
	        }
		
	}
	
	 private JobDetail buildJobDetail(final ScheduleEmailRequest scheduleEmailRequest) {
	        final JobDataMap jobDataMap = new JobDataMap();

	        jobDataMap.put("email", scheduleEmailRequest.getEmail());
	        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
	        jobDataMap.put("body", scheduleEmailRequest.getBody());

	        return JobBuilder.newJob(EmailJob.class).withIdentity(UUID.randomUUID().toString(), "email-jobs")
	                .withDescription("Send Email Job").usingJobData(jobDataMap).storeDurably().build();
	    }

	    private Trigger buildJobTrigger(final JobDetail jobDetail, final ZonedDateTime startAt) {
	        return TriggerBuilder.newTrigger().forJob(jobDetail)
	                .withIdentity(jobDetail.getKey().getName(), "email-triggers").withDescription("Send Email Trigger")
	                .startAt(Date.from(startAt.toInstant()))
	                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
	    }

}
