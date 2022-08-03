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
 ▶ 게시글 관련 화면
  #### 1.게시글 전체목록
  ![게시글 전체목록1](https://user-images.githubusercontent.com/75352561/182563108-6153f9bf-c5d3-48a0-9beb-968a0e5b0958.JPG)
  #### 2.게시글 검색(제목)
  제목으로 검색한 게시글 목록 화면입니다.
  ![게시글 페이징 전체목록2](https://user-images.githubusercontent.com/75352561/182563833-22bd1ba4-d56a-4d3c-89ba-bee00d62f072.JPG)

  #### 3.게시글 조회
  다른사람이 작성한 게시글 조회의 경우 "글쓰기"버튼과, "목록"버튼만 존재합니다.
  ![게시글 조회1](https://user-images.githubusercontent.com/75352561/182563206-4f0dd646-b254-4f5d-872d-50daa2ba6a35.JPG)
  
  본인이 작성한 게시글 조회의 경우 "수정","삭제"버튼이 추가로 존재합니다.
  ![게시글 조회2](https://user-images.githubusercontent.com/75352561/182563218-d6d4e6a4-01de-45ce-80c5-78914dfe6694.JPG)

  
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


