<template>
  <div id="app">
    <transition name="el-zoom-in-center">
      <div class="reco-list" v-if="showRecoList">
        <i class="el-icon-close" @click="showRecoList=false"></i>
        <div class="user-info">
          <span>欢迎回来<br/></span>
          <span class="username">{{username}}</span>
        </div>
        <div class="score">
          <span>你的抽卡运气指数为：</span>
          <span class="score-number">{{score}}</span>
          <span>分</span>
          <span class="comment">{{commentOfScore}}</span>
        </div>
        <el-table :data="tableData" style="width: 80vh;">
          <el-table-column prop="username" label="用户名" width="180">
          </el-table-column>
          <el-table-column prop="rarity" label="稀有度" width="180">
          </el-table-column>
          <el-table-column prop="userid" label="用户id" width="180">
          </el-table-column>
          <el-table-column prop="recoid" label="抽卡id" width="180">
          </el-table-column>
        </el-table>
      </div>
    </transition>
    
    <div class="nav">
      <ConfirmationDialog
      ref="confirmationDialog"
      @confirm="handleConfirm"
      @cancel="handleCancel"
    ></ConfirmationDialog>
      <img src="./assets/PageLogo.png" alt="logo" class="logo">
      <a v-if="!isLoginPage" @click="quit" class="quit"><img src="./assets/poweroff.png" alt="quit"></a>
      <a v-if="isHomePage" class="record" @click="showReco"><img src="./assets/reco.png" alt="reco"></a>
    </div>
    <main>
      <router-view/>
    </main>
    <footer>
      © 2023 Let_sRoll
    </footer>
  </div>
</template>

<script>
import ConfirmationDialog from './components/tools/ConfirmationDialog.vue'
import api from './interceptors.js'
export default {
  name: 'App',
  components: {
    ConfirmationDialog
  },
  data(){
    return{
      // 用于判断当前页面是否为登录页面
      isLoginPage: true,
      isHomePage: false,
      showRecoList: false,
      tableData: [],
      score: 0,
      commentOfScore: '',
      username: localStorage.getItem('username')
    }
  },
  created(){
    // 判断当前页面是否为登录页面
    if(this.$route.path === '/LoginPage'){
      this.isLoginPage = true
    }else if(this.$route.path === '/HomePage'){
      this.isHomePage = true
    }
  },
  watch: {
    // 监听路由变化
    $route(to){
      if(to.path === '/LoginPage'){
        this.isLoginPage = true
      }else{
        this.isLoginPage = false
      }
      if(to.path === '/HomePage'){
        this.isHomePage = true
      }else{
        this.isHomePage = false
      }
    }
  },
  methods:{
    // 退出登录
    quit(){
      this.$refs.confirmationDialog.show('退出登录', '确定要退出登录吗？')
    },
    // 确认退出登录
    handleConfirm(){
      // 清除本地存储的token
      localStorage.removeItem('token')
      // 跳转到登录页面
      if (this.$route.path !== "/LoginPage") {
        this.$router.push("/LoginPage");
      }
    },
    // 取消退出登录
    handleCancel(){
      // 隐藏确认对话框
      this.$refs.confirmationDialog.hide()
    },
    showReco(){
      this.getRecoList()
      this.getUserList()
      this.showRecoList = true
    },
    getRecoList(){
      const self = this
      api.post('/reco/listReco',{
        username: localStorage.getItem('username'),
        userid: localStorage.getItem('id')
      }).then(function(response){
        if(response.data.code === 200){
          self.tableData = response.data.data
        }else{
          alert(response.data.data)
        }
      }).catch(function(error){
        console.log(error);
      })
    },
    getUserList(){
      const self = this
      api.get('/user/listUser').then(function(response){
        if(response.data.code === 200){
          // 寻找userid=本地存储的id的用户
          for(let i = 0; i < response.data.data.length; i++){
            if(response.data.data[i].userid == localStorage.getItem('id')){
              
              // ur=5分，ssr=4分，sr=3分，r=2分，计算平均数得到score
              let score = (response.data.data[i].ur * 5 + response.data.data[i].ssr * 4 + response.data.data[i].sr * 3 + response.data.data[i].r * 2) / (response.data.data[i].ur + response.data.data[i].ssr + response.data.data[i].sr + response.data.data[i].r)
              // 将score保留两位小数
              score = score.toFixed(2)
              self.score = score
              self.getComment()
            }
          }
        }else{
          alert(response.data.data)
        }
      }).catch(function(error){
        console.log(error);
      })
    },
    getComment(){
      // 根据score的值，给出评价
      console.log(this.score)
      if(this.score >= 4.5){
        this.commentOfScore = '你的抽卡运气令人惊叹！'
      }else if(this.score >= 4){
        this.commentOfScore = '你的抽卡运气远超常人！'
      }else if(this.score >= 3.5){
        this.commentOfScore = '你的抽卡运气鹤立鸡群！'
      }else if(this.score >= 3){
        this.commentOfScore = '你的抽卡运气真不错！'
      }else if(this.score >= 2.5){
        this.commentOfScore = '你的抽卡运气还可以！'
      }else if(this.score >= 2.3){
        this.commentOfScore = '芸芸众生'
      }else{
        this.commentOfScore = '史诗级非酋！'
      }

    }
  }
}
</script>


<style lang="less" scoped>
  #app{
    background-image: url('./assets/background.png');
    background-size: 100%;

    .nav{
      height: 70px;
      background-color: #fff;
      box-shadow: #353535 0 0 10px;

      .logo{
        height: 50px;
        margin: 10px auto 10px auto;
      }
      .record{
        position: absolute;
        left: 20px;
        top: 10px;
        img{
          height: 50px;
        }
      }
      .quit{
        position: absolute;
        right: 20px;
        top: 10px;
        img{
          height: 50px;
        }
      }
    }
    main{
      min-height: calc(100vh - 120px);
    }

    footer{
      height: 50px;
      background-color: #fff;
      text-align: center;
      padding-top: 10px;
    }
  }
.reco-list{
  position: absolute;
  top: 70px;
  left: 0;
  width: 100%;
  height: calc(100vh - 70px);
  background-color: #fff;
  box-shadow: #353535 0 0 10px;
  z-index: 100;
  overflow-y:scroll ;
  .el-icon-close{
    position: absolute;
    right: 20px;
    top: 20px;
    font-size: 30px;
    cursor: pointer;
  }
  .el-table{
    position: relative;
    left: 50%;
    transform: translateX(-50%);
  }
  .user-info{
    text-align: center;
    font-size: 20px;
    margin-top: 20px;
    .username{
      font-size: 30px;
      font-weight: bold;
    }
  }
  .score{
    text-align: center;
    font-size: 20px;
    margin-top: 20px;
    .score-number{
      font-size: 30px;
      font-weight: bold;
    }
    .comment{
      font-size: 20px;
      font-weight: bold;
    }
  }
}
</style>
