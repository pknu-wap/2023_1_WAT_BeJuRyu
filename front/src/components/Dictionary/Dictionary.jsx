import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Dictionary() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);

  const DictionaryView = () => {
    return (
      <S.Container>
        <S.Wrapper>
          <S.Title>주류를 검색해 보세요!</S.Title>
          /*검색창 기능이 들어갈 공간입니다.*/
          <S.WhiteBox></S.WhiteBox>
        </S.Wrapper>
      </S.Container>
    );
  };

  return <DictionaryView />;
}

export default Dictionary;
