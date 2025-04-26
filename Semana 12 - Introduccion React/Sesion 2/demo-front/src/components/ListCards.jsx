import React from "react";
import Card from "./Card";

const ListCards = ({ products }) => {
    // const inProducts = products;
    const [inProuctsState, setInProductsState] = React.useState(products);

    const handleAddProduct = () => {
        const newProduct = {
            id: inProuctsState.length + 1,
            name: "New Product",
            price: Math.floor(Math.random() * 100),
            image: "https://picsum.photos/200/200?random=" + (inProuctsState.length + 1),
        };
        
        // inProducts.push(newProduct);
        // console.log('Nuevos productos', inProducts);
        setInProductsState(prevState => [...prevState, newProduct]);
    };

    return (
        <div className="list-cards">
            <button onClick={handleAddProduct}>Add Product</button>
            {/* {inProducts.map((product) => (
                <Card key={product.id} {...product} />
            ))} */}
            {inProuctsState.map((product) => (
                <Card key={product.id} {...product} />
            ))}
        </div>
    );
};

export default ListCards;