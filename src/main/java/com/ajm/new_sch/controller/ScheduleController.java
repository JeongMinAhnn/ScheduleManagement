package com.ajm.new_sch.controller;

import com.ajm.new_sch.dto.ScheduleRequestDto;
import com.ajm.new_sch.dto.ScheduleResponseDto;
import com.ajm.new_sch.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

  private final ScheduleService scheduleService;

  private ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @GetMapping
  public ResponseEntity<List<ScheduleResponseDto>> findAll(@RequestParam(value = "workerName", required = false) String workerName,
                                                           @RequestParam(value = "modifiedDate") String modifiedDate) {
    return new ResponseEntity<>(scheduleService.findAll(workerName, modifiedDate), HttpStatus.OK);
  }
  @GetMapping("/{id}")
  public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
  }
  @PostMapping
  public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
    return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
  }
  //

}
