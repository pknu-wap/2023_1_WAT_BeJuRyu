/*TODO (목요일 완료해)
    1. 페이지 전체적인 ui
    2. 이미지를 눌렸을때 띄워지는 주류정보창
    3. 주류정보 & 평점 및 리뷰 
*/
import React from "react";
import S from "./styled";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useDispatch } from "react-redux";
import Rating from "@mui/material/Rating";
import StarIcon from "@mui/icons-material/Star";
import Box from "@mui/material/Box";

function JuryuInfo() {
  const [value, setValue] = useState(2);
  const [hover, setHover] = useState(-1);
  const navigate = useNavigate();

  const labels = {
    0.5: "Useless",
    1: "Useless+",
    1.5: "Poor",
    2: "Poor+",
    2.5: "Ok",
    3: "Ok+",
    3.5: "Good",
    4: "Good+",
    4.5: "Excellent",
    5: "Excellent+",
  };

  const dictionary = () => {
    navigate("/dictionary");
  };

  function getLabelText(value) {
    return `${value} Star${value !== 1 ? "s" : ""}, ${labels[value]}`;
  }

  const JuryuView = (
    <S.Container>
      <S.Wrapper>
        <S.WhiteBox>
          <S.Title>주류를 평가해주세요!</S.Title>

          <Box
            sx={{
              width: 200,
              display: "flex",
              alignItems: "center",
            }}
          >
            <Rating
              name="hover-feedback"
              value={value}
              precision={0.5}
              getLabelText={getLabelText}
              onChange={(event, newValue) => {
                setValue(newValue);
              }}
              onChangeActive={(event, newHover) => {
                setHover(newHover);
              }}
              emptyIcon={
                <StarIcon
                  style={{ opacity: 0.55 }}
                  fontSize="inherit"
                  sx={{
                    color: "yellow",
                  }}
                />
              }
            />
            {value !== null && (
              <Box sx={{ ml: 2 }}>{labels[hover !== -1 ? hover : value]}</Box>
            )}
          </Box>
          <S.textInput></S.textInput>
        </S.WhiteBox>
        <S.ReButton onClick={dictionary}>다른 술 검색하기</S.ReButton>
      </S.Wrapper>
    </S.Container>
  );

  return JuryuView;
}

export default JuryuInfo;
