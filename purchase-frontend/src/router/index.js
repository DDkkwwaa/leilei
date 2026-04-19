import Vue from 'vue'
import  Router from 'vue-router';
import Home from  '@/common/Home'

Vue.use(Router)

export default new Router({
       mode: 'history',
 routes: [
    {
        path: '/login',
        component: () => import('@/common/Login.vue'),
        name: 'Login',
        hidden: true
    },
    {
        path: '/404',
        component:() => import('@/common/404.vue'),
        name: 'Notfound',
        hidden: true
    },
//  home页面对应跳转Index
    {
        path: '/',
        name: 'Home',
        component: () => import('@/common/Home.vue'),
        redirect:'index',
      //  iconCls: 'el-icon-menu',
        children: [
            {
            path: '/index',
            name: 'Index',
            component:() =>import('@/views/Index.vue'),
             }
        ]
    },

    {
        path: '/',
        component: Home,
        name: '基础信息管理',
        iconCls: 'el-icon-menu',//图标样式class
        leaf: false,    //是否只有一个节点
        children: [

             {path: '/customer', name: '客户资料',
              component: () => import('@/views/jcmd/customer.vue'),
            },
            { path: '/shop', name: '商品资料',
               component: () => import('@/views/jcmd/shop.vue'),
             },
             { path: '/dept', name: '仓库资料',
                component: () => import('@/views/jcmd/dept.vue'),
              },
              { path: '/supplier', name: '供应商资料',
              component: () => import('@/views/jcmd/supplier.vue'),
              }
        ]
    },

    {
        path: '/',
        component: Home,
        name: '进销管理',
        iconCls: 'el-icon-menu',
        children: [
             { path: '/purchase', name: '采购',
               component: () => import('@/views/jxmd/purchase.vue')
             },
            { path: '/purchaseExit', name: '采购退货',
              component: () => import('@/views/jxmd/purchaseExit.vue')
            },
            { path: '/sale', name: '销售',
              component: () => import('@/views/jxmd/sale.vue')
           },
           { path: '/saleExit', name: '销售退货',
             component: () => import('@/views/jxmd/saleExit.vue')
           },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '仓库管理',
        iconCls: 'el-icon-menu',
        children: [
            {
                path:'/depository', name:'仓库列表',
                component:() =>import('@/views/ckmd/depositoryList')
            },
            { path: '/stockList',  name: '库存列表',
            component: () => import('@/views/ckmd/stockList')
            },
            { path: '/depositoryIn',  name: '入库清单',
            component: () => import('@/views/ckmd/depositoryIn')
            },
            { path: '/depositoryOut',  name: '出库清单',
            component: () => import('@/views/ckmd/depositoryOut')
            },

        ]
    },
    {
        path: '/',
        component: Home,
        name: '系统管理',
        iconCls: 'el-icon-menu',
        children: [
             { path: '/adminList', name: '员工管理',
               component: () => import('@/views/xtmd/adminList.vue')
              },
           {  path: '/roleList', name: '角色管理',
              component: () => import('@/views/xtmd/roleList.vue')
           },
           {  path: '/deptList', name: '部门管理',
              component: () => import('@/views/xtmd/deptList.vue')
           },
           {  path: '/syscfg', name: '系统配置管理',
              component: () => import('@/views/xtmd/syscfg.vue')
            }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
]
})
