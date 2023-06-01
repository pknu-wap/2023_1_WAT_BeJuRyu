import React from "react";
import JuryuInfo from "../components/JuryuInfo/JuruInfo";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;
const JuryuInfoPage = () => {
  return (
    <Main>
      <Content>
        <JuryuInfo />
      </Content>
    </Main>
  );
};

export default JuryuInfoPage;
