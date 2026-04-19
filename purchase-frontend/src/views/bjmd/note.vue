<template>
    <!-- 左侧 笔记  创建日期  -->
    <el-row :gutter="20">
        <el-col :span="7">
            <div>
                <div class="grid-content leftNote">
                    <el-table :data="tableNote" style="width: 100%; ">
                        <el-table-column  label="笔记" width="140">   //prop="title"
                            <template slot-scope="scope">
                                <div style="color:red; cursor: pointer; " @click="getText(scope.row)">{{ scope.row.title }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="createTime" label="创建日期" width="200">
                            <template slot-scope="scope">{{ scope.row.createTime ==null ? null: scope.row.createTime.replace("T"," ")  }} </template>
                        </el-table-column>

                        <el-table-column label="操作">
					<template slot-scope="scope" >
						<el-button size="mini" type="danger" icon="el-icon-delete" @click="DeleteNote(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
                    </el-table>
                </div>

            </div>
        </el-col>

        <!--  查询 -->
        <el-col :span="17">
            <el-row>
                <div style="margin-top:33px">
                    <el-input class="selectNote" v-model="dataForm.select" placeholder="请输入笔记名称" clearable></el-input>
                    <el-button type="primary" icon="el-icon-search" @click="getNoteDateList"
                        style="margin-left: 5px;">查询</el-button>
                </div>
                <span style="margin-left:13px;">当前笔记名称</span>
                <el-input class="Titledisabled" v-model="detail.title"></el-input>
                <el-button type="primary" @click="submitNote()" style="margin-left:15px">保存</el-button>
                <el-button type="primary" @click="createNote()" style="margin-left:15px">新建</el-button>

                <!-- 富文本 -->
                <div class="grid-content rightEditor">
                    <quill-editor class="ql-editor" v-model="detail.content" ref="myQuillEditor" :options="editorOption"
                        @blur="onEditorBlur($event)" @focus="onEditorFocus($event)" @change="onEditorChange($event)">
                    </quill-editor>
                </div>
            </el-row>
        </el-col>


        <!-- 新建笔记------弹框 -->
        <div>
            <el-dialog title="请输入笔记名称" :visible.sync="dialogNoteFormVisible">
                <el-form :model="tableNoteDialog">
                    <el-form-item label="笔记命名" label-width="200px">
                        <el-input v-model=" tableNoteDialog.title" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogNoteFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="dialogNoteForm()">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </el-row>
</template>
 
<script>
import { quillEditor } from 'vue-quill-editor'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
export default {
    components: {
        quillEditor
    },
    data() {
        return {

            //查询
            dataForm: {
                select: "",
            },
            //保存笔记内容的变化
            quillTemp: {
                quill: [],
                html: "",
                text: "",
            },

            detail: {
                type: String,
                default: "123",
                title: "",
                content: "",
                id: 0,
            },
            //创建笔记弹出框
            dialogNoteFormVisible: false,

            tableNoteDialog: {
                title: "",
            },
            tableNote:[],
            // tableNote: {
            //     // id: 0,
            //     // userName: '',
            //     // title: '',
            //     // content: "",
            //     // createTime: '',
            //     // updateTime: '',
            //     // remark: '',
            // },
            content: "", //双向数据绑定数据
            // 富文本编辑器配置
            editorOption: {
                placeholder: '请输入正文',
                modules: {
                    toolbar: [
                        ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
                        ['blockquote', 'code-block'], // 引用  代码块
                        [{ header: 1 }, { header: 2 }], // 1、2 级标题
                        [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
                        [{ script: 'sub' }, { script: 'super' }], // 上标/下标
                        [{ indent: '-1' }, { indent: '+1' }], // 缩进
                        [{ direction: 'rtl' }], // 文本方向
                        [{ size: ['12px', false, '16px', '18px', '20px', '30px'] }], // 字体大小
                        [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
                        [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
                        [{ font: [false, 'SimSun', 'SimHei', 'Microsoft-YaHei', 'KaiTi', 'FangSong', 'Arial'] }], // 字体种类
                        [{ align: [] }], // 对齐方式
                        ['clean'], // 清除文本格式
                        // ['link', 'image', 'video'] // 链接、图片、视频
                    ],
                },
            }

        }
    },


    mounted() {
        // this.getNoteList()
        this.getNoteDateList()
    },
    methods: {

        getText(row) { //点击某个笔记后的参数传递
            // console.log("row", row)
            this.detail.id = row.id;  //更新id
            this.detail.content = row.content;
            this.detail.title = row.title;
            // console.log("row赋值后的", this.detail);
            //    this.getNoteList() //点击后更新
            this.getNoteDateList()
        },
        //获取初始化数据
        getNoteList() {
            if (this.dataListLoading) {
                return;
            }
            //后端请求 分页获取对象
            this.$http.get("/bjNote/listByName").then((res) => {
                // console.log("笔记", res);
                this.tableNote = res.data.data.list;
                // console.log("笔记-------", this.tableNote);
            });
        },


        // 失去焦点事件
        onEditorBlur(quill) {
            //   console.log('editor blur!', quill)
        },
        // 获得焦点事件
        onEditorFocus(quill) { //   console.log('editor focus!', quill)
        },
        // 准备富文本编辑器
        onEditorReady(quill) {
            //    console.log("-----------55------------", this.tableNote.content);
            //    console.log('editor ready!', quill)
        },

        // 内容改变事件
        onEditorChange({ quill, html, text }) {
            // console.log("quill ", quill)
            // console.log("html ", html)
            // console.log("text ", text);
            //保存改变的内容
            this.quillTemp.quill = quill;
            this.quillTemp.html = html;
            this.quillTemp.text = text;
        },
        //----------保存笔记
        submitNote() {
            //请求参数封装
            const params = {
                id: this.detail.id,
                title: this.detail.title,
                content: this.quillTemp.html,
            };
            //    console.log("当前id", +this.detail.id)
            //  console.log("this.quillTemp.html", this.quillTemp.html)
            // console.log("content文本内容：",params.content)
            this.$http.post("/bjNote/update", params).then((res) => {
                // console.log(res)
                if (res.data.data === "成功") {
                    this.$message({
                        type: "success",
                        message: "保存成功",
                    })
                } else {
                    this.$message({
                        type: "error",
                        message: "保存出错，请联系管理员"
                    })
                }
            }).catch((console.error()))

            this.getNoteDateList()  //保存后更新
        },

        // 查询笔记
        getNoteDateList() {
            //    this.detail.content="";
            //请求参数封装   //每个用户只能创建8个笔记
            const params = {
                params: {
                    pageSize: 8,
                    pageNum: 1,
                    keyword: this.dataForm.select,
                }
            };
            this.$http.get("/bjNote/listByName", params).then((res) => {
                //  console.log("提交的参数", params);
                //   console.log("查询笔记",res);
                this.tableNote = res.data.data.list;
            })
        },


        //----------------------新建笔记
        createNote() {
            this.dialogNoteFormVisible = true;
        },
        //dialog  新建笔记的 弹窗中的确定按钮 
        dialogNoteForm() {
            //请求参数封装   //每个用户只能创建8个笔记
            const title = this.tableNoteDialog.title;
            // console.log("title ",title);
            if (title === '') {
                this.$message({
                    message: '请输入笔记名称',
                    type: 'warning'
                });
                return;
            }
            this.$http.post("/bjNote/createByTitle?title="+title).then((res) => {
                // console.log(res);
                if(res.data.code ===200){
                    this.$message({
                type: "success",
                message: "添加成功!",
              });
              this.tableNoteDialog.title = "";
            this.dialogNoteFormVisible = false;
              this.getNoteDateList() //更新笔记列表
        }
        if(res.data.code ===500){
            this.dialogNoteFormVisible = false;
            this.$message({
                type: "error",
                message: "创建失败! 系统暂时只能创建8个笔记!",
              });
              return;
        }
            });   
        },

        //删除笔记
        DeleteNote(index,item){
            this.$confirm("此操作将永久该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(()=>{
        this.$http.post("/bjNote/deleteById?id="+item.id).then((res)=>{
                // console.log("删除笔记res:",res);
                if(res.data.code ===200){
                    this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getNoteDateList();
        }})
      }).catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
        }
    }
}
</script>
 
<style lang='less' scoped>
.el-row {
    margin-top: 0px;
    height: 500px;

    .leftNote {
        border: 1px solid #ccc;
        margin-top: 34px;
        padding-left: 10px;
        padding-right: 0px;
    }

    .rightEditor {
        margin-top: 0px;
    }

    .Titledisabled {
        width: 30%;
        margin-left: 13px;
        margin-top: 20px;
    }

    .selectNote {
        width: 30%;
        margin-left: 13px;
    }

    .ql-editor {
        margin-top: 0px;
        height: 50%;
    }
}
</style>
