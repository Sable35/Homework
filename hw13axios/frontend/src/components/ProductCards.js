import { Button, Card, Col, Input, Row } from 'antd';
import {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import productService from "../services/productService";
import CartService from "../services/CartService";


const ProductCards = () => {
    const products = useSelector((state) => state.products.products);
    const dispatch = useDispatch()
    const [searchValue, setSearchValue] = useState('');
    const [filteredProducts, setFilteredProducts] = useState(products);

    useEffect(() => {
        getProducts();
    }, []);

    const getProducts = () => {
        productService.getProducts(dispatch);
    }

    const handleSearch = (value) => {
        setSearchValue(value);

        const filtered = products.filter((product) =>
            product.name.toLowerCase().includes(value.toLowerCase())
        );
        setFilteredProducts(filtered);
    };

    return (
        <div>
            {console.log(filteredProducts)}
            <Input.Search
                placeholder="Поиск по имени"
                allowClear
                onSearch={handleSearch}
                style={{ marginBottom: 16 }}
            />
            <Row gutter={[24, 24]}>
                {filteredProducts.map(product => (
                    <Col xs={24} sm={12} md={8} lg={6} key={product.id}>
                        <Card title={product.name} bordered>
                            <div style={{ minHeight: 200, display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                                <div style={{fontSize: 18}}>
                                    <p>Количество: {product.amount}</p>
                                    <p>Цена: {product.price}</p>
                                </div>

                                <div>
                                    <Button onClick={() => CartService.addToCart(2,{ "id": product.id, "amount": 1 }, dispatch)} type="primary" size="large" style={{ width: '50%', marginBottom: 16, backgroundColor: 'green', position: 'absolute', bottom: 0, right: 15 }}>
                                        В корзину
                                    </Button>
                                </div>
                            </div>
                        </Card>
                    </Col>
                ))}
            </Row>
        </div>
    );
};

export default ProductCards;