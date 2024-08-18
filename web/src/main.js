import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
import * as Icons from '@ant-design/icons-vue'
import router from "./router"
import axios from "axios";

const app = createApp(App);
const icons = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}
/*循环注册组件必须在app.mount('#app')前，否则 Icon 组件无法显示*/
app.use(Antd).use(router).mount('#app');
axios.defaults.baseURL = import.meta.env.VITE_SERVER