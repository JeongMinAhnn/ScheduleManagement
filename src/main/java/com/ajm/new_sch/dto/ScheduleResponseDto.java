package com.ajm.new_sch.dto;

import com.ajm.new_sch.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class ScheduleResponseDto {
  private Long id;
  private String task;
  private String workerName;
  private LocalDateTime created;
  private LocalDateTime modifiedAt;

  public ScheduleResponseDto(Schedule schedule) {
    this.id = schedule.getId();
    this.task = schedule.getTask();
    this.workerName = schedule.getWorkerName();
    this.created = schedule.getCreatedAt();
    this.modifiedAt = schedule.getModifiedAt();
  }

  public ScheduleResponseDto(Long id, String task, String workerName, LocalDateTime created, LocalDateTime modifiedAt) {
    this.id = id;
    this.task = task;
    this.workerName = workerName;
    this.created = created;
    this.modifiedAt = modifiedAt;
  }

}
