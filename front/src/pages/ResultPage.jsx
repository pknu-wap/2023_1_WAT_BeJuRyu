import React from "react";
import Result from "../components/Result/Result";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;

const ResultPage = () => {
  return (
    <Main>
      <Content>
        <Result />
      </Content>
    </Main>
  );
};

export default ResultPage;
