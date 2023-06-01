/* TODO
1. 거의 완료
2. [X] 이미지 첨부 후 서버와 통신할 때 json로 넘기는 방법 찾아볼 것.(facial-expression: string => base64 인코딩
3. [X] 현재 줄글 작성 부분과 이미지 들어가는 게 따로 구현되있는데, 서버 넘길때 한 번에 넘겨야되므로 코드 합침 필요
  => formData*/
import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import PhotoUpload from "./PhotoUpload";
import authClient from "../../apis/authClient";

function Recommend() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [inputValue, setInputValue] = useState(""); // 텍스트 입력값 상태 추가

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    navigate("/result");
    if (selectedFile) {
      const reader = new FileReader();
      // base64 encoding해서
      reader.onloadend = async () => {
        const base64Data = reader.result.split(",")[1];
        reader.readAsDataURL(selectedFile); // 이미지 파일을 Base64로 인코딩
        // console.log(typeof parseInt(localStorage.getItem("user-id"), 10));
        // console.log(typeof base64Data);
        // console.log(typeof inputValue);
        try {
          const res = await authClient({
            method: "post",
            url: "/analyze/sources",
            data: {
              userId: parseInt(localStorage.getItem("user-id"), 10),
              textExpression: inputValue,
              facialExpression: base64Data,
            },
          });
          if (res) {
            console.log(res);
          }

          // 서버 응답 처리
        } catch (error) {
          if (error.response) {
            // 서버 응답 에러
            const err = error.response.data;
            console.log(err);
          } else {
            // 네트워크 에러 또는 클라이언트 에러
            console.log("Error:", error.message);
          }
        }
      };

      reader.readAsDataURL(selectedFile);
      // const userId = localStorage.getItem("user-id");
      // console.log(typeof parseInt(userId, 10));
    }
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
            <S.MyState>
              기분이 어떠신가요? 감정이 표현되는 언어가 들어가있으면 좋아요!
            </S.MyState>
          )}
        </S.WhiteBox>
        <PhotoUpload
          setSelectedFile={handleFileChange}
          setImagePreview={setImagePreview}
          imagePreview={imagePreview}
        />

        <S.SubmitButton onClick={handleFormSubmit}>분석 시작</S.SubmitButton>
      </S.Wrapper>
    </S.Container>
  );
}

export default Recommend;
