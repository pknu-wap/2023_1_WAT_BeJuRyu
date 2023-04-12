import React, { useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
//import noAuthClient from "../../apis/noAuthClient";
import S from "./styled";

const Register = () => {
  // 닉네임
  const [nickname, setNickname] = useState("");

  // 오류메시지 상태저장
  const [nameMessage, setNameMessage] = useState("");

  // 유효성 검사
  const [isName, setIsName] = useState(false);

  const navigate = useNavigate();

  // 이름
  const onChangeNickName = useCallback((e) => {
    setNickname(e.target.value);
    if (e.target.value.length < 3 || e.target.value.length > 15) {
      setNameMessage("3글자 이상 15글자 미만으로 입력해주세요.");
      setIsName(false);
    } else {
      setNameMessage("올바른 닉네임 형식입니다.");
    }
  }, []);

  // 닉네임 중복확인

  return (
    <S.Container>
      <S.Wrapper>
        <S.Title>Register Page</S.Title>
        <S.Form>
          <S.formbox>
            <S.TextField
              text="이름"
              type="text"
              typeName="nickname"
              placeholder="Nickname"
              onChange={onChangeNickName}
              //onBlur={checkNickname}
            />
            {nickname.length > 0 && (
              <span className={`message ${isName ? "success" : "error"}`}>
                {nameMessage}
              </span>
            )}
          </S.formbox>

          <S.BtnList>
            <S.SubmitButton type="button" onClick={() => navigate("/")}>
              돌아가기
            </S.SubmitButton>
            <S.SubmitButton type="submit">가입하기</S.SubmitButton>
          </S.BtnList>
        </S.Form>
      </S.Wrapper>
    </S.Container>
  );
};

export default Register;
