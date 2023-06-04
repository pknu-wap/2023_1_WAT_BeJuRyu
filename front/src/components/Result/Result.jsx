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

    const formData = new FormData();
    formData.append("image", selectedFile);
    //formData.append("mood", inputValue);

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
        <S.Title>현재 감정은{resultData.sentiment}입니다.</S.Title>
        <S.WhiteBox>
          <S.CenteredFormBox>
            <S.Title>🍺주류정보🍺</S.Title>
            <StyledImage src={logo} alt="주류 이미지" />
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
