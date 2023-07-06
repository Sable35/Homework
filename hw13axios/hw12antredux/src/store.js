import { configureStore } from '@reduxjs/toolkit'
import productsReducer from "./slices/productsSlice";
import productsInCartReducer from "./slices/CartProductsSlice";
import UserReducer from "./slices/UserSlice";

export default configureStore({
    reducer: {
        products: productsReducer,
        productsInCart: productsInCartReducer,
        User: UserReducer
    }
})