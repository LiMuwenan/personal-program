import { createApp } from 'vue'
import 'element-plus/dist/index.css'
import 'virtual:windi.css'
import App from './App.vue'
import ElementPlus from 'element-plus'
import Router from './router'
import store from './store/store'

const app = createApp(App)
app.use(ElementPlus)
app.use(Router)
app.use(store)
app.mount('#app')
