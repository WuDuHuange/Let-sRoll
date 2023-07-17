<template>
    <Transition name="fade"><div class="confirmation-dialog" v-if="visible">
      <div class="overlay"></div>
      <div class="dialog">
        <h2>{{ title }}</h2>
        <p>{{ message }}</p>
        <div class="buttons">
          <button @click="confirm">确定</button>
          <button @click="cancel">取消</button>
        </div>
      </div>
    </div></Transition>
  </template>
  
  <script>
  export default {
    data() {
      return {
        visible: false,
        title: '',
        message: ''
      };
    },
    methods: {
      confirm() {
        // 确认操作的逻辑
        // 可以触发相应的事件或执行回调函数等
        this.$emit('confirm');
        this.hide();
      },
      cancel() {
        // 取消操作的逻辑
        // 可以触发相应的事件或执行回调函数等
        this.$emit('cancel');
        this.hide();
      },
      show(title, message) {
        this.title = title;
        this.message = message;
        this.visible = true;
      },
      hide() {
        this.title = '';
        this.message = '';
        this.visible = false;
      }
    }
  };
  </script>
  
  <style scoped>
  .confirmation-dialog {
    z-index: 100;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
  }
  
  .dialog {
    
    background-color: #fff;
    padding: 20px;
    border-radius: 4px;
    z-index: 30;
  }
  
  button {
    border: none;
    text-align: right;
    margin: 10px 20px 0 0;
    background-color:rgb(95, 143, 187);
    color: #fff;
    height: 40px;
    width: 100px;
    text-align: center;
  }


    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s;
    }
    .fade-enter, .fade-leave-to  {
        opacity: 0;
    }
  </style>
  