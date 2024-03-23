<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['eventName'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as &$event){
        if($event['eventName'] == $_POST['eventName']){
            foreach($event['participants'] as $participant){
                if($participant['username'] == $_POST['username']){
                   echo json_encode(array('success'=>false, 'message'=>'Participant already registered'));
                   exit;
                }
            }
            $event['participants'][] = [
                'username'=> $_POST['username']
            ];
            file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
            echo json_encode(array('success'=>true, 'message'=>'Participant registered'));
            exit;
        }
    }
    echo json_encode(array('success'=>false, 'message'=>"Invalid Event"));
}else{
    echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/addParticipant.php
 * method: 'POST'
 * parameters: {username, eventName}
 */