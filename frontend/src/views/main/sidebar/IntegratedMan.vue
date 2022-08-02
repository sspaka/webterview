<template>
    <div class="d-flex flex-column justify-content-center align-items-between mt-5">
        <div class="head mb-4">정보 업로드</div>
        <div class="d-flex justify-content-center align-items-between">
            <div class="col-3 container border border-dark mx-2"> 
                <h2>면접관</h2>
                <form @submit.prevent="uploadInterviewer(interviewer)">
                    <div class="d-flex justify-content-center">
                        <input type="file">
                        <button type="button" class="btn btn-primary">업로드</button>
                    </div>
                </form>
                <div data-bs-spy="scroll" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" class="scrollspy-example bg-light p-3 rounded-2" tabindex="0">
                    <h4 id="scrollspyHeading1">First heading</h4>
                    <p>...</p>
                    <h4 id="scrollspyHeading2">Second heading</h4>
                    <p>...</p>
                    <h4 id="scrollspyHeading3">Third heading</h4>
                    <p>...</p>
                    <h4 id="scrollspyHeading4">Fourth heading</h4>
                    <p>...</p>
                    <h4 id="scrollspyHeading5">Fifth heading</h4>
                    <p>...</p>
                </div>
            </div>
            <div class="col-3 container border border-dark mx-2"> 
                <h2>지원자</h2>
                <form  @submit.prevent="uploadApplicant(applicant)">
                    <div class="d-flex justify-content-center">
                        <input type="file">
                        <button type="button" class="btn btn-primary">업로드</button>
                    </div>
                </form>
                <div>목록쓰
                    <ul class="list-group">
                        <li class="list-group-item">An item</li>
                        <li class="list-group-item">A second item</li>
                        <li class="list-group-item">A third item</li>
                        <li class="list-group-item">A fourth item</li>
                        <li class="list-group-item">And a fifth one</li>
                    </ul>
                </div>
            </div>
            <div class="col-5 container border border-dark mx-2"> 
                <h2>평가표</h2>
                <form  @submit.prevent="uploadScoreBoard(scoreBoard)">
                    <div class="d-flex justify-content-center">
                        <input type="file">
                        <button type="button" class="btn btn-primary">업로드</button>
                    </div>
                </form>
                <div>목록쓰
                    <ul class="list-group">
                        <li class="list-group-item">An item</li>
                        <li class="list-group-item">A second item</li>
                        <li class="list-group-item">A third item</li>
                        <li class="list-group-item">A fourth item</li>
                        <li class="list-group-item">And a fifth one</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
  
</template>

<script>
export default {

}
</script>

<style scoped>
    .btn {
        background-color: #30475e;
        color: white;
    }

    .container {
        background-color: #fff;
        padding: 2px;
    }
</style>