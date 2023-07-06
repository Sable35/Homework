import axios from "axios";
import {set} from "../slices/UserSlice";

const API_URL = "http://localhost:8080/clients";

const getUser = (login,password, dispatch) => {
    return axios.get(API_URL + `/${login}` + `/${password}`).then(
        (response) => {
            dispatch(set(response.data));
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)

            dispatch(set([]));
        });

};

const createUser = (user, dispatch) => {
    return axios.post(API_URL, user).then(
        (response) => {
            getUser(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const deleteUser = (id, dispatch) => {
    return axios.delete(API_URL + `/${id}`).then(
        (response) => {
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const UserService = {
    getUser: getUser,
    createUser: createUser,
    deleteUser: deleteUser,
};

export default UserService