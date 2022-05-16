import React, { useEffect, useState } from 'react'
import { MDBContainer, MDBBtn, MDBTableBody, MDBTableHead, MDBTable } from 'mdb-react-ui-kit';
import './Orders.css';
import { NavLink } from 'react-router-dom';
import { getAuth, signOut } from "firebase/auth";
import firebase from 'firebase/app';
import 'firebase/auth';
import { Navigate } from "react-router-dom";
import firebaseApp from '../../../firebaseauth';
import userEvent from '@testing-library/user-event';
import 'firebase/firestore';

function Orders() {

  const db = firebase.firestore();

  //const [islogged, setIsLogged] = useState(false);
  const [orderList, setOrderList] = useState([]);


  useEffect(() => {
    var user = firebase.auth().currentUser;
    console.log(user);

    if (user) {
      //setIsLogged(true);
    } else {
      window.history.go(-1);
    }
  }, []);

  useEffect(() => {
    fetchOrderList();
  }, []);

  const fetchOrderList = async() => {
    db.collection("orders").get().then((querySnapshot) => {
             
      // Loop through the data and store
      // it in array to display
      setOrderList(querySnapshot.docs.map((doc) => ({id: doc.id, ...doc.data()})));

  })
  }


  return (
    <MDBContainer>

      {
  /*
        islogged
          ? <Navigate to={{ pathname: '/orders' }} />
          : <></>
    */  }

      <MDBContainer className="searchcont">
        <form className='d-flex input-group w-auto'>
          <input type='search' className='form-control searcharea' placeholder='Order ID' aria-label='Search' />
          <MDBBtn color='primary' className="searchbtn">Search</MDBBtn>
        </form>
      </MDBContainer>
      <MDBContainer className="datatablecont">
        <MDBTable striped className="mytable">
          <MDBTableHead className="tablehead">
            <tr>
              <th scope='col'>Order ID</th>
              <th scope='col'>Customer</th>
              <th scope='col'>Description</th>
              <th scope='col'>Price</th>
              <th scope='col'>Status</th>
              <th scope='col'>View</th>
            </tr>
          </MDBTableHead>
          <MDBTableBody>
            {
              orderList.map((i, key) => {
                return (
                  <tr key={key}>
                    <th scope='row'>{i.id}</th>
                    <td>{i.customer}</td>
                    <td>{i.description}</td>
                    <td>{i.amount}</td>
                    <td>{i.status}</td>
                    <td><NavLink className="btn tablebtn" to={`${i.id}/singleorder`}>View</NavLink></td>
                  </tr>
                )
              })
            }

          </MDBTableBody>
        </MDBTable>
      </MDBContainer>

    </MDBContainer>

  )
}

export default Orders