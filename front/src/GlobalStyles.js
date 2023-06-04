import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
@font-face {
    font-family: 'BejuryuFont';
    src: url('/fonts/BejuryuFont.ttf') format('truetype');
    font-weight: normal;
    font-style: normal;
  }
  *, *::before, *::after {
    box-sizing: border-box;
    color: #212A3E;
  }
  body {
    font-family: SCDream5;
  }
  :root {
  --vh: 100%;
}
`;

export default GlobalStyle;
