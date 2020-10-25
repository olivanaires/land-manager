import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import NotFound from '../components/NotFound.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/home',
        component: Home
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/userRegister',
        name: 'userRegister',
        component: () => import('../components/register/User.vue')
    },
    {
        path: '/userList',
        name: 'userList',
        component: () => import('../components/list/User.vue')
    },
    {
        path: '/contractList',
        name: 'contractList',
        component: () => import('../components/list/Contract.vue')
    },
    {
        path: '/userUpdate/:id',
        name: 'userUpdate',
        component: () => import('../components/register/User.vue')
    },
    {
        path: '/profile',
        name: 'profile',
        component: () => import('../views/Profile.vue')
    },
    {
        path: '*',
        component: NotFound
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router
