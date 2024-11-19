package com.ajm.new_sch.repository;

import com.ajm.new_sch.dto.ScheduleResponseDto;
import com.ajm.new_sch.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateScheduleRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }


  @Override
  public List<ScheduleResponseDto> findAll(String workerName, String modifiedDate) {

    // 수정일(YYYY-MM-DD), 작성자명 조건으로 전부 조회
    // 한 가지만을 충족 혹은 둘 다 충족하지 않거나 혹은 두 가지를 모드 충족
    // 수정일 기준 내림차순

    StringBuilder query = new StringBuilder("SELECT * FROM schedule WHERE 1=1");
    List<Object> params = new ArrayList<>();
    if (workerName != null) {
      query.append(" AND worker_name = ?");
      params.add(workerName);
    }
    if (modifiedDate != null) {
      query.append(" AND DATE_FORMAT(modified_at, '%Y-%m-%d') = ?");
      params.add(modifiedDate);
    }
    query.append(" ORDER BY modified_at DESC");
    return jdbcTemplate.query(query.toString(), params.toArray(), scheduleRowMapper());
  }

  @Override
  public Schedule findScheduleByIdOrElseThrow(Long id) {
    List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id);
    return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, id + ": 아이디에 맞는 데이터가 없습니다."));
  }

  @Override
  public ScheduleResponseDto saveSchedule(Schedule schedule) {
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    simpleJdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("task", schedule.getTask());
    parameters.put("worker_name", schedule.getWorkerName());
    parameters.put("password", schedule.getPassword());
    parameters.put("created_at", schedule.getCreatedAt());
    parameters.put("modified_at", schedule.getModifiedAt());

    Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));


    return new ScheduleResponseDto(key.longValue(), schedule.getTask(), schedule.getWorkerName(), schedule.getCreatedAt(), schedule.getModifiedAt());
  }

  private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
    return new RowMapper<ScheduleResponseDto>() {

      @Override
      public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ScheduleResponseDto(
            rs.getLong("id"),
            rs.getString("task"),
            rs.getString("worker_name"),
            rs.getObject("created_at", LocalDateTime.class),
            rs.getObject("modified_at", LocalDateTime.class)

        );
      }
    };
  }
  private RowMapper<Schedule> scheduleRowMapperV2() {
    return new RowMapper<Schedule>() {
      @Override
      public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Schedule (
            rs.getLong("id"),
            rs.getString("task"),
            rs.getString("worker_name"),
            rs.getObject("created_at", LocalDateTime.class),
            rs.getObject("modified_at", LocalDateTime.class)
        );
      }
    };
  }
}
