<template>
  <!-- <div class="score">면접 평가창입니다.</div> -->
  <div class="score">
    <h3>평가표</h3>
      <table class="table">
        <tr>
          <th class="checks" scope="row">인성:</th>
          <td><input type="radio" name="chk_info" value="5">A</td>
          <td><input type="radio" name="chk_info" value="4">B</td>
          <td><input type="radio" name="chk_info" value="3">C</td>
          <td><input type="radio" name="chk_info" value="2">D</td>
          <td><input type="radio" name="chk_info" value="1">F</td>
        </tr>
        <tr>
          <th class="checks" scope="row">창의성:</th>
          <td><input type="radio" name="chk_info2" value="5">A</td>
          <td><input type="radio" name="chk_info2" value="4">B</td>
          <td><input type="radio" name="chk_info2" value="3">C</td>
          <td><input type="radio" name="chk_info2" value="2">D</td>
          <td><input type="radio" name="chk_info2" value="1">F</td>
        </tr>
        <tr>
          <th class="checks" scope="row">조직적응력:</th>
          <td><input type="radio" name="chk_info3" value="5">A</td>
          <td><input type="radio" name="chk_info3" value="4">B</td>
          <td><input type="radio" name="chk_info3" value="3">C</td>
          <td><input type="radio" name="chk_info3" value="2">D</td>
          <td><input type="radio" name="chk_info3" value="1">F</td>
        </tr>
        <tr>
          <th class="checks" scope="row">문제해결능력:</th>
          <td><input type="radio" name="chk_info4" value="5">A</td>
          <td><input type="radio" name="chk_info4" value="4">B</td>
          <td><input type="radio" name="chk_info4" value="3">C</td>
          <td><input type="radio" name="chk_info4" value="2">D</td>
          <td><input type="radio" name="chk_info4" value="1">F</td>
        </tr>
        <tr>
          <th class="checks" scope="row">책임감:</th>
          <td><input type="radio" name="chk_info5" value="5">A</td>
          <td><input type="radio" name="chk_info5" value="4">B</td>
          <td><input type="radio" name="chk_info5" value="3">C</td>
          <td><input type="radio" name="chk_info5" value="2">D</td>
          <td><input type="radio" name="chk_info5" value="1">F</td>
        </tr>
        <tr>
          <th class="checks" scope="row">태도:</th>
          <td><input type="radio" name="chk_info6" value="5">A</td>
          <td><input type="radio" name="chk_info6" value="4">B</td>
          <td><input type="radio" name="chk_info6" value="3">C</td>
          <td><input type="radio" name="chk_info6" value="2">D</td>
          <td><input type="radio" name="chk_info6" value="1">F</td>
        </tr>
      </table>

    <h3>특이사항</h3>
    <div><input class="card" type="text" style="width:100%; height:100px;"></div>  
      
  </div>
</template>

<script></script>

<style>
.score {
  background-color: #ffffff;
  width: 100%;
  padding: 3rem;
  border-radius: 1rem;
}

.score img {
  width: 100%;
}


input[type="radio"]{width:30px; /*or whatever */}


</style>
