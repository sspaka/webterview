<template>
  <div id="main-page">
    <header>
      <h1>
        <a href="#" class="logo"
          ><img src="@/../public/resources/images/logo.png" width="240"
        /></a>
      </h1>
      <div>
        <input
          class="btn btn-large"
          type="button"
          id="login-button"
          value="로그인"
        />
      </div>
    </header>
    <div id="banner" style="text-align: center">
      <h1>평가 시스템이 결합된 면접 서비스</h1>
      <h2>면접에 꼭 필요한 서비스들을 한번에</h2>
    </div>
    <div class="manual">
      <h1>지원자의 정보를 한눈에 보세요</h1>
      <div>
        불필요한 종이 대신 모니터 하나만으로 면접 및 평가를 진행해 보세요
      </div>
    </div>
    <div class="manual">
      <h1>순위를 매겨 드립니다</h1>
      <div>
        면접 종료와 동시에 지원자들의 순위와 평균 점수를 살필 수 있습니다.
      </div>
    </div>
    <div class="manual">
      <h1>면접을 복기하세요</h1>
      <div>
        면접이 끝난 뒤 면접 영상을 복기하는 것은 면접관과 지원자 모두에게 도움이
        될 것입니다.
      </div>
    </div>
    <div class="manual">
      <h1>내 마음대로 커스텀</h1>
      <div>
        지원자 정보, 자기소개서, 면접 화면, 평가표 등을 사용자의 편의에 따라
        임의로 배치할 수 있습니다.
      </div>
    </div>
    <footer>
      <div>Copyright ©2022 Webterview, Inc. All rights reserved.</div>
    </footer>
  </div>
</template>

<script>
export default {};
</script>

<style>
#main-page {
}

#banner {
  height: 40rem;
  overflow: hidden;
  margin: 0;
  background-image: url(../../public/resources/images/main.png);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  color: #fff;
}

.manual {
  height: 40rem;
}

header {
  width: 100%;
  text-align: center;
  position: relative;
  height: 80px;
  background-color: #fff;
}
header h1 {
  position: absolute;
  top: 5px;
  left: 5%;
}

#login-button {
  position: absolute;
  top: 10px;
  right: 5%;
  padding: 10px;
  margin: 10px;
  background-color: #f05454;
  color: white;
}
</style>
