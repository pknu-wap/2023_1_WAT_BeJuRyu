// reducer/dictionaryReducer.js
const initialState = []; // 초기 상태

const dictionaryReducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_DICTIONARY_DATA":
      return action.payload; // 액션 페이로드를 새로운 상태로 반환
    default:
      return state; // 기본 상태 반환
  }
};

export default dictionaryReducer;
