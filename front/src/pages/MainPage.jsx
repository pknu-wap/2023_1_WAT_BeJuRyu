import React from "react";
import MainP from "../components/Main/Main";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;
const MainPage = () => {
  return (
    <Main>
      <Content>
        <MainP />
      </Content>
    </Main>
  );
};

export default MainPage;
