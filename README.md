# WebRTC 화상면접 프로젝트
 


## 카테고리

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :white_check_mark: Vue.js |
| :white_check_mark: Mobile Web | :black_square_button: Big Data | :black_square_button: TypeScript | :black_square_button: React |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :black_square_button: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :black_square_button: Python | :black_square_button: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |



## 프로젝트 소개

* 프로젝트명: 비대면 화상 면접 서비스
* 서비스 특징: 면접 진행 시 평가표와 카메라 등으로 시선을 계속 움직여야 하는 불편함을 해소하기 위한 웹 프로젝트
* 주요 기능
  - 회원 관리
  - 화상 미팅룸
  - 면접관 관리
  - 지원자 관리
  - 게시판
* 주요 기술
  - WebRTC
  - WebSocket
  - JWT Authentication
  - JPA
  - REST API
* 참조 리소스
  * Vuetify: 디자인 전반 적용
  * OpenVidu: webRTC 구현을 위한 데모코드와 라이브러리 활용
  * Animation.css: CSS 애니메이션 지원 라이브러리. 메인 페이지 진입 애니메이션에 활용
* 배포 환경
  - URL: https://i7c205.p.ssafy.io
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



## 프로젝트 상세 설명

* 개발 환경: Intellij, VS Code, Openvidu, Github Desktop, Postman, JIRA, MobaXterm
* 기술 스택 
  - 프론트: HTML/CSS, JavaScript, Vue.js, Bootstrap
  - 백엔드: JAVA, , MySQL, SpringBoot
* 시스템 구성도

![시스템구성도](/uploads/d129826e2dd5f12b34bc129a888d1373/시스템구성도.png)

* ERD

![webterview_erd](/uploads/4ae73f39a0a00eddce5e1c1afc097e0d/webterview_erd.png)

* 상세 기능 설명
  - 해당 프로그램은 회사에서 면접 시간과 인원 등을 관리하는 관리자만이 회원가입을 진행하며, 면접관과 지원자는 관리자의 행동이 있어야만 면접에 참여할 수 있다.

- 관리자
  - 회원가입, 로그인
  - 날짜를 정하여 면접장 생성
  - 엑셀 파일을 통한 면접관 추가
  - 엑셀 파일을 통한 지원자 추가
  - 면접관/지원자 관리 (추가, 수정, 삭제 등)
  - 엑셀 파일을 통한 평가표 업로드
  - 지원자의 면접 영상 다운로드
  - 점수에 따른 지원자의 순위 열람
  - 면접관이 작성한 평가표 다운로드

- 면접관
  - 엑셀 파일에 작성된 이메일로 면접장 URL을 받을 수 있음
  - 해당 URL을 누르면 휴대폰을 통한 본인인증 진행
  - 본인인증 성공 시 면접장 입장하여, 한 화면에 지원자의 이력서/자기소개서, 지원자의 카메라, 평가표를 동시에 볼 수 있음
  - 평가표에 지원자에 대한 점수, 특이사항 입력 가능

- 지원자
  - 엑셀 파일에 작성된 이메일로 면접장 URL을 받을 수 있음
  - 해당 URL을 누르면 휴대폰을 통한 본인인증 진행
  - 본인인증 성공 시 면접장 입장하여, 면접 참여 가능    
  - 면접 후 해당 면접 영상 다운로드   
