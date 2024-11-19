package com.ajm.new_sch.service;

import com.ajm.new_sch.dto.ScheduleRequestDto;
import com.ajm.new_sch.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
  List<ScheduleResponseDto> findAll(String workerName, String modifiedDate);

  ScheduleResponseDto findScheduleById(Long id);

  ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
}
