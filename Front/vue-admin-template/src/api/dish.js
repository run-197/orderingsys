import request from '@/utils/request'

export function getdishList(params) {
  return request({
    url: '/dish/getalldish',
    method: 'get',
    params
  })
}

export function updateDishstatus(dish_ID, dish_quantity) {
  return request({
    url: '/dish/updateDishstatus',
    method: 'get',
    params: { dish_ID, dish_quantity }
  })
}

export function getalltype(params) {
  return request({
    url: '/dish/getalltype',
    method: 'get',
    params
  })
}

export function addnewdish(dish_name, dish_nuitprice, dish_quantity, dish_description, picture_address, type) {
  return request({
    url: '/dish/addnewdish',
    method: 'get',
    params: { dish_name, dish_nuitprice, dish_quantity, dish_description, picture_address, type }
  })
}

export function adddishpic(dish_ID, pic_address) {
  return request({
    url: '/dish/adddishpic',
    method: 'get',
    params: { dish_ID, pic_address }
  })
}

export function getdish(dish_ID) {
  return request({
    url: '/dish/getdishbyID',
    method: 'get',
    params: { dish_ID }
  })
}

export function updatedish(dish_ID, dish_name, dish_nuitprice, dish_description, type) {
  return request({
    url: '/dish/updateDishByID',
    method: 'get',
    params: { dish_ID, dish_name, dish_nuitprice, dish_description, type }
  })
}

export function delDishbyID(dish_ID) {
  return request({
    url: '/dish/delDishbyID',
    method: 'get',
    params: { dish_ID }
  })
}

export function getmonth(params) {
  return request({
    url: '/statistic/getmonth',
    method: 'get',
    params
  })
}

export function getmonthsales(sales_time) {
  return request({
    url: '/statistic/getmonthsales',
    method: 'get',
    params: { sales_time }
  })
}

export function getDishmonthsales(sales_time, the_type) {
  return request({
    url: '/statistic/getDishmonthsales',
    method: 'get',
    params: { sales_time, the_type }
  })
}
