import { products1, products2} from "./data/products.js";
import ListCards from "./components/ListCards.js";
// import getRickAndMortyCharacters from "./services/api.js";

console.log('Hola mundo')

const listCards = new ListCards(products1);
document.body.appendChild(listCards.render());

// const rickChars = new ListCards(products2);
// getRickAndMortyCharacters()
//     .then((response) => {
//         const characters = response.data.results;
//         console.log(characters);
//         const listCards2 = new ListCards(characters);
//         document.body.appendChild(listCards2.render());
//     })
//     .catch((error) => {
//         console.error('Error fetching characters:', error);
//     });