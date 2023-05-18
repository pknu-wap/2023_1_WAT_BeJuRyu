import nicknameReducer from "../reducer/nicknameReducer.js";
const { configureStore } = require("@reduxjs/toolkit");

const store = configureStore({
  reducer: {
    nickname: nicknameReducer,
  },
});

export default store;
