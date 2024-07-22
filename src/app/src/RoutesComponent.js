import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Authentication from './component/authentication/Authentication';
import BuyerDashboard from './component/buyerdashboard/BuyerDashboard';
import SellerDashboard from './component/sellerdashboard/SellerDashboard';
import Header from "./component/header/Header";
import BuyerBids from "./component/buyerbids/BuyerBids";
// import SellerProjects from './component/SellerProjects';

export const RoutesComponent = () => {
  return (
    <Router>
      <Header />
      <Routes>
        <Route exact path="/" element={ <Authentication /> } />
        <Route exact path="/buyerdashboard" element={ <BuyerDashboard /> } />
        <Route exact path="/buyerbids" element={ <BuyerBids /> } />
        <Route exact path="/sellerdashboard" element={ <SellerDashboard /> } />
        {/* <Route exact path="/sellerprojects" element={ <SellerProjects /> } /> */}
      </Routes>
    </Router>
  )
}

export default RoutesComponent