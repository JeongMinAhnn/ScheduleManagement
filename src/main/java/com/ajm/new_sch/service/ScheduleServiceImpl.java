package com.ajm.new_sch.service;

import com.ajm.new_sch.dto.ScheduleRequestDto;
import com.ajm.new_sch.dto.ScheduleResponseDto;
import com.ajm.new_sch.entity.Schedule;
import com.ajm.new_sch.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
  private final ScheduleRepository scheduleRepository;
  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }
  @Override
  public List<ScheduleResponseDto> findAll(String workerName, String modifiedDate) {
    return scheduleRepository.findAll(workerName, modifiedDate);
  }

  @Override
  public ScheduleResponseDto findScheduleById(Long id) {
    Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
    return new ScheduleResponseDto(schedule);
  }

  @Override
  public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
    Schedule schedule = new Schedule(scheduleRequestDto.getTask(), scheduleRequestDto.getWorkerName(),scheduleRequestDto.getPassword());
    return scheduleRepository.saveSchedule(schedule);
  }
}
