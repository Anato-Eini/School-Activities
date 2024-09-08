function fillEventContainer() {
    $.ajax({
        url: 'http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/getEvents.php',
        method: 'GET',
        data: {page: page},
        success: (data)=>{
            let eventID = 1, button = "", isFirst = true
            JSON.parse(data).forEach(elements => {
                $('<div class="event"><span class="eventPost" id="' + ("eventID" + eventID) + '">' +
                    '<h2>' + elements.eventName + '</h2><h4>Organizer: '+ elements.organizer +'</h4>' +
                    '<p>Upvotes: '+ elements.upVotes.length +'<span onclick="upVote(\''+ elements.eventName +'\', ' +
                    '\''+ username +'\')" class="upVote"><ion-icon name="thumbs-up-outline"></ion-icon></span></p>' +
                    '<button class="eventJoiner" onclick=' +
                    '"joinEvent(\''+ elements.organizer +'\', \''+ username +'\', 2, \''+ elements.eventName +'\')">Join Event' +
                    '</button></div><div class="participants" id="'+ ("participantsID" + (eventID)) + '">Participants:</div>' +
                    '<div class="reviews" id="'+ ("reviewID" + eventID++)+'"><h3>Reviews</h3></div>'+
                    '</div>').prependTo('#containerOne')
                elements.reviews.forEach(review => {
                    $('<div class="review"><h4>'+ review.username +'</h4><p>'+ review.review +'</p></div>')
                        .appendTo('#reviewID' + (eventID - 1))
                })
                elements.participants.forEach(participantsList=>{
                    $('<div class="participant">' + participantsList.username + '</div>').appendTo('#participantsID' + (eventID - 1))
                })
                $('<div class="theReviewContainer"><textarea class="theReview" id="'+ ("textArea" + (eventID - 1))
                    +'" rows="1" required></textarea><span onclick="updateReviewHelper(\''+  elements.eventName +'\', \''+
                    ("textArea" + (eventID - 1)) +'\')"><ion-icon class="sendIcon" name="send-outline"></ion-icon></span></div>')
                    .appendTo('#reviewID' + (eventID - 1))
                elements.participants.forEach(participant => {
                    if(isFirst){
                        $('<h3 style="color: white">Reminders: </h3>').appendTo('#containerThree')
                        isFirst = false
                    }
                    if(participant.username === username){
                        $('<div class="reminders"><p>You have joined &lt'+ elements.eventName +'&gt</p></div>').appendTo('#containerThree');
                    }
                })
            })
        }
    })
}
