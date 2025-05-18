import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import polyfillNode from 'rollup-plugin-polyfill-node'

// https://vite.dev/config/
export default defineConfig({
  base: '/compu2/front',
  plugins: [react(), polyfillNode()],
  server: {
    port: 3000
  }
})
