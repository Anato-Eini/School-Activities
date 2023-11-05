var page =1;
document.addEventListener('DOMContentLoaded', function () {
  let container = document.querySelector('.container');
  const login = document.querySelector('.toLogin')
  const register = document.querySelector('.register')
  let nextPage, prevPage;
  let jsonData, replyArray;
  pageReloader(1);
  $('.logo').click(function () {
    window.location.href = "CSIT201.html"
  })
  $('<h3> Page: '+(page)+' </h3>').prependTo('.buttonPages')
  $('#postContainer').on('click', '.nextPrev', function () {
    const buttonId = $(this).attr('id');
    if (buttonId === 'prev' && page > 1) {
      --page
      containerPostReset();
      pageReloader(page);
    } else if (buttonId === 'next') {
      ++page
      containerPostReset();
      pageReloader(page);
    }
  });
  function containerPostReset() {
    const containerDelete = document.querySelector('#postContainer')
    while(containerDelete.firstChild){
      containerDelete.removeChild(containerDelete.firstChild)
    }
    $('<div class="buttonPages"><h3> Page: '+(page)+' </h3><span ><span ><ion-icon name="arrow-back-circle-outline" class="nextPrev" id="prev"></ion-icon><ion-icon name="arrow-forward-circle-outline" class="nextPrev" id="next"></ion-icon></span</div>').prependTo('#postContainer')
  }


  function pageReloader(pageNumber) {
    $.ajax({
      url: "http://hyeumine.com/forumGetPosts.php",
      method: "GET",
      data: {page: pageNumber},
      success: (data) => {
        let replyNumber = 1
        jsonData = JSON.parse(data)
        jsonData.forEach(element => {
          replyArray = null;
          let replyConNumber = ("replyContainer" + (replyNumber++));
          replyArray = element.reply;
          if (replyArray === undefined) {
            $('<div class="Posts"><h3>' + element.user + '</h3>' + element.date + '<p><i>' + element.post + '</i></p></div><div class="replyContainer"><div class= ' + replyConNumber + ' ></div></div>').appendTo("#postContainer")
          } else {
            $('<div class="Posts postRep"><h3>' + element.user + '</h3>' + element.date + '<p><i>' + element.post + '</i></p></div><div class="replyContainer"><div class= ' + replyConNumber + ' ></div></div>').appendTo("#postContainer")
          }
          if (replyArray !== undefined) {
            replyArray.forEach(replies => {
              $('<div class="replies"><h4>' + replies.user + '</h4>' + replies.date + '<p><i>' + replies.reply + '</i></p></div>').prependTo('.' + replyConNumber)
            })
          }
          $('<h3>Replies</h3>').prependTo('.' + replyConNumber)
        })
      }
    })
  }

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
    container.classList.add('activePopUp');
  })
  $('#popUpRegister').click(function () {
    event.preventDefault();
    container.classList.add('activePopUp')
    container.classList.add('active')
  })
  $('.closeIcon').click(function () {
    event.preventDefault();
    container.classList.remove('activePopUp')
  })
   $("#registerButton").click(function () {
     event.preventDefault();
     $.ajax({
       url: "http://hyeumine.com/forumCreateUser.php",
       method: "POST",
      data: {username: $('#firstNameInputRegister').val() + " "+ $('#lastNameInputRegister').val()},
      success: (data) => {
        jsonData = JSON.parse(data);
        if (jsonData.username !== '') {
          alert("Registered Successfully")
        }
      }
    })
  })

  $("#loginButton").click(function () {
    event.preventDefault();
    if($('#firstNameInputLogin').val() !== "" || $('#lastNameInputLogin').val() !== ""){
      $.ajax({
        url: "http://hyeumine.com/forumLogin.php",
        method: "POST",
        data: {username: $('#firstNameInputLogin').val() + " " + $('#lastNameInputLogin').val()},
        success: function (data) {
          jsonData = JSON.parse(data);
          if (jsonData.success === true) {
            localStorage.setItem('id', jsonData.user.id + "")
            localStorage.setItem('pageNum', '1')
            window.location.href = "Forum.html";
          }else{
            alert("Incorrect User")
          }
        }
      })
    }else{
      alert("Blank Inputs")
    }
  })
})
