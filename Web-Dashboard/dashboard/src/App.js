import logo from './logo.svg';
import './App.css';
import './firebase/config';
import './pages/login';import Login from './pages/login';
//import {UserProvider} from './firebase/UserProvider';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Home from './pages/home';

function App() {
  return (
    // <UserProvider>
      <BrowserRouter>
        <Switch>
          <Route exact path="/login" component={Login}/>
          <Route exact path="/home" component={Home}/>
        </Switch>
      </BrowserRouter>
    //</UserProvider>
  );
}

export default App;
