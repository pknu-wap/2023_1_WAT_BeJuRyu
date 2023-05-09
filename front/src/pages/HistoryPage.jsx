import React from "react";
import History from "../components/History/History";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;
const HistoryPage = () => {
  return (
    <Main>
      <Content>
        <History />
      </Content>
    </Main>
  );
};

export default HistoryPage;
