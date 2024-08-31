<template>
  <p>
    <a-form layout="inline" :model="param">
      <a-form-item>
        <a-input v-model:value="param.name" placeholder="名称">
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
          查询
        </a-button>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="add()">
          新增
        </a-button>
      </a-form-item>
    </a-form>
  </p>
  <a-table :dataSource="labels"
           :row-key="record => record.id"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="tableLoading">
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
            <a-button type="primary" danger>
              删除
            </a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </template>
  </a-table>

  <a-modal
      title="标签管理"
      v-model:open="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleOk"
  >
    <a-form :model="label" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="标签名称">
        <a-input v-model:value="label.name"/>
      </a-form-item>
      <a-form-item label="父标签ID">
        <a-input v-model:value="label.parent"/>
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="label.sort"/>
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="label.comment"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import axios from "axios";
import {ref} from "vue";
import {message} from "ant-design-vue";

//----------------- 列表查询 -----------------
const param = ref();
param.value = {};
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 2,
});
const labels = ref();
labels.value = [];
const tableLoading = ref(false);
const modalVisible = ref(false);
const modalLoading = ref(false);
const label = ref({});

const columns = [{
  title: '标签名称',
  dataIndex: 'name',
}, {
  title: '父标签ID',
  dataIndex: 'parent',
}, {
  title: '顺序',
  dataIndex: 'sort',
}, {
  title: '描述',
  dataIndex: 'comment',
}, {
  title: '操作',
  dataIndex: 'operation'
}];

const handleQuery = (params) => {
  if (!params) {
    params = {
      page: 1,
      size: pagination.value.pageSize
    };
  }
  tableLoading.value = true;
  // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
  labels.value = [];
  axios.get("/nls/web/label-info/query", {
    params: {
      page: params.page,
      size: params.size,
      name: param.value.name
    }
  }).then((response) => {
    tableLoading.value = false;
    let data = response.data;
    if (data.success) {
      labels.value = data.content.list;
      // 设置分页控件的值
      pagination.value.current = params.page;
      pagination.value.total = data.content.total;
    } else {
      message.error(data.message);
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

const handleOk = () => {
  modalLoading.value = true;
  axios.post("/nls/web/label-info/save", label.value).then((response) => {
    modalLoading.value = false;
    const data = response.data; // data = commonResp
    if (data.success) {
      modalVisible.value = false;
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

const edit = (record) => {
  modalVisible.value = true;
  label.value = Tool.copy(record);
  ;
};

const add = () => {
  modalVisible.value = true;
  label.value = {};
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