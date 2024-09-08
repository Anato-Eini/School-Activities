<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['eventName'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as $key => &$event) {
        if ($event['eventName'] == $_POST['eventName']) {
            unset($events[$key]);
            $events = array_values($events);
            file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
            echo json_encode(array('success'=>true, 'message'=>'Event Canceled'));
            exit;
        }
    }
    echo json_encode(array('success'=>false, 'message'=>"Event didnot exist"));
}else{
    echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/cancelEvent.php
 * method: 'POST'
 * parameters: eventName
 */