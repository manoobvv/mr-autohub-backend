package com.trackvehicle.magicrabbit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleEmailResponse {

    private boolean success;

    private String jobId;

    private String jobGroup;

    private String message;

    public ScheduleEmailResponse(final boolean success, final String message) {
        this.success = success;
        this.message = message;
    }

    public ScheduleEmailResponse(final boolean success, final String jobId, final String jobGroup,
            final String message) {
        this.success = success;
        this.jobId = jobId;
        this.jobGroup = jobGroup;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(final String jobId) {
        this.jobId = jobId;
    }

    public String getJobGroup() {
        return this.jobGroup;
    }

    public void setJobGroup(final String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    // Getters and Setters (Omitted for brevity)
}
