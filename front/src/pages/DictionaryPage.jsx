import React from "react";
import Dictionary from "../components/Dictionary/Dictionary";
import styled from "styled-components";

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 48.5px);
`;

const Content = styled.div``;

const DictionaryPage = () => {
  return (
    <Main>
      <Content>
        <Dictionary />
      </Content>
    </Main>
  );
};

export default DictionaryPage;
