class Card {
    constructor(product) {
        this.product = product;
    }

    render() {
        const cardDiv = document.createElement("div");
        cardDiv.className = "card";

        const title = document.createElement("h2");
        title.textContent = this.product.name;

        const price = document.createElement("p");
        price.textContent = `Price: $${this.product.price}`;

        cardDiv.appendChild(title);
        cardDiv.appendChild(price);

        return cardDiv;
    }
}

export default Card;