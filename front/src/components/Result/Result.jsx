/* TODO
1. [X] 주류 이미지와 대략적인 줄글이 들어갈 공간 component 작성
2. [] 주류 추천 결과를 whitebox내에 구현할 것이므로 whitebox자체를 image로 처리하여 결과공유가 가능한지, 앱 설치링크?*/
import S from "./styled";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import logo from "../../image/bejuryu.png";
import authClient from "../../apis/authClient";
import noAuthClient from "../../apis/noAuthClient";
import { List, ListItem, ListItemText } from "@mui/material";
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

const StyledTypography = styled(Typography)`
  font-family: "BejuryuFont";
`;

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
  height: 150px;
  object-fit: cover;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
`;

function Result() {
  const navigate = useNavigate();
  const location = useLocation();
  //const data = location.state?.data;
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [drinkInfo, setDrinkInfo] = useState(null);
  // 받은 데이터 저장 상태
  const [resultData, setResultData] = useState(null);
  // drinkId 상태
  const [drinkId, setDrinkId] = useState(null);

  // 이미지 디코딩 함수
  const decodeBase64 = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    for (let i = 0; i < binaryString.length; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }
    return URL.createObjectURL(new Blob([bytes.buffer], { type: "image/png" }));
  };
  // 주류 추천 결과 id
  const analysisId = location.state?.analysisId;

  // 주류 추천결과 멘트
  const getSentence = (sentiment) => {
    switch (sentiment) {
      case "SAD_3":
      case "SAD_2":
      case "SAD_1":
        return {
          level: "슬픔😥",
          comment:
            "힘들 하루와 슬픔을 느끼고 있는 당신에게,\n술 한 잔하며, 슬픔이 시들어간 마음을 다시 활기차게 만들어 보는 것은 어떨까요?",
        };
      case "MEDIAN":
        return {
          level: "중립",
          comment:
            "일상에서 조화와 안정을 느끼고 있는 당신에게,\n 술 한 잔하며, 가끔은 풀어내고 즐거움을 더해보는 것은 어떨까요?",
        };
      case "HAPPY_1":
      case "HAPPY_2":
      case "HAPPY_3":
        return {
          level: "😄기쁨😁",
          comment:
            "일상 속에서 행복을 만끽하고 있는 당신에게, \n술 한 잔하며, 지금 이 순간에 즐거움을 더해보는 것은 어떨까요?",
        };
      default:
        return {
          level: "",
          comment: "",
        };
    }
  };

  useEffect(() => {
    // 결과 불러오기 위한 로직
    const getSentiment = async () => {
      try {
        const response = await authClient({
          method: "get",
          url: `/analyze/${analysisId}`,
        });
        console.log(response);
        const data = response.data;
        console.log(data);
        setResultData(data);

        if (response) {
          console.log(response.data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    if (analysisId) {
      getSentiment();
    }
  }, [analysisId]);

  const handleFormSubmit = (e) => {
    e.preventDefault();

    navigate("/result");
  };

  const [inputValue, setInputValue] = useState("");

  // 결과 공유
  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleButtonClick = () => {
    navigate("/recommend");
  };

  return (
    <S.Container>
      <S.Wrapper>
        <S.Title>🤗분석 결과🤗</S.Title>
        <S.BtnList>
          <S.WhiteBox>
            <S.Title>
              현재 감정은 {getSentence(resultData?.sentiment).level} 입니다!
            </S.Title>
            <StyledImage
              src={resultData && decodeBase64(resultData?.drinkImage)}
              alt="주류 이미지"
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
            />
            <StyledList>
              <StyledListItem>
                <ListItemText
                  primary={resultData?.name}
                  secondary="주류 이름"
                />
                <ListItemText
                  primary={`${resultData?.dosu}%`}
                  secondary="도수"
                />
                <ListItemText
                  primary={`${resultData?.price}원`}
                  secondary="가격"
                />
              </StyledListItem>
              <StyledListItem>
                <ListItemText primary={resultData?.type} secondary="종류" />
                <ListItemText
                  primary={`${resultData?.sweetness}g`}
                  secondary="당도(100ml당)"
                />
                <ListItemText
                  primary={resultData?.volume}
                  secondary="용량(ml)"
                />
              </StyledListItem>
            </StyledList>
            <S.Text>
              BeJuryu의 comment: {getSentence(resultData?.sentiment).comment}
            </S.Text>
          </S.WhiteBox>
          <S.WhiteBox2>
            <S.Title>입력하신 데이터</S.Title>

            <S.ImageContainer>
              <S.Image
                src={decodeBase64(resultData?.facialExpression)}
              ></S.Image>
            </S.ImageContainer>
            <S.Text>
              <br />
              <br />
              {resultData.textExpression}
            </S.Text>
          </S.WhiteBox2>
        </S.BtnList>

        <S.BtnList>
          <S.SubmitButton onClick={handleInputChange}>
            결과 공유하기
          </S.SubmitButton>
          <S.SubmitButton onClick={handleButtonClick}>
            다시 추천받기
          </S.SubmitButton>
        </S.BtnList>
      </S.Wrapper>
    </S.Container>
  );
}

export default Result;
