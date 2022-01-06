import Vue from "vue"
import Router from "vue-router"
import Index from "./views/index.vue"
import List from "./views/list.vue"
import Detail from "./views/detail.vue"
import Infodetail from "./views/infodetail"

import Setting from "./views/Setting";


Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "*",
        redirect: "/index",
    }, {
        path: "/index",
        component: Index
    }, {
        path: "/list",
        component: List
    },
    {
        path: "/detail",
        component: Detail
    },
    {
        path: "/infodetail",
        component: Infodetail
    },
    {
        path: "/setting",
        component: Setting
    }
    ]
})
