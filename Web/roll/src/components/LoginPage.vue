<template>
    <div id="container">
        <AlertBox :message="alertMessage" :alertType="alertType" v-if="showAlert"></AlertBox>
        <div id="input-box" :class="{flipped:isFliiped}">
            <p>{{title}}</p>
            <input type="text" placeholder="用户名" v-model="username">
            <input type="password" placeholder="密码" v-model="password">
            <Transition name="fade"><input type="password" placeholder="确认密码" v-model="confirmPassword" v-show="isActiveB"></Transition>
            <button @click="toggleButton">{{ title }}</button>
        </div>
        <div id="choose-box">
            <a :class="{ active: isActiveA }" @click="toggleActive('A')">登录</a>·
            <a :class="{ active: isActiveB }" @click="toggleActive('B')">注册</a>
        </div>
    </div>
</template>


<style lang="less" scoped>
#container{
    padding-top: 30px;
    #input-box{
        margin: 80px auto 0px auto;
        text-align: center;
        background-color: #ffffffe5;
        min-height: 350px;
        width: 350px;
        border-radius: 25px;
        box-shadow: #353535 0 0 10px;
        p{
            padding-top: 10px;
            font-size: 30px;
            font-weight: bold;
            color: rgb(46, 91, 134);
        }
        input{
            border: none;
            border-bottom: 1px solid rgb(95, 143, 187);
            height: 40px;
            width: 300px;
            margin: 10px auto 10px auto;
        }
        button{
            border: none;
            border-radius: 5px;
            background-color: rgb(95, 143, 187);
            color: #fff;
            height: 40px;
            width: 300px;
            margin: 10px auto 30px auto;
        }
    }
    #choose-box{
        margin: 20px auto 0px auto;
        text-align: center;
        background-color: #fffffff8;
        min-height: 30px;
        width: 120px;
        border-radius: 25px;
        box-shadow: #353535 0 0 10px;
        a{
            text-decoration: none;
            color: #000;
            transition: all 0.5s;
        }
        .active{
            color: rgb(95, 143, 187);
            font-weight: bold;
        }
    }
}
@keyframes flip{
    0%{
        transform: rotateY(0deg);
    }
    30%{
        transform: rotateY(-50deg);
    }
    100%{
        transform: rotateY(360deg);
    }
}
#input-box.flipped{
    animation: flip 0.7s;
}
.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to  {
    opacity: 0;
}
</style>

<script>
import AlertBox from './tools/AlertBox.vue'
import api from '../interceptors.js'
import jwt_decode from 'jwt-decode'

export default {
    components:{
        AlertBox
    },
    created(){
        console.log("api--rrrr---------------",api);
    },  
    data(){
        return {
            username: '',
            password: '',
            confirmPassword: '',
            isActiveA: true,
            isActiveB: false,
            isFliiped: false,
            title:"登录",
            alertMessage: '',
            alertType: '',
            showAlert: false
        }
    },
    methods:{
        toggleActive(tab){
            if(tab == 'A'){
                this.isFliiped = true;
                this.isActiveA = true;
                this.isActiveB = false;
                setTimeout(() => {
                    this.title = "登录";
                    this.isFliiped = false;
                }, 700);
            }else{
                this.isFliiped = true;
                this.isActiveB = true;
                this.isActiveA = false;
                setTimeout(() => {
                    
                    this.title = "注册";
                    this.isFliiped = false;
                }, 700);
            }
        },
        toggleButton(){
            if(this.isActiveA){
                this.login();
            }else{
                this.register();
            }
        },
        showMessage(message, type){
            this.alertMessage = message;
            this.alertType = type;
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1000);
        },
        login(){
            if(this.username == ''){
                this.showMessage("用户名不能为空", "error")
                return;
            }
            if(this.password == ''){
                this.showMessage("密码不能为空", "error")
                return;
            }
            const self = this;
            console.log(self.username, self.password);
            api.post('/user/login', {
                username: self.username,
                password: self.password
            }).then(function(response){
                if(response.data.code === 200){
                    self.showMessage("登录成功,即将跳转", "success")
                    console.log(response.data.data);
                    let decoded = jwt_decode(response.data.data);
                    localStorage.setItem('token', response.data.data);
                    localStorage.setItem('username', decoded.username);
                    localStorage.setItem('role', decoded.role);
                    localStorage.setItem('id', decoded.id);
                    setTimeout(() => {
                        if(decoded.role == 'admin'){
                            self.$router.push("/ManagePage");
                        }else{
                            self.$router.push("/HomePage");
                        }
                    }, 2000);
                }else{
                    self.showMessage(response.data.data, "error")
                    console.log(response);
                }
            }).catch(function(error){
                console.log(error);
            })
        },
        register(){
            if(this.username == ''){
                this.showMessage("用户名不能为空", "error")
                return;
            }
            if(this.password == ''){
                this.showMessage("密码不能为空", "error")
                return;
            }
            if(this.confirmPassword == ''){
                this.showMessage("确认密码不能为空", "error")
                return;
            }
            if(this.password != this.confirmPassword){
                this.showMessage("两次密码不一致", "error")
                return;
            }
            const self = this;
            console.log(self.username, self.password);
            api.post('/user/register', {
                username: self.username,
                password: self.password
            }).then(function(response){
                if(response.data.code === 200){
                    self.showMessage("注册成功", "success")
                    console.log(response.data.data);
                    self.toggleActive('A');
                }else{
                    self.showMessage(response.data.data, "error")
                    console.log(response);
                }
            }).catch(function(error){
                console.log(error);
            })
        }
    }
}
</script>
