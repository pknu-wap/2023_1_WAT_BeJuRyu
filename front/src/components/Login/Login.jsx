import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import S from "./styled";
import logo from "../../image/bejuryu.png";
import { connect } from "react-redux";

function Login() {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(false);
  const [jwtToken, setJwtToken] = useState("");

  // 카카오 로그인
  const loginWithKakao = async () => {
    try {
      return new Promise((resolve, reject) => {
        if (!window.Kakao) {
          reject("Kakao 인스턴스가 존재하지 않습니다.");
        }
        window.Kakao.Auth.login({
          success: (auth) => {
            console.log("로그인 성공", auth);
            setIsLogin(true);
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

  const logoutWithKakao = () => {
    if (window.Kakao.Auth.getAccessToken()) {
      console.log(
        "카카오 인증 액세스 토큰이 존재합니다.",
        window.Kakao.Auth.getAccessToken()
      );
      window.Kakao.Auth.logout(() => {
        console.log("로그아웃 되었습니다", window.Kakao.Auth.getAccessToken());
        setIsLogin(false);
      });
    }
  };

  useEffect(() => {
    const script = document.createElement("script");
    script.async = true;
    script.src = "https://developers.kakao.com/sdk/js/kakao.min.js";
    document.head.appendChild(script);
    script.onload = () => {
      window.Kakao.init(process.env.REACT_APP_KAKAO_KEY);
      if (window.Kakao.Auth.getAccessToken()) {
        console.log("액세스 토큰이 존재합니다. 세션을 유지합니다.");
        setIsLogin(true);
      }
    };
  }, []);

  const loginView = (
    <S.Container>
      <S.Wrapper>
        <S.Title>BeJuRyu</S.Title>
        <S.Form>
          <S.bejuryuImg src={logo} alt="logo"></S.bejuryuImg>
        </S.Form>
        <S.explainBox>
          많이 마시면 해롭지만, 즐겁게 마시면 활력소가 되어 줍니다. 오늘,
          비주류와 함께 술 한잔 어떤가요?
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

  const mainView = (
    <div>
      <button onClick={logoutWithKakao}>카카오 로그아웃</button>
    </div>
  );

  return <div className="Login">{isLogin ? mainView : loginView}</div>;
}

export default Login;

/*
const Login = () => {
  const JavaScript_Key = "d19046b7f49f6376e5f701a2a57167e3";
  const REDIRECT_URI = "http://localhost:3000/oauth";
  //const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${JavaScript_Key}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  //Kakao.init(JavaScript_Key);

  function loginWithKakao() {
    if (typeof window.Kakao !== "undefined") {
      Kakao.Auth.login({
        success: function (authObj) {
          alert("액세스 토큰: " + authObj.access_token);
        },
        fail: function (err) {
          alert(JSON.stringify(err));
        },
        redirectUri: REDIRECT_URI,
      });
    }
  }

  return (
    <S.Container>
      <S.Wrapper>
        <S.Title>BeJuRyuLogin!</S.Title>
        <S.snsTitle>KAKAO LOGIN</S.snsTitle>

        <S.BtnList>
          <div>
            
            <div id="kakao-login-btn" onClick={loginWithKakao}>
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
};

export default Login;*/
