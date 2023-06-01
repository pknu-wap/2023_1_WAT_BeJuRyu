import styled from "styled-components";
import { useEffect, useState } from "react";
import logo from "../image/logo2.png";
import { Link } from "react-router-dom";
// 사용자 닉네임 불러올 떄
import { useSelector, useDispatch } from "react-redux";
import noAuthClient from "../apis/noAuthClient";

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
    color: white; /* 글씨 색상 변경 */
    font-family: Arial, Helvetica, sans-serif; /* 글씨체 변경 */
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
  const dispatch = useDispatch();
  const [dictionaryData, setDictionaryData] = useState(null);

  // const handleDictionaryButtonClick = async () => {
  //   try {
  //     const response = await noAuthClient({
  //       method: "get",
  //       url: "/drinks",
  //     });
  //     if (response.status === 200) {
  //       const data = response.data;
  //       // setDictionaryData(data); // 데이터를 상태에 저장
  //       //localStorage.setItem("data", data.drinks);
  //       console.log(data);
  //       //dispatch({ type: "SET_DICTIONARY_DATA", payload: data }); // 가져온 데이터를 Redux 스토어에 저장
  //       console.log("데이터를 가져왔습니다.");
  //     } else {
  //       console.log("데이터를 가져오는데 실패하였습니다.");
  //     }
  //   } catch (error) {
  //     console.log("오류가 발생하였습니다.", error);
  //   }
  // };

  // useEffect(() => {
  //   const dictionaryButton = document.querySelector('li a[href="/Dictionary"]');
  //   if (dictionaryButton) {
  //     dictionaryButton.addEventListener("click", handleDictionaryButtonClick);
  //   }

  //   return () => {
  //     if (dictionaryButton) {
  //       dictionaryButton.removeEventListener(
  //         "click",
  //         handleDictionaryButtonClick
  //       );
  //     }
  //   };
  // }, []);

  // const dictionaryData = useSelector((state) => state.dictionaryData); // Redux 스토어에서 필요한 데이터 선택
  //console.log(dictionaryData); // 데이터 콘솔 출력

  // return () => {
  //   if (dictionaryButton) {
  //     dictionaryButton.removeEventListener(
  //       "click",
  //       handleDictionaryButtonClick
  //     );
  //   }
  // };

  return (
    <>
      <Navbar>
        <Navbarlogo>
          <NavbarlogoImage src={logo} alt="BeJuRyu Logo" />
          <Link to="/" style={{ textDecoration: "none", color: "inherit" }}>
            BeJuRyu
          </Link>
        </Navbarlogo>

        <Navbarmenu>
          <div>
            <li>
              <Link
                to={{
                  pathname: "/Dictionary",
                  state: { dictionaryData: dictionaryData }, // 데이터를 props로 전달
                }}
                style={{ textDecoration: "none", color: "inherit" }}
              >
                주류사전메뉴
              </Link>
            </li>
          </div>
          <li>
            <Link
              to="/Recommend"
              style={{ textDecoration: "none", color: "inherit" }}
            >
              주류추천메뉴
            </Link>
          </li>
          <li>
            <Link
              to="/MyPage"
              style={{ textDecoration: "none", color: "inherit" }}
            >
              마이페이지
            </Link>
          </li>
        </Navbarmenu>

        {/* {userName === "" ? (
          <Navbarlink>
            <div></div>
          </Navbarlink>
        ) : (
          <Navbarlink>
            <li style={{ color: "white", fontSize: "24px" }}>{userName}</li>
          </Navbarlink>
        )} */}
      </Navbar>
    </>
  );
}
