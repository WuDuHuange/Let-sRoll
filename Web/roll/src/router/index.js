import Vue from 'vue'
import Router from 'vue-router'

import LoginPage from '@/components/LoginPage'
import ManagePage from '@/components/ManagePage'
import HomePage from '@/components/HomePage'

Vue.use(Router)

const router = new Router({
    routes:[
        {path: '/', redirect: '/LoginPage'},
        {path: '/LoginPage', component: LoginPage},
        {path: '/ManagePage', component: ManagePage},
        {path: '/HomePage', component: HomePage}
    ]
})

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem("token");
    if (to.path === '/LoginPage') {
        if (token) {
            next()
        } else {
            next()
        }
    } else {
        if (token) {
            next()
        } else {
            console.log("没有token")
            alert("请先登录")
            next('/LoginPage')
        }
    }
})

export default router