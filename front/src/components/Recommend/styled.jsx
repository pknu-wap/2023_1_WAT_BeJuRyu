import styled from "styled-components";

const Container = styled.div`
  width: 30rem;
  max-width: 45rem;
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
  margin: 2rem 0;
`;

const SubmitButton = styled.button`
  height: 3rem;
  width: 8rem;
  font-family: "BejuryuFont";
  border-radius: 1rem;
  border: none;
  background-color: #9932cc;
  color: #f7f7f7;
  cursor: pointer;
  margin: 20px auto 0;
  display: block;
`;

const Title = styled.div`
  margin: 2rem 0;
  font-weight: 600;
  text-align: center;
  font-size: 1rem;
  font-family: "BejuryuFont";
  color: #262626;
`;

const WhiteBox = styled.div`
  width: 25rem;
  height: 10rem;
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

const ImageBox = styled.div`
  width: 25rem;
  height: 17rem;
  margin-top: 20px;
  margin: 0 auto;
  padding: 2rem;
  color: #9a208c;
  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  }
`;

const MyState = styled.div`
  font-weight: 300;
  font-size: 0.8rem;
  opacity: 1;
  transition: opacity 0.2s ease-in-out;
  color: #9a208c;
  font-family: "BejuryuFont";
  &:hover {
    opacity: 0;
  }
`;

const textInput = styled.textarea`
  font-family: "BejuryuFont";
  width: 100%;
  height: 100px;
  font-weight: 400;
  font-size: 1rem;
  resize: none;
  border: none;
  color: #e11299;
  &:focus {
    outline: none;
  }
`;

const uploader_wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .img-wrapper {
    margin: 50px 0 20px 0;
    img {
      width: 200px;
      height: 200px;
    }
  }
  .upload-button {
    button {
      margin: 0 5px;
    }
  }
`;

const Text = styled.p`
  color: #9a208c;
  text-align: center;
  //margin-top: 1rem;
  font-family: "BejuryuFont";
`;

const ContentText = styled.p`
  color: #9a208c;
  text-align: center;
  font-size: 0.9rem;
  //margin-top: 0.5rem;
  font-family: "BejuryuFont";
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const S = {
  Wrapper,
  Container,
  SubmitButton,
  WhiteBox,
  Title,
  MyState,
  textInput,
  ImageBox,
  uploader_wrapper,
  Text,
  ContentText,
  ButtonContainer,
};

export default S;
