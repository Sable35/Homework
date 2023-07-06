import React from "react";
import CartProducts from "../components/CartProducts";
import {Button} from "antd";

export const CartPage = () => {
    return (
        <>
            <h2 style={{color: "#228B22", fontSize:'30px'}}>Корзина</h2>
            <CartProducts />
            <Button type="primary" size="large" style={{ backgroundColor: 'green', margin: '15px' }}>
                Купить
            </Button>
        </>
    )
}