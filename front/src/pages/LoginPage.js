import React from "react";
import Login from "../components/Login/Login";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;
const LoginPage = () => {
  return (
    <Main>
      <Content>
        <Login />
      </Content>
    </Main>
  );
};

export default LoginPage;
