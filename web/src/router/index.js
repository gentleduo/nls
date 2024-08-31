import {createRouter, createWebHistory} from 'vue-router'
import Home from '../view/home.vue'
import Login from '../view/login.vue'
import Register from "../view/register.vue";
import Reset from "../view/reset.vue"
import Welcome from "../view/home/welcom.vue"
import Help from "../view/home/help.vue"
import Filetrans from "../view/home/filetrans.vue";
import Detail from "../view/app/detail.vue"
import Label from "../view/home/label.vue"
import Product from "../view/home/product.vue"

const routes = [{
    path: '/', redirect: '/login'
}, {
    path: '/login', component: Login
}, {
    path: "/register", component: Register
}, {
    path: "/reset", component: Reset
}, {
    path: '/home', component: Home, children: [{
        path: "product", component: Product,
    }, {
        path: "label", component: Label,
    }, {
        path: "detail", component: Detail,
    }, {
        path: "filetrans", component: Filetrans,
    }, {
        path: "welcome", component: Welcome,
    }, {
        path: "help", component: Help,
    }]
}, {
    path: "/app/detail", component: Detail
}]

const router = createRouter({
    history: createWebHistory(), routes
})

export default router
