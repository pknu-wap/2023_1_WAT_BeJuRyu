import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function MyPage() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  console.log("hihi");

  const MyPageView = (
    <S.Container>
      <S.Wrapper>
        <S.SubmitButton>
          <strong>결과보기</strong>
        </S.SubmitButton>
      </S.Wrapper>
    </S.Container>
  );

  const params = new URLSearchParams(window.location.search);
  const accessToken = params.get("access_token");
  const refreshToken = params.get("refresh_token");
  console.log("access token:", accessToken);
  console.log("refresh token:", refreshToken);

  return MyPageView;
}

export default MyPage;
