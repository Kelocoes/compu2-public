import axios from 'axios';

export default function getRickAndMortyCharacters() {
  return axios.get('https://rickandmortyapi.com/api/character');
}