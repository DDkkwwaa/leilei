<template>
	<!-- 部门资料 -->
	<div>
		<el-card class="box-card">
			<div class="mod-dept">
				<el-form :inline="true" :model="selectDept" class="demo-form-inline">
					<el-form-item>
						<el-input v-model="selectDept.dept" clearable placeholder="请输入部门名称"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="getDataList">查询</el-button>
						<!-- <el-button type="primary">增加</el-button> -->
					</el-form-item>
				</el-form>

				<el-table :data="dataList" border style="width: 100%">
					<el-table-column type="selection"> </el-table-column>
					<el-table-column prop="id" label="ID" width="60">
					</el-table-column>
					<el-table-column prop="deptName" label="部门名称" width="220">
					</el-table-column>
					<el-table-column prop="leader" label="负责人" width="170">
					</el-table-column>
					<el-table-column prop="phone" label="电话" width="220">
					</el-table-column>
					<el-table-column prop="status" label="状态" width="120">
						<template slot-scope="scope">
							<span>{{ scope.row.status == 0 ? '正常' : '禁用' }}</span>
						</template>
					</el-table-column>
					<el-table-column prop="createTime" label="创建时间" width="300">
						<template slot-scope="scope">
						<span>{{ scope.row.createTime == null ? '' : scope.row.createTime.replace("T", " ") }}</span>
					</template>
					</el-table-column>
					<el-table-column prop="updateTime" label="更新时间" width="300">
						<template slot-scope="scope">
						<span>{{ scope.row.updateTime == null ? '' : scope.row.updateTime.replace("T", " ") }}</span>
					</template>
					</el-table-column>
					<!-- <el-table-column fixed="right" label="操作" width="200">
						<template slot-scope="scope">
							<el-button size="mini"  type="primary" @click="handleClick(scope.row)"  >编辑</el-button>
							<el-button type="danger" size="small">删除</el-button>
						</template>
					</el-table-column> -->

				</el-table>
				<el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle"
					:current-page="pageIndex" :page-sizes="[5,7, 10]" :page-size="pageSize" :total="totalPage"
					layout="total, sizes, prev, pager, next, jumper" style="margin-top: 30px">
				</el-pagination>
			</div>
		</el-card>
	</div>
</template>
<script>

export default {
	name: "dept",

	data() {
		return {

			selectDept: {
				dept: "",
			},
			dataList: [], //数据列表
			pageIndex: 1,   //初始页
			pageSize: 5,        //每页条数
			totalPage: 0,         //总条数
			dataListLoading: false,
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
					keyword: this.selectDept.dept
				}

			};
			//后端请求 分页获取对象
			this.$http.get("jcDept/list", params).then((res) => {
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
		this.getDataList()
	}
}
</script>
<style></style>
