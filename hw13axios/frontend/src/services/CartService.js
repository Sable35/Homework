import axios from "axios";
import {set} from "../slices/CartProductsSlice";
import {useSelector} from "react-redux";
import AuthHeader from "./AuthHeader";

const API_URL = "http://localhost:8080/carts";

const getProductsInCart = (dispatch, idClient) => {
    return axios.get(API_URL + `/${idClient}`,  {headers: AuthHeader()}).then(
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
const addToCart = (idUser, product, dispatch) => {
    return axios.post(API_URL + `/${idUser}`, product,  {headers: AuthHeader()}).then(
        (response) => {
            getProductsInCart(dispatch, idUser)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const deleteProductFromCart = (id, idUser, dispatch) => {
    return axios.delete(API_URL + `/${id}`,  {headers: AuthHeader()}) .then(
        (response) => {
            getProductsInCart(dispatch, idUser)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const updateProductFromCart = (id,product, idUser, dispatch) => {
    return axios.put(API_URL + `/${id}`, product,  {headers: AuthHeader()}) .then(
        (response) => {
            getProductsInCart(dispatch, idUser)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const CartService = {
    getProductsInCart: getProductsInCart,
    addToCart: addToCart,
    deleteProductFromCart: deleteProductFromCart,
    updateProductFromCart: updateProductFromCart,
};

export default CartService