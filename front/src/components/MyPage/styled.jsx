import styled from "styled-components";

const Container = styled.div`
  width: 90rem;
  //max-width: 45rem;
  height: 40rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: black;
  //border: 5px solid #e11299;

  //display: flex;
  justify-content: center;
  align-items: center;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -45%);
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
  font-family: "BejuryuFont";
  border-radius: 1rem;
  border: none;
  background-color: #9932cc;
  color: white;
  font-weight: 700;
  margin: 20px auto 0;
  display: block;
  cursor: pointer;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const LogoutButton = styled.button`
  width: 100px;
  height: 48px;
  border: none;
  font-weight: 700;
  float: right;
  font-family: "BejuryuFont";
  background-color: #9932cc;
  border-radius: 64px;
  color: white;
  margin-bottom: 16px;
  text-align: center;
  margin-right: 20px;
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
  font-family: "BejuryuFont";
  width: 100%;
  height: 30rem;
  margin: 0 auto;
  padding: 2rem;
  text-align: center;
  align-items: center;
  justify-content: center;
  background-color: white;
  color: #9a208c;
  //box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
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
  font-family: "BejuryuFont";

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
  background-color: #9932cc;
  border-radius: 64px;
  color: white;
  margin-bottom: 16px;

  text-align: center;
  font-family: "BejuryuFont";
  cursor: pointer;
`;

const ReviewBox = styled.div`
  width: 7.5rem;
  height: 11rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 1rem;
  font-family: "BejuryuFont";
  background: #f5f1f1;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  cursor: text;

  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
    cursor: pointer;
  }
`;

const juruBox = styled.div`
  width: 86rem;
  //max-width: 45rem;
  height: 15rem;
  //background-color: #e11299;
  //border-radius: 2rem;
  color: black;
  //border: 5px solid #e11299;
  display: flex;
  //flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  margin-bottom: 0;

  /* top: 50%;
  left: 50%; */
  //transform: translate(-50%, -50%);
`;

const JuruBoxContainer = styled.div`
  display: flex;
  gap: 20px; // 좌우 간격을 조정할 값
  justify-content: center;
  align-items: center;
`;

const Image = styled.img`
  width: 80%;
  height: 60%;
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
  ReviewBox,
  juruBox,
  ButtonContainer,
  JuruBoxContainer,
  Image,
};

export default S;
