import { React, useState } from 'react';
import { MDBContainer, MDBCol, MDBRow } from 'mdb-react-ui-kit';
import './Login.css';
import Logo from '../../../Assets/Images/logo.png';

function Login() {

  const [email, setEmail] = useState("");
  const [pwd, setPwd] = useState("");

  const emailHandler = event => {
    setEmail(event.target.value);
  }

  const passwordHandler = event => {
    setPwd(event.target.value);
  }

  return (
    <MDBContainer className="main-container">
      <MDBRow>
        <MDBCol sm="6">
          <div className="logindiv">
            <h2>LOGIN</h2>
            <form className="loginfom">
              
              <MDBRow>
                <MDBCol>
                  <input type="text" className="form-ccontrol" placeholder="Email Address" onChange={emailHandler} />
                  <br />
                </MDBCol>
              </MDBRow>

              <MDBRow>
                <MDBCol>
                  <input type="password" className="form-ccontrol" placeholder="Password" onChange={passwordHandler} />
                  <br />
                </MDBCol>
              </MDBRow>
              <input className="btn create-button center" type="submit" onClick={() => {}} value="LOGIN" />
            </form>
          </div>
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