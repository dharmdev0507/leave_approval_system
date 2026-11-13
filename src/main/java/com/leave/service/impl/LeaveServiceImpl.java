package com.leave.service.impl;

import com.leave.service.LeaveService;
import com.leave.dto.LeaveRequestDto;
import com.leave.dto.LeaveResponseDto;
import com.leave.entity.LeaveRequest;
import com.leave.repository.LeaveRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository repository;
    private final RuntimeService runtimeService;

    public LeaveServiceImpl(
            LeaveRepository repository,
            RuntimeService runtimeService) {
        this.repository = repository;
        this.runtimeService = runtimeService;
    }

    @Transactional
    public LeaveResponseDto applyLeave(LeaveRequestDto dto) {

        LeaveRequest leave = new LeaveRequest();

        leave.setEmployeeName(dto.getEmployeeName());
        leave.setLeaveType(dto.getLeaveType());
        leave.setNumberOfDays(dto.getNumberOfDays());
        leave.setStatus("PENDING");

        leave = repository.save(leave);

        Map<String, Object> variables = new HashMap<>();

        variables.put("leaveId", leave.getId());
        variables.put("numberOfDays", leave.getNumberOfDays());
        variables.put("employeeName", leave.getEmployeeName());

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(
                        "leaveApprovalProcess",
                        String.valueOf(leave.getId()),
                        variables
                );

        leave.setProcessInstanceId(
                processInstance.getProcessInstanceId()
        );

        repository.save(leave);

        return new LeaveResponseDto(
                leave.getId(),
                leave.getProcessInstanceId(),
                leave.getStatus()
        );
    }

    public LeaveRequest getLeave(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave not found: " + id));
    }
}