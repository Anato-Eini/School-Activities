
document.addEventListener('DOMContentLoaded', function () {
  let container = document.querySelector('.container');
  const login = document.querySelector('.toLogin')
  const register = document.querySelector('.register')
  let jsonData;
  register.addEventListener('click', function () {
    event.preventDefault();
    container.classList.add('active');
  });
  login.addEventListener('click', function () {
    event.preventDefault();
    container.classList.remove('active');
  });
  $('#popUpLogin').click(function () {
    event.preventDefault();
    container.classList.remove('active')
  })
  $('#popUpRegister').click(function () {
    event.preventDefault();
    container.classList.add('active')
  })
   $("#registerButton").click(function () {
     event.preventDefault();
     if($('#firstNameInputRegister').val() !== "" || $('#lastNameInputRegister').val() !== "") {
       $.ajax({
         url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/register.php",
         method: "POST",
         data: {username: $('#firstNameInputRegister').val(), password: $('#lastNameInputRegister').val()},
         success: (data) => {
           jsonData = JSON.parse(data);
           alert(jsonData.message);
         }
       })
     }else{
       alert("Blank Inputs")
     }
  })
  $("#loginButton").click(function () {
    event.preventDefault();
    if($('#firstNameInputLogin').val() !== "" || $('#lastNameInputLogin').val() !== ""){
      $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/logIn.php",
        method: "GET",
        data: {username: $('#firstNameInputLogin').val(), password: $('#lastNameInputLogin').val()},
        success: function (data) {
          jsonData = JSON.parse(data);
          alert(jsonData.message);
          if(jsonData.success === true){
            localStorage.setItem('username', $('#firstNameInputLogin').val());
            localStorage.setItem('page', '1');
            if (jsonData.type === 1) {
              window.location.href = 'administrator.html';
            } else if (jsonData.type === 2) {
              window.location.href = 'organizer.html';
            } else if (jsonData.type === 3) {
              window.location.href = 'user.html';
            }
          }
        }
      })
    }else{
      alert("Blank Inputs")
    }
  })
})
