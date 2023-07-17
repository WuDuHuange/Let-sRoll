<template>
    <div id="container">
        <Transition name="fade"><div class="overlay" v-if="startAnimation"></div></Transition>
        <Transition name="fade">
            <div id="roll-result" v-if="showResult&& !isTen" :class="getStyleClass" >
                <img src="../assets/operator.png" alt="op" >
                <span>{{ rarity }}</span>
                <el-rate v-model="rarityValue" show-text disabled :colors="colors" ></el-rate>
                <el-button type="primary" icon="el-icon-check" circle @click="startAnimation=false;showResult=false"></el-button>
            </div>
        </Transition>
        <Transition name="fade">
            <div v-if="showResult && isTen" class="roll-ten">
                <div class="rollTen-result"  :class="getShadowStyle(item)" v-for="item in resultList" :key="item.indexOf()">
                <img src="../assets/operator.png" alt="op" >
                <span>{{ item }}</span>
                <el-button type="primary" icon="el-icon-check" circle @click="startAnimation=false;showResult=false"></el-button>
            </div>
            </div>
            <!--十连抽结果,v-for绑定resultList数组-->
            
        </Transition>
        <div id="circle" :class="{'animate-shadow':startAnimation}"></div>
        <div id="user-info">
            <span>欢迎回来<br/></span>
            <span class="username">{{username}}</span>
        </div>
        <!-- 切换十连抽/单抽 -->
        <div class="switch">
            <span>单抽/十连</span>
            <el-switch
                v-model="isTen"
                active-color="#13ce66"
                inactive-color="#ff4949">
            </el-switch>
        </div>
        <div id="functional-area">
            <a id="up-box" :class="{'animate-left':startAnimation}">
                <img src="../assets/up.png" alt="up">
            </a>
            <a id="roll-box" @click="toggleStart">
                <img src="../assets/Roll.png" alt="roll">
            </a>
            <a id="down-box" :class="{'animate-right':startAnimation}">
                <img src="../assets/down.png" alt="down">
            </a>
        </div>
        
    </div>
</template>


<style lang="less" scoped>
.roll-ten{
    position: absolute;
    height: 450px;
    width: 800px;
    z-index: 100;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    .rollTen-result{
        background-color: #ffffffe5;
        margin: 20px;
        img{
            margin: 10px auto 10px auto;
            height: 100px;
            width: 100px;
        }
        span{
            display: block;
            font-size: 20px;
            font-weight: bold;
            color: #000000;
        }
        .el-rate{
            margin: 10px auto 10px auto;
        }
        .el-button{
            margin: 10px auto 10px auto;
        }
    }
}
.switch{
    position: absolute;
    top: 0;
    left: 0;
    color: #ffa600;
    font-size: 25px;
    z-index: 1;
    margin: 10px 0 0 10px;
}
#container{
    position: absolute;
    height: 85vh;
    width: 100vw;
    #roll-result{
        position: absolute;
        height: 450px;
        width: 400px;
        background-color: #ffffffe5;
        z-index: 100;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
        
        img{
            margin: 10px auto 10px auto;
            height: 200px;
            width: 200px;
        }
        span{
            display: block;
            font-size: 50px;
            font-weight: bold;
            color: #000000;
        }
        .el-rate{
            margin: 10px auto 10px auto;
        }
        .el-button{
            margin: 10px auto 10px auto;
        }
    }
    .overlay {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
    }
    #circle{
        box-shadow: #353535 0 0 10px;
        position: absolute;
        top: 52%;
        left: 49.5%;
        transform: translate(-50%, -50%);
        border-radius: 50%;
        background-color: #ffffffe5;
        height: 350px;
        width: 350px;
        z-index: 2;
    }
    #user-info{
        text-align: center;
        position: fixed;
        color: #ffffffb9;
        font-size: 175px;
        z-index: 1;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        .username{
            position: relative;
            top: 15vh;
        }
    }
    #functional-area{
        
        a{
            z-index: 3;
        }
        #up-box{
            
            position: absolute;
            top: 8%;
            left: 50%;
            transform: translate(-50%, 0);
            img{
                width: 100%;
            }
        }:hover{
            transform: rotate(10deg);
        }
        #roll-box{
            z-index: 8;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -45%);
            
            border-radius: 150%;
            img{
                height: 100%;
                
            }
        }:hover{
            transform: rotate(10deg);
            transition: all 0.5s ease-in-out;
        }
        #down-box{
            position: absolute;
            bottom: 0%;
            left: 50%;
            transform: translate(-50%, 0);
        }
    }
}
.animate-left{
    animation: left-shift 1s ease-in-out;
}
.animate-right{
    animation: right-shift 1s ease-in-out;
}
.animate-shadow{
    animation: change-colorshadow 1s ease-in-out;
}
@keyframes left-shift{
    20%{
        transform: translate(-45%,0)
    }
    100%{
        transform: translate(-200%,0)
    }
}
@keyframes right-shift{
    20%{
        transform: translate(-55%,0)
    }
    100%{
        transform: translate(80%,0)
    }
}
@keyframes change-colorshadow{
    0%{
        box-shadow: #fffdfd 0 0 100px;
    }
    33%{
        box-shadow: #4ce7fc 0 0 100px;
    }
    66%{
        box-shadow: #f369ff 0 0 100px;
    }
    100%{
        box-shadow: #ff3737 0 0 100px;
    }
}


.animate-ur{
    animation: ur-shadow 2s infinite ease-in-out;
}
@keyframes ur-shadow{
    0%{
        box-shadow: #ff3737 0 0 100px;
    }
    50%{
        box-shadow: #942f2f 0 0 100px;
    }
    100%{
        box-shadow: #ff3737 0 0 100px;
    }
}
.animate-ssr{
    animation: ssr-shadow 2s infinite ease-in-out;
}
@keyframes ssr-shadow{
    0%{
        box-shadow: #f369ff 0 0 100px;
    }
    50%{
        box-shadow: #6c2f94 0 0 100px;
    }
    100%{
        box-shadow: #f369ff 0 0 100px;
    }
}
.animate-sr{
    animation: sr-shadow 2s infinite ease-in-out;
}
@keyframes sr-shadow{
    0%{
        box-shadow: #4ce7fc 0 0 100px;
    }
    50%{
        box-shadow: #2f9494 0 0 100px;
    }
    100%{
        box-shadow: #4ce7fc 0 0 100px;
    }
}
.animate-r{
    animation: r-shadow 2s infinite ease-in-out;
}
@keyframes r-shadow{
    0%{
        box-shadow: #fffdfd 0 0 100px;
    }
    50%{
        box-shadow: #c2c2c2 0 0 100px;
    }
    100%{
        box-shadow: #fffdfd 0 0 100px;
    }
}
.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to  {
    opacity: 0;
}
</style>

<script>

import api from '../interceptors';
export default {
    name: 'HomePage',
    data(){
        return{
            username: localStorage.getItem('username'),
            startAnimation: false,
            rarity: 'ur',
            rarityValue: 5,
            colors: {
                '5': '#ff3737',
                '4': '#f369ff',
                '3': '#4ce7fc',
                '2': '#fffdfd',
                '1': '#c2c2c2'},
            showResult: false,
            isTen: false,
            resultList: []
            
        }
    },
    methods:{
        toggleStart(){
            const self = this;
            this.startAnimation = true;
            if(!this.isTen){
                api.post('/reco/roll',{
                    username:localStorage.getItem('username'),
                    userid:localStorage.getItem('id')
                }).then(function(response){
                    if(response.data.code === 200){
                        console.log(response.data.data);
                        self.rarity = response.data.data;
                        self.rarityValue = self.getRarityValue();
                        setTimeout(function(){
                            self.showResult = true;
                        },1000)
                    }else{
                        alert(response.data.data)
                        console.log(response);
                    }
                }).catch(function(error){
                    console.log(error);
                })
            }else{
                api.post('/reco/rollTen',{
                    username:localStorage.getItem('username'),
                    userid:localStorage.getItem('id')
                }).then(function(response){
                    if(response.data.code === 200){
                        console.log(response.data.data);
                        self.resultList = response.data.data;
                        setTimeout(function(){
                            self.showResult = true;
                        },1000)
                    }else{
                        alert(response.data.data)
                        console.log(response);
                    }
                }).catch(function(error){
                    console.log(error);
                })
            }
            
        },
        getRarityValue(){
            switch(this.rarity){
                case 'ur':
                    return 5;
                case 'ssr':
                    return 4;
                case 'sr':
                    return 3;
                case 'r':
                    return 2;
                default:
                    return 1;
            }
        },
        getShadowStyle(item){
            switch(item){
                case 'ur':
                    return 'animate-ur';
                case 'ssr':
                    return 'animate-ssr';
                case 'sr':
                    return 'animate-sr';
                case 'r':
                    return 'animate-r';
                default:
                    return 'animate-r';
            }
        }
        
    },
    computed:{
        getStyleClass(){
            return {
                'animate-ur': this.rarity === 'ur',
                'animate-ssr': this.rarity === 'ssr',
                'animate-sr': this.rarity === 'sr',
                'animate-r': this.rarity === 'r',
            }
        },
        
    }
}
</script>