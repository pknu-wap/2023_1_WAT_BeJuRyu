/* TODO
1. [] 주류 히스토리 페이지 대략적으로 어떻게 구현할 건지 UI
2. [] 감정 분석 내역 리스트 형태로 => 반환 값이 id, date*/
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
        <S.Title>현재까지 추천받은 주류 목록입니다</S.Title>
        <S.WhiteBox></S.WhiteBox>
        <S.ReButton onClick={goMypage}>마이페이지로 돌아가기</S.ReButton>
      </S.Wrapper>
    </S.Container>
  );

  return HistoryView;
}

export default History;
