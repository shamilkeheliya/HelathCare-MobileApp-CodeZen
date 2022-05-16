import React, { useEffect, useState } from 'react';
import { MDBContainer, MDBBtn, MDBRow, MDBCol, MDBDropdownLink, MDBDropdown, MDBDropdownItem, MDBDropdownMenu, MDBDropdownToggle } from 'mdb-react-ui-kit';
import './SingleOrder.css';
import { useParams} from 'react-router-dom';
import firebase from 'firebase/app';
import 'firebase/firestore';


function SingleOrder(props) {
  const {id} = useParams();

const [singleOrder, setSingleOrder] = useState([]);
const ref = firebase.firestore().collection("orders").doc(id); 

useEffect(() => {
  ref.get().then(DocumentSnapshot => {
    const docData = DocumentSnapshot.data();
    setSingleOrder(docData);
})
},[]);


  return (
    <MDBContainer className="singleordercont">

      {
        console.log(singleOrder.amount)
      }
      <MDBRow className="orderrow">
        <MDBCol>
          <MDBRow className="rows">
            <h5 className="headertext">Order Status</h5>
            <div>
              <table border="0" className="orderstatus">
                <tr>
                  <td className="ordertbrow colheader">Order ID</td>
                  <td className="ordertbrow">{id}</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">Date</td>
                  <td className="ordertbrow">{singleOrder.date}</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">Time</td>
                  <td className="ordertbrow">{singleOrder.time}</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">Payment Status</td>
                  <td className="ordertbrow">{singleOrder.status}</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">New Status</td>
                  <td className="ordertbrow">
                    <select name="status" className="statusdd">
                      <option value="pending">Pending</option>
                      <option value="confirmed">Confirmed</option>
                      <option value="delivering">Delivering</option>
                      <option value="completed">Completed</option>
                    </select>
                  </td>
                </tr>
              </table>
              <MDBBtn className="updatebtn">Update Order</MDBBtn>
            </div>
          </MDBRow>
          <MDBRow className='middlerow'>
            <div>
              <h5 className="headertext">Total Amount</h5>
              <p>LKR <b>{singleOrder.amount}</b></p>
            </div>
          </MDBRow>
          <MDBRow>
            <MDBBtn className="deletebtn">Delete Order</MDBBtn>
          </MDBRow>
        </MDBCol>
        <MDBCol>
          <MDBRow>
            <h5 className="headertext">Prescription</h5>
            <div className="imagecont">
              <img className='img-fluid' src={singleOrder.prescription} alt='prescription'/>
            </div>
          </MDBRow>
          <MDBRow>
            <h5 className="headertext">Description</h5>
            <div className="desctext">{singleOrder.description}</div>
          </MDBRow>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  )
}

export default SingleOrder