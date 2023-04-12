import styled from "styled-components";

const FooterDiv = styled.footer`
  text-align: center;
  background-color: #9a208c;
  font-size: 1.6rem;
  line-height: 3rem;
  height: 3rem; //calc(var(--vh, 1vh) * 100 + 66px);
  color: white;
  display: flex;
  justify-content: space-between;
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
