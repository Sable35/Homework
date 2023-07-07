import ProductCards from "../components/ProductCards";
import React from "react";

export const ProductsPage = () => {
    return (
        <>
            <h2 style={{color: "#228B22", fontSize:'30px'}}>Список продуктов</h2>
            <ProductCards />
        </>
    )
}