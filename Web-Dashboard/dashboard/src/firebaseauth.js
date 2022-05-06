import withFirebaseAuth from 'react-with-firebase-auth';
//import * as firebase from 'firebase/app';
import 'firebase/auth';
import firebaseConfig from '../src/firebaseConfig';
import firebase from 'firebase/app';

const firebaseApp = firebase.initializeApp(firebaseConfig);

export default firebaseApp;