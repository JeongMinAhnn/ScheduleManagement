package com.ajm.new_sch.repository;

import com.ajm.new_sch.dto.ScheduleResponseDto;
import com.ajm.new_sch.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
  List<ScheduleResponseDto> findAll(String workerName, String modifiedDate);

  Schedule findScheduleByIdOrElseThrow(Long id);

  ScheduleResponseDto saveSchedule(Schedule schedule);
}
