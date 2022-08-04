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
 <details>
    <summary>게시글 관련 화면</summary>
    <h4> 1.게시글 전체목록 </h4>
    <img src=https://user-images.githubusercontent.com/75352561/182808791-403fab2f-3eec-4127-b674-ac239a8e1351.JPG />
    <h4> 2.게시글 검색 </h4>
    <img src=https://user-images.githubusercontent.com/75352561/182809213-01c403e6-215a-4288-b882-40ce2c309a99.JPG />
    <h4> 3-1게시글 조회 </h4>
      다른사람이 작성한 게시글 조회의 경우 "글쓰기"버튼과, "목록"버튼만 존재합니다.
    <img src=https://user-images.githubusercontent.com/75352561/182809323-843db8a2-9a02-4f6d-bf1f-56d31d353016.JPG />
    <h4> 3-2게시글 조회
      본인이 작성한 게시글 조회의 경우 "수정","삭제"버튼이 추가로 존재합니다.
    <img src=https://user-images.githubusercontent.com/75352561/182809619-6bec1b18-54f1-4447-b98c-0fbca53f9747.JPG />
</details>

 <details>
  <summary>댓글 관련 화면</summary>
  <h4> 1.댓글 입력 </h4>
  <img src=https://user-images.githubusercontent.com/75352561/182828695-70cbd5f3-ea38-4d91-851f-69539894cd83.JPG />
  <h4> 2.댓글 목록 </h4>
  <img src=https://user-images.githubusercontent.com/75352561/182828831-67b8b814-aab5-4f85-bb13-7fde2cbf3ddc.JPG />
  <h4> 3.댓글 목록 </h4>
  다른 사람이 작성한 댓글의 경우 밑에와 같이 수정,삭제 버튼이 없습니다.
  <img src=https://user-images.githubusercontent.com/75352561/182829098-7240c819-4994-4fdc-a909-9d6bbd34e85f.JPG />
 </details>
 
 <details>
  <symmary>회원 관련 화면</summary>
 </details>

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


