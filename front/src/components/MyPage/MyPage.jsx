import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
//import settingCookie from "../../utils/settingCookie";
import { useDispatch } from "react-redux";
import Logout from "./Logout";

function MyPage() {
  const navigate = useNavigate();
  //const dispatch = useDispatch();

  const checkHistory = () => {
    navigate("/history");
  };

  const MyPageView = (
    <S.Container>
      <S.Info>
        <Logout />
      </S.Info>
      <S.Wrapper>
        <S.Form>이름: 선예림</S.Form>
      </S.Wrapper>
      <S.SubmitButton onClick={checkHistory}>
        <strong>추천 히스토리 확인</strong>
      </S.SubmitButton>
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
