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

function Result() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state?.data;

  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [drinkInfo, setDrinkInfo] = useState(null);
  // 받은 데이터 저장 상태
  const [resultData, setResultData] = useState(null);
  // drinkId 상태
  const [drinkId, setDrinkId] = useState(null);

  // 결과 불러오기 위한 로직
  useEffect(() => {
    const getSentiment = async (e, analysisId) => {
      try {
        const response = await authClient({
          method: "get",
          url: `/analyze/${analysisId}`,
        });
        setResultData(response.data.data);
        setDrinkId(response.data.data.drinkId);
        console.log(resultData);
      } catch (error) {
        const err = error.response.data;
        console.log(err);
      }
    };

    if (data) {
      getSentiment(data.id);
    }
  }, [data]);

  useEffect(() => {
    const getDrink = async () => {
      try {
        const response = await noAuthClient({
          method: "get",
          url: `/drinks/${drinkId}`,
        });
        setDrinkInfo(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    getDrink();
  }, [drinkId]);

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

  // 주류 추천결과 멘트

  const SAD_CONTENT =
    "힘들 하루와 슬픔을 느끼고 있는 당신에게,\n술 한 잔하며, 슬픔이 시들어간 마음을 다시 활기차게 만들어 보는 것은 어떨까요?";
  const HAPPY_CONTENT =
    "일상 속에서 행복을 만끽하고 있는 당신에게, \n술 한 잔하며, 지금 이 순간에 즐거움을 더해보는 것은 어떨까요?";
  const NEUTRAL_CONTENT =
    "일상에서 조화와 안정을 느끼고 있는 당신에게,\n 술 한 잔하며, 가끔은 풀어내고 즐거움을 더해보는 것은 어떨까요?";

  // api 요청 성공적으로 되면 그때 sentiment 추가할 것임.
  // resultData.sentiment;

  return (
    <S.Container>
      <S.Wrapper>
        <S.Title>현재 감정은 기쁨!입니다.</S.Title>
        <S.WhiteBox>
          <S.Title>🤗분석결과🤗</S.Title>
          <StyledImage
            src={logo}
            alt="주류 이미지"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
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

          <S.Text>BeJuryu의 comment: {SAD_CONTENT}</S.Text>
        </S.WhiteBox>

        <S.BtnList>
          <S.SubmitButton onClick={handleFormSubmit}>
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
