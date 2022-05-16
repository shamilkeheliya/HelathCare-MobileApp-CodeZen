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
import { NavLink } from 'react-router-dom';
import { CgProfile } from 'react-icons/cg';
import firebaseAuth from "firebase/auth";
import firebase from 'firebase/app';
import { Navigate } from "react-router-dom";
import firebaseApp from '../../../firebaseauth';


function NavBar() {

    //const [loggedout, setLoggedOut] = useState(false);

    const logout = () => {
        //const auth = firebase.getAuth();
        firebase.auth().signOut().then(() => {
            window.history.go(-1);
            //setLoggedOut(true);
        }).catch((error) => {
            window.confirm("An error occured. Please try again!");
            //setLoggedOut(false);
        });
    }

    const [showNavText, setShowNavText] = useState(false);
    return (
        <MDBNavbar expand='lg' light bgColor='light'>

            {/*
                loggedout
                    ? <Navigate to={{ pathname: '/' }} />
                    : <></> */
            }

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
                            <NavLink active aria-current='page' to='/orders' className='navlink'>
                                Order History
                            </NavLink>
                        </MDBNavbarItem>
                        <MDBNavbarItem>
                            <MDBNavbarLink href='#'></MDBNavbarLink>
                        </MDBNavbarItem>
                    </MDBNavbarNav>
                    <MDBDropdown group className='shadow-0'>
                        <MDBDropdownToggle color='light'><span className='navbar-text'>< CgProfile /></span></MDBDropdownToggle>
                        <MDBDropdownMenu>
                            <MDBDropdownItem>
                                <MDBDropdownLink onClick={logout}>Log Out</MDBDropdownLink>
                            </MDBDropdownItem>
                        </MDBDropdownMenu>
                    </MDBDropdown>
                </MDBCollapse>
            </MDBContainer>
        </MDBNavbar>
    )
}

export default NavBar