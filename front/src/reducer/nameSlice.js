const { createSlice } = require("@reduxjs/toolkit");

//const storedName = localStorage.getItem("nickname");

const initialState = {
  name: "",
};

const nameSlice = createSlice({
  name: "NAME",
  initialState,
  reducers: {
    GET_NAME: (state, action) => {
      state.name = action.payload;
      //localStorage.setItem("nickname", action.payload);
    },
  },
});

export const { GET_NAME } = nameSlice.actions;

export default nameSlice.reducer;
