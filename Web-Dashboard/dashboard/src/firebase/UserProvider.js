import React, {useEffect, useState, useContext } from "react";
import auth from 'firebase/auth';

export const UserContext = React.createContext();

export const UserProvider = (props) => {
    const [session, setSession] = useState({user: null, loading: true});

    useEffect(() =>{
        const unsubscribe = auth.onAuthStateChanged.setSession({loading: false, user});

        return () => unsubscribe();
    }, []);

    return(
        <UserContext.Provider value={session}>
            {!session.loading && props.children}
        </UserContext.Provider>
    )
}

export const useSession = () =>{
    const session = useContext(UserContext);
    return session;
}