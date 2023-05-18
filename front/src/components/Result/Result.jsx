/* TODO
1. [] 주류 이미지와 대략적인 줄글이 들어갈 공간 component 작성
2. [] 주류 추천 결과를 whitebox내에 구현할 것이므로 whitebox자체를 image로 처리하여 결과공유가 가능한지, 앱 설치링크?*/
import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Recommend() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFormSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("image", selectedFile);
    //formData.append("mood", inputValue);

    navigate("/result");
  };

  const RecommendView = () => {
    const [inputValue, setInputValue] = useState("");

    const handleInputChange = (e) => {
      setInputValue(e.target.value);
    };

    const handleButtonClick = () => {
      navigate("/recommend");
    };

    return (
      <S.Container>
        <S.Wrapper>
          <S.Title>현재 당신에게 어울리는 주류는 바로!</S.Title>
          <S.WhiteBox>주류 추천 결과가 들어올 공간입니다.</S.WhiteBox>
          <S.BtnList>
            <S.SubmitButton onClick={handleFormSubmit}>
              <strong>결과 공유하기</strong>
            </S.SubmitButton>
            <S.SubmitButton onClick={handleButtonClick}>
              <strong>다시 추천받기</strong>
            </S.SubmitButton>
          </S.BtnList>
        </S.Wrapper>
      </S.Container>
    );
  };

  return <RecommendView />;
}

export default Recommend;