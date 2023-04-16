import axios from "axios";

const noAuthClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

export default noAuthClient;
