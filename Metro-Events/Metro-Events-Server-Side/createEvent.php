<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['eventName'], $_POST['organizer'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as $event){
        if($event['eventName'] == $_POST['eventName']){
            echo json_encode(array('success'=>false, 'message'=>"Event Already Exists"));
            exit;
        }
    }
    $events[] = [
        'eventName'=> $_POST['eventName'],
        'organizer' => $_POST['organizer'],
        'upVotes'=> [],
        'participants'=>[],
        'reviews'=>[]
    ];
    file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
    echo json_encode(array('success'=>true, 'message'=>'Event added'));
}else{
    echo json_encode(array(
        "success"=>false,
        "message"=> "Parameters not provided"
    ));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/createEvent.php
 * method: 'POST'
 * parameters: {eventName, organizer}
 */