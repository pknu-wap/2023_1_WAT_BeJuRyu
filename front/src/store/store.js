import nameReducer from "../reducer/nameSlice.js";
//import dictionaryReducer from "../reducer/dictionaryReducer.js";
import { configureStore, combineReducers } from "@reduxjs/toolkit";
import {
  persistReducer,
  persistStore,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storage from "redux-persist/lib/storage";

const rootReducer = combineReducers({
  name: nameReducer,
});

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["name"],
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
  // name: nameReducer,
  //dictionary: dictionaryReducer,
});

const persistor = persistStore(store);

export { store, persistor };
