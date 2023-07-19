import React from "react";
import Recommend from "../components/Recommend/Recommend";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;

const RecommendPage = () => {
  return (
    <Main>
      <Content>
        <Recommend />
      </Content>
    </Main>
  );
};

export default RecommendPage;
