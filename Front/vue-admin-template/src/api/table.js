import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'get',
    params
  })
}

export function getorderList(params) {
  return request({
    url: '/order/getallorder',
    method: 'get',
    params
  })
}
