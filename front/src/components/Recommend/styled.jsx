import styled from "styled-components";

const Container = styled.div`
  width: 30rem;
  max-width: 45rem;
  height: 38rem;
  background-color: #f5c6ec;
  border-radius: 2rem;
  color: black;
  border: 5px solid #e11299;
`;

const Wrapper = styled.div`
  margin: 2rem 0;
`;

const SubmitButton = styled.button`
  height: 3rem;
  width: 8rem;
  font-family: SCDream5;
  border-radius: 1rem;
  border: none;
  background-color: #e11299;
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
  font-family: SCDream5;
  color: #262626;
`;

const WhiteBox = styled.div`
  width: 25rem;
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

const ImageBox = styled.div`
  width: 25rem;
  height: 10rem;
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

  &:hover {
    opacity: 0;
  }
`;

const textInput = styled.textarea`
  width: 100%;
  height: 50%;
  font-size: 1.2rem;
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
};

export default S;
