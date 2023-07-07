import {createSlice} from '@reduxjs/toolkit'

export const CartProductsSlice = createSlice({
    name: 'productsInCart',
    initialState: {
        productsInCart: [],
    },
    reducers: {
        set: (state, action) => {
            state.productsInCart = action.payload;
        }
    },
})

// Action creators are generated for each case reducer function
export const {set} = CartProductsSlice.actions

export default CartProductsSlice.reducer