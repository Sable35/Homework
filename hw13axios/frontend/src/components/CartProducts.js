import { Avatar, Button, Card, Input, Table } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect, useState } from 'react';
import cartService from '../services/CartService';
import paymentService from '../services/PaymentService';

const ProductCards = () => {
    const cartProducts = useSelector((state) => state.productsInCart.productsInCart);
    const { user: currentUser } = useSelector((state) => state.auth);
    const dispatch = useDispatch()
    useEffect(() => {
        if (currentUser){
            getProductsInCart();
        }
    }, [currentUser]);

    const getProductsInCart = () => {
        console.log('id пользователя', currentUser);
        cartService.getProductsInCart(dispatch, currentUser.id);
        console.log('товары', cartProducts);
    }
    const columns = [
        {
            title: 'Название',
            dataIndex: 'name',
            key: 'name',
            sorter: (a, b) => a.name.localeCompare(b.name),
        },
        {
            title: 'Количество',
            dataIndex: 'amount',
            key: 'amount',
            render: (text, record) => (
                <div>
                    <Button onClick={record.amount > 0 ? () => cartService.updateProductFromCart(record.id,{ "amount":record.amount-1}, currentUser.id, dispatch) : null}>-</Button>
                    <Input value={text} style={{ width: '50px' }} />
                    <Button onClick={() => cartService.updateProductFromCart(record.id,{ "amount":record.amount+1}, currentUser.id, dispatch)}>+</Button>
                </div>
            ),
        },
        {
            title: 'Цена',
            dataIndex: 'price',
            key: 'price',
        },
        {
            title: 'Действие',
            key: 'action',
            render: (text, record) => (
                <div style={{ display: 'flex', justifyContent: 'center' }}>
                    <Button onClick={() => cartService.deleteProductFromCart(record.id,currentUser.id, dispatch)}type="primary" size="large" style={{ backgroundColor: 'red', margin: '0px 5px' }}>
                        Удалить
                    </Button>
                </div>
            ),
        },
    ];

    const totalCost = cartProducts.reduce((total, product) => {
        return total + (product.amount * product.price);
    }, 0);

    return (
        <div>
            {currentUser ? (
                <div>
                    <Table
                        dataSource={cartProducts}
                        columns={columns}
                        pagination={false}
                        rowKey="id"
                    />
                    <div style={{ textAlign: 'left', margin: '20px' }}>
                        <h3>Общая стоимость: {totalCost} рублей</h3>
                    </div>
                    <Button onClick={() => paymentService.SumToPay(dispatch,currentUser.id)} type="primary" size="large" style={{ backgroundColor: 'green', margin: '15px' }}>
                        Купить
                    </Button>
                </div>
            ) : (
                <h3>Вам нужно войти в аккаунт, чтобы посмотреть корзину</h3>
            )}
        </div>
    );
};

export default ProductCards;