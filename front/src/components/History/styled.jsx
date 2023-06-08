import styled from "styled-components";

const Container = styled.div`
  width: 40rem;
  max-width: 85rem;
  height: 40rem;
  background-color: #f7cefc;
  border-radius: 2rem;
  color: black;
  border: 2px solid #be12e1;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -45%);
`;

const Wrapper = styled.div`
  margin: 1.5rem 0;
`;

const ReButton = styled.button`
  height: 3rem;
  width: 10rem;
  font-family: "BejuryuFont";
  border-radius: 1rem;
  border: none;
  background-color: #9932cc;
  color: #f7f7f7;
  margin: 20px auto 0;
  display: block;
  cursor: pointer;
`;

const Info = styled.div`
  height: 4.8rem;
  margin-top: 1rem;
  width: 100%;
  display: flex;
  justify-content: flex-end;
  column-gap: 0.5rem;
`;

const Main = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100% - 135px);
`;

const Form = styled.div`
  width: 80%;
  height: 8rem;

  margin: 0 auto;
  padding: 2rem;
  text-align: center;
  background-color: white;
  color: #9a208c;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
`;

const WhiteBox = styled.div`
  width: 35rem;
  height: 28rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 0.5rem;

  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  cursor: text;

  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  }
`;

const Title = styled.div`
  margin: 2rem 0;
  font-weight: 600;
  text-align: center;
  font-size: 1.5rem;
  font-family: "BejuryuFont";
  color: black;
`;

const PageButton = styled.button`
  height: 2rem;
  width: 5rem;
  border-radius: 0.5rem;
  border: none;
  background-color: #9932cc;
  color: #f7f7f7;
  margin: 10px auto 0;
  display: block;
  cursor: pointer;
  font-family: "BejuryuFont";
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const listStyle = styled.div`
  font-size: 20px;
  font-family: "BejuryuFont";

  text-align: center;
  border-bottom: 1px solid black;
  margin-bottom: 10px;
`;

const PageInfo = styled.span`
  font-family: "BejuryuFont";
`;

const S = {
  Wrapper,
  Container,
  ReButton,
  Title,
  Info,
  Main,
  Form,
  WhiteBox,
  PageButton,
  ButtonContainer,
  listStyle,
  PageInfo,
};

export default S;
