# 📌  QnaService_Project

해당 프로젝트는 자유게시판의 형식으로 질문과 답변을 할 수 있는 사이트입니다.

해당 프로젝트를 시작한 동기는 웹 애플리케이션 개발의 기본 모델인 ```MVC```패턴을 기반의 구조를 가진 ```Spring MVC```에 대해 학습하기 위해서 시작하게 되었습니다.
[점프투 스프링부트](https://wikidocs.net/book/7601)를 기반으로 하여 유효성 검사부분, 페이징 기능, 게시글 정렬 부분에 대해 커스텀하여 진행해보았습니다.

<br>

# 🖥  Stacks 

<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=OpenJDK&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-logo&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-logo&logo=MariaDB&logoColor=white"> <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-logo&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=Spring Security&logoColor=white"/> 

```
Java : 17
MySQL : 8.0.33
Spring Boot : 3.0.5
Spring Security : 3.1.1
Gradle
AWS EC2
AWS RDS
```
### Spring Boot 사용한 이유

Spring 프레임워크는 Java언어를 기반으로 애플리케이션을 개발을 지원하는 프레임워크입니다. Spring을 제외하고도 서버를 구현할 수 있는 Expree, Django, Ruby on Rails, .NET, PHP 등 다양한 프레임워크들은 많습니다. 하지만 Spring을 사용한 이유는 컴파일된 언어로서 JS, Python보다는 **높은 성능**을 보여주기 때문입니다. 그리고 국내 전자정부프레임워크는 스프링을 기반으로 구성되어있어 다양한 참고자료와 정형화된 패턴의 장점으로 사용하게 되었습니다. 
하지만 Spring프레임 워크는 XML파일 설정, war파일 배포 등 에서 어려움이 많다는 점에서 의존성관리, was 서버 구성의 번거로움 등을 해결해줌으로써 높은 초기 학습 난이도를 낮춰주는 Spring Boot를 선택하게 되었습니다.

<br>

# 🖼️  Service Architecture

![soa](https://github.com/wooyong99/QnaService_Project/assets/85385921/bf372888-c717-4f1d-b50c-220c4a0ae52f)

```
해당 프로젝트는 하나의 EC2와 RDS로 구축했습니다. 
EC2는 외부로부터 들어오는 요청을 받아야하기 때문에 Public Subnet에 위치해 두었고, RDS는 외부에서 접근이 불가능하도록 하기위해 Private Subnet에 위치해두었습니다.
```

<br>

# 💡  Funtions

### ⭐️ 회원가입 / 로그인
- 유효성 검사 ( NULL, 빈 공백 ) / 이메일 형식

### ⭐️ 질문, 답변 CRUD
- 유효성 검사 ( NULL, 빈 공백 )

### ⭐️ 비회원 사용자 질문 작성 차단
- 비회원 사용자 질문 작성 x / 답변 작성 o (익명)

### ⭐️ 질문(제목, 생성날짜) 정렬

### ⭐️ 페이징 기능

<br>

# ❗️ Issues

#### 👉🏻 [Issue1](https://velog.io/@wooyong99/Spring-Boot-SpringBoot-Validation-%EC%82%AC%EC%9A%A9-%EC%8B%9C-%EC%9C%A0%EC%9D%98%EC%82%AC%ED%95%AD)
####  👉🏻[ Issue2](https://velog.io/@wooyong99/%EC%A7%88%EB%AC%B8-%EC%88%98%EC%A0%95-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EC%B2%B4%ED%81%AC-%EC%9E%85%EB%A0%A5-%EA%B0%92-%EC%9C%A0%EC%A7%80-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0%EB%B0%A9%EB%B2%95)
#### 👉🏻 [Issue3](https://velog.io/@wooyong99/Security-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%8B%A4%ED%8C%A8-%EC%8B%9C-%EC%97%90%EB%9F%AC%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%B6%9C%EB%A0%A5)


