import styled from "styled-components";
import logo from "../image/bejuryu.png";
import { Link } from "react-router-dom";
// 사용자 닉네임 불러올 떄
import { useSelector } from "react-redux";

const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #9a208c;

  padding: 0.5rem;
  @media only screen and (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
    padding: 8px 24px;
  }
  font-size: 2rem;
`;

const Navbarlogo = styled.div`
  font-size: 36px;
  justify-content: space-between;
  color: white;
  display: flex;
  align-items: center;
  i {
    color: orange;
  }
  @media only screen and (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
  }
`;

const NavbarlogoImage = styled.img`
  height: 50px;
  margin-right: 10px;
`;

const Navbarmenu = styled.ul`
  display: flex;
  list-style: none;
  padding-left: 0;
  font-size: 16px;
  justify-content: center;

  li {
    padding: 12px 24px;
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
  const userName = useSelector((state) => state.name.name);
  return (
    <>
      <Navbar>
        <Navbarlogo>
          <NavbarlogoImage src={logo} alt="BeJuRyu Logo" />
          <Link to="/" style={{ textDecoration: "none" }}>
            BeJuRyu
          </Link>
        </Navbarlogo>
        {userName === "" ? (
          ""
        ) : (
          <Navbarmenu>
            <li>
              <Link to="/Dictionary" style={{ textDecoration: "none" }}>
                주류사전메뉴
              </Link>
            </li>
            <li>
              <Link to="/Recommend" style={{ textDecoration: "none" }}>
                주류추천메뉴
              </Link>
            </li>
            <li>
              <Link to="/MyPage" style={{ textDecoration: "none" }}>
                마이페이지
              </Link>
            </li>
          </Navbarmenu>
        )}
        {userName === "" ? (
          <Navbarlink>
            <div></div>
          </Navbarlink>
        ) : (
          <Navbarlink>
            <li>{userName}님</li>
          </Navbarlink>
        )}
      </Navbar>
    </>
  );
}
