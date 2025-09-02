import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [
    vue(),
  ],
  build: {
    outDir: './dist',
    emptyOutDir: false, // conserva archivos previos si lo deseas
    rollupOptions: {
      output: {
        // Genera un único bundle JS con nombre fijo
        entryFileNames: 'js/app.js',
        // Acomoda los CSS en dist/css/app.css
        assetFileNames: ({ name }) => {
          if (name && name.endsWith('.css')) {
            return 'css/app.css'
          }
          return name
        }
      }
    }
  },
})