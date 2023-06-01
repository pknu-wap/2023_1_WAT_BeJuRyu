import nameReducer from "../reducer/nameSlice.js";
const { configureStore } = require("@reduxjs/toolkit");

const store = configureStore({
  reducer: {
    name: nameReducer,
  },
});

export default store;
