<template>
  <a-table :dataSource="labels"
           :row-key="record => record.id"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="loading">
    <template v-slot:bodyCell="{ column, record }">
      <template v-if="column.dataIndex == 'operation'">
        <a-space size="small">
          <!--          <router-link :to="'/nls/web/label-info/doc?id=' + record.id">
                      <a-button type="primary">
                        管理
                      </a-button>
                    </router-link>-->
          <a-button type="primary" @click="edit(record)">
            编辑
          </a-button>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.id)"
          >
            <a-button type="danger">
              删除
            </a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </template>
  </a-table>

  <a-modal
      title="Title"
      v-model:open="visible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
  >
    <a-form :model="label" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="标签名称">
        <a-input v-model:value="label.name" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="label.comment" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script setup>
import axios from "axios";
import {ref} from "vue";
import {notification} from "ant-design-vue";

//----------------- 列表查询 -----------------
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 2,
});
const loading = ref(false);
const labels = ref();
labels.value = [];
const visible = ref(false);
const confirmLoading = ref(false);
const label = ref({});
const columns = [{
  title: '标签名称',
  dataIndex: 'name',
}, {
  title: '描述',
  dataIndex: 'comment',
}, {
  title: '操作',
  dataIndex: 'operation'
}];

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    };
  }
  loading.value = true;
  labels.value = [];
  axios.get("/nls/web/label-info/query", {
    params: {
      page: param.page,
      size: param.size
    }
  }).then((response) => {
    loading.value = false;
    let data = response.data;
    if (data.success) {
      labels.value = data.content.list;
      // 设置分页控件的值
      pagination.value.current = param.page;
      pagination.value.total = data.content.total;
    } else {
      notification.error({description: data.message});
    }
  });
};

const handleTableChange = (pagination) => {
  //console.log("看看自带的分页参数都有啥：" + pagination);
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  });
};

const edit = (record) => {
  visible.value = true;
  label.value = record;
};

/**
 * 编辑
 */
const handleOk = () => {
  confirmLoading.value = true;
  setTimeout(() => {
    visible.value = false;
    confirmLoading.value = false;
  }, 2000);
};

const handleDelete = (id) => {
  axios.delete("/nls/web/label-info/delete/" + id).then((response) => {
    const data = response.data; // data = commonResp
    if (data.success) {
      // 重新加载列表
      handleQuery({
        page: pagination.value.current,
        size: pagination.value.pageSize,
      });
    } else {
      message.error(data.message);
    }
  });
};

/**
 * 时间格式化，将"秒"格式化成"时:分:秒"
 * @param second
 * @returns {string}
 */
const formatSecond = (second) => {
  return Tool.formatSecond(second);
};

handleQuery();

</script>