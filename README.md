# MyPortfolio
전반적인 웹의 기본 소양이 되는 게시판 프로젝트입니다.
![게시판소개](https://user-images.githubusercontent.com/75352561/182530010-2d866255-f9ce-451b-bde5-c1b40a344430.JPG)
# 목차

+ 들어가며

  + 프로젝트 소개
  + 프로젝트 기능
  + 개발도구
  + 실행 화면
  
+ 구조 및 설계
  + 패키지 구조
  + DB 설계
  + API 설계
+ 개발 내용

+ 마치며
 
 + 프로젝트 보완사항
 + 후기
# 들어가며
### 1. 프로젝트소개
웹 프로그래밍의 기본 소양이라 할 수 있는 게시판을 직접 만들어보았습니다.
독학으로 배우면서 제작한 개인 프로젝트이기 때문에 부족한점이 많아도 끝까지 완성하겠습니다.

### 2. 프로젝트 기능
프로젝트의 주요 기능은 다음과 같습니다.
  + 게시판 - CRUD 기능, 조회수, 페이징 및 검색 처리
  + 사용자 - 회원가입 및 로그인, 회원정보 수정, 회원가입시 유효성 검사 및 중복 검사
  + 댓글 - CRUD 기능(Ajax)

### 3. 개발 도구
 프로젝트 제작에 사용한 개발 도구들은 다음과 같습니다.
  + 자바 개발도구 : Java 11
  + 통합개발 환경 : STS(3.9.17), Intellij
  + 웹 서버 : Tomcat 9
  + 웹 브라우저 : chrome
  + 데이터 베이스 : MySQL 8.0
  + Build Tool : Maven
  + 기타 : VS code, Git, AWS

 ### 4. 실행 화면

# 구조 및 설계

### 1. 패키지 구조

### 2. DB 설계

![ERD](https://user-images.githubusercontent.com/75352561/182555467-f353b2e9-0473-4de5-af20-58bc747a7a90.JPG)


### 3. API 설계

#### 게시글 관련 API
![image](https://user-images.githubusercontent.com/75352561/182540779-71443375-0cd2-454c-bf4b-8a03821025e9.png)
#### 회원 관련 API
![image](https://user-images.githubusercontent.com/75352561/182545876-480ad2ec-2882-49bf-ae14-92c679be128c.png)
#### 댓글 관련 API
![image](https://user-images.githubusercontent.com/75352561/182545920-8a6de42b-77d1-43e0-a0ef-e874fa55ebb9.png)


