import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import PhotoUpload from "./PhotoUpload";

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

    return (
      <S.Container>
        <S.Wrapper>
          <S.Title>주류 추천을 받기 위해서 필요한 정보를 알려주세요!</S.Title>
          <S.WhiteBox>
            <S.textInput value={inputValue} onChange={handleInputChange} />
            {inputValue === "" && (
              <S.MyState>
                현재 기분이 어떠신가요? 간단하게 적어주세요 !
              </S.MyState>
            )}
          </S.WhiteBox>
          <PhotoUpload
            setSelectedFile={setSelectedFile}
            setImagePreview={setImagePreview}
            imagePreview={imagePreview}
          />

          <S.SubmitButton onClick={handleFormSubmit}>
            <strong>분석 시작</strong>
          </S.SubmitButton>
        </S.Wrapper>
      </S.Container>
    );
  };

  return <RecommendView />;
}

export default Recommend;
