const { createSlice } = require("@reduxjs/toolkit");

const initialState = {
  nickname: "",
};

const nicknameSlice = createSlice({
  nickname: "NAME",
  initialState,
  reducers: {
    GET_NAME: (state, action) => {
      state.name = action.payload;
    },
  },
});

export const { GET_NAME } = nicknameSlice.actions;

export default nicknameSlice.reducer;
