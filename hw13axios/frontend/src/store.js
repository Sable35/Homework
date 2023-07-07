import { configureStore } from '@reduxjs/toolkit'
import productsReducer from "./slices/productsSlice";
import productsInCartReducer from "./slices/CartProductsSlice";
import AuthReducer from "./slices/AuthSlice";

export default configureStore({
    reducer: {
        products: productsReducer,
        productsInCart: productsInCartReducer,
        auth: AuthReducer,
    }
})