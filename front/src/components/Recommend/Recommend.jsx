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
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import { styled } from "@mui/system";

const StyledTypography = styled(Typography)`
  font-family: "BejuryuFont";
`;

function Recommend() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [inputValue, setInputValue] = useState(""); // 텍스트 입력값 상태 추가
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const currentDate = getCurrentDateTime(); // 현재 시각 가져오기
  const handleFormSubmit = async (e) => {
    e.preventDefault();
    navigate("/result");
    if (selectedFile) {
      const reader = new FileReader();
      // base64 encoding해서
      reader.onloadend = async () => {
        // const arrayBuffer = reader.result;
        // const byteArray = new Uint8Array(arrayBuffer);

        const base64Data = String(reader.result.split(",")[1]);

        try {
          const res = await authClient({
            method: "post",
            url: "/analyze/sources",

            data: {
              date: currentDate,
              facialExpression: base64Data,
              textExpression: inputValue,
            },
            headers: {
              "Content-Type": "text/plain",
            },
          });
          if (res) {
            console.log(res.data);
          }
          console.log(res);
          navigate("/result", { data: res.data });
          // 서버 응답 처리
        } catch (error) {
          if (error.response) {
            // 서버 응답 에러
            const err = error.response.data;
            console.log(err);
            console.log(error.message);
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
              {/* 모달창으로 약관 집어넣기 */}
              기분이 어떠신가요? 감정이 표현되는 언어가 들어가있으면 좋아요!
            </S.MyState>
          )}
        </S.WhiteBox>
        <PhotoUpload
          setSelectedFile={handleFileChange}
          setImagePreview={setImagePreview}
          imagePreview={imagePreview}
        />
        <S.ButtonContainer>
          <S.SubmitButton onClick={handleFormSubmit}>
            감정 분석 시작
          </S.SubmitButton>
          <S.SubmitButton onClick={openModal}>주류추천 tip</S.SubmitButton>
        </S.ButtonContainer>
        <Modal open={isModalOpen} onClose={closeModal}>
          <Box
            sx={{
              position: "absolute",
              top: "50%",
              left: "50%",
              transform: "translate(-50%, -50%)",
              bgcolor: "white",
              p: 2,
              maxHeight: "80vh",
              overflowY: "auto",
            }}
          >
            <StyledTypography variant="h6" component="h2">
              텍스트작성 tip
            </StyledTypography>
            <StyledTypography sx={{ fontStyle: "BejuryuFont" }}>
              이렇게 하면 인공지능이 더 잘 이해해요! <br />
              1. 명확하고 감정이 표현된 단어를 활용하기 - “기쁘다”, “슬프다”,
              “화나다”와 같은 명확한 감정 표현을 사용하면 더 좋아요! <br />
              2. 간결하고 명확한 문장을 구성하기 - 짧고 간결한 문장을 사용하면
              더 좋아요!
              <br />
              3. 대화체나 감정을 강조하는 어구 사용하기 - 직접 다른 사람에게
              말하는 것처럼 작성하면 더 좋아요!
              <br />
              4. 표정과 얼굴 특징: 인공지능이 감정을 인식하는 데 가장 중요한
              힌트는 사람의 표정과 얼굴 특징입니다. 감정을 잘 인식하기 위해서는
              사람의 얼굴이 명확하게 보이고 표정이 잘 드러나는 사진을
              선택하세요.
              <br />
              5. 눈의 표현: 눈은 감정을 전달하는 데 중요한 역할을 합니다. 눈의
              표정, 눈동자의 크기와 위치, 눈썹의 움직임 등을 인식할 수 있는
              사진을 선택하세요. 이러한 특징들은 인공지능이 더 정확하게 감정을
              파악하는 데 도움이 됩니다.
              <br /> 6. 입 모양과 표현: 입의 모양과 표현은 감정을 전달하는 데
              중요한 역할을 합니다. 입의 움직임, 입술의 형태와 표정 등을 잘
              나타내는 사진을 선택하세요. 이러한 정보는 인공지능이 행복, 슬픔,
              분노 등의 감정을 더 잘 인식하는 데 도움이 됩니다. <br /> 7. 자세와
              신체 언어: 사람의 자세와 신체 언어도 감정을 전달하는 데 중요한
              힌트를 제공합니다. 손동작, 어깨의 움직임, 몸의 자세 등이
              인공지능이 감정을 인식하는 데 도움이 될 수 있습니다. 따라서
              사진에서 사람의 자세와 신체 언어를 잘 포착할 수 있도록 주의하세요.{" "}
              <br /> 8. 환경과 배경 정보: 사진을 분석하는 인공지능은 감정 인식을
              위해 사람의 얼굴과 함께 주변 환경과 배경 정보를 고려하기도 합니다.
              따라서 사진에서 사람의 주변 환경을 고려하고, 감정을 파악하는 데
              도움이 될 수 있는 힌트를 포함하는 사진을 선택하세요. <br />
              9. 다양한 감정 표현: 인공지능을 훈련시키기 위해서는 다양한 감정
              표현이 포함된 사진을 사용하세요. 행복, 슬픔, 분노, 놀람 등 다양한
              감정을 나타내는 사진을 선택하여 모델의 다양성과 정확성을 향상시킬
              수 있습니다.
            </StyledTypography>
            <br />
            <StyledTypography variant="h6" component="h2">
              사진 tip
            </StyledTypography>
            <StyledTypography sx={{ mt: 2 }}>
              이런 사진일 수록 인공지능이 더 잘 이해해요!
              <br /> 1. 이목구비가 다 들어난 사진 - 각 부위의 모양과 위치를 보고
              감정을 판단하기 때문에, 다 들어난 사진이 더 좋아요!
              <br />
              2. 자세와 동작이 표현된 사진 - 자세와 동작도 감정을 전달할 수 있기
              때문에, 표현된 사진이 더 좋아요!
              <br />
              3. 감정을 나타내는 배경이 들어난 사진 - 얼굴이 표현된 사진이
              어려우면, 배경 사진도 괜찮아요!
            </StyledTypography>

            <Button onClick={closeModal}>주류추천 받으러 go</Button>
          </Box>
        </Modal>
      </S.Wrapper>
    </S.Container>
  );
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

export default Recommend;
