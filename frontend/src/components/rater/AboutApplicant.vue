<template>
  <!-- <div class="info">지원자 정보와 자소서 창입니다.</div> -->
  <div class="impormation">
    <h3>입사지원서</h3>
    <table class="table">
  <!-- <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead> -->
  <tbody>
    <tr>
      <th scope="row">이름:</th>
      <td colspan="3">이성찬</td>
      <!-- <td>Otto</td>
      <td>@mdo</td> -->
    </tr>
    <tr>
      <th scope="row">나이:</th>
      <td colspan="3">27</td>
      <!-- <td>Thornton</td>
      <td>@fat</td> -->
    </tr>
    <tr>
      <th scope="row">기술스택:</th>
      <td>Vue,</td>
      <td>Js,</td>
      <td>python</td>
    </tr>
    <tr>
      <th scope="row">거주지:</th>
      <td colspan="3">광주광역시</td>
    </tr>
  </tbody>
</table>
    <!-- <img src="../../assets/recruit.jpg" alt="recruit" class="recruit" /> -->
    <!-- <img src="../../assets/introduce.jpg" alt="introduce" class="introduce" /> -->

  </div>
</template>

<script></script>

<style scoped>
.impormation {
  background-color: #ffffff;
  width: 100%;
  padding: 3rem;
  border-radius: 1rem;
}

.impormation img {
  width: 100%;
}

th{
  text-align: center;
}
</style>
