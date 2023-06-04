/*TODO
  1. 카카오 로그아웃시 쿠키 clear 추가 (자사 서비스 로그아웃은 됨)*/

import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import s from "./styled";
import { useDispatch } from "react-redux";

import settingCookie from "../../utils/settingCookie";
const { Kakao } = window;

function Logout() {
  //const dispatch = useDispatch();
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(false);

  function deleteCookie() {
    document.cookie =
      "authorize-access-token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
    document.cookie = "_kahai=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
  }

  // logout 로컬 구현
  const logout = () => {
    settingCookie("remove");
    //dispatch(GET_NAME(""));
    navigate("/login");
  };

  const logoutWithKakao = async () => {
    //logout();
    // Kakao.API.request({
    //   url: "/v1/user/unlink",
    // })
    //   .then(function (response) {
    //     alert(response);
    //   })
    //   .catch(function (error) {
    //     alert(error);
    //   });

    try {
      await Kakao.Auth.logout();
      logout();

      console.log("logout ok\naccess token -> " + Kakao.Auth.getAccessToken());
      deleteCookie();
      setIsLogin(false);
      localStorage.clear();
    } catch (error) {
      alert(error.message);
    }
  };

  useEffect(() => {
    // if (Kakao.Auth.getAccessToken()) {
    setIsLogin(false);
    // }
  }, []);

  const logoutView = (
    <s.LogoutButton onClick={logoutWithKakao}>로그아웃</s.LogoutButton>
  );

  return <div className="Logout">{logoutView}</div>;
}

export default Logout;
