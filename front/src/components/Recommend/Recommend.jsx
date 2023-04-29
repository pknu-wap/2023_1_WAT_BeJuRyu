import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Recommend() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  console.log("hihi");

  const RecommendView = (
    <S.Container>
      <S.Wrapper>
        <S.Title>현재 나의 기분을 표현해주세요</S.Title>
        <S.WhiteBox>
          <S.MyState>나의 기분을 입력하세요...</S.MyState>
        </S.WhiteBox>
        <S.SubmitButton>
          <strong>결과페이지로!</strong>
        </S.SubmitButton>
      </S.Wrapper>
    </S.Container>
  );

  return RecommendView;
}

export default Recommend;
