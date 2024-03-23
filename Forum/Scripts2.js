var contents = [];
var idReply = [];
var page = localStorage.getItem('pageNum')
$(document).ready(function () {
  let container = document.querySelector('.container');
  let jsonData, replyArray;
  const id = localStorage.getItem('id');
  $('#logOut').click(function () {
    window.location.href = 'CSIT201.html'
  })
  $('#post').click(function (){
    container.classList.add('activePopUp')
  })
  $('.closeIcon').click(function () {
    event.preventDefault();
    container.classList.remove('activePopUp')
  })
  pageReloader(page)
  $('.logo').click(function () {
    localStorage.setItem('pageNum', '1')
    window.location.href = "Forum.html"
  })
  $('<h3> Page: '+(page)+' </h3>').prependTo('.buttonPages')
  $('#submitButton').click(function () {
    event.preventDefault();
    let thePost = $('.thePost').val()
    if(thePost!== ""){
      $.ajax({
        url: "http://hyeumine.com/forumNewPost.php",
        method: "POST",
        data: {id: id, post: thePost},
        success: (data) => {
          jsonData = JSON.parse(data)
          if (jsonData.success === true) {
            location.reload()
          } else {
            alert(jsonData.message)
          }
        }
      })
    }else{
      alert("Blank Post")
    }
  })
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
    $('<div class="buttonPages" xmlns="http://www.w3.org/1999/html"><h3> Page: '+(page)+' </h3><span><ion-icon name="arrow-back-circle-outline" class="nextPrev" id="prev"></ion-icon><ion-icon name="arrow-forward-circle-outline" class="nextPrev" id="next"></ion-icon></span</div>').prependTo('#postContainer')
  }
  function pageReloader(pageNumber) {
    contents = []
    idReply = []
    $.ajax({
      url:"http://hyeumine.com/forumGetPosts.php",
      method: "GET",
      data: {page: pageNumber},
      success:(data)=>{
        let replyNumber = 1
        let replyIdNumber = 1;
        jsonData = JSON.parse(data)
        jsonData.forEach(element => {
          contents.push(element.id)
          let replyConNumber = "replyContainer" + (replyNumber);
          replyArray = element.reply;
          let replyId = "replyId" + (replyNumber)
          let buttonId = "buttonId" + (replyNumber)
          let closeIdPost = "closeIdPost" + (replyNumber++)
          if(replyArray === undefined){
            $('<div class="Posts"><span class="closeIcon" id='+ closeIdPost + '><ion-icon name="close-outline"></ion-icon></span><h3>' + element.user + '</h3>' + element.date + '<p><i>' + element.post + '</i></p></div><div class="replyContainer"><div class= ' + replyConNumber + ' ></div></div>').appendTo("#postContainer")
          }else{
            $('<div class="Posts postRep"><span class="closeIcon" id='+ closeIdPost + '><ion-icon name="close-outline"></ion-icon></span><h3>' + element.user + '</h3>' + element.date + '<p><i>' + element.post + '</i></p></div><div class="replyContainer"><div class= ' + replyConNumber + ' ></div></div>').appendTo("#postContainer")
            replyArray.forEach(replies=>{
              idReply.push(replies.id)
              $('<div class="replies"><div class="containerForDeletion"><h4>'+ replies.user +'<span id='+ ("replyIdDelete" + (replyIdNumber++))+ ' class="closeIcon"><ion-icon name="close-outline"></ion-icon></h4></span></div>' + replies.date + '<p><i>' + replies.reply + '</i></p></div>').prependTo('.' + replyConNumber)
            })
          }
          $('<h3>Replies</h3>').prependTo('.' + replyConNumber)
          $('<div class="theReplyContainer"><span><textarea class="theReply" id='+  replyId +' rows="1" required></textarea><ion-icon class="sendIcon" id='+ buttonId +' name="send-outline"></ion-icon></span></div>').appendTo('.' + replyConNumber)
        })
        $('#closeIdPost1').click(function () {
          Deleter(contents[0])
        })
        $('#closeIdPost2').click(function () {
          Deleter(contents[1])
        })
        $('#closeIdPost3').click(function () {
          Deleter(contents[2])
        })
        $('#closeIdPost4').click(function () {
          Deleter(contents[3])
        })
        $('#closeIdPost5').click(function () {
          Deleter(contents[4])
        })
        $('#closeIdPost6').click(function () {
          Deleter(contents[5])
        })
        $('#closeIdPost7').click(function () {
          Deleter(contents[6])
        })
        $('#closeIdPost8').click(function () {
          Deleter(contents[7])
        })
        $('#closeIdPost9').click(function () {
          Deleter(contents[8])
        })
        $('#closeIdPost10').click(function () {
          Deleter(contents[9])
        })
        $('#closeIdPost11').click(function () {
          Deleter(contents[10])
        })
        $('#closeIdPost12').click(function () {
          Deleter(contents[11])
        })
        $('#closeIdPost13').click(function () {
          Deleter(contents[12])
        })
        $('#closeIdPost14').click(function () {
          Deleter(contents[13])
        })
        $('#closeIdPost15').click(function () {
          Deleter(contents[14])
        })
        $('#closeIdPost16').click(function () {
          Deleter(contents[15])
        })
        $('#closeIdPost17').click(function () {
          Deleter(contents[16])
        })
        $('#closeIdPost18').click(function () {
          Deleter(contents[17])
        })
        $('#closeIdPost19').click(function () {
          Deleter(contents[18])
        })
        $('#closeIdPost20').click(function () {
          Deleter(contents[19])
        })
        //----------------------------------------------------------------------------------------------------------------
        $('#buttonId1').click(function () {
          Poster(parseInt(contents[0]),parseInt(id), $('#replyId1').val())
        })
        $('#buttonId2').click(function () {
          Poster(parseInt(contents[1]),parseInt(id), $('#replyId2').val())
        })
        $('#buttonId3').click(function () {
          Poster(parseInt(contents[2]),parseInt(id), $('#replyId3').val())
        })
        $('#buttonId4').click(function () {
          Poster(parseInt(contents[3]),parseInt(id), $('#replyId4').val())
        })
        $('#buttonId5').click(function () {
          Poster(parseInt(contents[4]),parseInt(id), $('#replyId5').val())
        })
        $('#buttonId6').click(function () {
          Poster(parseInt(contents[5]),parseInt(id), $('#replyId6').val())
        })
        $('#buttonId7').click(function () {
          Poster(parseInt(contents[6]),parseInt(id), $('#replyId7').val())
        })
        $('#buttonId8').click(function () {
          Poster(parseInt(contents[7]),parseInt(id), $('#replyId8').val())
        })
        $('#buttonId9').click(function () {
          Poster(parseInt(contents[8]),parseInt(id), $('#replyId9').val())
        })
        $('#buttonId10').click(function () {
          Poster(parseInt(contents[9]),parseInt(id), $('#replyId10').val())
        })
        $('#buttonId11').click(function () {
          Poster(parseInt(contents[10]),parseInt(id), $('#replyId11').val())
        })
        $('#buttonId12').click(function () {
          Poster(parseInt(contents[11]),parseInt(id), $('#replyId12').val())
        })
        $('#buttonId13').click(function () {
          Poster(parseInt(contents[12]),parseInt(id), $('#replyId13').val())
        })
        $('#buttonId14').click(function () {
          Poster(parseInt(contents[13]),parseInt(id), $('#replyId14').val())
        })
        $('#buttonId15').click(function () {
          Poster(parseInt(contents[14]),parseInt(id), $('#replyId15').val())
        })
        $('#buttonId16').click(function () {
          Poster(parseInt(contents[15]),parseInt(id), $('#replyId16').val())
        })
        $('#buttonId17').click(function () {
          Poster(parseInt(contents[16]),parseInt(id), $('#replyId17').val())
        })
        $('#buttonId18').click(function () {
          Poster(parseInt(contents[17]),parseInt(id), $('#replyId18').val())
        })
        $('#buttonId19').click(function () {
          Poster(parseInt(contents[18]),parseInt(id), $('#replyId19').val())
        })
        $('#buttonId20').click(function () {
          Poster(parseInt(contents[19]),parseInt(id), $('#replyId20').val())
        })
        //----------------------------------------------------------------------------------------------------------------
        $('#replyIdDelete1').click(function () {
          deleteReply(idReply[0])
        })
        $('#replyIdDelete2').click(function () {
          deleteReply(idReply[1])
        })
        $('#replyIdDelete3').click(function () {
          deleteReply(idReply[2])
        })
        $('#replyIdDelete4').click(function () {
          deleteReply(idReply[3])
        })
        $('#replyIdDelete5').click(function () {
          deleteReply(idReply[4])
        })
        $('#replyIdDelete6').click(function () {
          deleteReply(idReply[5])
        })
        $('#replyIdDelete7').click(function () {
          deleteReply(idReply[6])
        })
        $('#replyIdDelete8').click(function () {
          deleteReply(idReply[7])
        })
        $('#replyIdDelete9').click(function () {
          deleteReply(idReply[8])
        })
        $('#replyIdDelete10').click(function () {
          deleteReply(idReply[9])
        })
        $('#replyIdDelete11').click(function () {
          deleteReply(idReply[10])
        })
        $('#replyIdDelete12').click(function () {
          deleteReply(idReply[11])
        })
        $('#replyIdDelete13').click(function () {
          deleteReply(idReply[12])
        })
        $('#replyIdDelete14').click(function () {
          deleteReply(idReply[13])
        })
        $('#replyIdDelete15').click(function () {
          deleteReply(idReply[14])
        })
        $('#replyIdDelete16').click(function () {
          deleteReply(idReply[15])
        })
        $('#replyIdDelete17').click(function () {
          deleteReply(idReply[16])
        })
        $('#replyIdDelete18').click(function () {
          deleteReply(idReply[17])
        })
        $('#replyIdDelete19').click(function () {
          deleteReply(idReply[18])
        })
        $('#replyIdDelete20').click(function () {
          deleteReply(idReply[19])
        })
        $('#replyIdDelete21').click(function () {
          deleteReply(idReply[20])
        })
        $('#replyIdDelete22').click(function () {
          deleteReply(idReply[21])
        })
        $('#replyIdDelete23').click(function () {
          deleteReply(idReply[22])
        })
        $('#replyIdDelete24').click(function () {
          deleteReply(idReply[23])
        })
        $('#replyIdDelete25').click(function () {
          deleteReply(idReply[24])
        })
        $('#replyIdDelete26').click(function () {
          deleteReply(idReply[25])
        })
        $('#replyIdDelete27').click(function () {
          deleteReply(idReply[26])
        })
        $('#replyIdDelete28').click(function () {
          deleteReply(idReply[27])
        })
        $('#replyIdDelete29').click(function () {
          deleteReply(idReply[28])
        })
        $('#replyIdDelete30').click(function () {
          deleteReply(idReply[29])
        })
        $('#replyIdDelete31').click(function () {
          deleteReply(idReply[30])
        })
        $('#replyIdDelete32').click(function () {
          deleteReply(idReply[31])
        })
        $('#replyIdDelete33').click(function () {
          deleteReply(idReply[32])
        })
        $('#replyIdDelete34').click(function () {
          deleteReply(idReply[33])
        })
        $('#replyIdDelete35').click(function () {
          deleteReply(idReply[34])
        })
        $('#replyIdDelete36').click(function () {
          deleteReply(idReply[35])
        })
        $('#replyIdDelete37').click(function () {
          deleteReply(idReply[36])
        })
        $('#replyIdDelete38').click(function () {
          deleteReply(idReply[37])
        })
        $('#replyIdDelete39').click(function () {
          deleteReply(idReply[38])
        })
        $('#replyIdDelete40').click(function () {
          deleteReply(idReply[39])
        })
        $('#replyIdDelete41').click(function () {
          deleteReply(idReply[40])
        })
        $('#replyIdDelete42').click(function () {
          deleteReply(idReply[41])
        })
        $('#replyIdDelete43').click(function () {
          deleteReply(idReply[42])
        })
        $('#replyIdDelete44').click(function () {
          deleteReply(idReply[43])
        })
        $('#replyIdDelete45').click(function () {
          deleteReply(idReply[44])
        })
        $('#replyIdDelete46').click(function () {
          deleteReply(idReply[45])
        })
        $('#replyIdDelete47').click(function () {
          deleteReply(idReply[46])
        })
        $('#replyIdDelete48').click(function () {
          deleteReply(idReply[47])
        })
        $('#replyIdDelete49').click(function () {
          deleteReply(idReply[48])
        })
        $('#replyIdDelete50').click(function () {
          deleteReply(idReply[49])
        })
        $('#replyIdDelete51').click(function () {
          deleteReply(idReply[50])
        })
        $('#replyIdDelete52').click(function () {
          deleteReply(idReply[51])
        })
        $('#replyIdDelete53').click(function () {
          deleteReply(idReply[52])
        })
        $('#replyIdDelete54').click(function () {
          deleteReply(idReply[53])
        })
        $('#replyIdDelete55').click(function () {
          deleteReply(idReply[54])
        })
        $('#replyIdDelete56').click(function () {
          deleteReply(idReply[55])
        })
        $('#replyIdDelete57').click(function () {
          deleteReply(idReply[56])
        })
        $('#replyIdDelete58').click(function () {
          deleteReply(idReply[57])
        })
        $('#replyIdDelete59').click(function () {
          deleteReply(idReply[58])
        })
        $('#replyIdDelete60').click(function () {
          deleteReply(idReply[59])
        })
        $('#replyIdDelete61').click(function () {
          deleteReply(idReply[60])
        })
        $('#replyIdDelete62').click(function () {
          deleteReply(idReply[61])
        })
        $('#replyIdDelete63').click(function () {
          deleteReply(idReply[62])
        })
        $('#replyIdDelete64').click(function () {
          deleteReply(idReply[63])
        })
        $('#replyIdDelete65').click(function () {
          deleteReply(idReply[64])
        })
        $('#replyIdDelete66').click(function () {
          deleteReply(idReply[65])
        })
        $('#replyIdDelete67').click(function () {
          deleteReply(idReply[66])
        })
        $('#replyIdDelete68').click(function () {
          deleteReply(idReply[67])
        })
        $('#replyIdDelete69').click(function () {
          deleteReply(idReply[68])
        })
        $('#replyIdDelete70').click(function () {
          deleteReply(idReply[69])
        })
        $('#replyIdDelete71').click(function () {
          deleteReply(idReply[70])
        })
        $('#replyIdDelete72').click(function () {
          deleteReply(idReply[71])
        })
        $('#replyIdDelete73').click(function () {
          deleteReply(idReply[72])
        })
        $('#replyIdDelete74').click(function () {
          deleteReply(idReply[73])
        })
        $('#replyIdDelete75').click(function () {
          deleteReply(idReply[74])
        })
        $('#replyIdDelete76').click(function () {
          deleteReply(idReply[75])
        })
        $('#replyIdDelete77').click(function () {
          deleteReply(idReply[76])
        })
        $('#replyIdDelete78').click(function () {
          deleteReply(idReply[77])
        })
        $('#replyIdDelete79').click(function () {
          deleteReply(idReply[78])
        })
        $('#replyIdDelete80').click(function () {
          deleteReply(idReply[79])
        })
        $('#replyIdDelete81').click(function () {
          deleteReply(idReply[80])
        })
        $('#replyIdDelete82').click(function () {
          deleteReply(idReply[81])
        })
        $('#replyIdDelete83').click(function () {
          deleteReply(idReply[82])
        })
        $('#replyIdDelete84').click(function () {
          deleteReply(idReply[83])
        })
        $('#replyIdDelete85').click(function () {
          deleteReply(idReply[84])
        })
        $('#replyIdDelete86').click(function () {
          deleteReply(idReply[85])
        })
        $('#replyIdDelete87').click(function () {
          deleteReply(idReply[86])
        })
        $('#replyIdDelete88').click(function () {
          deleteReply(idReply[87])
        })
        $('#replyIdDelete89').click(function () {
          deleteReply(idReply[88])
        })
        $('#replyIdDelete90').click(function () {
          deleteReply(idReply[89])
        })
        $('#replyIdDelete91').click(function () {
          deleteReply(idReply[90])
        })
        $('#replyIdDelete92').click(function () {
          deleteReply(idReply[91])
        })
        $('#replyIdDelete93').click(function () {
          deleteReply(idReply[92])
        })
        $('#replyIdDelete94').click(function () {
          deleteReply(idReply[93])
        })
        $('#replyIdDelete95').click(function () {
          deleteReply(idReply[94])
        })
        $('#replyIdDelete96').click(function () {
          deleteReply(idReply[95])
        })
        $('#replyIdDelete97').click(function () {
          deleteReply(idReply[96])
        })
        $('#replyIdDelete98').click(function () {
          deleteReply(idReply[97])
        })
        $('#replyIdDelete99').click(function () {
          deleteReply(idReply[98])
        })
        $('#replyIdDelete100').click(function () {
          deleteReply(idReply[99])
        })
      }
    }
    )}
  function deleteReply(idOfReply) {
    $.ajax({
      url:"http://hyeumine.com/forumDeleteReply.php",
      method: "GET",
      data: {id: idOfReply},
      success:(data)=>{
        jsonData = JSON.parse(data)
        if(jsonData.success === true){
          localStorage.setItem('pageNum', (page + ''))
          location.reload()
        }
      }
    })
  }
  $.ajax({
    url: "http://hyeumine.com/forumDeletePost.php",
    method: "GET",
    data: {id: idOfPost},
    success: (data)=>{
      jsonData = JSON.parse(data)
      if(jsonData.success === true){
        localStorage.setItem('pageNum', (page + ""))
        location.reload()
      }
    }
  })
  function Deleter(idOfPost) {
    $.ajax({
      url: "http://hyeumine.com/forumDeletePost.php",
      method: "GET",
      data: {id: idOfPost},
      success: (data)=>{
        jsonData = JSON.parse(data)
        if(jsonData.success === true){
          localStorage.setItem('pageNum', (page + ""))
          location.reload()
        }
      }
    })
  }
  function Poster(idPost, idUser, imongReply) {
    if(imongReply !== ""){
      $.ajax({
        url: "http://hyeumine.com/forumReplyPost.php",
        method: "POST",
        data: {post_id: idPost, user_id: idUser, reply: imongReply},
        success: (data) => {
          jsonData = JSON.parse(data)
          if (jsonData.success === true) {
            localStorage.setItem('pageNum', (page + ""))
            location.reload()
          }
        }
      })
    }else{
      alert("Blank Message")
    }
  }
  document.getElementById('autoResizeTextarea').addEventListener('input', function () {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
  });
})
