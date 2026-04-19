<template>
        <div>
                <hr style=" border-color: #000000;">
                <el-row>
                        <el-col :span="12">
                                <!-- 第一行第一列 -->
                                <el-card shadow="hover" class="box-card1 ">
                                        <div class="welcome" style="color: lightcoral; font-size: 13px;">
                                                <span> 欢迎使用商品进销存管理系统 </span> <br>
                                                <span>&emsp;springboot,mybatis,SpringSecurity,mysql,vue <br>
                                                        完善的认证授权</span>
                                        </div>
                                </el-card>
                                <el-card shadow="hover" class="box-card2 " style=" box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.0) ">
                                        <span>当前的登陆用户是:</span>
                                        <span style="color: rgb(237, 24, 24); font-size: 20px;">{{ this.adminName }}</span>
                                </el-card>

                        </el-col>

                </el-row>

                <el-row>
                        <el-col  :span="6">
                                <el-card shadow="hover" class="box-card2 " style=" box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.0) ">
                                  </el-card>
                        </el-col>
                </el-row>
        </div>
</template>

<script>

export default {
        data() {
                return {
                        adminName: sessionStorage.getItem('username'),
                }
        }
}
</script>
<style lang='less' scoped>

.el-card{
        border: none;
}
.el-row {
        margin-bottom: 20px;

        &:last-child {
                margin-bottom: 0;
        }
}

.el-col {
        margin-top: 12px;
}

.box-card1 {
        width: 500px;
        margin-left: 0%;

        .welcome {
                //   margin-top: 100px;
                font-size: 16px;
        }

        .margin-top {
                font-size: 30px;
                // margin-top: 40px;
        }
}

//第二个框
.box-card2 {
        margin-top: 0%;
        width: 500px;
        // margin-left: 50%;

        .margin-top {
                font-size: 30px;
                margin-top: 20px;
        }
}
</style>
