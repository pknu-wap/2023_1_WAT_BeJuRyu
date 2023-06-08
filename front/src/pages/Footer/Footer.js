import styled from "styled-components";

const FooterDiv = styled.footer`
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  text-align: center;
  background-color: #9932cc;
  font-size: 1rem;
  line-height: 2rem;
  height: 2rem; //calc(var(--vh, 1vh) * 100 + 66px);
  color: white;
  display: flex;
  justify-content: space-between;
  font-family: "BejuryuFont";

  @media screen and (max-height: 600px) {
    height: 5rem;
  }
`;

const Content = styled.div`
  margin: 0 auto;
  color: white;
`;

const Footer = () => {
  return (
    <FooterDiv>
      <Content>CopyRight BeJuRyu</Content>
    </FooterDiv>
  );
};

export default Footer;
