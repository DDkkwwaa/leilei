<template>
  <div>
    <el-card class="box-card">
      <div class="mod-admin">
        <el-form :inline="true" :model="dataForm" class="demo-form-inline">
          <el-form-item>
            <el-input v-model="dataForm.select" clearable placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="info" icon="el-icon-search" @click="getDataList">查询</el-button>
            <el-button type="primary" @click="openAddDialog()">新增</el-button>
          </el-form-item>
        </el-form>

        <el-table ref="multipleTable" :data="dataList" style="width: 100%">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="id" label="ID" width="60">
          </el-table-column>
          <el-table-column prop="dept" label="部门" width="60">
          </el-table-column>
          <el-table-column prop="userName" label="用户名" width="80">
          </el-table-column>
          <el-table-column prop="trueName" label="真实姓名" width="80">
          </el-table-column>
          <el-table-column prop="sex" label="性别" width="60">
            <template slot-scope="scope">
              <span>{{ scope.row.sex == 0 ? '男' : '女' }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="eMail" label="邮箱" width="160" show-overflow-tooltip>
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="120">
          </el-table-column>
          <el-table-column prop="status" label="状态" width="60">
            <template slot-scope="scope">
              <span>{{ scope.row.status == 0 ? '正常' : '禁用' }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="createTime" label="创建时间" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.createTime == null ? null : scope.row.createTime.replace("T", " ") }}</span>
            </template>
          </el-table-column>

          <el-table-column label="操作">
            <template slot-scope="scope" width="120">
              <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              <el-button size="mini" type="primary" @click="passwordInit(scope.$index, scope.row)">密码初始化</el-button>
              <el-button size="mini" type="primary" @click="handleAdminMenu(scope.$index, scope.row)">用户分配</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination  @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
          :page-sizes="[5, 7, 10]" :page-size="pageSize" :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper" style="margin-top: 30px">
        </el-pagination>
      </div>


      <!-- Form -->
      <el-dialog :title="dataDialogForm.id === 0 ? '新增用户' : '更新用户'" width="35%" :visible.sync="dialogFormVisible"
        @close="closeDialog()">
        <el-form :model="dataDialogForm" status-icon :rules="rules" ref="adminForm">

          <el-form-item label="用户名" label-width="120px" prop="userName">
            <el-input v-model="dataDialogForm.userName" placeholder="请设置系统登陆用户名" autocomplete="off"
              style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="真实姓名" label-width="120px" prop="trueName">
            <el-input v-model="dataDialogForm.trueName" placeholder="请输入真实姓名" style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="部门" label-width="120px" prop="dept">
            <template> 
              <el-select style="width: 300px;" v-model="dataDialogForm.dept" placeholder="请选择">
                <el-option v-for="item in deptAll" :key="item.id" :label="'[' + item.id + '] ' + item.deptName"
                  :value="item.id">
                </el-option>
              </el-select>
            </template>
          </el-form-item>

          <el-form-item label="性别" label-width="120px" prop="sex">
            <template>
              <el-select style="width: 300px;" v-model="dataDialogForm.sex" placeholder="请选择">
                <el-option v-for="item in sexTwo" :key="item.id" :label="'[' + item.id + '] ' + item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </template>
          </el-form-item>

          <el-form-item label="邮箱" label-width="120px" prop="eMail"  >
            <el-input v-model="dataDialogForm.eMail" placeholder="邮箱格式 xxx@qq.com" style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="电话" label-width="120px" prop="phone">
            <el-input v-model="dataDialogForm.phone" placeholder="电话" style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="状态" label-width="120px" prop="status">
            <template> <!-- label是显示的东西  value是对应选中的值 -->
              <el-select style="width: 300px;" v-model="dataDialogForm.status" placeholder="默认禁用。创建用户后,请更新状态为正常！">
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
          <el-button type="primary" @click="handleSubmitFormData('adminForm')">确 定</el-button>
        </div>
      </el-dialog>


      <!-- 角色分配 -->
      <el-dialog title="用户分配角色" width="35%" :visible.sync="dialogAdminToRoleFormVisible">
        <el-tree style="padding-left:45px;" :data="adminRoles.treeData" show-checkbox node-key="id" ref="treeRole"
          @check="handleChecked" :default-checked-keys="adminRoles.roleIds" :props="defaultProps">
        </el-tree>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogAdminToRoleFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleSubmitAdminFormData()">确 定</el-button>
        </div>
      </el-dialog>


    </el-card>
  </div>
</template>
<script>

export default {
  name: "adminList",

  data() {
    //用户名 实际是登陆名
    var checkUserName = (rule, value, callback) => {

      var pattern = /[A-Za-z_\-\u4e00-\u9fa5]+/;
      // console.log("正则匹配",pattern.test(value));
      if (!value) {
        callback(new Error("请输入用户名"));
      }
      setTimeout(() => {
 
        if (this.checkUserId === 0) {
          // console.log("获取value:", value);
          // 调用后端接口 检查 角色名称是否存在
          this.$http.get("/admin/checkUserName?userName=" + value).then((res) => {
            // console.log("验证用户是否存在", res);
            if (res.data.data == 'YES' && Number.isInteger(value)) {
              // 说明角色名存在，不可以使用
              callback(new Error("用户名已存在!"));
            } else {
              callback();
            }
          }).catch(console.error());
        } else {
          callback();
        }
      }, 1000)


    }

    //电话校验
    var checkPhone = (rule, value, callback) => {
      // console.log("电话校验----", value.length)
      // console.log("是否为数字：",!Number.isInteger(value))
      if (!value) {
        return callback(new Error('电话不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value) && value.length != 11) {
          callback(new Error('请输入数字且必须为11位!'));
        }
        else { callback(); }
      }, 1000);
    };

    return {
      checkUserId: '',  //点击编辑后存储的id
 
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      //部门
      deptAll: [],
      //性别
      sexTwo: [
        { id: 0, name: '男' }, { id: 1, name: '女' }
      ],
      //状态
      statusTwo: [
        { id: 0, name: '正常' }, { id: 1, name: '禁用' }
      ],


      dataForm: {
        select: "",
      },
      dataList: [], //数据列表   
      pageIndex: 1,   //初始页
      pageSize: 5,        //每页条数
      totalPage: 0,         //总条数
      dataListLoading: false,
      dialogAdminToRoleFormVisible: false, //用户角色
      adminRoles: {},                //角色菜单
      adminId: 0,                    //用户id
      //编辑弹窗框
      dialogFormVisible: false,   //增加或编辑 dilog
      dialogFormSubmitVisible: false,   //删除
      dataDialogForm: {   //参数
        id: 0,
        userName: '',
        trueName: '',
        sex: '',
        eMail: "",
        phone: '',
        dept: '',
        status: '',
        remark: '',
      },
      // 用户校验---------前端提示
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },   //长度
          { pattern: /[A-Za-z_\-\u4e00-\u9fa5]+/, message: '姓名不支持特殊字符', trigger: 'blur' },  //正则
          { validator: checkUserName, trigger: 'blur' },
        ],
        phone: [{ required: true, validator: checkPhone, trigger: 'blur' },],

        // eMail: [{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
        // { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],

        trueName: [{ required: true, message: '请输入真实姓名', trigger: 'blur' }],

        dept: [{ required: true, message: '请选择部门', trigger: 'blur' }]
      },

    }
  },
  methods: {
    //每页显示几条
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList()
    },
    //当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList()
    },

    // 导航栏 中的新增  打开窗口
    openAddDialog() {
      this.dialogFormVisible = true;
      this.dataDialogForm.id = 0;
      this.checkUserId = 0;
      this.dataDialogForm.userName = '';
      this.dataDialogForm.trueName = '';
      this.dataDialogForm.sex = '';
      this.dataDialogForm.eMail = "";
      this.dataDialogForm.phone = '';
      this.dataDialogForm.dept = '';
      this.dataDialogForm.status = '';
      this.dataDialogForm.remark = '';
    },


    //关闭窗口
    closeDialog() {
      // 清空添加数据的表单
      this.dataDialogForm = {
        id: 0,
        userName: '',
        trueName: '',
        sex: '',
        email: "",
        phone: '',
        status: '',
        dept: '',
        remark: '',
      };
    },


    //编辑----------------------
    handleEdit(index, item) {
      this.dialogFormVisible = true;   // 打开更新的窗口
      this.checkUserId = item.id;     //辨别 新增和编辑
      // 绑定需要更新的数据  数据是table里的prop
      this.dataDialogForm.id = item.id;
      this.dataDialogForm.userName = item.userName;
      this.dataDialogForm.trueName = item.trueName;
      this.dataDialogForm.sex = item.sex;
      this.dataDialogForm.eMail = item.eMail;
      this.dataDialogForm.phone = item.phone;
      this.dataDialogForm.status = item.status;
      this.dataDialogForm.dept = item.dept;
      this.dataDialogForm.remark = item.remark;
      // console.log(this.dataDialogForm);
    },
    //编辑-->确定提交  ruleFrom -->  formName
    handleSubmitFormData(formName) {
      this.updateUser(formName);
    },

    //-------------------------更新/增加用户
    updateUser(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.dialogFormSubmitVisible) {
            return;
          }
          this.dialogFormSubmitVisible = true;

          //add调用---> saveOrUpdateRole 通过id(前端传的)判断是增加还是修改
          this.$http.post("/admin/add", this.dataDialogForm)
            .then((res) => {
              // console.log("添加/更新", res);
              // 清空添加数据的表单
              this.dialogFormVisible = false; // 关闭窗口
              if (this.dataDialogForm.id == 0) {
                this.$message({
                  type: "success",
                  message: "增加成功!\n初始登陆密码为123456,请尽快修改密码!",
                });
              }
              this.dataDialogForm = {
                id: 0,
                userName: '',
                trueName: '',
                sex: '',
                eMail: "",
                phone: '',
                status: '',
                dept: '',
                remark: '',
              };
              //this.dialogFormVisible =false;
              this.dialogFormSubmitVisible = false;
              this.getDataList(); // 刷新数据
            });
        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },


    // 删除用户信息------------------------
    handleDelete(index, item) {

      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          if (this.dialogFormSubmitVisible) {
            return;
          }
          this.dialogFormSubmitVisible = true;
          this.$http
            .post("/admin/delete/?id=" + item.id)
            .then((res) => {
              // console.log(res)
              if (res.data.data === '0') {
                // 表示数据不能被删除
                this.$message({
                  type: "warning",
                  message: "该条记录不允许删除!",
                });
              } else {
                this.$message({
                  type: "success",
                  message: "删除成功!",
                });
              }
              this.dialogFormSubmitVisible = false;
              this.pageIndex =1;
              this.getDataList(); // 刷新数据
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    //树形选中的数据
    handleChecked() {
      // console.log("---树形选中的数据------", currentNode, selectedNodes);
      // console.log("getCheckedKeys",this.$refs.treeRole.getCheckedKeys());
      this.adminRoles.roleIds = this.$refs.treeRole.getCheckedKeys();
      // this.adminRoles.roleIds = selectedNodes.checkedKeys;
    },

    //获取用户---角色信息----用户角色-树图-----------------------------------------
    handleAdminMenu(index, item) {
      if (this.dialogAdminToRoleFormVisible) {
        return;
      }
      this.$http.get("/admin/getRoleByAdminId?adminId=" + item.id)
        .then((res) => {
          // console.log(res.data);
          this.adminRoles.treeData = res.data.data.treeData;
          this.adminRoles.roleIds = res.data.data.checks;
        })
      this.adminId = item.id;
      this.dialogAdminToRoleFormVisible = true;
    },


    //角色权限分配-确定按钮 roleMenuForm
    handleSubmitAdminFormData() {
      const params = { //传递给后端的数据
        params: {
          adminId: this.adminId,
          roleIds: this.adminRoles.roleIds
        }
      };
      //params.params 传参问题
      // console.log(params);
      this.$http.post('/admin/adminToRole', params.params)
        .then((res) => {
          // console.log("用户分配角色", res);
          if (res.data.data = '成功') {
            this.$message({
              type: "success",
              message: "用户分配角色成功!",
            });
          }
        })
      this.dialogAdminToRoleFormVisible = false;

    },

    getDeptAll() {    //获取所有部门
      this.$http.get("/jcDept/listAll").then((res) => {
          console.log("部门", res);
        this.deptAll = res.data.data;
      })
    },

    //密码初始化
    passwordInit(index, item) {
      this.$confirm("此操作为密码初始化，是否继续！", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(() => {
        this.$http.post("/admin/pwdInit?id=" + item.id).then((res) => {
          // console.log("初始化密码：", res);
          if (res.data.code == 200) {
            this.$message({
              type: "success",
              message: "密码初始化为 123456 成功!",
            });
          } else {
            this.$message({
              type: "erro",
              message: "密码初始化失败!",
            });
          }
        });
      })
    },


    //-------------------------------------
    //获取初始化数据
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      // this.dataListLoading=true;
      //请求参数封装
      const params = {
        params: {
          pageSize: this.pageSize,
          pageNum: this.pageIndex,
          keyword: this.dataForm.select,
        }
      };
      //后端请求 分页获取对象
      this.$http.get("/admin/List", params).then((res) => {
        // console.log("提交的参数", params);
        // console.log(res);
        //需要通过响应的结果配置
        this.dataList = res.data.data.list;
        this.totalPage = res.data.data.total;
        this.dataListLoading = false;
      });
    },

  },

  mounted() {
    this.getDeptAll(),
      this.getDataList()
  }
}
</script>
<style></style>
  
  
  