import { combineReducers } from "redux";
import dictionaryReducer from "./reducer/dictionaryReducer";
import nameSlice from "./reducer/nameSlice";

const rootReducer = combineReducers({
  dictionary: dictionaryReducer,
  name: nameSlice,
});

export default rootReducer;
