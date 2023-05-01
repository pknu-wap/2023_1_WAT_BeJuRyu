import styled from "styled-components";

const FooterDiv = styled.footer`
  //position: absolute;
  text-align: center;
  background-color: #9a208c;
  font-size: 1rem;
  line-height: 2rem;
  height: 2rem; //calc(var(--vh, 1vh) * 100 + 66px);
  color: white;
  display: flex;
  justify-content: space-between;

  @media screen and (max-height: 600px) {
    height: 5rem;
  }
`;

const Content = styled.div`
  margin: 0 auto;
`;

const Footer = () => {
  return (
    <FooterDiv>
      <Content>CopyRight BeJuRyu</Content>
    </FooterDiv>
  );
};

export default Footer;
