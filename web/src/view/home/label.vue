<template>
  <p>
    <a-form layout="inline" :model="param">
      <a-form-item>
        <a-button type="primary" @click="handleQuery()">
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
  <a-table :dataSource="level1"
           :row-key="record => record.id"
           :columns="columns"
           :loading="tableLoading"
           :pagination="false">
    <template v-slot:bodyCell="{ column, record }">
      <template v-if="column.dataIndex == 'operation'">
        <a-space size="small">
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
      @ok="handleModalSave"
  >
    <a-form :model="label" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="标签名称">
        <a-input v-model:value="label.name"/>
      </a-form-item>
      <a-form-item label="父标签ID">
        <a-tree-select
            v-model:value="label.parent"
            show-search
            style="width: 100%"
            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
            placeholder="选择父标签"
            allow-clear
            tree-default-expand-all
            :tree-data="treeSelectData"
            tree-node-filter-prop="name"
            :fieldNames="{label:'name', value: 'id'}"
        >
        </a-tree-select>
      </a-form-item>
      <!--      <a-form-item label="父标签ID">
              &lt;!&ndash;<a-input v-model:value="label.parent"/>&ndash;&gt;
              <a-select
                  v-model:value="label.parent"
                  ref="select"
              >
                <a-select-option :value="0">
                  无
                </a-select-option>
                &lt;!&ndash;
                1、由于目前树只有两级，所以父标签只能选level1中的节点，不看children
                2、父标签不能选择自己，否则经过递归算法后分类显示不出来了
                &ndash;&gt;
                <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="label.id === c.id">
                  {{ c.name }}
                </a-select-option>
              </a-select>
            </a-form-item>-->
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
import {ref, createVNode} from "vue";
import {message, Modal} from "ant-design-vue";
import ExclamationCircleOutlined from "@ant-design/icons-vue/ExclamationCircleOutlined";

//----------------- 列表查询 -----------------
const param = ref();
param.value = {};

const labels = ref();
labels.value = [];
const tableLoading = ref(false);
const modalVisible = ref(false);
const modalLoading = ref(false);
const label = ref({});
// 因为树选择组件的属性状态，会随当前编辑的节点而变化，所以单独声明一个响应式变量
const treeSelectData = ref();
treeSelectData.value = [];

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

/**
 * Ant Design vue中的表格支持树形数据的展示，当数据中有children字段时会自动展示为树形表格，如果不需要或配置为其他字段可以用
 * 所以只要将后端返回的List数据，通过自定义的Tool.array2Tree方法，将数据变成如下的格式即可：
 *
 * 一级分类树，children属性就是二级分类
 * [{
 *   id: "",
 *   name: "",
 *   children: [{
 *     id: "",
 *     name: "",
 *   }]
 * }]
 */
const level1 = ref(); // 一级分类树，children属性就是二级分类
level1.value = [];
const deleteIds = [];
const deleteNames = [];

const handleQuery = () => {
  tableLoading.value = true;
  // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
  level1.value = [];
  axios.get("/nls/web/label-info/all").then((response) => {
    tableLoading.value = false;
    let data = response.data;
    if (data.success) {
      labels.value = data.content;
      level1.value = [];
      level1.value = Tool.array2Tree(labels.value, 0);
      // 父文档下拉框初始化，相当于点击新增
      treeSelectData.value = Tool.copy(level1.value) || [];
      // 选择树的头部添加"无"，表示无父节点
      treeSelectData.value.unshift({id: 0, name: '无'});
    } else {
      message.error(data.message);
    }
  });
};

const handleModalSave = () => {
  modalLoading.value = true;
  axios.post("/nls/web/label-info/save", label.value).then((response) => {
    modalLoading.value = false;
    const data = response.data; // data = commonResp
    if (data.success) {
      modalVisible.value = false;
      // 重新加载列表
      handleQuery();
    } else {
      message.error(data.message);
    }
  });
};

const edit = (record) => {
  modalVisible.value = true;
  label.value = Tool.copy(record);
  // 不能选择当前节点及其所有子孙节点，作为父节点，会使树断开
  treeSelectData.value = Tool.copy(level1.value);
  setDisable(treeSelectData.value, record.id);
  // 选择树的头部添加"无"，表示无父节点
  treeSelectData.value.unshift({id: 0, name: '无'});
};

const add = () => {
  modalVisible.value = true;
  label.value = {};
  treeSelectData.value = Tool.copy(level1.value) || [];
  // 选择树的头部添加"无"，表示无父节点
  treeSelectData.value.unshift({id: 0, name: '无'});
};

const handleDelete = (id) => {
  // 清空数组，否则多次删除时，数组会一直增加
  deleteIds.length = 0;
  deleteNames.length = 0;
  getDeleteIds(level1.value, id);
  Modal.confirm({
    title: '重要提醒',
    icon: createVNode(ExclamationCircleOutlined),
    content: '将删除：【' + deleteNames.join("，") + "】删除后不可恢复，确认删除？",
    onOk() {
      axios.delete("/nls/web/label-info/batch-delete/" + deleteIds.join(",")).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          // 重新加载列表
          handleQuery();
        } else {
          message.error(data.message);
        }
      });
    },
  });
};

/**
 * 将某节点及其子孙节点全部置为disabled
 */
const setDisable = (treeSelectData, id) => {
  // console.log(treeSelectData, id);
  // 遍历数组，即遍历某一层节点
  for (let i = 0; i < treeSelectData.length; i++) {
    const node = treeSelectData[i];
    if (node.id === id) {
      // 如果当前节点就是目标节点
      console.log("disabled", node);
      // 将目标节点设置为disabled
      node.disabled = true;

      // 遍历所有子节点，将所有子节点全部都加上disabled
      const children = node.children;
      if (Tool.isNotEmpty(children)) {
        for (let j = 0; j < children.length; j++) {
          setDisable(children, children[j].id)
        }
      }
    } else {
      // 如果当前节点不是目标节点，则到其子节点再找找看。
      const children = node.children;
      if (Tool.isNotEmpty(children)) {
        setDisable(children, id);
      }
    }
  }
};

/**
 * 查找整根树枝
 */
const getDeleteIds = (treeSelectData, id) => {
  // console.log(treeSelectData, id);
  // 遍历数组，即遍历某一层节点
  for (let i = 0; i < treeSelectData.length; i++) {
    const node = treeSelectData[i];
    if (node.id === id) {
      // 如果当前节点就是目标节点
      console.log("delete", node);
      // 将目标ID放入结果集ids
      // node.disabled = true;
      deleteIds.push(id);
      deleteNames.push(node.name);

      // 遍历所有子节点
      const children = node.children;
      if (Tool.isNotEmpty(children)) {
        for (let j = 0; j < children.length; j++) {
          getDeleteIds(children, children[j].id)
        }
      }
    } else {
      // 如果当前节点不是目标节点，则到其子节点再找找看。
      const children = node.children;
      if (Tool.isNotEmpty(children)) {
        getDeleteIds(children, id);
      }
    }
  }
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