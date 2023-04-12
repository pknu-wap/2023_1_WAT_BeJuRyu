import styled from "styled-components";
import logo from "../image/bejuryu.png";
import { Link } from "react-router-dom";
import KakaoLogin from "./Login/Login";
// 사용자 닉네임 불러올 떄
// import { useSelector } from "react-redux";

const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #9a208c;
  padding: 1rem;
  @media only screen and (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
    padding: 8px 24px;
  }
  font-size: 2rem;
`;

const Navbarlogo = styled.div`
  font-size: 24px;
  justify-content: space-between;
  color: white;
  i {
    color: orange;
  }
  @media only screen and (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
  }
`;

const Navbarmenu = styled.ul`
  display: flex;
  list-style: none;
  padding-left: 0;
  font-size: 16px;

  li {
    padding: 8px 12px;
  }
  li:hover {
    background-color: #e11299;
  }

  @media only screen and (max-width: 576px) {
    //width: 100%;
    flex-direction: column;
    align-items: center;
    width: 100%;

    li {
      width: 100%;
      text-align: center;
    }
  }
`;

const Navbarlink = styled.div`
  @media only screen and (max-width: 768px) {
    margin: 0 auto;
  }
  > li {
    list-style: none;
  }
`;

export default function Header() {
  // const userName = useSelector((state) => state.name.name);
  return (
    <>
      <Navbar>
        <Navbarlogo>
          <Link to="/" style={{ textDecoration: "none" }}>
            <img src={logo} alt="Logo" width="50" height="50" />
            BeJuRyu
          </Link>
        </Navbarlogo>
        <Navbarmenu>
          <li>주류추천메뉴에용</li>
          <li>주류사전메뉴에용</li>
          <li>메뉴에용</li>
          <li>
            <Link to="/MyPage" style={{ textDecoration: "none" }}>
              마이페이지에용
            </Link>
          </li>
        </Navbarmenu>
      </Navbar>
    </>
  );
}
