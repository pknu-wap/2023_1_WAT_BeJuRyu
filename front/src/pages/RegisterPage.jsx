import React from "react";
import Register from "../components/Register/Register";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;
const RegisterPage = () => {
  return (
    <Main>
      <Content>
        <Register />
      </Content>
    </Main>
  );
};

export default RegisterPage;
