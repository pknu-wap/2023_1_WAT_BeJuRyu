/* 로그인, 회원가입의 로직
  1. [x]카카오 토큰을 받아온다
  2. [x]서버에게 GET 요청 (서버와 통신 될때 넣을 얘쩡)
  3. [x]JWT 토큰(access, refresh)을 RESPONSE로 받게 되고 localstorage나 cookie에 저장시킨다. => 현재 cookie 사용으로 setting 해놓음.
  4. */
import { useEffect, useState } from "react";
import { useNavigate, withRouter } from "react-router-dom";
// import axios from "axios";
import { Cookies } from "react-cookie";
import S from "./styled";
import logo from "../../image/logo2.png";
import { useDispatch } from "react-redux";
import jwt_decode from "jwt-decode";
import { GET_NAME } from "../../reducer/nameSlice";
import noAuthClient from "../../apis/noAuthClient";
import authClient from "../../apis/authClient";

const { Kakao } = window;

function Login() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [isLogin, setIsLogin] = useState(false);
  const [jwtToken, setJwtToken] = useState("");

  // snsLogin
  const snsLogin = async (kakaoToken) => {
    try {
      const res = await noAuthClient({
        method: "get",
        url: `/auth/login?token=${kakaoToken}`,
      });
      console.log("res: ", res);

      const cookie = new Cookies();
      cookie.set("accessToken", res.data.token.access);
      cookie.set("refreshToken", res.data.token.refresh);

      const decode = jwt_decode(res.data.token.access);
      console.log("login하면서 decode한 것.", decode);

      // redux에 nickname 저장
      dispatch(GET_NAME(res.data.memberResponse.nickname));

      localStorage.setItem("user-id", res.data.memberResponse.id);
      localStorage.setItem("nickname", res.data.memberResponse.nickname);

      await sendTestRequest();
    } catch (error) {}
  };

  // test 용
  // 테스트용 GET 요청
  const sendTestRequest = async () => {
    try {
      const res = await authClient({
        method: "get",
        url: "/test",
      });

      console.log("Test Response:", res.data);
      // console.log(res);
      console.log(res.data.id);
    } catch (error) {
      console.error("Test Error:", error);
    }
  };

  // 로그인
  const login = async (e) => {
    e.preventDefault();
    try {
      return new Promise((resolve, reject) => {
        if (!Kakao) {
          reject("Kakao 인스턴스가 존재하지 않습니다.");
        }
        Kakao.Auth.login({
          success: async (res) => {
            // 서버에 GET 요청을 보내는 작업
            const { access_token } = res;

            // snsLogin 함수 호출
            await snsLogin(access_token);
            navigate("/");

            // test 요청 보내기
            // await sendTestRequest();

            // setIsLogin(true);
            //console.log(res);
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

  return (
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
              onClick={login}
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
}

export default Login;
