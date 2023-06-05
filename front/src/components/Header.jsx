import styled from "styled-components";
import { useEffect, useState } from "react";
import logo from "../image/logo2.png";
import { Link } from "react-router-dom";
// 사용자 닉네임 불러올 떄
import { useSelector, useDispatch } from "react-redux";
import settingCookie from "../utils/settingCookie";
import noAuthClient from "../apis/noAuthClient";

const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #9932cc;
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
    color: white; /* 글씨 색상 변경 */
    font-family: "BejuryuFont", Arial, Helvetica, sans-serif; /* 글씨체 변경 */
  }
  li:hover {
    background-color: #9444df;
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

const CustomLink = styled(Link)`
  text-decoration: none;
  color: inherit;
  font-family: "BejuryuFont";
`;

const CustomNavbarlink = styled(Navbarlink)`
  li {
    color: white;
    font-size: 18px;
    font-family: "BejuryuFont";
  }
`;

export default function Header() {
  const nickname = localStorage.getItem("nickname");
  const userName = useSelector((state) => state.name.name);
  const dispatch = useDispatch();
  const [dictionaryData, setDictionaryData] = useState(null);
  // 로그인 상태 관리

  return (
    <>
      <Navbar>
        <Navbarlogo>
          <NavbarlogoImage src={logo} alt="BeJuRyu Logo" />
          <CustomLink
            to="/"
            style={{ textDecoration: "none", color: "inherit" }}
          >
            BeJuRyu
          </CustomLink>
        </Navbarlogo>
        {userName === "" ? (
          ""
        ) : (
          <Navbarmenu>
            <div>
              <li>
                <Link
                  to={{
                    pathname: "/Dictionary",
                  }}
                  style={{ textDecoration: "none", color: "inherit" }}
                >
                  주류사전
                </Link>
              </li>
            </div>
            <li>
              <Link
                to="/Recommend"
                style={{ textDecoration: "none", color: "inherit" }}
              >
                주류추천
              </Link>
            </li>
            {/* <li>
              <Link
                to="/MyPage"
                style={{ textDecoration: "none", color: "inherit" }}
              >
                마이페이지
              </Link>
            </li> */}
          </Navbarmenu>
        )}

        {userName === "" ? (
          <Navbarlink>
            <div></div>
          </Navbarlink>
        ) : (
          <CustomNavbarlink to="/">
            <li>
              <CustomLink to="/">{nickname} 님</CustomLink>
            </li>
          </CustomNavbarlink>
        )}
      </Navbar>
    </>
  );
}
