import S from "./styled";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

function History() {
  const navigate = useNavigate();

  const goMypage = () => {
    navigate("/mypage");
  };
  const HistoryView = (
    <S.Container>
      <S.Wrapper>
        <S.WhiteBox>
          <S.ReButton onClick={goMypage}>마이페이지로 돌아가기</S.ReButton>
        </S.WhiteBox>
      </S.Wrapper>
    </S.Container>
  );

  return HistoryView;
}

export default History;
