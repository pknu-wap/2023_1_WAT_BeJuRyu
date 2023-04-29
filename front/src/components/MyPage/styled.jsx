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
  height: 4rem;
  width: 6rem;
  font-family: SCDream5;
  border-radius: 1rem;
  border: none;
  background-color: #e11299;
  color: #f7f7f7;
  cursor: pointer;
`;

const S = {
  Wrapper,
  Container,
  SubmitButton,
};

export default S;
