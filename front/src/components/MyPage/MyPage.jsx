const MyPage = () => {
  console.log("hihi");

  const params = new URLSearchParams(window.location.search);
  const accessToken = params.get("access_token");
  const refreshToken = params.get("refresh_token");
  console.log("access token:", accessToken);
  console.log("refresh token:", refreshToken);
};

export default MyPage;
