
package com.leave.delegate;

import com.leave.entity.LeaveRequest;
import com.leave.repository.LeaveRepository;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("leaveApprovalDelegate")
public class LeaveApprovalDelegate implements JavaDelegate {

    private final LeaveRepository repository;

    public LeaveApprovalDelegate(LeaveRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DelegateExecution execution) {

        Long leaveId = Long.valueOf(
                execution.getVariable("leaveId").toString()
        );

        Integer numberOfDays = (Integer)
                execution.getVariable("numberOfDays");

        LeaveRequest leave = repository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException("Leave not found"));

        if (numberOfDays <= 5) {
            leave.setStatus("APPROVED");
        } else {
            leave.setStatus("REJECTED");
        }

        repository.save(leave);

        execution.setVariable(
                "approvalStatus",
                leave.getStatus()
        );

        System.out.println(
                "Camunda processed leave ID: " + leaveId
        );

        System.out.println(
                "Leave status: " + leave.getStatus()
        );
    }
}
