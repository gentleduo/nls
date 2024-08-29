<template>
  <h1>HELP</h1>
  <pre>
    {{ resp }}
  </pre>
  <!--  <a-input v-model:value="resp" @change="onChange"/>-->
</template>

<script setup>
import axios from "axios";
import {ref} from "vue";
import {message} from "ant-design-vue";

const resp = ref();

axios.get("/nls/query", {
  params: {
    deptno: 30
  }
}).then(response => {
  let data = response.data;
  if (data.success) {
    resp.value = data.content;
  } else {
    message.error(data.message)
  }
})
</script>