export function validateString(String){
    const reg = /^[A-Za-z]+$/;
    return reg.test(String);
}

/*验证内容是否英文数字以及下划线*/
export function isPassword(rule, value, callback) {
    const reg =/^[_a-zA-Z0-9]+$/;
    if(value==''||value==undefined||value==null){
      callback();
    } else {
      if (!reg.test(value)){
        callback(new Error('密码仅由英文字母，数字以及下划线组成'));
      } else {
        callback();
      }
    }
}