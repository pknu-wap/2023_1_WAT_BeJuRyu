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

const Title = styled.div`
  margin: 2rem 0;
  text-align: center;
  font-size: 1.5rem;
  font-family: SCDream5;
  color: #262626;
`;

const WhiteBox = styled.div`
  width: 25rem;
  height: 20rem;
  margin: 0 auto;
  padding: 1rem;

  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);

  cursor: text;

  &:hover {
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  }
`;

const MyState = styled.div`
  font-weight: 300;
  font-size: 1.2rem;
`;

const S = {
  Wrapper,
  Container,
  SubmitButton,
  WhiteBox,
  Title,
  MyState,
};

export default S;
