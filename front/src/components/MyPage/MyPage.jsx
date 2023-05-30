/* TODO
  1. [o] 닉네임 변경 기능 추가 */

import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
//import settingCookie from "../../utils/settingCookie";
import { useDispatch } from "react-redux";
import Logout from "./Logout";

import { useSelector } from "react-redux";

function MyPage() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const checkHistory = () => {
    navigate("/history");
  };

  const changeNick = () => {
    // const newNickname = "choonsik";

    navigate("/nickChange");
  };
  const userName = useSelector((state) => state.name.name);

  const MyPageView = (
    <S.Container>
      <S.Info>
        <Logout />
        <S.LogoutButton type="button" onClick={changeNick}>
          닉네임 변경
        </S.LogoutButton>
      </S.Info>
      <S.Wrapper>
        {/* 전역 상태관리 기능 추가 */}
        <S.Form>
          사용자: {userName} 님
          <S.SubmitButton onClick={checkHistory}>
            추천 히스토리 확인
          </S.SubmitButton>
        </S.Form>
      </S.Wrapper>
    </S.Container>
  );

  return MyPageView;
}

export default MyPage;
