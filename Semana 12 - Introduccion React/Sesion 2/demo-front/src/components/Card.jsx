import React from "react";

const Card = ({ name, price, image }) => {
    return (
        <div className="card">
            <img src={image} alt={name} />
            <h3>{name}</h3>
            <p>${price}</p>
        </div>
    );
}

export default Card;