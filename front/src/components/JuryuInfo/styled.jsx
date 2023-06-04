import styled from "styled-components";

const Container = styled.div`
  width: 100rem;
  //max-width: 85rem;
  height: 40rem;
  // background-color: #f5c6ec;
  //border-radius: 2rem;
  color: black;
  //border: 5px solid #e11299;
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
  width: 75rem;
  height: 29.5rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 0.5rem;
  border-radius: 1rem;
  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  cursor: text;

  /* &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  } */
  display: flex;
  justify-content: space-between;
`;

const WhiteBox2 = styled.div`
  width: 20rem;
  height: 25rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 0.5rem;

  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  cursor: text;

  display: flex;
  justify-content: space-between;
`;

const FormBox = styled.div`
  border-radius: 1rem;
  width: 25rem;
  height: 28rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 0.5rem;
  font-family: "BejuryuFont";
  background: #f7cefc;
  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  }
`;

const ReviewBox = styled.div`
  border-radius: 1rem;
  width: 20rem;
  height: 28rem;
  margin: 0 auto;
  margin-bottom: 20px;
  padding: 0.5rem;
  font-family: "BejuryuFont";
  background: #f7cefc;
  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  }
`;

const Title = styled.div`
  margin: 2rem 0;
  font-weight: 600;
  text-align: center;
  font-size: 1.2rem;
  font-family: "BejuryuFont";
  color: black;
`;

const textInput = styled.textarea`
  width: 100%;
  height: 100px;
  font-size: 1rem;
  font-family: "BejuryuFont";
  resize: none;
  border: 1px solid purple;
  background-color: #f3f2f2;
  color: black;
  &:focus {
    outline: none;
  }
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const CenteredFormBox = styled(FormBox)`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
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

const S = {
  Wrapper,
  Container,
  ReButton,
  Title,
  Info,
  Main,
  Form,
  WhiteBox,
  textInput,
  FormBox,
  ReviewBox,
  WhiteBox2,
  ButtonContainer,
  CenteredFormBox,
  PageButton,
};

export default S;
