import Header from "./components/Header";
import styled from "styled-components";
import Footer from "./pages/Footer/Footer";
import GlobalStyle from "./GlobalStyles";
import LoginPage from "./pages/LoginPage";
import RecommendPage from "./pages/RecommendPage";
import RegisterPage from "./pages/RegisterPage";
import MyPage from "./pages/MyPage";
import ResultPage from "./pages/ResultPage";
import DictionaryPage from "./pages/DictionaryPage";
import HistoryPage from "./pages/HistoryPage";
import NickChangePage from "./pages/NickChangePage";
import axios from "axios";
import { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import jwt_decode from "jwt-decode";
import { useDispatch, useSelector } from "react-redux";
import settingCookie from "./utils/settingCookie";

import { GET_NAME } from "./reducer/nameSlice";
import React from "react";
import NickChange from "./components/MyPage/Nickchange";
import Login from "./components/Login/Login";

const AllWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const ContentWrapper = styled.div`
  flex: 1;
`;

axios.defaults.withCredentials = true;

function App() {
  const dispatch = useDispatch();
  const userName = useSelector((state) => state.name.name);

  const isLogin = () => {
    const token = settingCookie("get-access");
    // 로그인이 되어있다면
    if (token !== undefined) {
      const decode = jwt_decode(token);
      dispatch(GET_NAME(decode.nickname));
    }
  };

  function setScreenSize() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
  }

  useEffect(() => {
    setScreenSize();
  });

  useEffect(() => {
    isLogin();
  }, []);

  return (
    <AllWrapper>
      <GlobalStyle />
      <ContentWrapper>
        <Header />
        <Routes>
          {userName === "" ? (
            <Route path="/" element={<LoginPage />} />
          ) : (
            <Route path="/" element={<MyPage />} />
          )}
          {/* <Route path="/" element={<LoginPage />} /> */}
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/recommend" element={<RecommendPage />} />
          <Route path="/result" element={<ResultPage />} />
          <Route path="/dictionary" element={<DictionaryPage />} />
          <Route path="/history" element={<HistoryPage />} />
          <Route path="/nickchange" element={<NickChange />} />
        </Routes>
      </ContentWrapper>
      <Footer />
    </AllWrapper>
  );
}

export default App;
