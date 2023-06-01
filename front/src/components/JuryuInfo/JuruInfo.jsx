/*TODO (목요일 완료해)
    1. 페이지 전체적인 ui
    2. 이미지를 눌렸을때 띄워지는 주류정보창
    3. 주류정보 & 평점 및 리뷰 
*/
import React from "react";
import S from "./styled";
import { useNavigate, useLocation } from "react-router-dom";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import Rating from "@mui/material/Rating";
import StarIcon from "@mui/icons-material/Star";
import Box from "@mui/material/Box";
import authClient from "../../apis/authClient";

function JuryuInfo() {
  const location = useLocation();
  const [value, setValue] = useState(2);
  const [hover, setHover] = useState(-1);
  // 주류 리뷰 텍스트 입력값 상태
  const [inputValue, setInputValue] = useState("");
  const juryuId = location.state?.juryuId;

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const navigate = useNavigate();

  const labels = {
    1: "Useless",
    2: "Useless+",
    3: "Poor",
    4: "Poor+",
    5: "Ok",
    6: "Ok+",
    7: "Good",
    8: "Good+",
    9: "Excellent",
    10: "Excellent+",
  };

  const dictionary = () => {
    navigate("/dictionary");
  };

  function getLabelText(value) {
    return `${value} Star${value !== 1 ? "s" : ""}, ${labels[value]}`;
  }

  // 현재 날짜와 시각을 "yyyy.MM.dd HH:mm" 형식으로 반환하는 함수
  function getCurrentDateTime() {
    const date = new Date();

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");

    const formattedDateTime = `${year}-${month}-${day} ${hours}:${minutes}`;

    return formattedDateTime;
  }

  const currentDate = getCurrentDateTime(); // 현재 시각 가져오기

  const handleReviewSubmit = async (e, juryuId) => {
    e.preventDefault();
    alert("리뷰 등록이 완료되었습니다!");
    navigate("/dictionary");

    const labelKey = parseInt(value);
    try {
      const res = await authClient({
        method: "post",
        url: `/drinks/${juryuId}/reviews`,
        data: {
          userId: localStorage.getItem("user-id"),
          comment: inputValue,
          score: labelKey,
          date: currentDate,
        },
      });
      if (res) {
        console.log(res.data);
        console.log(res.status); // HTTP 응답 상태 코드
        console.log(res.statusText); // HTTP 응답 상태 텍스트
        console.log(res.headers); // 응답 헤더 정보
      }
    } catch (error) {
      if (error.response) {
        const err = error.response.data;
        console.log(err);
        console.log(error.message);
      } else {
        console.log("ERROR:", error.message);
      }
    }
  };

  const JuryuView = (
    <S.Container>
      <S.Wrapper>
        <S.WhiteBox>
          <S.FormBox>
            <S.Title>주류정보</S.Title>
          </S.FormBox>
          <S.ReviewBox>
            <S.Title>주류를 평가해주세요!</S.Title>
            <Box
              sx={{
                width: 200,
                display: "flex",
                alignItems: "center",
              }}
            >
              <Rating
                name="hover-feedback"
                value={value}
                precision={0.5}
                getLabelText={getLabelText}
                onChange={(event, newValue) => {
                  setValue(newValue);
                }}
                onChangeActive={(event, newHover) => {
                  setHover(newHover);
                }}
                emptyIcon={
                  <StarIcon
                    style={{ opacity: 0.55 }}
                    fontSize="inherit"
                    // sx={{
                    //   color: "yellow",
                    // }}
                  />
                }
              />
              {value !== null && (
                <Box sx={{ ml: 2 }}>{labels[hover !== -1 ? hover : value]}</Box>
              )}
            </Box>
            <S.textInput
              value={inputValue}
              onChange={handleInputChange}
            ></S.textInput>
            <S.ReButton onClick={(e) => handleReviewSubmit(e, juryuId)}>
              리뷰 등록하기
            </S.ReButton>
            <S.ReButton>리뷰 보러가기</S.ReButton>
          </S.ReviewBox>
        </S.WhiteBox>
        <S.ReButton onClick={dictionary}>다른 주류 둘러보기</S.ReButton>
      </S.Wrapper>
    </S.Container>
  );

  return JuryuView;
}

export default JuryuInfo;
