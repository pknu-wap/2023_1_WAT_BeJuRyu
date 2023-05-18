const { createSlice } = require("@reduxjs/toolkit");

const initialState = {
  name: "",
};

const nameSlice = createSlice({
  name: "NAME",
  initialState,
  reducers: {
    GET_NAME: (state, action) => {
      state.name = action.payload;
    },
  },
});

export const { GET_NAME } = nameSlice.actions;

export default nameSlice.reducer;
