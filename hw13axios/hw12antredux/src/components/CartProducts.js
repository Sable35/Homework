import { Button, Input, Table } from 'antd';
import {useDispatch, useSelector} from 'react-redux';
import {useEffect, useState} from "react";
import cartService from "../services/CartService";

const ProductCards = () => {
    const [isAuthorized, setIsAuthorized] = useState(false);
    const cartProducts = useSelector((state) => state.productsInCart.productsInCart);
    const user = useSelector((state) => state.User.User);
    const dispatch = useDispatch()

    useEffect(() => {
        isUserAuthorized(user)
        getProductsInCart();
    }, []);

    const getProductsInCart = () => {
        console.log('id пользователя', user);
        cartService.getProductsInCart(dispatch, 2);
        console.log('товары', cartProducts);
    }

    const isUserAuthorized = (user) => {
        if (user !== null){
            setIsAuthorized(true);
        } else setIsAuthorized(false);
    }

    const columns = [
        {
            title: 'Название',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'Количество',
            dataIndex: 'amount',
            key: 'amount',
            render: (text, record) => (
                <div>
                    <Button onClick={record.amount >0 ? () => cartService.updateProductFromCart(record.id,{"amount":record.amount-1}, dispatch) : null}>-</Button>
                    <Input value={text} style={{ width: '50px' }} />
                    <Button onClick={() => cartService.updateProductFromCart(record.id,{"amount":record.amount+1}, dispatch)}>+</Button>
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
                    <Button onClick={() => cartService.deleteProductFromCart(record.id, dispatch)}type="primary" size="large" style={{ backgroundColor: 'red', margin: '0px 5px' }}>
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
            <Table
                dataSource={cartProducts}
                columns={columns}
                pagination={false}
                rowKey="id"
            />
            <div style={{ textAlign: 'left', margin: '20px' }}>
                <h3>Общая стоимость: {totalCost} рублей</h3>
            </div>
        </div>
    );
};

export default ProductCards;