/* TODO
1. 거의 완료
2. [] 이미지 첨부 후 서버와 통신할 때 URL로 넘기는 방법 찾아볼 것.(facial-expression: string
3. [] 현재 줄글 작성 부분과 이미지 들어가는 게 따로 구현되있는데, 서버 넘길때 한 번에 넘겨야되므로 코드 합침 필요*/
import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import PhotoUpload from "./PhotoUpload";

function Recommend() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [inputValue, setInputValue] = useState(""); // 텍스트 입력값 상태 추가

  const handleFormSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("mood", inputValue);
    formData.append("image", selectedFile);

    // 서버와의 통신 들어갈 부분

    navigate("/result");
  };

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleFileChange = (file) => {
    setSelectedFile(file);
    //setInputValue(e.target.value);
  };

  return (
    <S.Container>
      <S.Wrapper>
        <S.Title>주류 추천을 받기 위해서 필요한 정보를 알려주세요!</S.Title>
        <S.WhiteBox>
          <S.textInput value={inputValue} onChange={handleInputChange} />
          {inputValue === "" && (
            <S.MyState>현재 기분이 어떠신가요? 간단하게 적어주세요 !</S.MyState>
          )}
        </S.WhiteBox>
        <PhotoUpload
          setSelectedFile={handleFileChange}
          setImagePreview={setImagePreview}
          imagePreview={imagePreview}
        />

        <S.SubmitButton onClick={handleFormSubmit}>
          <strong>분석 시작</strong>
        </S.SubmitButton>
      </S.Wrapper>
    </S.Container>
  );
}

export default Recommend;
