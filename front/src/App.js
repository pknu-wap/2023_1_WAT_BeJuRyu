import Header from "./components/Header";
import styled from "styled-components";
import Footer from "./pages/Footer/Footer";
import GlobalStyle from "./GlobalStyles";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";
import RegisterPage from "./pages/RegisterPage";
import MyPage from "./pages/MyPage";
//import axios from "axios";
import { useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import jwt_decode from "jwt-decode";

import React from "react";

const AllWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const ContentWrapper = styled.div`
  flex: 1;
`;

function App() {
  function setScreenSize() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
  }
  useEffect(() => {
    setScreenSize();
  });

  return (
    <AllWrapper>
      <GlobalStyle />
      <ContentWrapper>
        <Header />
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/oauth" element={<MyPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>
      </ContentWrapper>
      <Footer />
    </AllWrapper>
  );
}

export default App;
