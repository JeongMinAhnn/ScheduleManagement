package com.ajm.new_sch.entity;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class Schedule {
  // ID, 할일, 작성자명, 비밀번호, 작성일, 수정일

  private Long id;
  private String task;
  private String workerName;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public Schedule(Long id, String task, String workerName, LocalDateTime created, LocalDateTime modifiedAt) {
    this.id = id;
    this.task = task;
    this.workerName = workerName;
    this.createdAt = created;
    this.modifiedAt = modifiedAt;
  }
  public Schedule(String task, String workerName, String password) {
    this.task = task;
    this.workerName = workerName;
    this.password = password;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }

}
