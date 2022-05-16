import { React, useState } from 'react';
import { MDBContainer, MDBCol, MDBRow } from 'mdb-react-ui-kit';
import './Login.css';
import Logo from '../../../Assets/Images/logo.png';
//import Firebaseauth from '../../../firebaseauth';
import withFirebaseAuth from 'react-with-firebase-auth';
import firebase from 'firebase/app';
import 'firebase/auth';
import { Navigate } from "react-router-dom";
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
import firebaseApp from '../../../firebaseauth';
//const firebaseApp = firebase.initializeApp(firebaseConfig);
/* const firebaseAppAuth = firebaseApp.auth(); const providers = {
  googleProvider: new firebase.auth.GoogleAuthProvider(),
}; */

function Login() {

  const [email, setEmail] = useState("");
  const [pwd, setPwd] = useState("");
  const [logged, setLogged] = useState(false);

  const emailHandler = event => {
    setEmail(event.target.value);
  }

  const passwordHandler = event => {
    setPwd(event.target.value);
  }

  const loginAuth = (e) => {
    e.preventDefault();
    firebase.auth().signInWithEmailAndPassword(email, pwd).then(function (firebaseUser) {
      setLogged(true);

    })
      .catch(function (error) {
        // Handle Errors here.
        window.confirm("sorry" + error);
        var errorCode = error.code;
        var errorMessage = error.message;
        setLogged(false);
      });

  }


  return (

    <MDBContainer className="main-container">

      {
        logged
          ? <Navigate to={{ pathname: '/orders' }} />
          : <></>
      }

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

              <input className="btn create-button center" type="submit" onClick={loginAuth} value="LOGIN" />
            </form>
          </div>
        </MDBCol>

        <MDBCol sm="6">
          <div className="center">
            <img src={Logo} className="logo-in" />
          </div>
          <h2 className="login-text">Welcome to the Healthcare <br />Administrator Dashboard</h2>
        </MDBCol>

      </MDBRow>
    </MDBContainer>

  )
}

export default Login