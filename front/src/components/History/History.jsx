/* TODO
1. [o] 주류 히스토리 페이지 대략적으로 어떻게 구현할 건지 UI*/

// 리스트 형식으로 들어가고 onclick 시키게끔 깔끔하게 고
import S from "./styled";
import { useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import authClient from "../../apis/authClient";

function History() {
  const navigate = useNavigate();
  const location = useLocation();

  // 추천결과 리스트
  const [recommendList, setRecommendList] = useState([]);
  // 리스트 길이에 따라 pagination
  const [page, setPage] = useState(1);
  const [perPage, setPerPage] = useState(9);
  const totalPages = Math.ceil(recommendList.length / perPage);

  // 현재 페이지에 해당하는 항목들만 추출하는 함수
  const getCurrentPageItems = () => {
    const startIndex = (page - 1) * perPage;
    const endIndex = startIndex + perPage;
    return recommendList.slice(startIndex, endIndex);
  };

  // 다음 페이지로 이동하는 함수
  const goToNextPage = () => {
    if (page < totalPages) {
      setPage(page + 1);
    }
  };

  // 이전 페이지로 이동하는 함수
  const goToPreviousPage = () => {
    if (page > 1) {
      setPage(page - 1);
    }
  };

  useEffect(() => {
    const reviewList = async () => {
      try {
        const response = await authClient({
          method: "get",
          url: "/analyze",
        });
        // 최신순으로 정렬하기 위함.
        const sortedList = response.data.sort((a, b) => b.id - a.id);
        setRecommendList(sortedList);
      } catch (error) {
        if (error.response) {
          const err = error.response.data;
          //console.log(err);
        }
      }
    };
    reviewList();
  }, []);

  const handleListClick = (id) => {
    navigate("/historyInfo", { state: { id } });
  };

  const goMypage = () => {
    navigate("/mypage");
  };

  // 리스트의 각 요소를 눌렸을 때 해당 페이지로 이동 시킨다.

  const HistoryView = (
    <S.Container>
      <S.Wrapper>
        <S.Title>현재까지 이용기록입니다</S.Title>
        <S.WhiteBox>
          {getCurrentPageItems().map((item) => (
            <S.listStyle key={item.id} onClick={() => handleListClick(item.id)}>
              {item.date}
            </S.listStyle>
          ))}
          <S.ButtonContainer>
            <S.PageButton onClick={goToPreviousPage} disabled={page === 1}>
              이전 페이지
            </S.PageButton>
            <S.PageInfo>
              페이지 {page} / {totalPages}
            </S.PageInfo>
            <S.PageButton onClick={goToNextPage} disabled={page === totalPages}>
              다음 페이지
            </S.PageButton>
          </S.ButtonContainer>
        </S.WhiteBox>
        <S.ReButton onClick={goMypage}>마이페이지로 돌아가기</S.ReButton>
      </S.Wrapper>
    </S.Container>
  );

  return HistoryView;
}

export default History;
