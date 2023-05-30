import request from '@/utils/request'

export function getorderList(params) {
  return request({
    url: '/order/getallorder',
    method: 'get',
    params
  })
}

export function getOneorder(order_ID) {
  return request({
    url: '/order/getorderbyorderID',
    method: 'post',
    params: { order_ID }
  })
}

export function dishServing(order_ID) {
  return request({
    url: '/order/serving',
    method: 'post',
    params: { order_ID }
  })
}

export function getDishesofOrder(order_ID) {
  return request({
    url: '/order/getdishoforder',
    method: 'post',
    params: { order_ID }
  })
}
