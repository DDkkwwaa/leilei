<template>
  <div>
    <el-card class="box-card">
      <div>
        <el-form :inline="true" :model="dataForm" >
          <el-form-item label="角色名称">
            <el-input v-model="dataForm.select" placeholder="请输入角色名称" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="info" @click='getDataList'>查询</el-button>
            <el-button type="primary" @click="openDialog()">增加</el-button>
          </el-form-item>
        </el-form>

        <el-table ref="multipleTable" :data="dataList"  style="width: 100%">
          <el-table-column type="selection" width="55">  </el-table-column>
          <el-table-column prop="roleId" label="ID" width="60"></el-table-column>

          <el-table-column prop="roleName" label="角色名" width="100" show-overflow-tooltip>
          </el-table-column>
          <el-table-column prop="description" label="描述" width="100" show-overflow-tooltip>
          </el-table-column>
 
          <el-table-column prop="status" label="状态" width="80">
            <template slot-scope="scope">
              <span>{{ scope.row.status == 0 ? '正常' : '停用' }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="buildUser" label="创建人" width="100">
          </el-table-column>
          <el-table-column prop="remark" label="备注" width="120" show-overflow-tooltip>
          </el-table-column>

          <el-table-column prop="createTime" label="创建时间" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.createTime == null ? null : scope.row.createTime.replace("T", " ") }}</span>
            </template>
          </el-table-column>

          <el-table-column prop="updateTime" label="更新时间" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.updateTime == null ? null : scope.row.updateTime.replace("T", " ") }}</span>
            </template>
          </el-table-column>

          <el-table-column label="操作">
            <template slot-scope="scope" width="120">
              <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              <el-button size="mini" type="primary" style="margin-left: 0% ; margin-top: 5px;"
                @click="handleRoleMenu(scope.$index, scope.row)">角色分配</el-button>
            </template>
          </el-table-column>

        </el-table>
        <el-pagination  @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
          :page-sizes="[5, 7, 10]" :page-size="pageSize" :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper" style="margin-top: 30px">
        </el-pagination>
      </div>

      <!-- Form  -->
      <el-dialog :title="dataDialogForm.roleId === 0 ? '新增角色' : '更新角色'" width="35%" :visible.sync="dialogFormVisible"
        @close="closeDialog()">
        <el-form :model="dataDialogForm" :rules="rules" ref="ruleForm">
          <el-form-item label="角色名称" label-width="120px" prop="roleName">
            <el-input v-model="dataDialogForm.roleName" placeholder="角色名称" style="width: 300px"></el-input>
          </el-form-item>

          <el-form-item label="状态" label-width="120px" prop="status">
            <template> 
              <el-select v-model="dataDialogForm.status" placeholder="请选择">
                <el-option v-for="item in statusTwo" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </template>
          </el-form-item>
          <el-form-item label="描述信息" label-width="120px" prop="description">
            <el-input type="textarea" v-model="dataDialogForm.description" style="width: 300px"></el-input>
          </el-form-item>
          <el-form-item label="备注" label-width="120px" prop="remark">
            <el-input type="textarea" v-model="dataDialogForm.remark" style="width: 300px"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleSubmitFormData('ruleForm')">确 定</el-button>
        </div>
      </el-dialog>


      <!-- 角色分配 -->
      <el-dialog title="角色权限分配" width="35%" :visible.sync="dialogRoleFormVisible" >
        <!--  expanded 默认展开 按钮 @checked 默认勾选    ||:default-expanded-keys="[2, 3]"    -->
        <el-tree style="padding-left:45px;" :data="roleMenus.treeData" ref="treeMenu" show-checkbox node-key="id" @check="handleChecked"
          :default-checked-keys="roleMenus.menuIds">
        </el-tree>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogRoleFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleSubmitRoleFormData('roleMenuForm')">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>
 

<script>

export default {
  name: "roleList",

  data() {
    //增加--验证角色是否存在
    var checkRoleName = (rule, value, callback) => {
      if (this.dataDialogForm.roleId !== 0) {
        if (value === "") {
          callback(new Error("请输入角色名称"));
        }
        // 说明是更新操作
        callback();
      } else if (value === "") {
        callback(new Error("请输入角色名称"));
      } else {
        // console.log("获取value:", value);
        // 调用后端接口 检查 角色名称是否存在
        this.$http.get("/role/checkRoleName?roleName=" + value).then((res) => {
          // console.log("验证角色是否存在", res);
          if (res.data.data === "NO") {
            // 说明角色名不存在，可以使用
            callback();
          } else {
            callback(new Error("角色名称重复"));
          }
        }).catch(console.error());
        //callback();
      }

    };

    return {
      statusTwo: [                 //状态
        { id: 0, name: '正常' },
        { id: 1, name: '禁用' }
      ],

      dataForm: { select: "", },
      dataList: [],         //数据列表   
      pageIndex: 1,         //初始页
      pageSize: 5,          //每页条数
      totalPage: 0,         //总条数
      dataListLoading: false,
      //编辑弹窗框
      dialogFormVisible: false,
      dialogFormSubmitVisible: false,
      dialogRoleFormVisible: false,    //角色分配
      roleId: 0,                       //需要分配的角色id
      roleMenus: {},                   //分配的角色列表

      dataDialogForm: {
        roleId: 0,
        roleName: "",
        description: "",
        status: "",
        remark: "",
      },

      rules: {         //角色验证
        roleName: [{ validator: checkRoleName, trigger: "blur" }],
        //  remark: [  { required: true, message: "请输入描述信息", trigger: "blur" } ],
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

    //  编辑/增加完成后-->确定提交  ruleFrom -->  formName
    handleSubmitFormData(formName) {
      this.updateRole(formName);
    },
    updateRole(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.dialogFormSubmitVisible) {
            return;
          }
          this.dialogFormSubmitVisible = true;

          this.$http.post("/role/add", this.dataDialogForm)
            .then((res) => {
              // console.log("添加/更新", res);
              this.dialogFormVisible = false; // 关闭窗口
              // 清空添加数据的表单
              this.dataDialogForm = {
                roleId: 0,
                roleName: "",
                description: "",
                status: "",
                remark: "",
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
    //删除角色-----------------------------------
    handleDelete(index, item) {
      // 删除角色信息
      this.$confirm("此操作将永久该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
          // if (this.dialogFormSubmitVisible) {
          //   return;
          // }
          this.dialogFormSubmitVisible = true;
          this.$http.post("/role/delete?roleId=" + item.roleId)
            .then((res) => {
              // console.log(res)
              if (res.data.data === 'false') {
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
                 this.pageIndex =1;   //未在第一页删除数据导致请求的是当前页      
                this.getDataList();   // 刷新数据
            });

        }).catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    //编辑
    handleEdit(index, item) {
      this.dialogFormVisible = true;   // 打开更新的窗口
      // 绑定需要更新的数据  数据是table里的prop
      this.dataDialogForm.roleId = item.roleId;
      this.dataDialogForm.roleName = item.roleName;
      this.dataDialogForm.description= item.description;
      this.dataDialogForm.status = item.status;
      this.dataDialogForm.remark = item.remark;
      // console.log(this.dataDialogForm);
    },

  // async  getRoleMenu(){
  //     this.handleRoleMenu(index, item);
  //   },
    //获取角色---菜单信息  item为当前选中信息
    handleRoleMenu(index, item) {
      //  console.log("id--:",item);
      if (this.dialogRoleFormVisible) {
        return;
      }
      // console.log("id--2:",item.roleId);
      // console.log("item.roleId",item.roleId)
      this.$http.get("/role/getRoleMenuById?roleId="+ item.roleId)
        .then((res) => {
          // console.log("通过角色id获取相关菜单",res.data);
          this.roleMenus.treeData = res.data.data.treeData;       //菜单树形结构
          this.roleMenus.menuIds = res.data.data.checks;          //角色默认拥有的菜单id集合
          this.roleId = item.roleId;                              //存储角色id
        });
        // console.log("this.roleMenus:",this.roleMenus)
        this.dialogRoleFormVisible = true;
    },
 
    //树形选中的数据 currentNode, selectedNodes
    handleChecked() {
      // console.log(currentNode, selectedNodes);
      this.roleMenus.menuIds = this.$refs.treeMenu.getCheckedKeys();
      // this.roleMenus.menuIds = selectedNodes.checkedKeys;
    },

    //角色权限分配-确定按钮 roleMenuForm
    handleSubmitRoleFormData() {
      const params = { //传递给后端的数据
        params: {
          roleId: this.roleId,
          menuIds: this.roleMenus.menuIds
        }
      };
      // console.log(params);
      this.$http.post('/role/roleToAuth', params.params)
        .then((res) => {
          // console.log("角色分配权限", res);
          if (res.data.data = '成功') {
            this.$message({
              type: "success",
              message: "角色分配菜单成功!",
            });
          }
          this.dialogRoleFormVisible = false;
        })

    },

    //添加角色--的打开窗口
    openDialog() {
      // 打开窗口
      this.dialogFormVisible = true;
      this.dataDialogForm.roleId = 0;
      this.dataDialogForm.roleName = "";
      this.dataDialogForm.description="";
      this.dataDialogForm.status="",
      this.dataDialogForm.remark = "";
    },
    //关闭窗口
    closeDialog() {
      // 清空添加数据的表单
      this.dataDialogForm = {
        roleId: 0,
        roleName: "",
        description: "",
        status: "",
        remark: "",
      };
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
      this.$http.get("/role/list", params).then((res) => {
        // console.log("提交的参数", params);
        //   console.log("初始化角色信息：",res);
        //需要通过响应的结果配置
        this.dataList = res.data.data.list;
        this.totalPage = res.data.data.total;
        this.dataListLoading = false;
      });
    },

  },
  mounted() {
    this.getDataList()

  }
}
</script>
<style></style>
  
  
  