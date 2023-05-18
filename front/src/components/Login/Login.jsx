/* 로그인, 회원가입의 로직
  1. [x]카카오 토큰을 받아온다
  2. [ ]서버에게 GET 요청 (서버와 통신 될때 넣을 얘쩡)
  3. [ ]JWT 토큰(access, refresh)을 RESPONSE로 받게 되고 localstorage나 cookie에 저장시킨다. => 현재 cookie 사용으로 setting 해놓음.
  4. */
import { useEffect, useState } from "react";
import { useNavigate, withRouter } from "react-router-dom";
import axios from "axios";
import S from "./styled";
import logo from "../../image/bejuryu.png";
import { connect } from "react-redux";

const { Kakao } = window;

function Login() {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(false);
  const [jwtToken, setJwtToken] = useState("");

  // 카카오 로그인
  const loginWithKakao = async () => {
    try {
      return new Promise((resolve, reject) => {
        if (!Kakao) {
          reject("Kakao 인스턴스가 존재하지 않습니다.");
        }
        Kakao.Auth.login({
          success: async (res) => {
            localStorage.setItem("token", res.access_token);
            setIsLogin(true);
            console.log(res);
            navigate("/recommend");
          },
          fail: (err) => {
            console.error(err);
          },
        });
      });
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    if (Kakao.Auth.getAccessToken()) {
      setIsLogin(true);
    }
  }, []);

  const loginView = (
    <S.Container>
      <S.Wrapper>
        <S.Title>BeJuRyu 서비스에 오신것을 환영합니다.</S.Title>
        <S.Form>
          <S.bejuryuImg src={logo} alt="logo"></S.bejuryuImg>
        </S.Form>
        <S.explainBox>
          많이 마시면 해롭지만, 즐겁게 마시면 활력소가 되어 줍니다. 오늘,
          BeJuRyu와 함께 술 한잔 어떤가요?
        </S.explainBox>
        <S.BtnList>
          <div>
            <div
              id="kakao-login-btn"
              onClick={loginWithKakao}
              style={{ cursor: "pointer" }}
            >
              <img
                src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
                width="222"
                alt="카카오 로그인 버튼"
              />
            </div>
          </div>
        </S.BtnList>
      </S.Wrapper>
    </S.Container>
  );
  return <div className="Login">{loginView}</div>;
}

export default Login;
