let page = parseInt(localStorage.getItem('page')), username = localStorage.getItem('username'),
    jsonData, container = document.querySelector('.container'), notifications, reminders = []
$('#logOut').click(function () {
    window.location.href = 'CSIT201.html';
})
$('#post').click(function (){
    container.classList.add('activePopUp')
})
$('.closeIcon').click(function () {
    event.preventDefault();
    container.classList.remove('activePopUp')
})
$('#requestOrganizer').click(function () {
    pushNotification("Destiny", "N/A", username + " is requesting to be an Organizer",
        1, username)
})
$('#requestAdministrator').click(function () {
    pushNotification("Destiny", "N/A", username + " is requesting to be an Administrator",
        5, username)
})
function updateReviewHelper(eventName, idOfTextArea) {
    updateReview(username, eventName, $('#' + idOfTextArea).val())
}
$.ajax({
    url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/getNotificationReminder.php",
    method: "GET",
    data: {username: username},
    success: data=>{
        jsonData = JSON.parse(data)
        notifications = JSON.parse(jsonData.notifications)
        notifications.forEach(notification => {
            if(parseInt(notification.notificationType) === 2) {
                $('<div class="notifications">' + notification.notificationName +
                    '<br><span><button class="acceptDecline accept" onclick="addParticipant(\'' + notification.targetUsername +
                    '\', \'' + notification.eventName + '\', \'' + notification.targetUsername +
                    " is requesting to join an event &lt" + notification.eventName + "&amp;gt" + '\', 4); removeNotification(\'' +
                    notification.targetUsername + " is requesting to join an event &lt" + notification.eventName + "&amp;gt" + '\')">' +
                    'Accept</button><button class="acceptDecline decline" onclick="pushNotification(\'' + notification.targetUsername +
                    '\', \'' + notification.eventName + '\', \''+ "Your request to join the event &lt" + notification.eventName +
                    "&amp;gt was declined" +'\', 3, \'Null\'); removeNotification(\'' + notification.targetUsername +
                    " is requesting to join an event &lt" + notification.eventName + "&amp;gt" + '\')">Decline</button></span></div>')
                    .appendTo('#containerTwo');
            }else if(parseInt(notification.notificationType) === 3 || parseInt(notification.notificationType) === 4){
                $('<div class="notifications">' + notification.notificationName + '<button class="gwenchana" ' +
                    'onclick="removeNotification(\''+ notification.notificationName +'\')">Okay</button>' +
                    '</div>').appendTo('#containerTwo');
            }else if(parseInt(notification.notificationType) === 1){
                $('<div class="notifications">'+ notification.notificationName +
                    '<br><span><button class="acceptDecline accept" onclick="pushNotification(\''+ notification.targetUsername +'\', ' +
                    '\'Null\', \'Your application to be an Organizer was accepted, please relogin to reload the data\'' +
                    ', 3, \'Null\'); removeNotification(\''+ notification.notificationName +'\'); updateUserType(\''+
                    notification.targetUsername+'\', \'Organizer\')">Accept</button><button ' +
                    'class="acceptDecline decline" onclick="pushNotification(\''+ notification.targetUsername +'\', ' +
                    '\'Null\', \'Your application to be an Organizer was declined\'' +
                    ', 3, \'Null\'); removeNotification(\''+ notification.notificationName +'\')">Decline</button></span>' +
                    '</div>').appendTo('#containerTwo');
            }else if(parseInt(notification.notificationType) === 5){
                $('<div class="notifications">'+ notification.notificationName +
                    '<br><span><button class="acceptDecline accept" onclick="pushNotification(\''+ notification.targetUsername +'\', ' +
                    '\'Null\', \'Your application to be an Administrator was accepted, please relogin to reload the data\'' +
                    ', 3, \'Null\'); removeNotification('+ notification.notificationName +'); updateUserType(\''+
                    notification.targetUsername+'\', \'Administrator\')">Accept</button><button ' +
                    'class="acceptDecline decline" onclick="pushNotification(\''+ notification.targetUsername +'\', ' +
                    '\'Null\', \'Your application to be an Administrator was declined\'' +
                    ', 3, \'Null\'); removeNotification(\''+ notification.notificationName +'\')">Decline</button></span>' +
                    '</div>').appendTo('#containerTwo');
            }
        })
        if(notifications.length !== 0){
            $('<h3 style="font-weight: bolder">Notifications</h3>').prependTo('#containerTwo');
        }
    }
})
function notifyAll(eventName, message){
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/eventCanceled.php",
        method: "POST",
        data: {eventName: eventName, message: message},
        success: (data) => {
        }
    })
}

function updateUserType(targetUsername, userType) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/updateUserType.php",
        method: "POST",
        data: {username: targetUsername, userType: userType},
        success: (data) => {
        }
    })
}

function pushNotification(username, eventName, message, notificationType, targetUsername) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/pushNotification.php",
        method: "POST",
        data: {username: username, message: message, notificationType: notificationType, targetUsername:
            targetUsername, eventName: eventName},
        success: (data) => {
            jsonData = JSON.parse(data)
            alert(jsonData.message)
        }
    })
}

function addParticipant(username, eventName, message, notificationType) {
    console.log(message)
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/addParticipant.php",
        method: "POST",
        data: {username: username, eventName: eventName},
        success: data=>{
            jsonData = JSON.parse(data)
            console.log(jsonData)
            alert(jsonData.message)
            if(jsonData.success === true){
                pushNotification(username, eventName, "Your request to join the event &lt" +
                    eventName + "&gt was accepted", notificationType, "Null")
                location.reload()
            }
        }
    })

}
/*
let lastIndexOf = message.lastIndexOf('>')
message.substring(0, lastIndexOf) + "&gt" + message.substring(lastIndexOf + 1)
 */
function removeNotification(message) {
    if(message.includes('>')){
        let lastIndexOf = message.lastIndexOf('>')
        message = message.substring(0, lastIndexOf) + "&gt" + message.substring(lastIndexOf + 1)
    }
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/deleteNotification.php",
        method: "POST",
        data: {username: username, message: message},
        success: (data)=>{
            if(JSON.parse(data).success === true) location.reload();
        }
    })
}
function deleteReview(eventName, username, review) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/deleteReview.php",
        method: "POST",
        data: {eventName: eventName, username: username, review: review},
        success: data => {
            alert(JSON.parse(data).message)
            localStorage.setItem('username', username);
            location.reload();
        }
    })
}
function upVote(eventName, username) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/updateUpVotes.php",
        method: "POST",
        data: {eventName: eventName, username: username},
        success: data => {
            if(JSON.parse(data).success === true){
                alert(JSON.parse(data).message)
                localStorage.setItem('username', username);
                location.reload();
            }else{
                alert(JSON.parse(data).message)
            }
        }
    })
}
function cancelEvent(eventName) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/cancelEvent.php",
        method: "POST",
        data: {eventName: eventName},
        success: data => {
            alert(JSON.parse(data).message)
            localStorage.setItem('username', username);
            location.reload();
        }
    })
}
function deleteParticipant(name, eventName) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/removeParticipant.php",
        method: "POST",
        data: {username: name, eventName: eventName},
        success: data => {
            alert(JSON.parse(data).message)
            localStorage.setItem('username', username);
            location.reload();
        }
    })
}
$('.btn').click(()=>{
    let eventName = $('.thePost').val()
    if(eventName === "")
        alert("Empty event name")
    else{
        $.ajax({
            url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/createEvent.php",
            method: "POST",
            data: {eventName: eventName, organizer: username},
            success:(data)=>{
                alert(JSON.parse(data).message)
                localStorage.setItem('username', username);
                location.reload();
            }
        })
    }
})
fillEventContainer()
function updateReview(username, eventName, review) {
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/updateReview.php",
        method: "POST",
        data: {username: username, eventName: eventName, review: review},
        success: data => {
            alert(JSON.parse(data).message)
            localStorage.setItem('username', username);
            location.reload();
        }
    })
}
function joinEvent(organizer, userName, notificationType, eventName) {
    let message = "";
    if(notificationType === 1) message = userName + " is requesting to be an Organizer";
    else if(notificationType === 2) message = userName + " is requesting to join an event &lt" + eventName + "&gt";
    $.ajax({
        url: "http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/pushNotification.php",
        method: "POST",
        data: {username: organizer, message: message, notificationType: notificationType, targetUsername: username, eventName: eventName},
        success: data => {
            alert(JSON.parse(data).message)
        }
    })
}