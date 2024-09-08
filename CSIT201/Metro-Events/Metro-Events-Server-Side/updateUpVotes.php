<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['eventName'], $_POST['username'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as &$event){
        if($event['eventName'] == $_POST['eventName']){
            foreach ($event['upVotes'] as $key=>&$upVote) {
                if ($upVote['username'] == $_POST['username']) {
                    unset($event['upVotes'][$key]);
                    $event['upVotes'] = array_values($event['upVotes']);
                    file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
                    echo json_encode(array('success' => true, 'message' => 'Upvote Removed'));
                    exit;
                }
            }
            $event['upVotes'][] = [
                "username" => $_POST['username']
            ];
            file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
            echo json_encode(array('success'=>true, 'message'=>'Upvote Successfully'));
            exit;
        }
    }
}else{
    echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/updateUpVotes.php
 * method: 'POST'
 * parameter: {eventName, username}
 */