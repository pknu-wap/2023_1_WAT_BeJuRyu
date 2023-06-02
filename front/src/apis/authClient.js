import axios from "axios";
import jwtDecode from "jwt-decode";
import { Cookies } from "react-cookie";

import settingCookie from "../utils/settingCookie";

const authClient = axios.create({
  // baseURL: "http://localhost:8080",
  baseURL: "http://141.164.49.27",
  headers: {
    "Content-Type": "application/json",
  },
});

const userId = localStorage.getItem("user-id");

// 새 토큰 발급
const getNewToken = async () => {
  const access = settingCookie("get-access");
  const refresh = settingCookie("get-refresh");
  const cookie = new Cookies();
  try {
    const res = await axios({
      method: "post",
      url: `auth/${userId}`,
      data: {
        accessToken: access,
        refreshToken: refresh,
      },
    });
    settingCookie("remove");
    cookie.set("accessToken", res.data.accessToken);
    cookie.set("refreshToken", res.data.refreshToken);
    console.log(res.data);
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
      //console.log("새 토큰", newToken);
      config.headers["Authorization"] = `Bearer ${newToken}`;
    });
  } else {
    console.log("토큰이 아직 만료 안됐어요!");
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
});

export default authClient;
