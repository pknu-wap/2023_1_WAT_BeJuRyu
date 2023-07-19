import React from "react";
import HistoryInfo from "../components/HistoryInfo/HistoryInfo";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;
const HistoryInfoPage = () => {
  return (
    <Main>
      <Content>
        <HistoryInfo />
      </Content>
    </Main>
  );
};

export default HistoryInfoPage;
