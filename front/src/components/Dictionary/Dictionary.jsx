/* TODO
 1. [o] 사전 페이지 UI 구현 어떤식으로 할건지 결정
 2. [] 사전 페이지에서 주류 목록을 띄우고 해당 주류를 누르면 주류에 대한 정보와 후기 작성까지 가능하게끔
 3. [o] 주류 사진을 클릭할 때 마다 다른 페이지로 연결이 될 텐데, 페이지 100개를 만드는 것 말고 그때그때마다 정보 다르게 들어오게끔 하는 것 찾아봐야함.
 4. [o] 주류 검색 창과 "맥주, 소주"와 같은 태그 버튼 구현
  */
import S from "./styled";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import NativeSelect from "@mui/material/NativeSelect";
import FormControl from "@mui/material/FormControl";
import SearchIcon from "@mui/icons-material/Search";
import IconButton from "@mui/material/IconButton";
import noAuthClient from "../../apis/noAuthClient";
import { InputLabel } from "@mui/material";

import logo from "../../image/bejuryu.png";

function Dictionary() {
  const navigate = useNavigate();
  const location = useLocation();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [jsonData, setJsonData] = useState(null);
  // 검색어 상태를 useState 훅 사용하여 관리
  const [searchTerm, setSearchTerm] = useState("");
  // 주류 종류
  const [selectedCategory, setSelectedCategory] = useState("");
  // 이미지
  const [decodedImage, setDecodedImage] = useState(null);
  // 주류 정보 상태
  const [drinkInfo, setDrinkInfo] = useState(null);
  const [drinkInfoList, setDrinkInfoList] = useState([]);

  // 이미지 디코딩 함수
  const decodeBase64 = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    for (let i = 0; i < binaryString.length; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }
    return URL.createObjectURL(new Blob([bytes.buffer], { type: "image/png" }));
  };

  useEffect(() => {
    if (drinkInfo) {
      const decodedImage = decodeBase64(drinkInfo.image);
      setDecodedImage(decodedImage);
    }
  }, [drinkInfo]);

  const handleSearch = async (e) => {
    e.preventDefault();

    try {
      if (searchTerm !== "") {
        const nameRes = await noAuthClient({
          method: "get",
          url: `/drinks/name/${searchTerm}`, // 작성한 주류 이름에 해당하는 API 요청
        });
        console.log("Name response:", nameRes);
        console.log(searchTerm);
        console.log(nameRes.data.drinks.length);

        setDrinkInfoList(nameRes.data.drinks);
        // 이미지 디코딩 및 설정
        // const decodedImage = decodeBase64(nameRes.data.drinks[0].image);
        // setDecodedImage(decodedImage);
      } else if (selectedCategory !== "ALL") {
        const typeRes = await noAuthClient({
          method: "get",
          url: `/drinks/type/${selectedCategory}`, // 선택한 카테고리에 해당하는 API 요청
        });
        console.log("Type response:", typeRes);
        console.log(selectedCategory);
        console.log(typeRes.data.drinks.length);

        setDrinkInfoList(typeRes.data.drinks);
        //setDrinkInfo(typeRes.data.drinks[typeRes.data.drinks.length - 1]);
        // const decodedImage = decodeBase64(typeRes.data.drinks[0].image);
        // setDecodedImage(decodedImage);
      }
    } catch (error) {
      if (error.response) {
        const err = error.response.data;
        console.log(err);
      }
    }
  };

  useEffect(() => {
    setDrinkInfoList([]);
  }, [searchTerm]);

  // // 주류 이름 검색 함수
  // const handleNameSearch = async (e) => {
  //   e.preventDefault();

  //   try {
  //     const res = await noAuthClient({
  //       method: "get",
  //       url: `/drinks/name/${searchTerm}`,
  //     });
  //     if (res) {
  //       console.log(res);
  //     }
  //   } catch (error) {
  //     if (error.response) {
  //       const err = error.response.data;
  //       console.log(err);
  //     }
  //     //console.log(searchTerm);
  //   }
  // };

  // 검색어 입력 필드의 onChange 핸들러
  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };

  // 카테고리
  const handleCategoryChange = (e) => {
    setSelectedCategory(e.target.value);
  };

  //const dictionaryData = location.state?.dictionaryData;

  return (
    <S.Container>
      <S.Wrapper>
        <S.Info>
          <S.searchBox>
            <Box
              component="form"
              sx={{
                display: "flex",
                alignitems: "center",
                justifyContent: "center",
                "&.MuiTextField-root": { height: "100%" },
                "&.MuiInputBase=root": { height: "100%" },
              }}
              noValidate
              autoComplete="off"
            >
              <FormControl sx={{ m: 1, width: "15ch", alignitems: "center" }}>
                <NativeSelect
                  defaultValue={"none"}
                  onChange={handleCategoryChange}
                  inputProps={{ name: "category", id: "uncontrolled-native" }}
                >
                  <option value={"ALL"}>이름검색</option>
                  <option value={"SOJU"}>소주</option>
                  <option value={"BEER"}>맥주</option>
                  <option value={"WINE"}>와인</option>
                  <option value={"LIQUEUR"}>리큐어</option>
                  <option value={"WHISKEY"}>위스키</option>
                  <option value={"FRUIT"}>과실주</option>
                  <option value={"YAKJU"}>약주</option>
                  <option value={"BRANDY"}>브랜디</option>
                  <option value={"RICE_WIND"}>청주</option>
                  <option value={"MAKGEOLLI"}>막걸리</option>
                </NativeSelect>
              </FormControl>
              <TextField
                id="standard-search"
                label="찾고싶은 주류를 입력해주세요!"
                type="search"
                variant="standard"
                alignitems="center"
                InputProps={{
                  disableUnderline: true,
                }}
                sx={{ m: 1, width: "60ch", height: "100%" }}
                value={searchTerm} // 검색어 상태를 입력 필드의 값으로 설정
                onChange={handleChange} // onChange 핸들러 연결
              />
              <IconButton
                type="submit"
                onClick={handleSearch}
                aria-label="search"
              >
                <SearchIcon />
              </IconButton>
            </Box>
          </S.searchBox>
        </S.Info>
        {/* <S.Title>주류를 검색해 보세요!</S.Title> */}
        <S.juruBox style={{ paddingTop: "20px" }}>
          <div style={{ display: "flex", alignitems: "center" }}>
            {drinkInfoList.map((drinkInfo) => (
              <S.WhiteBox key={drinkInfo.id}>
                <S.Image
                  src={decodeBase64(drinkInfo.image)}
                  alt="주류 이미지"
                />
                <S.Text>{drinkInfo.name}</S.Text>
              </S.WhiteBox>
            ))}
          </div>
        </S.juruBox>
      </S.Wrapper>
    </S.Container>
  );
}

export default Dictionary;
