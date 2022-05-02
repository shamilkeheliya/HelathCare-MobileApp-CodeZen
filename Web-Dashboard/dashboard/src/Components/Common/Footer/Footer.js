import React from 'react';
import { MDBFooter } from 'mdb-react-ui-kit';
import './Footer.css';

function Footer() {
  return (
    <MDBFooter className='text-center text-lg-left footercol'>
      <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
        &copy; {new Date().getFullYear()} Copyright:{' '}
        <a className='text-light' href='#'>
          CodeZen Technologies
        </a>
      </div>
    </MDBFooter>
  )
}

export default Footer