import styled from "styled-components";

const KakaoButton = styled.button`
  background-image: url("/image/auth/kakao.png");
  background-size: 4rem;
  background-color: white;
  width: 4rem;
`;

const Container = styled.div`
  width: 30rem;
  max-width: 45rem;
  background-color: #eac5f3;
  border-radius: 2rem;
  color: #f7f7f7;
  border: 2px solid #e11299;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const explainBox = styled.div`
  width: 28rem;
  font-weight: 500;
  font-family: "BejuryuFont";
  //background-color: white;
  color: #9a208c;
  text-align: center;
  justify-content: center;
  margin-left: 1rem;
`;

const Wrapper = styled.div`
  margin: 2rem 0;
`;

const Title = styled.div`
  margin: 2rem 0;
  font-weight: 600;
  text-align: center;
  font-size: 1.5rem;
  font-family: "BejuryuFont";
  color: black;
`;

const Form = styled.div`
  width: 25rem;
  //height: 20rem;
  margin: 0 auto;
  text-align: center;
  background-color: #eac5f3;
`;

const bejuryuImg = styled.img`
  width: 10rem;
  height: 10rem;
`;

const RegisterButton = styled.button``;

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

  RegisterButton,
  Wrapper,
  BtnList,
  snsTitle,
  KakaoButton,
  bejuryuImg,
  explainBox,
};

export default S;
