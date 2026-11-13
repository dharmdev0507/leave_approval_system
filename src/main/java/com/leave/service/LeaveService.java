package com.leave.service;

import com.leave.dto.LeaveRequestDto;
import com.leave.dto.LeaveResponseDto;
import com.leave.entity.LeaveRequest;

public interface LeaveService {

    LeaveResponseDto applyLeave(LeaveRequestDto request);
    LeaveRequest getLeave(Long id);
}
