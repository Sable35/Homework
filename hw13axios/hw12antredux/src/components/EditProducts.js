import React, { useEffect } from 'react';
import { Space, Table, Input, Button } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import ProductService from '../services/productService';
import {green} from "@ant-design/colors";

const EditProducts = () => {
    const products = useSelector((state) => state.products.products);
    const dispatch = useDispatch();

    useEffect(() => {
        getProducts();
    }, []);

    const getProducts = () => {
        ProductService.getProducts(dispatch);
    };

    const columns = [
        {
            title: 'Название',
            dataIndex: 'name',
            key: 'name',
            render: (text, record) => (
                <Input
                    placeholder={text}
                    id={record.id + 1}
                />
            ),
        },
        {
            title: 'Количество',
            dataIndex: 'amount',
            key: 'amount',
            render: (text, record) => (
                <Input
                    placeholder={text}
                    type="number"
                    id={record.id + 2}
                />
            ),
        },
        {
            title: 'Цена',
            dataIndex: 'price',
            key: 'price',
            render: (text, record) => (
                <Input
                    placeholder={text}
                    type="number"
                    id={record.id + 3}
                />
            ),
        },
        {
            title: 'Действие',
            key: 'action',
            render: (_, product) => (
                <Space size="middle">
                    <Button onClick={() => ProductService.updateProduct({"id": product.id, "price":document.getElementById(product.id + 3).value, "name":document.getElementById(product.id + 1).value, "amount": document.getElementById(product.id + 2).value}, dispatch)}>
                        Сохранить
                    </Button>
                    <Button  onClick={() => ProductService.deleteProduct(product.id, dispatch)} danger>
                        Удалить
                    </Button>
                </Space>
            ),
        },

    ];

    return <><Table pagination={false} rowKey="id" columns={columns} dataSource={products} />
            <Button onClick={() => ProductService.createProduct({"name":"Новый товар","amount":1, "price": 100}, dispatch)} type="primary" size="large" style={{ backgroundColor: 'green', margin: '15px' }}>
                Новый товар
            </Button></>;
};

export default EditProducts;