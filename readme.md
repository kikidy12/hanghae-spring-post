# hanghae-spring-post
항해 포스트 crud과제

![제목 없는 다이어그램.drawio-3.png](..%2F..%2F..%2F..%2F..%2F..%2FDownloads%2F%EC%A0%9C%EB%AA%A9%20%EC%97%86%EB%8A%94%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.drawio-3.png)

1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
- 수정의 경우 PUT을 사용했고 id를 path로 나머지 데이터는 body로 처리했습니다.
- 삭제는 delete를 사용하였고 path에 id query password로 처리했습니다.

2. 어떤 상황에 어떤 방식의 request를 써야하나요?
- get, delete의 경우에는 query를 사용하는것이 일반적이고 put, post의 경우 body를 주로 사용합니다.

3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
- 나름 잘 사용했다고 생각합니다. get, delete, post, put등을 나눠서 사용했고 일반적인 상황에 맞게 사용했습니다.

4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
- 분리를 해서 사용했고 controller에서 repository에 접근하지 못하도록 처리했습니다.

5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!
- 구성을 맞추려고 노력했습니다.
- api swagger router : /api-docs

|URL           |Method|Request|Response|
|--------------|------|-------|--------|
|/api/posts    |get   |-      |{<br>"success": true,<br>"message": "등록 성공",<br>"data": [<br>{<br>"id": 1,<br>"title": "제목",<br>"content": "내용",<br>"author":<br>"저자"<br>}<br>}<br>]|
|/api/post/{id}|get   |-      |{<br>"success": true,<br>"message": "조회 성공",<br>"data": {<br>"id": 1,<br>"title": "제목",<br>"content": "내용",<br>"author":<br>"저자"<br>}<br>}|
|/api/post     |post  |{<br>"title": "string",<br>"content": "string",<br>"author": "string",<br>"password": "string"<br>}|{<br>"success": true,<br>"message": "등록 성공",<br>"data": {<br>"id": 1,<br>"title": "제목",<br>"content": "내용",<br>"author":<br>"저자"<br>}<br>}|
|/api/post/{id}|put   |{<br>"title": "string",<br>"content": "string",<br>"author": "string",<br>"password": "string"<br>}|{<br>"success": true,<br>"message": "수정 성공",<br>"data": {<br>"id": 1,<br>"title": "제목",<br>"content": "내용",<br>"author":<br>"저자"<br>}<br>}|
|/api/post/{id}|delete|{<br>"password: "string"<br>}|{<br>"success": true,<br>"message": "삭제 성공"<br>}|