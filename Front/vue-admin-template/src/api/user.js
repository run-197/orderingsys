import request from '@/utils/request'

export function login(data) {
  const { username, password } = data
  return request({
    url: '/customer/managelogin',
    method: 'post',
    params: { username, password },
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/customer/getuserinfo',
    method: 'get',
    params: { token }
  })
}

export function logout(params) {
  return request({
    url: '/customer/manageloginout',
    method: 'get',
    params
  })
}
