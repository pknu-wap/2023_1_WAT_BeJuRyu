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
  //console.log(typeof userId);

  // 주류 랭킹 보여주기 위해 api 요청
  useEffect(() => {
    const getReviewRanking = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/rankings/review`,
        });
        if (response) {
          setReviewRank(response.data.drinks);
          setIsLoading(false);
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    const getScoreRanking = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/rankings/rating`,
        });
        if (response) {
          setScoreRank(response.data.drinks);
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    // API 요청 함수 호출
    getReviewRanking();
    getScoreRanking();
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

  const handleReviewData = () => {
    setSelectedData(reviewRank);
  };

  const handleScoreData = () => {
    setSelectedData(scoreRank);
  };
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
        <S.Form>
          {userName} 님 오늘의 기분은 어떠신가요? Be주류 랭킹을 확인할 수
          있어요!
          {isLoading ? (
            <div>Loading....</div>
          ) : (
            <>
              <S.ButtonContainer>
                <S.SubmitButton onClick={handleReviewData}>
                  리뷰많은순
                </S.SubmitButton>
                <S.SubmitButton onClick={handleScoreData}>
                  평점순
                </S.SubmitButton>
              </S.ButtonContainer>
              <S.juruBox>
                <S.JuruBoxContainer>
                  {selectedData &&
                    selectedData.map((drink, index) => (
                      <S.ReviewBox key={index}>
                        <div>
                          <h3>{drink.name}</h3>
                          <p>평점: {drink.score}</p>
                        </div>
                      </S.ReviewBox>
                    ))}
                </S.JuruBoxContainer>
              </S.juruBox>
            </>
          )}
        </S.Form>
      </S.Wrapper>
    </S.Container>
  );

  return MyPageView;
}

export default MyPage;
