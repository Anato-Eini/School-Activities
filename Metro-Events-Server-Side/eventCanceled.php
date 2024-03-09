<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['eventName'], $_POST['message'])){
    $users = json_decode(file_get_contents('user.json'), true);
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as &$event){
        if($event['eventName'] == $_POST['eventName']){
            foreach ($event['participants'] as &$participant){
                foreach ($users as &$user){
                    if($user['username'] == $participant['username']){
                        $user['notifications'][] = [
                            "targetUsername" => "Null",
                            "eventName" => $_POST['eventName'],
                            "notificationName" => "The event &lt". $_POST['eventName'] ."&gt you have joined was 
                                                    canceled due to ". $_POST['message'],
                            "notificationType"=> 3
                        ];
                        break;
                    }
                }
            }
            file_put_contents('user.json', json_encode($users, JSON_PRETTY_PRINT));
            echo json_encode(array('success' => true, 'message' => "All uses have been notified"));
            exit;
        }
    }
}else echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/eventCanceled.php
 * method: POST
 * parameters: eventName, message
 */