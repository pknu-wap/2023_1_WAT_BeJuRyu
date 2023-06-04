/*TODO (목요일 완료해)
    1. 페이지 전체적인 ui
    2. 이미지를 눌렸을때 띄워지는 주류정보창
    3. 주류정보 & 평점 및 리뷰 
*/
import React from "react";
import S from "./styled";
import styled from "styled-components";
import { useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import Rating from "@mui/material/Rating";
import StarIcon from "@mui/icons-material/Star";
import Box from "@mui/material/Box";
import authClient from "../../apis/authClient";
import Container from "@mui/material/Container";
import { List, ListItem, ListItemText } from "@mui/material";
import axios from "axios";
import noAuthClient from "../../apis/noAuthClient";

const StyledList = styled(List)`
  display: flex;
  //flex-direction: column;
  flex-wrap: wrap;
  align-items: center;
  //justify-content: space-between;
  //border: 1px solid #ccc;
  padding: 10px;
  font-family: "BejuryuFont";
`;

const StyledListItem = styled.li`
  border-bottom: 1px solid #ccc;
  padding: 10px;
  flex: 1 0 48%;

  & span {
    font-family: "BejuryuFont";
  }
`;

const StyledImage = styled.img`
  width: 100px;
  height: 200px;
  object-fit: cover;
`;

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

  const [alertMessage, setAlertMessage] = useState("");

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
          url: `drinks/${juryuId}`,
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
          url: `drinks/${juryuId}/rating`,
        });
        if (response) {
          console.log(response.data);
        }
      } catch (error) {
        console.log("juryuId error!", error.message);
      }
    };
    //console.log(drinkReviewList);

    const juryuReview = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `drinks/${juryuId}/reviews`,
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
    0.5: "형편없어",
    1: "맛없는 척",
    1.5: "그냥 그래",
    2: "조금 괜찮음",
    2.5: "맛있는 편",
    3: "꽤 괜찮을수도?",
    3.5: "정말 맛있음",
    4: "맛있는 최고봉",
    4.5: "감동적인 맛 🤤",
    5: "개쩐다❤️",
  };

  const dictionary = () => {
    navigate("/dictionary");
  };

  const ranking = () => {
    navigate("/MyPage");
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
    //alert("리뷰 등록이 완료되었습니다!");
    //navigate("/dictionary");

    const labelKey = parseInt(value);

    try {
      const res = await authClient({
        method: "post",
        url: `drinks/${juryuId}/reviews`,
        data: {
          userId: localStorage.getItem("user-id"),
          comment: inputValue,
          score: labelKey * 2,
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

    setAlertMessage("등록되었습니다. 최신 리뷰를 확인하려면 새로고침해주세요!");
  };

  return (
    <S.Container>
      <S.Wrapper>
        <S.WhiteBox>
          <S.CenteredFormBox>
            <S.Title>🍺주류정보🍺</S.Title>
            <StyledImage
              src={drinkInfo && decodeBase64(drinkInfo.image)}
              alt="주류 이미지"
            />
            <StyledList>
              <StyledListItem>
                <ListItemText primary={drinkInfo?.name} secondary="주류 이름" />
              </StyledListItem>
              <StyledListItem>
                <ListItemText primary={drinkInfo?.dosu} secondary="도수" />
              </StyledListItem>
              <StyledListItem>
                <ListItemText primary={drinkInfo?.price} secondary="가격" />
              </StyledListItem>
              <StyledListItem>
                <ListItemText primary={drinkInfo?.type} secondary="종류" />
              </StyledListItem>
            </StyledList>
          </S.CenteredFormBox>
          <S.FormBox>
            <S.Title>Be주류 사용자들의 한줄 리뷰</S.Title>

            {getCurrentPageItems().map((reviews) => (
              <p key={reviews.id}>
                {reviews.comment}{" "}
                <p>
                  {reviews.nickname} {reviews.date}
                </p>
              </p>
            ))}
            <S.ButtonContainer>
              {/* 이전 페이지로 돌아가기 버튼 */}
              {page > 1 && (
                <S.PageButton onClick={goToPreviousPage}>
                  이전 페이지
                </S.PageButton>
              )}

              {/* 더 보기 버튼 */}
              {page < totalPages && (
                <S.PageButton onClick={goToNextPage}>다음 페이지</S.PageButton>
              )}
            </S.ButtonContainer>
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
                    style={{ opacity: 0.55, color: "action" }}
                    fontSize="inherit"
                    sx={{
                      color: "yellow",
                    }}
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
            {alertMessage && <p>{alertMessage}</p>}
            {/* <S.ReButton>리뷰 보러가기</S.ReButton> */}
          </S.ReviewBox>
        </S.WhiteBox>
        <S.ButtonContainer>
          <S.ReButton onClick={dictionary}>다른 주류 둘러보기</S.ReButton>
          <S.ReButton onClick={ranking}>BE주류 랭킹</S.ReButton>
        </S.ButtonContainer>
      </S.Wrapper>
    </S.Container>
  );
}

export default JuryuInfo;
