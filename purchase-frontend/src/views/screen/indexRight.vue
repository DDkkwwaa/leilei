<template>
    <div class="max ">

        <!-- <span style="font-size: 15px ; margin-top: 30px; color: azure;"> 这里可以作为介绍性内容，作为扩展模块</span> -->
        <h2 style="font-size: 15px ; color: azure;"> 这里可以作为介绍性内容，作为扩展模块 &nbsp;&nbsp;{{ this.length }}</h2> 
        <!-- <h2>5</h2> -->
    </div>
</template>

<script>

export default {
    data() {
        return {

            //右上
            value: 65.23,
            title: 'vision',
            length:'',
  
        }
    },
    monuted() {
        console.log("执行了");
        this.getLength();

    },
    methods: {
        //获取仓库数量
        getLength(){
        this.$http.get("/depository/getLength").then((res)=>{
                  this.length = res.data.data.count;
                // console.log("长度",res);
        });
        }
    },

}
</script>
<style lang='less' scoped>
.max {
    .box-card {
        background-color: #080a20;
        top: 0px;
        border-radius: 20px;
        border-color: #01010c;
    }

}
</style>