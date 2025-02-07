import "./App.css";
import React from "react";
import { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import MainPage from "./pages/MainPage";
import Kiosk from "./pages/Kiosk"
import KioskMainScreen from "./pages/Kiosk/main";
import SearchPage from "./pages/SearchPage";
import StoreDetail from "./pages/SearchPage/StoreDetail/";
import LoginRequired from "./components/LoginRequired";
import AdminRequired from "./components/AdminRequired";
import KakaoLoginHandler from "./components/KakaoLoginHandler";

function App() {

  function setScreenSize() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
  }
  useEffect(() => {
    setScreenSize();
  });

  return (
    <div className="App">

    <Routes>
      <Route path="/" element={<MainPage/>}/>
      <Route path="/kiosk" element={<Kiosk/>}/>
      <Route path="/kmain" element={<KioskMainScreen/>}/>
      <Route path="/search" element={<SearchPage/>}/>
      <Route path="/search/1" element={<StoreDetail/>}/>
      {/* <Route path="/callback" component={KakaoLoginHandler}/> */}

      {/*/!* 보호된 라우트 *!/*/}
      {/*<Route*/}
      {/*    path="/kiosk/{user_no}"*/}
      {/*    element={*/}
      {/*      <LoginRequired user={user}>*/}
      {/*        <AdminRequired user={user} requiredRole="A">*/}
      {/*          <Kiosk />*/}
      {/*        </AdminRequired>*/}
      {/*      </LoginRequired>*/}
      {/*    }*/}
      {/*/>*/}

    </Routes>

    </div>
  );
}

export default App;
