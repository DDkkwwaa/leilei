<template>
  <div>
    <el-card class="box-card">
      <div class="mod-role">
        <el-form :inline="true" :model="dataForm" class="demo-form-inline">
          <el-form-item>
            <el-input v-model="dataForm.select" clearable placeholder="请输入部门名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="info" @click="getDataList">查询</el-button>
            <el-button type="primary" @click="openDialog()">新增</el-button>

          </el-form-item>
        </el-form>
        <el-table :data="dataList" style="width: 100%">
          <el-table-column type="selection"> </el-table-column>
          <el-table-column prop="id" label="ID" width="60">
          </el-table-column>
          <el-table-column prop="deptName" label="部门名称" width="100">
          </el-table-column>

          <el-table-column prop="leader" label="负责人" width="80">
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="120">
          </el-table-column>
          <el-table-column prop="createBy" label="创建者" width="120">
          </el-table-column>
          <el-table-column prop="status" label="状态" width="80">
            <template slot-scope="scope">
              <span>{{ scope.row.status == 0 ? '正常' : '禁用' }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.createTime === null ? null : scope.row.createTime.replace("T", " ") }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="更新时间" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime === null ? null : scope.row.createTime.replace("T", " ") }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope" width="120">
              <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination  @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
          :page-sizes="[5, 7, 10]" :page-size="pageSize" :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper" style="margin-top: 30px">
        </el-pagination>
      </div>

      <!-- Form -->
      <el-dialog :title="dataDialogForm.userId === 0 ? '新增部门' : '更新部门'" width="35%" :visible.sync="dialogFormVisible"
        @close="closeDialog()">
        <el-form :model="dataDialogForm" status-icon :rules="rules" ref="deptForm">
          <el-form-item label="部门名称" label-width="120px" prop="deptName">
            <el-input v-model="dataDialogForm.deptName" placeholder="部门名称" style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="负责人" label-width="120px" prop="leader">
            <el-input v-model="dataDialogForm.leader" placeholder="负责人" style="width: 300px"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="120px" prop="phone">
            <el-input v-model="dataDialogForm.phone" placeholder="电话" style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="状态" label-width="120px" prop="status">
            <template>
              <el-select v-model="dataDialogForm.status" placeholder="请选择">
                <el-option v-for="item in statusTwo" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </template>
          </el-form-item>


          <el-form-item label="描述信息" label-width="120px" prop="remark">
            <el-input type="textarea" v-model="dataDialogForm.remark" style="width: 300px"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleSubmitFormData('deptForm')">确 定</el-button>
        </div>
      </el-dialog>

    </el-card>
  </div>
</template>
<script>

export default {
  name: "deptList",
  data() {
    //用户名 实际是登陆名
    var checkDeptName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入部门名称"));
      }
      // 说明是更新操作
      setTimeout(() => {
        if (this.checkUserId === 0) {  //0表示增加操作
          // 调用后端接口 检查 角色名称是否存在
          this.$http.get("/dept/checkDeptName?deptName=" + value)
          .then((res) => {
            // console.log("验证部门是否存在", res);
            if (res.data.data == 'YES') {
              // 说明角色名存在，不可以使用
              callback(new Error("部门已存在!"));
            } else {  callback();  }
          });

        }
      });

    };
    return {

      checkDeptId: '',     //辨别 新增和编辑
      //状态
      statusTwo: [
        { id: 0, name: '正常' },
        { id: 1, name: '禁用' }
      ],

      dataForm: {
        select: "",
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,

      //编辑弹窗框
      dialogFormVisible: false,
      dialogFormSubmitVisible: false,
      dataDialogForm: {   //参数
        id: 0,
        deptName: '',
        deptId: '',
        leader: '',
        phone: '',
        status: '',
        remark:'',
      },

      // 用户校验---------前端提示
      rules: {
        deptName: [
        { required: true, message: '请输入部门名', trigger: 'blur' },
        { validate: checkDeptName, trigger: "blur" }],
        //--------其他需要用的
      }
    };
  },
  methods: {
    //触发变动存储数据
    sizeChangeHandle(val) {
      this.pageSize = val;  //每页几条
      this.pageIndex = 1;
      this.getDataList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;   //当前页
      this.getDataList()
    },

    // 打开窗口
    openDialog() {
      this.dialogFormVisible = true;
      this.dataDialogForm.id = 0;
      this.dataDialogForm.deptName = '',
      this.dataDialogForm.deptId = '',
      this.dataDialogForm.leader = '',
      this.dataDialogForm.phone = '',
      this.dataDialogForm.status = '',
      this.dataDialogForm.remark = ''
    },
    //关闭窗口
    closeDialog() {
      // 清空添加数据的表单
      this.dataDialogForm = {
        id: 0,
        deptName: '',
        deptId: '',
        leader: '',
        phone: '',
        status: '',
        remark: '',
      };
    },


    //编辑----------------------
    handleEdit(index, item) {
      this.dialogFormVisible = true;   // 打开更新的窗口
      // 绑定需要更新的数据  数据是table里的prop
      this.dataDialogForm.id = item.id;
      this.checkUserId = item.id;     //辨别 新增和编辑
      this.dataDialogForm.deptName = item.deptName;
      this.dataDialogForm.deptId = item.deptId;  //这个作为下拉框选中后获取其id
      this.dataDialogForm.leader = item.leader;
      this.dataDialogForm.phone = item.phone;
      this.dataDialogForm.status = item.status;
      this.dataDialogForm.remark = item.remark;
      // console.log(this.dataDialogForm);
    },
    //编辑-->确定提交  ruleFrom -->  formName
    handleSubmitFormData(formName) {
      this.updatedept(formName);
    },

    //更新用户
    updatedept(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.dialogFormSubmitVisible) {
            return;
          }
          this.dialogFormSubmitVisible = true;

          //add调用---> saveOrUpdateRole 通过id(前端传的)判断是增加还是修改
          this.$http.post("/dept/add", this.dataDialogForm)
            .then((res) => {
              // console.log("添加/", res);
              this.dialogFormVisible = false; // 关闭窗口
              // 清空添加数据的表单
              this.dataDialogForm = {
                userId: 0,
                userName: '',
                sex: '',
                sex: '',
                email: '',
                mobile: '',
                status: '',
                remark: '',
              };
              this.dialogFormSubmitVisible = false;
              // 刷新数据
              this.getDataList();
            });
        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },


    // 删除用户信息------------------------
    handleDelete(index, item) {

      this.$confirm("此操作将永久该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        if (this.dialogFormSubmitVisible) {
          return;
        }
        this.dialogFormSubmitVisible = true;
        this.$http.post("/dept/delete?id=" + item.id)
          .then((res) => {
            // console.log(res)
            this.$message({
              type: "success",
              message: "删除成功!",
            });

            this.dialogFormSubmitVisible = false;
            // 刷新数据
            this.getDataList();
          });
      }).catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },


    //---------------------------
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      // 声明params 查询的参数
      const params = {
        params: {
          pageSize: this.pageSize,
          pageNum: this.pageIndex,
          keyword: this.dataForm.select,
        },
      };
      this.$http.get("/dept/list", params).then((res) => {
        // console.log("部门查询参数", params);
        // console.log(res);
        this.dataList = res.data.data.list;
        this.totalPage = res.data.data.total;
        this.dataListLoading = false;
      });
    },
  }, mounted() {
    this.getDataList();
  }
};
</script>

<style>
</style>
