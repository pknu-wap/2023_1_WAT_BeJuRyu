import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import S from "./styled";
import "../../utils/settingCookie";
import authClient from "../../apis/authClient";
import { GET_NAME } from "../../reducer/nameSlice";
import { useDispatch } from "react-redux";

function NickChange() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [nickname, setNickname] = useState("");
  const onNickHandler = (e) => {
    setNickname(e.currentTarget.value);
  };

  // api 명세서 업데이트 되면 수정 예정
  const onchangeNick = async () => {
    dispatch(GET_NAME(nickname));
    navigate("/");
    alert("변경이 완료되었습니다.");
    try {
      const res = await authClient({
        method: "post",
        //url: `${process.env.REACT_APP_LOCAL}/auth/`,
        data: nickname,
      });
      console.log(res);
    } catch (error) {
      const err = error.response.data;
      console.log(err);
    }
  };

  const NickChangeView = (
    <S.Container2>
      <S.Info>
        <S.LogoutButton type="button" onClick={() => navigate("/")}>
          되돌아가기
        </S.LogoutButton>
      </S.Info>
      <S.Wrapper>
        {/* 전역 상태관리 기능 추가 */}
        <S.Form>
          <S.Input
            type="text"
            value={nickname}
            placeholder="변경할 닉네임을 입력하세요"
            onChange={onNickHandler}
          ></S.Input>
          <S.NickCheck type="button" onClick={onchangeNick}>
            {" "}
            {/* 수정된 부분 */}
            변경
          </S.NickCheck>
        </S.Form>
      </S.Wrapper>
    </S.Container2>
  );

  return NickChangeView;
}

export default NickChange;
