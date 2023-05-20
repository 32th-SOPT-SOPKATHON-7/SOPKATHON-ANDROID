# SOPKATHON-ANDROID
그 누가 대안드로이드의 향해를 막을 수 있는가...

<div align="center">

<h2> 지역 문제를 빠르게 접하고 공유할 수 있는 서비스 - 동네 포차 </h2>

</div>

<h2> (개발자들) </h2>

<table align="center">
    <tr align="center">
        <td style="min-width: 150px;">
            <a href="https://github.com/KwakEuiJin">
              <img src="https://avatars.githubusercontent.com/u/93872496?v=4" width="200" alt="깃허브계정-프로필사진">
              <br />
              <b>KwakEuiJin</b>
            </a>d
        </td>
      <td style="min-width: 150px;">
            <a href="https://github.com/jihyunniiii">
              <img src="https://avatars.githubusercontent.com/u/103172971?v=4" width="200" alt="깃허브계정-프로필사진">
              <br />
              <b>jihyunniiii</b>
            </a>
        </td>
      <td style="min-width: 150px;">
            <a href="https://github.com/ss99x2002">
              <img src="https://avatars.githubusercontent.com/u/70602631?v=4" width="200" alt="깃허브계정-프로필사진">
              <br />
              <b>ss99x2002</b>
            </a>
        </td>
      <td style="min-width: 150px;">
            <a href="https://github.com/jooyyoo">
              <img src="https://avatars.githubusercontent.com/u/61531386?v=4" width="200" alt="깃허브계정-프로필사진">
              <br />
              <b>jooyyoo</b>
            </a>
        </td>
    </tr>
    <tr align="center">
        <td>
            곽의진 <br/>
      </td>
       <td>
            배지현 <br/>
      </td>
       <td>
            신서현 <br/>
      </td>
       <td>
            윤주영 <br/>
      </td>
    </tr>
    <tr align="center">
        <td>
            Android Large - 1 (지도뷰) <br/>
      </td>
       <td>
            Android Large - 2 (게시글 목록뷰) <br/>
      </td>
       <td>
            Android Large - 3 (게시글 상세뷰) <br/>
      </td>
       <td>
            Android Large - 4 (게시글 작성뷰)<br/>
      </td>
    </tr>
</table>

<h2>  📄 컨벤션 및 브랜치 전략 </h2>

<a href="https://www.notion.so/sungah/Android-Convention-a5cecbcb5622482b8147957e54158c7f" >7조 컨벤션</a>

<br/>

<br/>

<h2> 📸팀원끼리 응원의 메시지를 담은 사진 촬영 </h2>
<img src="https://github.com/jihyunniiii/BOJ/assets/103172971/49b2cd98-5624-4696-8237-0bcfff5da7d3"  alt="솝커톤-응원사진" />

결과가 어떻든 우리는 이 과정을 통해 성장할 것이고 이미 성장했습니다. 즐겨봐요!! 대 안드로이드 화이팅!!!

<h2> 💡솝커톤을 진행하며 구현하기 어려웠던 것에 대한 문제정의와 해결방안 </h2>

- 주영:  api를 각 뷰에 작성해주는 것이 어려웠음. 그 동안 api 사용이 굉장히 헷갈렸는데 이번 기회에 정말 잘 알고 갑니다(;-;). 시간이 없을거라 생각해 ui 를 이미지로 사용해서 적용했었는데 숫자와 같이 계속해서 바뀌는 부분을 마지막에 LinearLayout 으로 다시 작성해줬다.
- 서현: 모든 서울 지하철역의 데이터를 RoomDB에 담고, 사용자가 존재하지 않는 지하철역을 검색할 시 roomDB에서 값을 가져오지 않도록하는 예외처리를 하는게 어려웠다.
    - select query를 호출하는 함수의 반환값을 nullable 하도록 처리해 조건문으로 null일 경우에는 검색을 하지 않도록 하였다.
- 의진: 카카오맵을 이용한 지도 API 활용이 어려웠다.
    - 공식문서를 통한 코드분석으로 어려움을 극복할 수 있었다.
- 지현: 코루틴을 이용한 서버통신이 처음이라 만들어진 Service와 DTO 등을 통해 activity에서 서버통신 로직을 구현하는 것이 어려웠다.
    - 팀원들과 서버통신에 대해 이야기하고 반복적으로 함께 서버통신 로직을 작성해보면서 서버 통신 구현에 대해 이해하려고 노력하였다.
