import styled from "styled-components";
import "../../App.css";

const Container = styled.div`
  width: 45rem;
  max-width: 45rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: #f7f7f7;
  border: 5px solid #e11299;
`;

const Wrapper = styled.div`
  margin: 2rem 0;
`;

const TextField = styled.input`
  border: 1px solid #e2e8c0;
  color: #262626;
  border-radius: 1rem;
  width: 100%;
  height: 5rem;
  //margin: auto;
  position: relative;
  display: flex;
  font-family: SCDream5;

  //justify-content: flex-end;
  //align-items: center;
`;

const PasswordField = styled.input`
  border: 1px solid #e2e8c0;
  color: #262626;
  border-radius: 1rem;
  width: 100%;
  height: 5rem;
  //margin: auto;
  position: relative;
  display: flex;
  font-family: SCDream5;

  //justify-content: flex-end;
  //align-items: center;
`;

const BtnList = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const SubmitButton = styled.button`
  width: 45%;
  height: 48px;
  border: none;
  font-weight: 700;
  background-color: #e11299;
  border-radius: 64px;
  color: white;
  margin-bottom: 16px;
  text-align: center;
  font-family: SCDream5;
  font-size: 1.6rem;
  cursor: pointer;
`;

const Title = styled.div`
  //margin-top: 0px;
  //align-items: center;
  //justify-content: center;
  //font-size: 42px;
  //font-weight: 700;
  //margin-bottom: 50px;
  margin: 2rem 0;
  text-align: center;
  font-size: 3.5rem;
  font-family: SCDream9;
  color: #262626;
`;

const nickCheck = styled.button`
  width: 50%;
  height: 48px;
  border: none;
  font-weight: 700;
  background-color: #395b64;
  border-radius: 64px;
  color: white;
  margin-bottom: 16px;

  text-align: center;
  display: inline-block;
  cursor: pointer;
`;

const formbox = styled.div`
  position: relative;
  margin-bottom: 40px;
  .message {
    font-weight: 300;
    font-size: 1.5rem;
    line-height: 24px;
    letter-spacing: -1px;
    position: absolute;
    //bottom: -50px;
    left: 0;
    &.success {
      color: #8f8c8b;
    }
    &.error {
      color: #ff2727;
    }
  }

  button {
    position: absolute;
    color: white;
    top: 12px;
    right: 10px;
    background-color: #e11299;
    border: none;
    font-weight: 500;
    border-radius: 20px;
    height: 30px;
    cursor: pointer;
  }
`;

const Form = styled.form`
  width: 36rem;
  margin: 0 auto;
  text-align: center;
`;

const S = {
  Container,
  SubmitButton,
  Title,
  nickCheck,
  formbox,
  TextField,
  PasswordField,
  Wrapper,
  Form,
  BtnList,
};

export default S;
