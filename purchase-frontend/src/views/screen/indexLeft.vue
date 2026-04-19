<template>
    <div class="grid-content bg-purple">

        <el-card class="box-card shopArea">
            <!-- 商品类型饼状图 -->
            <!-- 从商品表中查 -->
            <div id="echart_shopType" style="height: 300px; width: 600px;"></div>
        </el-card>
        <!-- 柱状图  -->
        <!-- 商品类型? -->
        <el-card class="box-card Columnar-Left" style="margin-top: 2px;">
            <div ref="ColumnarLeft" style="height: 300px;"></div>
        </el-card>
    </div>
</template>

<script>

export default {
    data() {
        return {
            //饼图数据 ---------------------------------------------------------
            threeData: this.generateData(6, 40, 5, 'rgb(12,65,144)'),
            startAngle: 50, // 初始旋转角度
            //数据  商品
            dataShop: [
                { name: "电脑", value: 20 },
                { name: "苹果", value: 10 },
                { value: 10, name: '香蕉' },
                { value: 10, name: '平板' },
                { value: 10, name: '扫描器' },
                { value: 10, name: '衣柜' },
                { value: 10, name: '鱼' },
                { value: 10, name: '汽车' },
            ],
            dataShopAll: {},

            // ----------------------------------柱状图数据
      histogramList: {
            saleList: [],
            shopList: [],
            purchaseList: [],
            stockList: [],
            },
            //柱状图样式设置
            labelOption: {
                show: true,
                position: "20%",
                distance: 45,
                align: 'left',
                verticalAlign: 'bottom',
                rotate: 10,
                formatter: '{c}  ', //{name|{a}}  柱状图中显示的子
                rich: { name: {} },
                textStyle: {
                    fontSize: 15
                },
            },
        }
    },

        // 初始化  在请求方法中初始化echart
        mounted() {

        this.dataShopStockMap();
        //-----------------------------左下角
        this.getHistogramList();
    },

    //xx :-获取到对象  和-不能获取到对象 的区别($event)才能获取到对象-----------------------------------
    methods: {
        generateData(totalNum, bigvalue, smallvalue, color) {
            let dataArr = [];
            for (var i = 0; i < totalNum; i++) {
                if (i % 2 === 0) {
                    dataArr.push({
                        name: (i + 1).toString(),
                        value: bigvalue,
                        itemStyle: {
                            normal: {
                                color: color,
                                borderWidth: 0,
                            }
                        }
                    });
                } else {
                    dataArr.push({
                        name: (i + 1).toString(),
                        value: smallvalue,
                        itemStyle: {
                            normal: {
                                color: "rgb(0,0,0,0)",
                                borderWidth: 0,
                            }
                        }
                    });
                }

            }
            return dataArr;
        },

        //--------------------------遍历
ergodic(data){
    var res =[];
   for(var i=0;i<data.length;i++){
        if(data[i].name !=null){
                res.push({
                    name:data[i].name,
                    value:data[i].value,
                });
            }
   }
//    console.log(res);
   return res
},
        //-------------------------饼图获取库存数据 
        async dataShopStockMap() {
            this.$http.get("/stock/Pie").then((res) => {
                //  console.log("大屏-饼图",  res.data.data)
                this.dataShopAll = res.data.data;
                if(res.data.code=200){
                    this.echartShopInit();
                }
            });
        },

        echartShopInit() {
         var chart1 = this.$echarts.init(document.getElementById('echart_shopType'));
         var option1 = {
            backgroundColor: '#080a20',//背景颜色
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {d}% <br/> {c}"
                },
                //中间圆圈大小以及位置
                title: {
                    text: '库存',
                    subtext: '占比',
                    x: '19%',
                    y: '36%',
                    textStyle: {
                        fontSize: 20,
                        fontWeight: 'normal',
                        color: '#00FFFF',
                    },
                    subtextStyle: {
                        fontSize: 15,
                        fontWeight: 'normal',
                        color: '#00FFFF',
                    },
                },
                //圆圈大小
                series: [{
                    type: 'pie',
                    radius: ['90', '60'],
                    center: ['23%', '43%'],
                    color: ['rgb(255,183,112)', 'rgb(255,162,70)', 'rgb(254,130,8)', 'rgb(125,235,255)', 'rgb(77,194,255)', 'rgb(50,185,255)', 'rgb(24,176,255)', 'rgb(1,155,255)', 'rgb(39,115,254)', 'rgb(39,93,254)'],
                    itemStyle: {
                        normal: {
                            borderWidth: 2,
                            borderColor: '#031845',
                        }
                    },
                    //    data: this.dataShop,
                    //  data: this.dataShopAll,
                    data: this.ergodic(this.dataShopAll),
                    //线长
                    labelLine: {
                        normal: {
                            show: true,
                            length: 45,
                            length2: 10,
                            lineStyle: {
                                color: '#CCCCCC',
                                width: 2,
                                type: 'dashed'

                            }
                        }
                    },
                    label: {
                        normal: {
                            formatter: '{b|{b}}\n{hr|}',
                            rich: {
                                b: {
                                    fontSize: 12,
                                    color: '#FFF',
                                    align: 'left',
                                    padding: [-20, 0, 0, 0],
                                },
                                hr: {
                                    borderColor: '#CCCCCC',
                                    width: '100%',
                                    borderWidth: 2,
                                    borderType: 'dashed',
                                    height: 0,

                                },
                            }
                        }
                    }
                },

                {
                    type: 'pie',
                    name: '旋转圆',
                    zlevel: 10,
                    silent: true,
                    radius: ['20%', '20%'],
                    hoverAnimation: false,
                    startAngle: this.startAngle,
                    data: this.threeData,
                    label: {
                        normal: {
                            show: false
                        },
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                },
                ]

         }
            chart1.setOption(option1);
         },




//-----------------------------------------------------------------------------------
        // 获取柱状图数据
        async   getHistogramList() {
            this.$http.get("/stock/histogramList").then((res) => {
                  console.log("柱状图数据：",res);
                this.histogramList = res.data.data;
                if(res.data.code ==200){
                    this.echartColumnarInit();
                }
            });
        },


        //左下角柱状图
        echartColumnarInit() {
            //  console.log("商品list",this.histogramList[0].shopName);
            //  console.log("销售list",this.histogramList[0].saleList);
            //  console.log("库存list",this.histogramList[0].stockList);
            //  console.log("采购list",this.histogramList[0].purchaseList);
            var chart2 = this.$echarts.init(this.$refs.ColumnarLeft);
            var option2 = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['采购量', '销售量', '库存量']  //不用改
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true },
                        dataView: { show: true, readOnly: false },
                        magicType: { show: true, type: ['line', 'bar', 'stack'] },
                        restore: { show: true },
                        saveAsImage: { show: true }
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        fontSize: 5,
                        axisTick: { show: false },
                        //x族 进销存商品  shopName
                        // data:      this.shopList    // this.histogramList.shopList      // this.shopList 
                        data: this.histogramList[0].shopList
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '采购量',
                        type: 'bar',
                        barWidth: '13%',
                        barGap: 0,
                        label: this.labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        //采购的数据
                        data: this.histogramList[0].purchaseList
                        //   data:   [320, 332, 301, 334, 390]
                    },

                    {
                        name: '销售量',
                        type: 'bar',
                        barWidth: '13%',
                        label: this.labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.histogramList[0].saleList // [220, 182, 191, 234, 290]
                        //  data:   [220, 182, 191, 234, 290]
                    },
                    {
                        name: '库存量',
                        type: 'bar',
                        barWidth: '13%',
                        label: this.labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.histogramList[0].stockList // [150, 232, 201, 154, 190]
                        //  data:   [150, 232, 201, 154, 190]
                    },
                ]

            }
            chart2.setOption(option2);
        },

}
}
</script>
<style lang='less' scoped>
.bg-purple {
    padding-left: 0px;
    padding-right: 0px;

    //共同背景颜色
    .box-card {
        background-color: #080a20;
        left: 0px;
    }

    .shopArea {
        padding-right: 0px;
    }

    //左上角
    .shopArea {
        top: 0px;
        border-radius: 20px;
        border-color: #01010c;
    }

    .Columnar-Left {

        top: 20px;
        border-radius: 20px;
        border-color: #01010c;
    }

    //  border-color: #f8f8fa;
}
</style>