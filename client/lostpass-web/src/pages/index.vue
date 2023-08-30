<template>
    <div>
        后台首页
    </div>
    <el-button @click="set">设置</el-button>
    <el-button @click="read">读取</el-button>
    <el-button @click="del">删除</el-button>
</template>

<script setup>
import { useCookies } from '@vueuse/integrations/useCookies';
const cookies = useCookies();

console.log(cookies);

function set() {
    cookies.set("token", "123456789")
}

function read() {
    console.log(cookies.get("token"));
}

function del() {
    cookies.remove("token")
}

</script>