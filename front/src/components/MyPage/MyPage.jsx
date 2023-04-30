import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
//import settingCookie from "../../utils/settingCookie";
import { useDispatch } from "react-redux";

function MyPage() {
  const navigate = useNavigate();
  //const dispatch = useDispatch();

  const logout = () => {
    //settingCookie("remove");
    navigate("/");
  };

  const MyPageView = (
    <S.Container>
      <S.Info>
        <S.logoutButton onClick={logout}>로그아웃</S.logoutButton>
      </S.Info>
      <S.Wrapper>
        <S.SubmitButton>
          <strong>결과보기</strong>
        </S.SubmitButton>
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
