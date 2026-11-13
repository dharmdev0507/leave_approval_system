
package com.leave.controller;

import com.leave.dto.LeaveRequestDto;
import com.leave.dto.LeaveResponseDto;
import com.leave.entity.LeaveRequest;

import com.leave.service.LeaveService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveResponseDto applyLeave(
            @RequestBody LeaveRequestDto request) {

        return leaveService.applyLeave(request);
    }

    @GetMapping("/{id}")
    public LeaveRequest getLeave(@PathVariable("id") Long id) {
        return leaveService.getLeave(id);
    }
}
