import React from "react";

function Login(){
    return (
      <div className="login-container">
        <div className="ui card login-card">
          <div className="content">
            <form className="ui form">
              <div className="field">
                <label>
                  Email
                  <input type="email" name="email" placeholder="Email" />
                </label>
              </div>
              <div className="field">
                <label>
                  Password
                  <input type="password" name="password" placeholder="Password" />
                </label>
              </div>
              <button className="ui primary button login" type="submit">
                LOGIN
              </button>
            </form>
          </div>
        </div>
      </div>
    );
}

export default Login;