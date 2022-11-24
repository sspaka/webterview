# WEBTERVIEW

## 프로젝트 소개

면접 진행 시 평가표와 카메라 등으로 시선을 계속 움직여야 하는 불편함을 해소하기 위한 웹 프로젝트


### UCC 
클릭하면 영상으로 이동됩니다.

<a href="https://youtu.be/MpBbX2B9bbQ"><img src="https://i.ibb.co/x6zVg2Q/image.png" alt="UCC" border="0"></a>



### 주요 기능

- 회원 관리
- 화상 미팅룸
- 면접관 관리
- 지원자 관리
- 게시판



### 주요 기술

- WebRTC
- WebSocket
- JWT Authentication
- JPA
- REST API



### 참조 리소스

* Vuetify: 디자인 전반 적용
* OpenVidu: webRTC 구현을 위한 데모코드와 라이브러리 활용
* Animation.css: CSS 애니메이션 지원 라이브러리. 메인 페이지 진입 애니메이션에 활용



### 배포 환경

- URL: https://i7c205.p.ssafy.io (서비스 중지)
- 테스트 계정 
    - ID: webterview@gmail.com 
    - PW: test





## 팀 소개
* 이주영: 팀장, 백엔드 개발, Swagger API 문서 관리
* 김희주: 백엔드 개발, Swagger API 문서 관리, HTTPS 구성, Docker 구성, 프로젝트 배포
* 이성찬: 프론트엔드 개발, 와이어 프레임 구성
* 홍성준: 프론트엔드 개발, 와이어 프레임 구성
* 전윤희: 프론트엔드 개발, 백엔드 개발, WebRTC 기능 담당
* 허은지: 프론트엔드 개발, WebRTC 기능 담당, 화면 디자인 구성



## 프로젝트 기술 스택

### 개발 환경

- Intellij, Spring Tool Suite, VS Code, Github Desktop, Postman, JIRA, MobaXterm, Notion



### 기술 스택 

- 프론트 : Vue 3, OpenVidu 2.22.0, Javascript, HTML/CSS, Bootstrap 5
- 백엔드 :  JAVA zulu 8, , MySQL 8.0.30, SpringBoot 2.7.1
- 서버 : Docker 20.10.17, Jenkins 2.346.3, Nginx 1.18.0



### 시스템 구성도

<img src="https://i.ibb.co/xY1qHk3/image.png" alt="시스템구성도" border="0">





### ERD

<img src="https://i.ibb.co/W0Hq3C5/erd.png" alt="erd" border="0">







## 프로젝트 상세 기능 설명

서비스의 직접적인 사용자는 관리자, 즉 회사의 인사담당자입니다. 

관리자 이외에 면접관, 지원자들은 회원가입 하지 않고도 면접을 진행할 수 있도록 하기 위해서 관리자가 가지고 있는 이메일 정보로 면접장 URL을 보내는 방식을 사용하였습니다. 

해당 URL을 들어가 본인인증을 진행하여 성공하면, 회원가입을 하지 않고도 면접을 진행할 수 있습니다.



### 관리자

각각의 방에서 면접이 진행되고, 면접이 끝나면 면접관이 채점한 점수를 취합하여 각 지원자들의 점수 순위표가 자동으로 생성됩니다. 관리자는 해당 순위표를 열람할 수 있으며, 이 순위표와 저장된 평가표 등을 보며 누구를 채용할 건지 결정할 수 있습니다.

<img src="https://i.ibb.co/cL7TFY9/image.png" alt="image" border="0">

<img src="https://i.ibb.co/tZHdSP6/image.png" alt="image" border="0">



- 회원가입, 로그인

- 날짜를 정하여 면접장 생성

- 엑셀 파일을 통한 면접관 추가

- 엑셀 파일을 통한 지원자 추가

- 면접관/지원자 관리 (추가, 수정, 삭제 등)

- 엑셀 파일을 통한 평가표 업로드

- 지원자의 면접 영상 다운로드

- 점수에 따른 지원자의 순위 열람

- 면접관이 작성한 평가표 다운로드





### 면접관

<img src="https://i.ibb.co/KD8xH3H/image.png" alt="image" border="0">

- 엑셀 파일에 작성된 이메일로 면접장 URL을 받을 수 있음

- 해당 URL을 누르면 휴대폰을 통한 본인인증 진행

- 본인인증 성공 시 면접장 입장하여, 한 화면에 지원자의 이력서/자기소개서, 지원자의 카메라, 평가표를 동시에 볼 수 있음

- 평가표에 지원자에 대한 점수, 특이사항 입력 가능





### 지원자

<img src="https://i.ibb.co/Bqqcrf1/image.png" alt="image" border="0">

- 엑셀 파일에 작성된 이메일로 면접장 URL을 받을 수 있음
- 해당 URL을 누르면 휴대폰을 통한 본인인증 진행
- 본인인증 성공 시 면접장 입장하여, 면접 참여 가능    
- 면접 후 해당 면접 영상 다운로드   
