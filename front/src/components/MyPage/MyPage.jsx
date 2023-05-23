/* TODO
  1. [o] 닉네임 변경 기능 추가 */

import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
//import settingCookie from "../../utils/settingCookie";
import { useDispatch } from "react-redux";
import Logout from "./Logout";
import nickChange from "./Nickchange";

function MyPage() {
  const navigate = useNavigate();
  //const dispatch = useDispatch();

  const checkHistory = () => {
    navigate("/history");
  };

  const nickChange = () => {
    navigate(nickChange);
  };

  const MyPageView = (
    <S.Container>
      <S.Info>
        <Logout />
        <S.logoutButton onClick={nickChange}>닉네임 변경</S.logoutButton>
      </S.Info>
      <S.Wrapper>
        {/* 전역 상태관리 기능 추가 */}
        <S.Form>
          사용자: 선예림 님
          <S.SubmitButton onClick={checkHistory}>
            <strong>추천 히스토리 확인</strong>
          </S.SubmitButton>
        </S.Form>
      </S.Wrapper>
    </S.Container>
  );

  //const params = new URLSearchParams(window.location.search);
  //const accessToken = params.get("access_token");
  //const refreshToken = params.get("refresh_token");
  //console.log("access token:", accessToken);
  //console.log("refresh token:", refreshToken);

  return MyPageView;
}

export default MyPage;
