import React from 'react';
import { MDBContainer, MDBCol, MDBRow } from 'mdb-react-ui-kit';
import './Login.css';
import Logo from '../../../Assets/Images/logo.png';

function Login() {
  return (
    <MDBContainer className="main-container">
      <MDBRow>
        <MDBCol sm="6">
          <h1>hi</h1>
        </MDBCol>
        <MDBCol sm="6">
          <div className="center">
            <img src={Logo} className="logo-in" />
          </div>
          <h2 className="login-text">Welcome to the Healthcare <br />Admnistrator Dashboard</h2>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  )
}

export default Login