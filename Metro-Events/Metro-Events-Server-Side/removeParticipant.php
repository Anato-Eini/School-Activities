<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['eventName'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as &$event){
        if($_POST['eventName'] == $event['eventName']){
            foreach ($event['participants'] as $key=>&$participant){
                if($participant['username'] == $_POST['username']){
                    unset($event['participants'][$key]);
                    $event['participants'] = array_values($event['participants']);
                    file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
                    echo json_encode(array('success'=>true, 'message'=>'Participant removed successfully'));
                    exit;
                }
            }
            echo json_encode(array('success'=>false, 'message'=>'Participant did not exist'));
        }
    }
    echo json_encode(array('success'=>false, 'message'=>'Event did not exist'));
}else{
    echo json_encode(array(
        "success"=>false,
        "message"=> "Parameters not provided"
    ));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/removeParticipant.php
 * method: 'POST'
 * parameters: {username, eventName}
 */