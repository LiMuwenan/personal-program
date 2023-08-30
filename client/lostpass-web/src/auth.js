import { useCookies } from '@vueuse/integrations/useCookies'

const TokenKey = "token"
const cookie = useCookie()

// 获取Token
export function getToken() {
    return cookie.get(TokenKey)
}
// 设置Token
export function setToken(token) {
    return cookie.set(TokenKey, token)
}
// 清除Token
export function remove() {
    return cookie.remove(TokenKey)
}