import './App.css';
import NavBar from './Components/Common/NavBar/NavBar';
import Footer from './Components/Common/Footer/Footer';
import Orders from './Components/Pages/Orders/Orders';
import Login from './Components/Pages/Login/Login';
import SingleOrder from './Components/Pages/SingleOrder/SingleOrder';
import { Route, Routes, BrowserRouter } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <div>
        <NavBar />
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/orders" element={<Orders />} />
          <Route path="orders/:id/singleorder" element={<SingleOrder/>} />
        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
