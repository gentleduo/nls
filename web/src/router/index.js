import {createRouter, createWebHistory} from 'vue-router'
import Home from '../view/home.vue'
import Login from '../view/login.vue'
import Register from "../view/register.vue";
import Reset from "../view/reset.vue"
import Welcome from "../view/home/welcom.vue"
import Help from "../view/home/help.vue"

const routes = [{
    path: '/',
    redirect: '/login'
}, {
    path: '/login',
    component: Login
}, {
    path: "/register",
    component: Register
}, {
    path: "/reset",
    component: Reset
}, {
    path: '/home',
    component: Home,
    children: [{
        path: "welcome",
        component: Welcome,
    }, {
        path: "help",
        component: Help,
    }]
}]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
