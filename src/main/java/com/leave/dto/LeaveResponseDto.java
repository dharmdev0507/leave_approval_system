
package com.leave.dto;

public class LeaveResponseDto {

    private Long leaveId;
    private String processInstanceId;
    private String status;

    public LeaveResponseDto(Long leaveId,
                            String processInstanceId,
                            String status) {
        this.leaveId = leaveId;
        this.processInstanceId = processInstanceId;
        this.status = status;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getStatus() {
        return status;
    }
}