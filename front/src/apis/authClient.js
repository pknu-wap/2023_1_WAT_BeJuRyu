import axios from "axios";
import jwtDecode from "jwt-decode";
import { Cookies } from "react-cookie";

import settingCookie from "../utils/settingCookie";

const authClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

// 토큰 만료 확인하기
const checkToken = async () => {
  console.log("check token 실행 중");
  let token = settingCookie("get-access");
  const exp = jwtDecode(token);
  if (Date.now() / 1000 > exp.exp) {
    console.log("해당 토큰은 만료됨");
    await getNewToken();
  }
};

// 새 토큰 발급
const getNewToken = async () => {
  const access = settingCookie("get-access");
  const refresh = settingCookie("get-refresh");
  const cookie = new Cookies();
  try {
    const res = await axios({
      method: "post",
      url: "api/",
      data: {
        accessToken: access,
        refreshToken: refresh,
      },
    });
    settingCookie("remove");
    cookie.set("accessToken", res.data.accessToken);
    cookie.set("refreshToken", res.data.refreshToken);
    return res.data.accessToken;
  } catch (error) {
    alert("error");
  }
};

// axios 요청 전 수행할 작업
authClient.interceptors.request.use(function (config) {
  // 현재 토큰 가져오기
  let token = settingCookie("get-access");
  const exp = jwtDecode(token);
  // 토큰 만료여부 확인
  if (Date.now() / 1000 > exp.exp) {
    console.log("만료된 토큰", token);
    getNewToken().then((newToken) => {
      console.log("새 토큰", newToken);
      config.headers["Authorization"] = `${newToken}`;
    });
  } else {
    config.headers["Authorization"] = `${token}`;
  }
  return config;
});

export default authClient;
