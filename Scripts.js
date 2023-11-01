document.addEventListener('DOMContentLoaded', function (){
  let container = document.querySelector('.container');
  const login = document.querySelector('.toLogin')
  const register = document.querySelector('.register')
  let page = 1, jsonData, replyArray;
  $.ajax({
    url:"http://hyeumine.com/forumGetPosts.php",
    method: "GET",
    data: {page: page},
    success:(data)=>{
      let replyNumber = 1
      jsonData = JSON.parse(data)
      console.log(replyArray)
      jsonData.forEach(element => {
        replyArray = null;
        let replyConNumber = "replyContainer" + (replyNumber++);
        replyArray = element.reply;
        $('<div class="Posts">' +
          '<h3>'+ element.user +'</h3>'+
          element.date +
          '<p><i>'+
          element.post +
          '</i></p></div><div class="e"></div>'
        ).appendTo("#postContainer")
        $('.e').classList.add(replyConNumber)
        if(replyArray != null){
          replyArray.forEach(reply => {
            $('<div class="replies"><h4>' + reply.reply + '</h4></div>').appendTo(".e.replyContainer" + replyNumber)
          })
        }
      });
    }
  })
  register.addEventListener('click', function () {
    container.classList.add('active');
  });
  login.addEventListener('click', function () {
    container.classList.remove('active');
  });
  $('#popUpLogin').click(function () {
    container.classList.remove('active')
    container.classList.add('activePopUp');
  })
  $('#popUpRegister').click(function (){
    container.classList.add('activePopUp')
    container.classList.add('active')
  })
  $('.closeIcon').click(function () {
      container.classList.remove('activePopUp')
  })
  $("#registerButton").click(function(){
    $.ajax({
      url: "http://hyeumine.com/forumCreateUser.php",
      method: "POST",
      data: {username: $('#firstNameInputRegister').val() + $('#lastNameInputRegister').val()},
      success: (data)=>{
        jsonData = JSON.parse(data);
        console.log(jsonData)
        if(jsonData.username !== ''){
          alert("Registered Successfully")
        }
      }
    })
    $.ajax({
      url: "http://hyeumine.com/forumNewPost.php",
      method: "POST",
      data: {id: 2623, post: "mahal kita nicole"},
      success: (data)=>{
        jsonData = JSON.parse(data);
        console.log(jsonData)
      }
    })
  })


  $("#loginButton").click(function (){
    $.ajax({
      url: "http://hyeumine.com/forumLogin.php",
      method: "POST",
      data: {username: $('#firstNameInputLogin').val() + $('#lastNameInputLogin').val()},
      success:function (data){
        jsonData = JSON.parse(data);
        console.log(jsonData)
        if(jsonData.success === true && jsonData.id !== 25) {
          localStorage.setItem('id', jsonData.id)
          localStorage.setItem('username', jsonData.username)
          window.location.href = "Forum.html";
        }else{
          alert("Incorrect User")
        }
      }
    })
  })
})
