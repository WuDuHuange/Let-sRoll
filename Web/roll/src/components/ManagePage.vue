<template>
    <div id="container">
        <AlertBox :message="alertMessage" :alertType="alertType" v-if="showAlert"></AlertBox>
        <div id="edit-box" v-if="showEditBox" >
            <p>编辑用户</p>
            <input type="text" placeholder="用户名" v-model="editUsername">
            <select v-model="editRole">
                <option value="admin">管理员</option>
                <option value="user">普通用户</option>
            </select>

            <button @click="updateUser(editUserid, editUsername, editRole)">修改</button>
        </div>
        <div>
            <div id="user">
                <div id="user-info">
                    <span>当前账号</span>|
                    <span>{{username}}</span>
                </div>
            </div>
        </div>
        <div id="list-box">
            <h1>用户列表</h1>
            <span>id&emsp;&emsp;</span>
            <span>用户名&emsp;&emsp;</span>
            <span>权限&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>

            <!-- 用户列表，带删除和编辑按钮，当id与当前账号相同时不能修改 -->
            <div class="list-item" v-for="item in userList" :key="item.userid">
                <div class="list-item-info">
                    <span>{{item.userid}}</span>
                    <span>{{item.username}}</span>
                    <span>{{item.role}}</span>
                </div>
                <div class="list-item-btn">
                    <button @click="edit(item.userid, item.username, item.role)" v-if="item.userid != id">编辑</button>
                    <button @click="deleteUser(item.userid)" v-if="item.userid != id">删除</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import api from '../interceptors';
import AlertBox from './tools/AlertBox.vue'
export default{
    components:{
        AlertBox,
    },
    data(){
        return{
            username: localStorage.getItem('username'),
            id: localStorage.getItem('id'),
            userList: [],
            alertMessage: '',
            alertType: '',
            showAlert: false,
            editUsername: '',
            editUserid: '',
            editRole: '',
            showEditBox: false,
        }
    },methods:{
        getUserList(){
            const self = this;
            api.get('/user/listUser').then(function(response){
                if(response.data.code === 200){
                    self.userList = response.data.data;
                    console.log(response.data.data);
                }else{
                    self.showMessage(response.data.data, "error")
                    console.log(response);
                }
            }).catch(function(error){
                console.log(error);
            })
        },
        showMessage(message, type){
            this.alertMessage = message;
            this.alertType = type;
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1000);
        },
        deleteUser(userid){
            const self = this;
            api.get('/user/deleteUser', {
                params:{
                    userid:userid
                }
            }).then(function(response){
                if(response.data.code === 200){
                    self.showMessage("删除成功", "success")
                    self.getUserList();
                }else{
                    self.showMessage(response.data.data, "error")
                    console.log(response);
                }
            }).catch(function(error){
                console.log(error);
            })
        },
        updateUser(id, username, role){
            const self = this;
            api.post('/user/updateUser', {
                userid: id,
                username: username,
                role: role
            }).then(function(response){
                if(response.data.code === 200){
                    self.showMessage("修改成功", "success")
                    self.getUserList();
                    self.showEditBox = false;
                }else{
                    self.showMessage(response.data.data, "error")
                    console.log(response);
                }
            }).catch(function(error){
                console.log(error);
            })
        },
        edit(userid,username,role){
            this.editUserid = userid;
            this.editUsername = username;
            this.editRole = role;
            this.showEditBox = true;
        },
        
        
        
    },mounted(){
        this.getUserList();
    }
}
</script>
<style lang="less" scoped>
#container{
    padding-top: 30px;
    #edit-box{
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
        background-color: #ffffffe5;
        min-height: 200px;
        width: 300px;
        border-radius: 25px;
        box-shadow: #353535 0 0 10px;
        p{
            padding-top: 10px;
            font-size: 30px;
            font-weight: bold;
            color: rgb(46, 91, 134);
        }
        input{
            margin-top: 10px;
            width: 200px;
            height: 30px;
            border-radius: 5px;
            border: 1px solid #000;
            outline: none;
            padding-left: 10px;
        }
        select{
            margin-top: 10px;
            width: 200px;
            height: 30px;
            border-radius: 5px;
            border: 1px solid #000;
            outline: none;
            padding-left: 10px;
        }
        button{
            display: block;
            margin: 10px auto 0px auto;
            margin-top: 10px;
            width: 100px;
            height: 30px;
            border-radius: 5px;
            border: 1px solid #000;
            outline: none;
            background-color: #ffffff;
            cursor: pointer;
            &:hover{
                background-color: #000000;
                color: #ffffff;
            }
        }
    }
    #user{
        margin: 10px auto 0px auto;
        text-align: center;
        background-color: #ffffffe5;
        min-height: 60px;
        width: 300px;
        border-radius: 25px;
        box-shadow: #353535 0 0 10px;
        #user-info{
            padding-top: 10px;
            font-size: 30px;
            font-weight: bold;
            color: rgb(46, 91, 134);
        }
    }
    #list-box{
        margin: 20px auto 0px auto;
        text-align: center;
        background-color: #fffffff8;
        min-height: 30px;
        width: 350px;
        border-radius: 25px;
        box-shadow: #353535 0 0 10px;
        .list-item{
            
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            border-bottom: 1px solid #000;
            .list-item-info{
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 200px;
                span{
                    width: 60px;
                }
            }
            .list-item-btn{
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100px;
                span{
                    width: 60px;
                }
                button{
                    display: block;
                    width: 60px;
                    height: 30px;
                    border-radius: 5px;
                    border: 1px solid #000;
                    outline: none;
                    background-color: #ffffff;
                    cursor: pointer;
                    &:hover{
                        background-color: #000000;
                        color: #ffffff;
                    }
                }
            }
        }
    }
}

</style>