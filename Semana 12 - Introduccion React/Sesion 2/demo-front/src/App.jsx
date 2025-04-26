import './App.css'
import ListCards from './components/ListCards'
import { products1, products2 } from './data/products'

function App() {
  const apiUrl = import.meta.env.VITE_API_URL;
  console.log(apiUrl);
  console.log(products1, products2)

  return (
    <>
      <ListCards products={products1}/>
    </>
  )
}

export default App
