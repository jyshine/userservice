# 회원 가입 및 관리 서비스 샘플
**1. 프로젝트소개**
회원 가입 및 비밀번호 재설정이 가능한 API 샘플 프로젝트입니다.




**2. 실행 방법**
- 사전 설치 : JDK 15, Docker, Gradle 7.6
- 프로젝트 git clone
- docker 로 DB 설치
```
# 프로젝트 폴더(userservice) 디렉토리 기준
$ cd user-service/docker
$ docker-compose up -d

# 삭제
$ docker-compose down
```

- 프로젝트 빌드 및 실행
```
# 프로젝트 폴더(user-service) 디렉토리 기준
$ sudo ./gradlew bootRun

```

- API 문서 확인
http://localhost:8080/swagger-ui.html#/
- API 요청 순서 
1) 회원가입: 전화번호 인증 전송 -> 전화번호 인증 확인 -> 회원 가입
2) 로그인
```
$ curl -u front-ui:front-ui! http://localhost:8080/oauth/token -F grant_type=password -F username=XXXX -F password=YYYY
```
3) 내 정보 보기 기능: 토큰 입력 -> 내 정보 보기
4) 전화번호 인증 전송 -> 전화번호 인증 확인 -> 비밀번호 재설정




**3. 사용 기술**
- Java
- SpringBoot
- Spring Security



**4. 요구사항**
- 회원 가입 기능
- 로그인 기능
- 내 정보 보기 기능
- 비밀번호 재설정 기능

**5. 추가 예정**
- 유효성 검사 및 예외처리 추가
- AOP 이용하여 회원정보 생성

