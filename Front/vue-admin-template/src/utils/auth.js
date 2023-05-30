import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

export function getToken() {
  return Cookies.get(TokenKey)
//   return localStorage.getItem(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
//   return localStorage.setItem(TokenKey)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
//   return localStorage.removeItem(TokenKey)
}
