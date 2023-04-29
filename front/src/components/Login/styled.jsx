import styled from "styled-components";

const LoginButton = styled.button`
  height: 4rem;
  width: 8rem;
  font-family: SCDream5;
  border-radius: 1rem;
  border: none;
  background-color: #e11299;
  color: #f7f7f7;
  cursor: pointer;
`;

const KakaoButton = styled(LoginButton)`
  background-image: url("/image/auth/kakao.png");
  background-size: 4rem;
  background-color: white;
  width: 4rem;
`;

const Container = styled.div`
  width: 30rem;
  max-width: 45rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: #f7f7f7;
  border: 5px solid #e11299;
`;

const explainBox = styled.div`
  width: 28rem;
  //background-color: white;
  color: black;
  text-align: center;
  justify-content: center;
  margin-left: 1rem;
`;

const Wrapper = styled.div`
  margin: 2rem 0;
`;

const Title = styled.div`
  margin: 2rem 0;
  text-align: center;
  font-size: 3.5rem;
  font-family: SCDream5;
  color: #262626;
`;

const Form = styled.div`
  width: 25rem;
  //height: 20rem;
  margin: 0 auto;
  text-align: center;
  background-color: #f5c6ec;
`;

const bejuryuImg = styled.img`
  width: 10rem;
  height: 10rem;
`;

const RegisterButton = styled(LoginButton)``;

const BtnList = styled.div`
  text-align: center;
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1rem;
  column-gap: 1rem;
`;

const snsTitle = styled.div`
  color: #262626;
  text-align: center;
  font-size: 1.6rem;
`;

const S = {
  Container,
  Form,
  Title,
  LoginButton,
  RegisterButton,
  Wrapper,
  BtnList,
  snsTitle,
  KakaoButton,
  bejuryuImg,
  explainBox,
};

export default S;
