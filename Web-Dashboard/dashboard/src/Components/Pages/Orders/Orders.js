import React from 'react'
import { MDBContainer, MDBBtn, MDBTableBody, MDBTableHead, MDBTable } from 'mdb-react-ui-kit';
import './Orders.css';

function Orders() {
  return (
    <MDBContainer fluid>
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
              <th scope='col'>Prescription Image</th>
              <th scope='col'>Description</th>
              <th scope='col'>Price</th>
              <th scope='col'>Status</th>
              <th scope='col'>View</th>
            </tr>
          </MDBTableHead>
          <MDBTableBody>
            <tr>
              <th scope='row'>1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
              <td>Mark</td>
              <td>Otto</td>
              <td><MDBBtn className="tablebtn" to=''>View</MDBBtn></td>
            </tr>
            <tr>
              <th scope='row'>2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
              <td>Mark</td>
              <td>Otto</td>
              <td><MDBBtn className="tablebtn">View</MDBBtn></td>
            </tr>
            <tr>
              <th scope='row'>2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
              <td>Mark</td>
              <td>Otto</td>
              <td><MDBBtn className="tablebtn">View</MDBBtn></td>
            </tr>
            <tr>
              <th scope='row'>2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
              <td>Mark</td>
              <td>Otto</td>
              <td><MDBBtn className="tablebtn">View</MDBBtn></td>
            </tr>
          </MDBTableBody>
        </MDBTable>
      </MDBContainer>

    </MDBContainer>

  )
}

export default Orders