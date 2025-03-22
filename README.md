# 1:1 수업 수강 신청 시스템

## A. 과제 목적

학생(Student)과 튜터(Tutor) 간의 1:1 수업 신청 시스템을 설계 및 구현합니다.  
튜터는 수업 가능한 시간대를 오픈하고, 학생은 그 중 가능한 시간에 수업을 신청합니다.

---

## B. 주요 도메인 설계

- **Tutor** : 튜터 정보 및 수업 가능한 시간 등록/삭제  
- **Student** : 수강 신청을 위한 학생  
- **Course** : 수업 정보 (튜터, 학생, 시작 시간, 수업 길이 포함)

---

## C. API 구성

### 튜터 관련
- 튜터 생성 API ← 실제 테스트용 데이터 생성을 위해 포함
- 튜터 조회 API
- 튜터의 수업 가능 시간 등록 API
- 튜터의 수업 가능 시간 삭제 API
- 특정 시간대에 가능한 튜터 조회 API

### 학생 관련
- 학생 생성 API ← 실제 테스트용 데이터 생성을 위해 포함
- 수강 신청 API
- 특정 기간 및 수업 길이로 가능한 수업 시간대 조회 API
- 학생이 신청한 수업 목록 조회 API

---

## D. 가정 사항

- 수업은 **정각 또는 30분**에만 시작 가능
- 수업 길이는 **30분 또는 60분**으로 고정
- 튜터가 수업 가능한 시간으로 등록하면, course 엔터티에 **student 없이 등록됨**
- 학생이 신청 시, 해당 시간에 tutor와 course가 존재해야 하며, **이미 신청된 경우 불가능**
- 동일한 튜터가 동일한 시간에 **중복 수업 등록 불가능**

---

## E. 사용 기술 스택

- Java 17
- Spring Boot 3.x
- JPA (Hibernate)
- MySQL
- Flyway (DB 마이그레이션)
- Swagger (API 문서 자동화)

---

## F. 실행 방법 및 환경 설정

### 1. 프로젝트 실행 전 사전 준비

#### 필수 설치
- Java 17
- MySQL 8+
- Maven 3.8+
- IntelliJ, VS Code 등의 IDE

---

### 2. 🗄 MySQL 설정

#### 아래 명령어를 통해 MySQL 사용자 및 DB를 생성:

```bash
mysql -u root -p
```

```sql
CREATE DATABASE tutor;
CREATE USER 'dev'@'localhost' IDENTIFIED BY 'dev_password';
GRANT ALL PRIVILEGES ON tutor.* TO 'dev'@'localhost';
FLUSH PRIVILEGES;
```

---

### 3. 프로젝트 실행

```bash
# 의존성 설치 및 컴파일
mvn clean install

# 애플리케이션 실행
mvn spring-boot:run
```

---

### 4. Swagger 문서 확인

Spring Boot 실행 후, 아래 주소에서 API 문서를 확인할 수 있습니다:

```
http://localhost:8080/swagger-ui/index.html
```

---

## G. 구현 참고 사항

- DTO는 **Readable**, **Persistable**로 구분하여 API 요청/응답의 명확성 확보
- DTO ↔ Entity 변환은 **Mapper 클래스로 분리**하여 확장성과 유지보수성 고려
- API 단에서는 **Service를 직접 호출하지 않고 Facade**를 두어 역할 분리
- 시간대 관련 필드는 **enum (CourseDuration)** 을 통해 30분/60분만 허용
- DB 마이그레이션 파일은 `resources/db/migration` 하위에 위치
- 실제 데이터로 확인하기 위해 튜터와 학생 생성 api도 추가

---

## H. 설계 기준 및 의도

- Swagger API는 **요청 주체(튜터, 학생)** 가 아닌, **도메인 중심(Tutor, Course)** 으로 정렬함
- 전체 설계는 **유연성과 확장성**을 고려한 구조로 설계함
- 서비스가 다시 시작되어도 상태가 유지되도록 DB 기반의 **persistent storage**를 사용
