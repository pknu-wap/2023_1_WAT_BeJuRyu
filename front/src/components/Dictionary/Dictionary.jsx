/* TODO
 1. [o] 사전 페이지 UI 구현 어떤식으로 할건지 결정
 2. [] 사전 페이지에서 주류 목록을 띄우고 해당 주류를 누르면 주류에 대한 정보와 후기 작성까지 가능하게끔
 3. [] 주류 사진을 클릭할 때 마다 다른 페이지로 연결이 될 텐데, 페이지 100개를 만드는 것 말고 그때그때마다 정보 다르게 들어오게끔 하는 것 찾아봐야함.
 4. [o] 주류 검색 창과 "맥주, 소주"와 같은 태그 버튼 구현
  */
import S from "./styled";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import NativeSelect from "@mui/material/NativeSelect";
import FormControl from "@mui/material/FormControl";
import SearchIcon from "@mui/icons-material/Search";
import IconButton from "@mui/material/IconButton";
import { InputLabel } from "@mui/material";

function Dictionary() {
  const navigate = useNavigate();
  const [jwtToken, setJwtToken] = useState("");
  const [imagePreview, setImagePreview] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);

  const DictionaryView = () => {
    return (
      <S.Container>
        <S.Wrapper>
          <S.searchBox>
            <Box
              component="form"
              sx={{
                "&.MuiTextField-root": { m: 1, width: "40ch" },
              }}
              noValidate
              autoComplete="off"
            >
              <FormControl fullWidth>
                <NativeSelect
                  defaultValue={"none"}
                  inputProps={{ name: "category", id: "uncontrolled-native" }}
                >
                  <option value={"none"}>통합검색</option>
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
              />
              <IconButton type="submit" sx={{ p: "10px" }} aria-label="search">
                <SearchIcon />
              </IconButton>
            </Box>
          </S.searchBox>

          <S.Title>주류를 검색해 보세요!</S.Title>
          <S.WhiteBox></S.WhiteBox>
        </S.Wrapper>
      </S.Container>
    );
  };

  return <DictionaryView />;
}

export default Dictionary;
