/* TODO
  1. [o] 닉네임 변경 기능 추가 
  2. [ ]리뷰많은 순 랭킹 조회
  3. [ ]평점순 랭킹 조회 */

import S from "./styled";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import settingCookie from "../../utils/settingCookie";
import { useDispatch } from "react-redux";
import Logout from "./Logout";
import authClient from "../../apis/authClient";
import noAuthClient from "../../apis/noAuthClient";

import { useSelector } from "react-redux";

function MyPage() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const userId = localStorage.getItem("user-id");
  //console.log(typeof userId);

  // 주류 랭킹 보여주기 위해 api 요청
  useEffect(() => {
    const ReviewData = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/rankings/review`,
        });
        if (response) {
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    const ScoreData = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/rankings/rating`,
        });
        if (response) {
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    // API 요청 함수 호출
    ReviewData();
    ScoreData();
  }, []);

  const checkHistory = async (e) => {
    e.preventDefault();
    navigate("/history");

    try {
      const res = await authClient({
        method: "get",
        url: "/analyze",
      });
      console.log(res);
    } catch (error) {
      if (error.response) {
        const err = error.response.data;
        console.log(err);
      } else {
        // 네트워크 에러 또는 클라이언트 에러
        console.log("Error:", error.message);
      }
    }

    try {
      const response = await noAuthClient({
        method: "get",
        url: `/member/${userId}`,
      });
      console.log(response);
    } catch (error) {
      if (error.response) {
        const err = error.response.data;
        console.log(err);
      }
    }
  };

  const changeNick = () => {
    navigate("/nickChange");
  };

  const userName = localStorage.getItem("nickname");
  const MyPageView = (
    <S.Container>
      <S.Info>
        <S.LogoutButton onClick={checkHistory}>히스토리 확인</S.LogoutButton>

        <S.LogoutButton type="button" onClick={changeNick}>
          닉네임 변경
        </S.LogoutButton>
        <Logout />
      </S.Info>
      <S.Wrapper>
        {/* 전역 상태관리 기능 추가 */}
        <S.Form>
          {userName} 님 오늘의 기분은 어떠신가요? 다른 Be주류 유저들의 주류
          추천이에요!
        </S.Form>
      </S.Wrapper>
    </S.Container>
  );

  return MyPageView;
}

export default MyPage;
