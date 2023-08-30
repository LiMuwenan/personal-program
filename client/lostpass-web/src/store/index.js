import { createStore } from 'vuex'
const store = createStore({
    state () {
        return {
            // 用户信息
            user: {

            }
        }
    },
    mutations: {
        set_userinfo(state, user) {
            state.user = user
        }
      }
})

export default store