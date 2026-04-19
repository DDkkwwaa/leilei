export default{
    // 定义的state状态 类似于vue中的data，通过state来存放状态
    // 这是菜单栏里的所有菜单及其子菜单
 //   namespaced: true,// 
    state:{
        // 控制菜单栏的状态
        isCollapse:false,
        tabsList:[// 初始面包屑数据
            {
                path:'/',
                name:'home',
                label:'首页',
                icon:'s-home',
                url:'home'
            }
        ]
    },
    // Vuex需要对state进行管理，则必须控制state的维护入口，因此所有对state的变更请求都必须通过mutations来完成
    //此mutations有三个函数
    mutations:{
        collapseChange(state){
         //   console.log("state状态:",state.isCollapse);
                //  左边-初始化定义state=右边-传递的state
            state.isCollapse = !state.isCollapse
        },
        menuChange(state,val){
            // 更新面包屑的数据 val 菜单对象数据
            // console.log("面包屑数据更新:",val);
           
            if(val.name !== 'main'){
                //----------
                const index = state.tabsList.findIndex(item => item.name === val.name)
                if(index === -1){
                     state.tabsList.push(val)
                }
            }
        } ,
        closeTag(state,val){
            const index = state.tabsList.findIndex(item => item.name === val.name)
            state.tabsList.splice(index,1)
        }
    }
}