<template>
    <Transition name="fade">
        <div class="alert" :class="alertType">{{ message }}</div>
    </Transition>
</template>

<script>
export default{
    props:{
        message:{
            type:String,
            default:''
        },
        alertType:{
            type:String,
            default:''
        }
    }
}
</script>
<style lang="less" scoped>
.alert{
    text-align: center;
    position: fixed;
    top: 80px;
    left: 50%;
    transform: translateX(-50%);
    min-width: 200px;
    min-height: 50px;
}
.success{
    background-color: #5cb85c;
    color: #fff;
}
.error{
    background-color: #d9534f;
    color: #fff;
}
.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to  {
    opacity: 0;
}
</style>