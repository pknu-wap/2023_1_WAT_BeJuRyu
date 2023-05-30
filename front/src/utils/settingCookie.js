import { Cookies } from "react-cookie";

const settingCookie = (type) => {
  const cookie = new Cookies();

  if (type === "get-access") {
    const token = cookie.get("accessToken");
    return token;
  } else if (type === "get-refresh") {
    const token = cookie.get("refreshToken");
    return token;
  } else if (type === "remove") {
    cookie.remove("accessToken");
    cookie.remove("refreshToken");
  }
};

export default settingCookie;
