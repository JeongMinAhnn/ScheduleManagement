# ScheduleManagement
```

```
|기능|Method|URL|Request|Response|상태코드|
|------|---|---|------|---|---|
|일정 생성|`POST`|/api/schedules|{<br>"name" : "ajm",<br>"password" : "1234",<br>"title" : "side project",<br>"content" : "schedul app created",<br>"date" : 2024-11-01"<br>}|{<br>"id" : "1,<br>"name" : "ajm",<br>"title" : "side project",<br>"content" : "schedul app created",<br>"date" : 2024-11-01",<br>}|200: 정상 등록, 400: 비정상 값|
|전체 일정 조회|`GET`|/api/schedules|/api/schedules|"data": [{<br>"id" : "1,<br>"name" : "ajm",<br>"title" : "side project",<br>"date" : 2024-11-01",<br>},<br>{<br>"id" : "2,<br>"name" : "ajm2,<br>"title" : "side project",<br>"date" : 2024-11-01",<br>}|200: 정상 조회|
|선택 일정 조회|`GET`|/api/schedules/{schedule_id}||{<br>"id" : "1,<br>"name" : "ajm",<br>"title" : "side project",<br>"content" : "schedul app created",<br>"date" : 2024-11-01",<br>}|200: 정상 조회, 404: 일정이 사라짐|
|선택 일정 수정|`PUT`|/api/schedules/{schedule_id}|{<br>"name" : "ajm2",<br>"title" : "side project",<br>"date" : 2024-11-01",<br>"description" : "project updates"<br>}|{<br>"id" : 1,<br>"name": "ajm2",<br>"title" : "side project",<br>"date" : 2024-11-01",<br>"description" : "project updates"<br>}|200: 정상 수정, 400: 비정상 값, 404: 일정이 사라짐|
|선택 일정 삭제|`DELETE`|/api/schedules/{schedule_id}|요청 param|삭제 정보|200: 정상 등록, 404: 일정이 사라짐|
<details>
<summary>토글 생성 성공</summary>
<div markdown="1">
  내용
</div>
</details>
