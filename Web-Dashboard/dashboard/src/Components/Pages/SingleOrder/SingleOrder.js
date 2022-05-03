import React from 'react';
import { MDBContainer, MDBBtn, MDBRow, MDBCol, MDBDropdownLink, MDBDropdown, MDBDropdownItem, MDBDropdownMenu, MDBDropdownToggle } from 'mdb-react-ui-kit';
import './SingleOrder.css';
import { useParams} from 'react-router-dom';

function SingleOrder(props) {
  const {id} = useParams();
  return (
    <MDBContainer className="singleordercont">
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
                  <td className="ordertbrow">xxxx-xx-xx</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">Time</td>
                  <td className="ordertbrow">hh:mm</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">Payment Method</td>
                  <td className="ordertbrow">Cash</td>
                </tr>
                <tr>
                  <td className="ordertbrow colheader">Status</td>
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
              <p>LKR <b>3400.00</b></p>
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
              Image
            </div>
          </MDBRow>
          <MDBRow>
            <h5 className="headertext">Description</h5>
            <div className="desctext">Line 47:13:  img elements must have an alt prop, either with meaningful text, or an empty string for decorative images  jsx-a11y/alt-text</div>
          </MDBRow>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  )
}

export default SingleOrder