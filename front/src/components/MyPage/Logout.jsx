import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import s from "./styled";

const { Kakao } = window;

function Logout() {
  //const navigate = useNavigate;
  const [isLogin, setIsLogin] = useState(false);

  const logoutWithKakao = async () => {
    if (Kakao.Auth.getAccessToken()) {
      console.log(
        "카카오 인증 액세스 토큰이 존재합니다.",
        Kakao.Auth.getAccessToken()
      );
      Kakao.Auth.logout(() => {
        console.log("로그아웃 되었습니다.", Kakao.Auth.getAccessToken());

        setIsLogin(false);
        localStorage.clear();
        window.location.href = "/";
      });
    }
  };

  useEffect(() => {
    if (Kakao.Auth.getAccessToken()) {
      setIsLogin(false);
    }
  }, []);

  const logoutView = (
    <s.logoutButton onClick={logoutWithKakao}>로그아웃</s.logoutButton>
  );

  return <div className="Logout">{logoutView}</div>;
}

export default Logout;
