import { Component, createContext, useContext, useState } from 'react';
import './App.css'
import ListCards from './components/ListCards'
import { products1, products2 } from './data/products'

const UserContext = createContext({});

function App() {
  console.log('App ha sido actualizada');
  const apiUrl = import.meta.env.VITE_API_URL;
  console.log(apiUrl);
  console.log(products1, products2);
  const [user, setUser] = useState({ id: 1, name: "Kevin", age: 20});

  return (
    <>
      <UserContext.Provider value={user}>
      <button onClick={() => setUser(prev => ({ ...prev, name: "David" }))}>Cambiar nombre</button>
      <ComponentA />
      </UserContext.Provider>
    </>
  )
}

const ComponentA = () => {
  console.log('Component A ha sido renderizado');
  return (
    <>
      <h1>Component A</h1>
      <ComponentB />
    </>
  )
}

const ComponentB = () => {
  console.log('Component B ha sido renderizado');
  return (
    <>
      <h1>Component B</h1>
      <ComponentC />
    </>
  )
}

const ComponentC = () => {
  console.log('Component C ha sido renderizado');
  return (
    <>
      <h1>Component C</h1>
      <ComponentD />
    </>
  )
}

const ComponentD = () => {
  const user = useContext(UserContext);
  console.log('Component D ha sido renderizado');
  return (
    <>
      <h1>Component D</h1>
      <div>
        <p>{user.id}</p>
        <h2>Nombre: {user.name}</h2>
        <p>Edad: {user.age}</p>
      </div>
    </>
  )
}


export default App
