import firebase from 'firebase/app';
import auth from 'firebase/auth';

export const logout = () => {
    return auth.signOut();
}