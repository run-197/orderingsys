import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '主页', icon: 'dashboard' }
    }]
  },

  {
    path: '/order',
    component: Layout,
    name: '订单',
    redirect: '/order/index',
    meta: {
      title: '订单',
      icon: 'form'
    },
    children: [
      {
        path: 'index',
        name: '订单列表',
        hidden: true,
        component: () => import('@/views/order/index'),
        meta: { title: '订单列表', icon: 'form' }
      },
      {
        path: 'orderinfo',
        name: '订单详情',
        hidden: true,
        component: () => import('@/views/order/orderinfo'),
        meta: { title: '订单详情', icon: 'form' }
      }
    ]
  },
  {
    path: '/dish',
    component: Layout,
    name: '菜品',
    redirect: '/dish/index',
    meta: {
      title: '菜品',
      icon: 'el-icon-tableware'
    },
    children: [
      {
        path: 'index',
        name: '菜品列表',
        // hidden: true,
        component: () => import('@/views/dish/index'),
        meta: { title: '菜品列表', icon: 'nested' }
      },
      {
        path: 'add',
        name: '添加菜品',
        // hidden: true,
        component: () => import('@/views/dish/add'),
        meta: { title: '添加菜品', icon: 'el-icon-edit' }
      },
      {
        path: 'update',
        name: '更新菜品',
        hidden: true,
        component: () => import('@/views/dish/update'),
        meta: { title: '更新菜品', icon: 'nested' }
      }
    ]
  },
  {
    path: '/chart',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'chart',
        component: () => import('@/views/chart/index'),
        meta: { title: '销量信息', icon: 'el-icon-s-order' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
//   mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
