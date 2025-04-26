import Card from "./Card.js";

class ListCards {
    constructor(products) {
        this.products = products;
        this.container = document.createElement("div");
    }

    render() {
        this.container.className = "list-cards";

        this.products.forEach((product) => {
            const card = new Card(product);
            this.container.appendChild(card.render());
        });

        // Button to add a new product
        const button = document.createElement("button");
        button.textContent = "Add Product";
        button.addEventListener("click", () => {
            const newProduct = {
                id: this.products.length + 1,
                name: "New Product",
                price: Math.floor(Math.random() * 100),
                image: "https://picsum.photos/200/200?random=" + (this.products.length + 1),
            };
            this.addElement(newProduct);
        });

        this.container.appendChild(button);

        return this.container;
    }

    addElement(product) {
        this.products.push(product);
        const card = new Card(product);
        this.container.insertBefore(card.render(), this.container.lastChild);
    }
}

export default ListCards;