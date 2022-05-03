import React from 'react'
import { MDBContainer, MDBBtn, MDBTableBody, MDBTableHead, MDBTable } from 'mdb-react-ui-kit';
import './Orders.css';
import { NavLink } from 'react-router-dom';

function Orders() {
  const DUMMY_DATA = [
    {
      "id": "1",
      "customer": "Nishu",
      "Prescription": "Image",
      "Description": "Try the new cross-platform PowerShell https://aka.ms/pscore6, Try the new cross-platform PowerShell https://aka.ms/pscore6",
      "Price": "1230.00",
      "Status": "Completed"
    },
    {
      "id": "2",
      "customer": "Nishu",
      "Prescription": "Image",
      "Description": "Try the new cross-platform PowerShell https://aka.ms/pscore6, Try the new cross-platform PowerShell https://aka.ms/pscore6",
      "Price": "1230.00",
      "Status": "Completed"
    },
    {
      "id": "3",
      "customer": "Nishu",
      "Prescription": "Image",
      "Description": "Try the new cross-platform PowerShell https://aka.ms/pscore6, Try the new cross-platform PowerShell https://aka.ms/pscore6",
      "Price": "1230.00",
      "Status": "Completed"
    },
    {
      "id": "4",
      "customer": "Nishu",
      "Prescription": "Image",
      "Description": "Try the new cross-platform PowerShell https://aka.ms/pscore6, Try the new cross-platform PowerShell https://aka.ms/pscore6",
      "Price": "1230.00",
      "Status": "Completed"
    },
    {
      "id": "5",
      "customer": "Nishu",
      "Prescription": "Image",
      "Description": "Try the new cross-platform PowerShell https://aka.ms/pscore6, Try the new cross-platform PowerShell https://aka.ms/pscore6",
      "Price": "1230.00",
      "Status": "Completed"
    },
    {
      "id": "6",
      "customer": "Nishu",
      "Prescription": "Image",
      "Description": "Try the new cross-platform PowerShell https://aka.ms/pscore6, Try the new cross-platform PowerShell https://aka.ms/pscore6",
      "Price": "1230.00",
      "Status": "Completed"
    }
  ]
  return (
    <MDBContainer>
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
              DUMMY_DATA.map((i) => {
                return (
                <tr>
                  <th scope='row'>{i.id}</th>
                  <td>{i.customer}</td>
                  <td>{i.Description}</td>
                  <td>{i.Price}</td>
                  <td>{i.Status}</td>
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