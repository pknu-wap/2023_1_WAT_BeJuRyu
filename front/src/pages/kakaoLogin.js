import { BrowserRouter, Router, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";

const router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default router;
