# Saesig Diary

## 프로젝트 개요
새식일기는 반려동물 분양 서비스를 제공하는 애플리케이션입니다.

## 기술 스택

### 백엔드
- **Java 17**
- **Spring Boot 2.7.18**
- **Spring Security**
- **Spring Data JPA**
- **QueryDSL 5.0.0**
- **MyBatis**
- **Spring Batch**
- **Spring WebSocket**
- **JWT (JSON Web Token)**

### 데이터베이스
- **MySQL**
- **H2 Database** (개발 및 테스트용)
- **Redis** (캐싱 및 세션 관리)
- **MongoDB** (채팅 데이터용)

### 인프라
- **AWS** (Spring Cloud AWS 2.2.6)
- **S3** (파일 저장)
- **Jasypt** (암호화)
- **Prometheus** (모니터링)

### 기타 라이브러리
- **Lombok**
- **Thymeleaf** (템플릿 엔진)
- **P6Spy** (SQL 로깅)
- **OAuth2 Client** (소셜 로그인)
- **SpringDoc OpenAPI** (API 문서화)

## 프로젝트 구조

### module-core
핵심 기능과 공통 컴포넌트를 포함하는 라이브러리 모듈입니다.

### module-admin
관리자 웹 인터페이스를 제공하는 모듈입니다.

### module-batch
배치 작업을 처리하는 모듈입니다.

### module-chat
실시간 채팅 기능을 제공하는 모듈입니다.

## 개발 환경 설정

### 필수 요구사항
- JDK 17
- Gradle
- MySQL
- Redis
- MongoDB (채팅 기능 사용 시)

## 보안 설정
- Jasypt를 사용한 설정 파일 암호화
- JWT 기반 인증
- Spring Security를 통한 보안 설정
- OAuth2 클라이언트 지원

## 모니터링 및 로깅
- Spring Actuator를 통한 애플리케이션 상태 모니터링
- Prometheus 메트릭 수집
- P6Spy를 통한 SQL 쿼리 로깅
