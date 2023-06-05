/* TODO
  1. [o] 닉네임 변경 기능 추가 
  2. [o]리뷰많은 순 랭킹 조회
  3. [o]평점순 랭킹 조회 */

import S from "./styled";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import settingCookie from "../../utils/settingCookie";
import { useDispatch } from "react-redux";
import Logout from "./Logout";
import authClient from "../../apis/authClient";
import noAuthClient from "../../apis/noAuthClient";
import CircularProgress from "@mui/material/CircularProgress";

import { useSelector } from "react-redux";

function MyPage() {
  // 리뷰순 랭킹
  const [reviewRank, setReviewRank] = useState([]);
  // 평점순 랭킹
  const [scoreRank, setScoreRank] = useState([]);
  // 로딩 상태를 관리하는 변수
  const [isLoading, setIsLoading] = useState(true);
  const [selectedData, setSelectedData] = useState(null);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const userId = parseInt(localStorage.getItem("user-id"));

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

    // 닉네임 조회
    try {
      const response = await authClient({
        method: "get",
        url: `/member`,
      });
      console.log("떵공");
      console.log("member api response:", response);
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
      {/* 약관 추가 */}
      <S.Wrapper>
        <S.Form>
          BeJuRyu 서비스의 약관
          <br />
          <br />
          우리가 다 지켜줄게여 ~
        </S.Form>
      </S.Wrapper>
    </S.Container>
  );

  return MyPageView;
}

export default MyPage;
