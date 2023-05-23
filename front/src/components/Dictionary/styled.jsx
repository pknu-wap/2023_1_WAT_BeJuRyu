import styled from "styled-components";

const searchBox = styled.div`
  width: 50rem;
  //max-width: 45rem;
  height: 3rem;
  background-color: white;
  border-radius: 2rem;
  justify-content: center;
  display: flex;
  align-items: center;
`;
const Container = styled.div`
  width: 90rem;
  //max-width: 45rem;
  height: 42rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: black;
  border: 5px solid #e11299;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Wrapper = styled.div`
  margin: 2rem 0;
  //align-items: center;
`;

const Title = styled.div`
  margin: 2rem 0;
  font-weight: 600;
  text-align: center;
  font-size: 1.5rem;
  font-family: SCDream5;
  color: black;
`;

const SubmitButton = styled.button`
  height: 3rem;
  width: 10rem;
  font-family: SCDream5;
  border-radius: 1rem;
  border: none;
  background-color: #e11299;
  color: #f7f7f7;
  margin: 20px auto 0;
  display: block;
  cursor: pointer;
`;

const LogoutButton = styled.button`
  width: 100px;
  height: 48px;
  border: none;
  font-weight: 700;
  float: right;
  font-family: SCDream5;
  background-color: #9a208c;
  border-radius: 64px;
  color: white;
  margin-bottom: 16px;
  text-align: center;
  margin-right: 20px;
  cursor: pointer;
`;

const Info = styled.div`
  height: 4.8rem;
  margin-top: 1rem;
  width: 100%;
  display: flex;
  justify-content: center;
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
  width: 10rem;
  height: 15rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 1rem;

  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  cursor: text;

  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  }
`;

const juruBox = styled.div`
  width: 80rem;
  //max-width: 45rem;
  height: 34rem;
  //background-color: #e11299;
  //border-radius: 2rem;
  color: black;
  //border: 5px solid #e11299;
  //display: flex;
  justify-content: center;
  align-items: center;
`;

const S = {
  Wrapper,
  Container,
  SubmitButton,
  LogoutButton,
  Info,
  Main,
  Form,
  WhiteBox,
  Title,
  searchBox,
  juruBox,
};

export default S;
