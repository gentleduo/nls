<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys="selectedKeys"
        v-model:openKeys="openKeys"
        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
    >
      <a-sub-menu key="/home/welcome">
        <template #title>
              <span>
                <CoffeeOutlined/>
                首页
              </span>
        </template>
        <a-menu-item key="/home/welcome">
          <router-link to="/home/welcome">
            <span>首页</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="/home/help">
          <router-link to="/home/help">
            <span>帮助文档</span>
          </router-link>
        </a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="/home/filetrans">
        <template #title>
              <span>
                <VideoCameraOutlined/>
                业务管理
              </span>
        </template>
        <a-menu-item key="/home/filetrans">
          <router-link to="/home/filetrans">
            <span>语音识别</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="/home/detail">
          <router-link to="/home/detail">
            <span>分类管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="/home/label">
          <router-link to="/home/label">
            <span>标签管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="/home/product">
          <router-link to="/home/product">
            <span>商品管理</span>
          </router-link>
        </a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="/user">
        <template #title>
              <span>
                <!--<laptop-outlined/>-->
                <user-outlined/>
                用戶管理
              </span>
        </template>
        <a-menu-item key="5">option5</a-menu-item>
        <a-menu-item key="6">option6</a-menu-item>
        <a-menu-item key="7">option7</a-menu-item>
        <a-menu-item key="8">option8</a-menu-item>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>
</template>

<script setup>
import {ref, watch} from 'vue';
import {useRouter} from "vue-router";

const router = useRouter();
const selectedKeys = ref(['/home/welcome']);
const openKeys = ref(['/home/welcome']);

watch(() => router.currentRoute.value.path, (newValue, oldValue) => {
  console.log('watch', newValue, oldValue);
  selectedKeys.value = [];
  selectedKeys.value.push(newValue);
}, {immediate: true});
</script>