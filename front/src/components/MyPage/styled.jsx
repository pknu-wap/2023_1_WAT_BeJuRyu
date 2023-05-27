import styled from "styled-components";

const Container = styled.div`
  width: 40rem;
  max-width: 45rem;
  height: 20rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: black;
  border: 5px solid #e11299;

  /* display: flex;
  justify-content: center;
  align-itetms: center; */

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const Container2 = styled.div`
  width: 40rem;
  max-width: 45rem;
  height: 20rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: black;
  border: 5px solid #e11299;

  //display: flex;
  justify-content: center;
  align-items: center;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const Wrapper = styled.div`
  margin: 2rem 0;
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

const Title = styled.div`
  margin-top: 67px;
  font-size: 42px;
  font-weight: 700;
  margin-bottom: 1rem;
  color: #262626;
`;

const Input = styled.input`
  display: flex;
  border-radius: 8px;
  padding: 10px;
  //margin-top: 8px;
  border: 1px solid #e2e8c0;
  width: 100%;
  height: 50px;

  & + & {
    margin-top: 1rem;
  }
  color: black;
`;

const NickCheck = styled.button`
  width: 100px;
  height: 48px;
  border: none;
  font-weight: 700;
  float: right;
  background-color: #e11299;
  border-radius: 64px;
  color: white;
  margin-bottom: 16px;
  text-align: center;
  font-family: SCDream5;
  cursor: pointer;
`;

const S = {
  Wrapper,
  Container,
  SubmitButton,
  LogoutButton,
  Info,
  Main,
  Form,
  Title,
  Input,
  NickCheck,
  Container2,
};

export default S;
