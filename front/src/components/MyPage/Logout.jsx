/*TODO
  1. 카카오 로그아웃시 쿠키 clear 추가 (자사 서비스 로그아웃은 됨)*/

import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import s from "./styled";
import { useDispatch } from "react-redux";

import { GET_NAME } from "../../reducer/nameSlice";
import settingCookie from "../../utils/settingCookie";
const { Kakao } = window;

function Logout() {
  const dispatch = useDispatch();
  const navigate = useNavigate;
  const [isLogin, setIsLogin] = useState(false);

  function deleteAllCookies() {
    const cookies = document.cookie.split(";");

    for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i];
      const eqPos = cookie.indexOf("=");
      const name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
      document.cookie =
        name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/;";
    }
  }

  // logout 로컬 구현
  const logout = () => {
    settingCookie("remove");
    dispatch(GET_NAME(""));
    navigate("/");
    localStorage.clear();
  };

  const logoutWithKakao = async () => {
    if (Kakao.Auth.getAccessToken()) {
      console.log(
        "카카오 인증 액세스 토큰이 존재합니다.",
        Kakao.Auth.getAccessToken()
      );
      try {
        await Kakao.Auth.logout();
        console.log("로그아웃 되었습니다.", Kakao.Auth.getAccessToken());
        setIsLogin(false);
        localStorage.clear();

        //window.location.href = "/";
        navigate("/");
        Kakao.Auth.setAccessToken(null); // 카카오 SDK의 자동 로그인 초기화

        // 카카오 API에서 사용하는 쿠키 삭제
        const kakaoCookies = [
          "_kahai",
          "_karmt",
          "_karmtea",
          "_kawlt",
          "_kawltea",
        ];
        kakaoCookies.forEach((cookie) => {
          document.cookie =
            cookie + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        });

        logout(); // 비주류 서비스 로그아웃
      } catch (error) {
        console.error("카카오 로그아웃 중 오ㅇ류가 발생했습니다:", error);
      }
    }
  };

  useEffect(() => {
    if (Kakao.Auth.getAccessToken()) {
      setIsLogin(false);
    }
  }, []);

  const logoutView = (
    <s.LogoutButton onClick={logoutWithKakao}>로그아웃</s.LogoutButton>
  );

  return <div className="Logout">{logoutView}</div>;
}

export default Logout;
