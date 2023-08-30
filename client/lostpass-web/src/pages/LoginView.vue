<template>
    <el-row class="login-container">
        <el-col :lg="16" :md="12" class="left">
            <div>
                <div class="font-bold text-5xl text-light-50 mb-4">
                    你好！我的朋友
                </div>
                <div class="text-light-50 mb-4">
                    轻舟已过万重山
                </div>
            </div>

        </el-col>
        <el-col :lg="8" :md="12" class="right">
            <h2 class="font-bold text-3xl text-gray-800">欢迎回来</h2>
            <div class="flex items-center justify-center my-5 text-gray-300 space-x-2">
                <span class="h-[1px] w-16 bg-gray-100"></span>
                <span>账号密码登录</span>
                <span class="h-[1px] w-16 bg-gray-100"></span>
            </div>
            <el-form ref="loginForm" :rules="rules" :model="form" class="w-[250px]">
                <el-form-item prop="username">
                    <el-input v-model="form.username" placeholder="请输入用户名">
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <User />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.password" type="password" show-password placeholder="请输入密码">
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <Lock />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <el-button round color="#626aef" class="w-[250px]" type="primary" @click="onSubmit">登 录</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { login } from '~/api/manager.js'
import { useStore} from 'vuex'
import { ElNotification } from 'element-plus';

const loginForm = ref(null)
const store = useStore()
const form = reactive({
    username: "",
    password: ""
})

// 表单验证
const rules = {
    username: [
        {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
        }, {
            min: 3,
            max: 5,
            message: '用户名长度在3-5之间',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '密码不能为空',
            trigger: 'blur'
        }
    ]
}

const onSubmit = () => {
    console.log('submit!')
    // 验证通过才能请求，没有这个相当于页面提示错了，但是还是提交了
    loginForm.value.validate((valid) => {
        console.log(valid)
        if (valid) {
            login(form.username, form.password)
                .then((res) => {
                    console.log(res)
                    //todo store
                    store.commit("set_userinfo", res)
                })
                .catch((err) => {
                    ElNotification({
                        title: 'Error',
                        message: err.response.data.msg || '登录请求失败',
                        type: 'error',
                    })
                })
        }
    })

}

</script>

<style>
/* windicss样式抽离方式 @apply */
.login-container {
    @apply min-h-screen bg-indigo-500
}

.login-container .left {
    @apply flex items-center justify-center
}

.login-container .right {
    @apply flex items-center justify-center flex-col bg-light-50
}
</style>