import { React, useState } from 'react';
import './NavBar.css';
import Logo from '../../../Assets/Images/logo.png';
import {
    MDBNavbar,
    MDBContainer,
    MDBNavbarBrand,
    MDBIcon,
    MDBNavbarNav,
    MDBNavbarItem,
    MDBNavbarLink,
    MDBNavbarToggler,
    MDBCollapse,
    MDBDropdown,
    MDBDropdownMenu,
    MDBDropdownToggle,
    MDBDropdownItem,
    MDBDropdownLink
} from 'mdb-react-ui-kit';

import { CgProfile } from 'react-icons/cg';

function NavBar() {
    const [showNavText, setShowNavText] = useState(false);
    return (
        <MDBNavbar expand='lg' light bgColor='light'>
            <MDBContainer>
                <MDBNavbarBrand href='#' ><img src={Logo} className='logo' /></MDBNavbarBrand>
                <MDBNavbarToggler
                    type='button'
                    data-target='#navbarText'
                    aria-controls='navbarText'
                    aria-expanded='false'
                    aria-label='Toggle navigation'
                    onClick={() => setShowNavText(!showNavText)}
                >
                    <MDBIcon icon='bars' fas />
                </MDBNavbarToggler>
                <MDBCollapse navbar show={showNavText}>
                    <MDBNavbarNav className='mr-auto mb-2 mb-lg-0'>
                        <MDBNavbarItem>
                            <MDBNavbarLink active aria-current='page' href='#'>
                                Home
                            </MDBNavbarLink>
                        </MDBNavbarItem>
                        <MDBNavbarItem>
                            <MDBNavbarLink href='#'>Order History</MDBNavbarLink>
                        </MDBNavbarItem>
                        <MDBNavbarItem>
                            <MDBNavbarLink href='#'></MDBNavbarLink>
                        </MDBNavbarItem>
                    </MDBNavbarNav>
                    <MDBDropdown group className='shadow-0'>
                        <MDBDropdownToggle color='light'><span className='navbar-text'>< CgProfile /></span></MDBDropdownToggle>
                        <MDBDropdownMenu>
                            <MDBDropdownItem>
                                <MDBDropdownLink href="#">View Profile</MDBDropdownLink>
                            </MDBDropdownItem>
                            <MDBDropdownItem>
                                <MDBDropdownLink href="#">Log Out</MDBDropdownLink>
                            </MDBDropdownItem>
                        </MDBDropdownMenu>
                    </MDBDropdown>
                </MDBCollapse>
            </MDBContainer>
        </MDBNavbar>
    )
}

export default NavBar