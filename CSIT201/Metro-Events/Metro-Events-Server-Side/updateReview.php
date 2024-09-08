<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['eventName'], $_POST['review'])){
    $events = json_decode(file_get_contents('events.json'), true);
    foreach ($events as &$event){
        if($event['eventName'] == $_POST['eventName']){
            $event['reviews'][] = [
                'username'=>$_POST['username'],
                'review'=>$_POST['review']
            ];
            file_put_contents('events.json', json_encode($events, JSON_PRETTY_PRINT));
            echo json_encode(array('success'=>true, 'message'=>'Review update successfully'));
            exit;
        }
    }
    echo json_encode(array('success'=>false, 'message'=>'Event did not exist'));
}else{
    echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/updateReview.php
 * method: 'POST'
 * parameters: {username, eventName, review}
 */