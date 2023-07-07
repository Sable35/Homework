import axios from "axios";
import {set} from "../slices/CartProductsSlice";
import {useSelector} from "react-redux";
import AuthHeader from "./AuthHeader";

const API_URL = "http://localhost:8080/payments";

const SumToPay = (dispatch, idClient) => {
    return axios.post(API_URL + `/${idClient}`,  {headers: AuthHeader()}).then(
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

const PaymentService = {
    SumToPay: SumToPay,
};
export default PaymentService