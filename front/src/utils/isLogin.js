import settingCookie from "./settingCookie";

const isLogin = () => !!settingCookie("get-access");
export default isLogin;
