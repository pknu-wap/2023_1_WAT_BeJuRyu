import Header from "./components/Header";
import styled from "styled-components";
import Footer from "./pages/Footer/Footer";
import GlobalStyle from "./GlobalStyles";

const AllWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

const ContentWrapper = styled.div`
  flex: 1;
`;

function App() {
  return (
    <AllWrapper>
      <GlobalStyle />
      <ContentWrapper>
        <Header />
      </ContentWrapper>
      <Footer />
    </AllWrapper>
  );
}

export default App;
