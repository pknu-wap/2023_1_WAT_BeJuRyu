import axios from "axios";

const noAuthClient = axios.create({
  // baseURL은 서버 작업이 완료되면 결정짓는다.
  baseURL: "http://141.164.49.27",
  //baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

export default noAuthClient;
