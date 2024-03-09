<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['eventName'], $_POST['username'], $_POST['review'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as &$event){
        if($event['eventName'] == $_POST['eventName']){
            foreach ($event['reviews'] as $key=>&$review){
                if($review['username'] == $_POST['username']){
                    unset($event['reviews'][$key]);
                    $event['reviews'] = array_values($event['reviews']);
                    file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
                    echo json_encode(array('success'=>true, 'message'=>'Review deleted successfully'));
                    exit;
                }
            }
        }
    }
}else echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/deleteReview.php
 * method: POST
 * parameters: eventName, username, review
 */