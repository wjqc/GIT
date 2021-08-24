<template>
    <div class="login">
        <div class="signin-signup">
            <el-form :model="loginUser" class="loginForm" label-width="100px" :rules="rules" ref="loginUser">
            <el-form-item  label="账户" prop="username">
                <el-input v-model="loginUser.username" type="text" placeholder="用户名" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item  label="密码" prop="password">
                <el-input v-model="loginUser.password" type="password" placeholder="密码" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" class="submit-btn" @click="login('loginUser')">登录</el-button>
            </el-form-item>
            <div class="tiparea">
                <p><a>忘记密码？</a> <a>立即注册</a></p>
            </div>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return {
                loginUser :{
                    username:"",
                    password:"",
                },

                rules:{
                username:[
                    {
                        required:true,                    
                        message:"用户名不能为空!",
                        trigger:"blur",
                    }
                ],
                password:[
                    {
                        required:true,
                        message:"密码不能为空!",
                        trigger:"blur",
                    },
                    {
                        min:6,
                        max:15,
                        message:'弱密码!',
                    }
                ]
                },
        }
    },
    methods: {
    login(){
            this.$refs['loginUser'].validate((valid) => {
            if (valid) {
                alert('submit!');
                login(this.loginUser).then(res => {
                    console.log("res:", res)
                })
            } else {
                console.log('error submit!!');
                return false;
            }
        });       
    }
}
}
</script>

<style scoped>
.login{
    position: relative;
    width: 100%;
    background-color: #fff;
    min-height: 100vh;
    overflow: hidden;
    background-image: url('../assets/login.jpg');
    background-size: 100% 100%;
   
}
.signin-signup{
    position: absolute;
    top: 50%;
    transform: translate(-50%,-50%);
    left: 75%;
    width: 44%;
    transition: 1s 0.7s ease-in-out;
    display: grid;
    grid-template-columns: 1fr;
    z-index: 5;
}
.loginForm{
    margin-top: 20px;
    background-color: #fff;
    padding: 20px 40px 20px 20px;
    border-radius: 5px;
    box-shadow: 0px 5px 10px #cccc;
}

.submit-btn{
    width: 100%;
}

.tiparea{
    text-align: right;
    font-size: 12px;
    color: #333;
}
.tiparea p a{
    color: #409eff;
}
</style>