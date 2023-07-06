import axios from "axios";
import {set} from "../slices/CartProductsSlice";
import {useSelector} from "react-redux";

const API_URL = "http://localhost:8080/carts";

const getProductsInCart = (dispatch, idClient) => {
    return axios.get(API_URL + `/${idClient}`).then(
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
const addToCart = (idClient, product, dispatch) => {
    return axios.post(API_URL + `/${idClient}`, product).then(
        (response) => {
            getProductsInCart(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const deleteProductFromCart = (id, dispatch) => {
    return axios.delete(API_URL + `/${id}`) .then(
        (response) => {
            getProductsInCart(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const updateProductFromCart = (id,product, dispatch) => {
    return axios.put(API_URL + `/${id}`, product) .then(
        (response) => {
            getProductsInCart(dispatch)
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