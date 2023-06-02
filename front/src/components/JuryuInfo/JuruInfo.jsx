/*TODO (목요일 완료해)
    1. 페이지 전체적인 ui
    2. 이미지를 눌렸을때 띄워지는 주류정보창
    3. 주류정보 & 평점 및 리뷰 
*/
import React from "react";
import S from "./styled";
import { useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import Rating from "@mui/material/Rating";
import StarIcon from "@mui/icons-material/Star";
import Box from "@mui/material/Box";
import authClient from "../../apis/authClient";
import Container from "@mui/material/Container";
import InfiniteScroll from "react-infinite-scroller";
import axios from "axios";
import noAuthClient from "../../apis/noAuthClient";

function JuryuInfo() {
  const location = useLocation();
  const [value, setValue] = useState(2);
  const [hover, setHover] = useState(-1);
  // 주류 리뷰 텍스트 입력값 상태
  const [inputValue, setInputValue] = useState("");
  // 이미지
  const [decodedImage, setDecodedImage] = useState(null);
  const [drinkInfo, setDrinkInfo] = useState(null);
  // 주류 리뷰 리스트
  const [drinkReviewList, setDrinkReviewList] = useState([]);
  // 리뷰 리스트 길이에 따라 pagination
  // const [startIndex, setStartIndex] = useState(0); // 시작 인덱스 상태
  // const [endIndex, setEndIndex] = useState(4); // 종료 인덱스 상태
  // const [showMore, setShowMore] = useState(false); // 더보기 상태 추가
  const [page, setPage] = useState(1);
  const [perPage, setPerPage] = useState(4);

  const totalPages = Math.ceil(drinkReviewList.length / perPage);

  // 현재 페이지에 해당하는 항목들만 추출하는 함수
  const getCurrentPageItems = () => {
    const startIndex = (page - 1) * perPage;
    const endIndex = startIndex + perPage;
    return drinkReviewList.slice(startIndex, endIndex);
  };

  // 다음 페이지로 이동하는 함수
  const goToNextPage = () => {
    if (page < totalPages) {
      setPage(page + 1);
    }
  };

  // 이전 페이지로 이동하는 함수
  const goToPreviousPage = () => {
    if (page > 1) {
      setPage(page - 1);
    }
  };

  const juryuId = location.state?.juryuId;

  // 이미지 디코딩 함수
  const decodeBase64 = (base64) => {
    try {
      const binaryString = window.atob(base64);
      const bytes = new Uint8Array(binaryString.length);
      for (let i = 0; i < binaryString.length; i++) {
        bytes[i] = binaryString.charCodeAt(i);
      }
      return URL.createObjectURL(
        new Blob([bytes.buffer], { type: "image/png" })
      );
    } catch (error) {
      console.error(error);
      return null;
    }
  };

  // 주류 정보 import
  useEffect(() => {
    const JuryuData = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/${juryuId}`,
        });
        //const { name, image, dosu, price } = response.data;
        //const decodedImage = decodeBase64(image)'

        setDrinkInfo(response.data);

        if (response) {
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    const juryuScore = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/${juryuId}/rating`,
        });
        if (response) {
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };
    //console.log(drinkReviewList);

    const juryuReview = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/${juryuId}/reviews`,
        });

        if (response) {
          setDrinkReviewList(response.data.reviews);
        }
      } catch (error) {
        console.error(error);
      }
    };

    JuryuData();
    juryuScore();
    juryuReview();
  }, [juryuId]);

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

  return (
    <S.Container>
      <S.Wrapper>
        <S.WhiteBox>
          <S.FormBox>
            <S.Title>주류정보</S.Title>
            <Container>
              <img
                src={drinkInfo && decodeBase64(drinkInfo.image)}
                width="200"
                height="300"
                alt="주류 이미지"
              />
              <p>{drinkInfo?.name}</p>
              <p>{drinkInfo?.dosu}</p>
              {drinkInfo?.price}
            </Container>
          </S.FormBox>
          <S.FormBox>
            <S.Title>Be주류 사용자들의 한줄 리뷰</S.Title>

            {/* {drinkReviewList.map((reviews) => (
              <p key={reviews.id}>
                {reviews.comment}{" "}
                <p>
                  {reviews.nickname} {reviews.date}
                </p>
              </p>
            ))} */}
            {getCurrentPageItems().map((reviews) => (
              <p key={reviews.id}>
                {reviews.comment}{" "}
                <p>
                  {reviews.nickname} {reviews.date}
                </p>
              </p>
            ))}

            {/* 이전 페이지로 돌아가기 버튼 */}
            {page > 1 && <button onClick={goToPreviousPage}>앞으로</button>}

            {/* 더 보기 버튼 */}
            {page < totalPages && (
              <button onClick={goToNextPage}>더 많은 리뷰!</button>
            )}
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
            {/* <S.ReButton>리뷰 보러가기</S.ReButton> */}
          </S.ReviewBox>
        </S.WhiteBox>
        <S.ReButton onClick={dictionary}>다른 주류 둘러보기</S.ReButton>
      </S.Wrapper>
    </S.Container>
  );
}

export default JuryuInfo;
